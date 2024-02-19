package frc.robot.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.JSONArray;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.tools.controlloops.PID;
import frc.robot.tools.math.Vector;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Peripherals;

public class NotePickupAutonomousFollower extends CommandBase {
  private Drive drive;
  private Peripherals peripherals;

  private JSONArray path;
  private JSONArray commands;

  private double initTime;
  private double currentTime;
  private double previousTime;

  private double odometryFusedX = 0;
  private double odometryFusedY = 0;
  private double odometryFusedTheta = 0;

  private double[] desiredVelocityArray = new double[3];
  private double desiredThetaChange = 0;

  private boolean record;
  private String fieldSide;

  private ArrayList<double[]> recordedOdometry = new ArrayList<double[]>();
  private double pathStartTime;
  private double pathEndTime;

  private PID pid;
  private double kP = 10;
  private double kI = 0;
  private double kD = 4;
  private PID vectorPid;
  private double vectorKP = 17;
  private double vectorKI = 0;
  private double vectorKD = 2.5;

  private boolean pickupNote;

  public NotePickupAutonomousFollower(Drive drive, Peripherals peripherals, JSONArray pathPoints, double pathStartTime, double pathEndTime, boolean record, boolean pickupNote) {
    this.drive = drive;
    this.peripherals = peripherals;
    this.path = pathPoints;
    this.record = record;
    this.pathStartTime = pathStartTime;
    this.pathEndTime = pathEndTime;
    this.pickupNote = pickupNote;
    addRequirements(drive, peripherals);
  }

  public NotePickupAutonomousFollower(Drive drive, Peripherals peripherals, JSONArray pathPoints, double pathStartTime, boolean record, boolean pickupNote) {
    this.drive = drive;
    this.peripherals = peripherals;
    this.path = pathPoints;
    this.record = record;
    this.pathStartTime = pathStartTime;
    this.pathEndTime = path.getJSONArray(path.length() - 1).getDouble(0);
    this.pickupNote = pickupNote;
    addRequirements(drive, peripherals);
  }

  @Override
  public void initialize() {
    initTime = Timer.getFPGATimestamp();
    pid = new PID(kP, kI, kD);
    pid.setSetPoint(0);
    pid.setMinOutput(-7);
    pid.setMaxOutput(7);
    vectorPid = new PID(vectorKP, vectorKI, vectorKD);
    vectorPid.setSetPoint(0);
    vectorPid.setMinOutput(-1);
    vectorPid.setMaxOutput(1);
  }

  @Override
  public void execute() {
    // System.out.println("auto runs");
    drive.updateOdometryFusedArray();
    odometryFusedX = drive.getFusedOdometryX();
    odometryFusedY = drive.getFusedOdometryY();
    odometryFusedTheta = drive.getFusedOdometryTheta();
    // System.out.println("Follower field side: " + this.drive.getFieldSide());

    // System.out.println("Odom - X: " + odometryFusedX + " Y: " + odometryFusedY + " Theta: " + odometryFusedTheta);

    currentTime = Timer.getFPGATimestamp() - initTime + pathStartTime;
    
    // call PIDController function
    desiredVelocityArray = drive.pidController(odometryFusedX, odometryFusedY, odometryFusedTheta, currentTime, path);
    
    double angleToPiece = peripherals.getBackCamTargetTx();
    System.out.println("Angle to note: " + angleToPiece);
    double vectorToNoteI = -Constants.angleToUnitVectorI(angleToPiece);
    double vectorToNoteJ = -Constants.angleToUnitVectorJ(angleToPiece);

    pid.updatePID(angleToPiece);
    double result = pid.getResult();
    vectorPid.updatePID(angleToPiece);
    double vectorPIDResult = vectorPid.getResult();
    System.out.println("Result: " + result);
    System.out.println("VectorResult: " + vectorPIDResult);

    double unitVectorToNoteI;
    double unitVectorToNoteJ;
    // create velocity vector and set desired theta change
    Vector velocityVector = new Vector();

    if (pickupNote){
      if (angleToPiece < 0){
        unitVectorToNoteI = 0;
        unitVectorToNoteJ = -1;
      } else {
        unitVectorToNoteI = 0;
        unitVectorToNoteJ = 1;
      }
      double adjustedUnitVectorToNoteI = unitVectorToNoteI * result;
      double adjustedUnitVectorToNoteJ = unitVectorToNoteJ * result;
      double adjustedVectorToNoteI = unitVectorToNoteI * vectorPIDResult;
      double adjustedVectorToNoteJ = unitVectorToNoteJ * vectorPIDResult;
      double finalVectorI = desiredVelocityArray[0] + adjustedUnitVectorToNoteI;
      double finalVectorJ = desiredVelocityArray[1] + adjustedUnitVectorToNoteJ;
      double vectorAngle = Math.atan2(finalVectorJ, finalVectorI);
      velocityVector.setI(desiredVelocityArray[0] + adjustedVectorToNoteI);
      velocityVector.setJ(desiredVelocityArray[1] + adjustedVectorToNoteJ);
      System.out.println("I: " + adjustedVectorToNoteI);
      System.out.println("J: " + adjustedVectorToNoteJ);
      System.out.println("Path I: " + desiredVelocityArray[0]);
      System.out.println("Path J: " + desiredVelocityArray[1]);
      System.out.println("Angle: " + vectorAngle);
      System.out.println("Path Theta: " + desiredVelocityArray[2]);
      desiredThetaChange = desiredVelocityArray[2] - result;
    } else {
      velocityVector.setI(desiredVelocityArray[0]);
      velocityVector.setJ(desiredVelocityArray[1]);
      desiredThetaChange = desiredVelocityArray[2];
    }
    
    // velocityVector.setI(0);
    // velocityVector.setJ(0);
    // desiredThetaChange = 0;

    drive.autoDrive(velocityVector, desiredThetaChange);

    previousTime = currentTime;

    if (this.record){
      recordedOdometry.add(new double[] {currentTime, odometryFusedX, odometryFusedY, odometryFusedTheta});
    }
  }

  @Override
  public void end(boolean interrupted) {
    Vector velocityVector = new Vector();
    velocityVector.setI(0);
    velocityVector.setJ(0);
    double desiredThetaChange = 0.0;
    drive.autoDrive(velocityVector, desiredThetaChange);

    odometryFusedX = drive.getFusedOdometryX();
    odometryFusedY = drive.getFusedOdometryY();
    odometryFusedTheta = drive.getFusedOdometryTheta();
    currentTime = Timer.getFPGATimestamp() - initTime;

    if (this.record){
      recordedOdometry.add(new double[] {currentTime, odometryFusedX, odometryFusedY, odometryFusedTheta});

      try {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String filename = "/home/lvuser/deploy/recordings/" + dtf.format(now) + ".csv";
        File file = new File(filename);
        if (!file.exists()){
          file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i <recordedOdometry.size(); i ++){
          String line = "";
          for (double val : recordedOdometry.get(i)){
            line += val + ",";
          }
          line = line.substring(0, line.length() - 1);
          line += "\n";
          bw.write(line);
        }
        
        bw.close();
      } catch (Exception e) {
        System.out.println(e);
        System.out.println("CSV file error");
      }
    }
  }

  @Override
  public boolean isFinished() {
    if(currentTime > this.pathEndTime) {
      return true;
    }
    else {
      return false;
    }
  }
}
