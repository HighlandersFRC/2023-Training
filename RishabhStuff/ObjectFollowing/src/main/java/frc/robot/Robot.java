// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveAutoAligned;
import frc.robot.commands.MoveToPiece;
import frc.robot.commands.ZeroAngleMidMatch;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Peripherals;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  //Subsystems
  private Lights lights = new Lights();
  private Peripherals peripherals = new Peripherals();
  private Drive drive = new Drive(peripherals);
  
  //Sensors

  // private Logger logger = Logger.getInstance();
  
  File fourPiecePart1PathingFile;
  String fourPiecePart1PathString;
  JSONObject fourPiecePart1PathRead;
  JSONArray fourPiecePart1PathJSON;

  File fourPiece1FarPart1PathingFile;
  String fourPiece1FarPart1PathString;
  JSONObject fourPiece1FarPart1PathRead;
  JSONArray fourPiece1FarPart1PathJSON;

  File threePieceBottomPart1PathingFile;
  String threePieceBottomPart1PathString;
  JSONObject threePieceBottomPart1PathRead;
  JSONArray threePieceBottomPart1PathJSON;

  File fourPiece2FarPart1PathingFile;
  String fourPiece2FarPart1PathString;
  JSONObject fourPiece2FarPart1PathRead;
  JSONArray fourPiece2FarPart1PathJSON;

  String fieldSide = "blue";

  Command auto;

  @Override
  public void robotInit() {
    // Logger.recordMetadata("ProjectName", "MyProject"); // Set a metadata value

    // if (isReal()) {
    //   Logger.addDataReceiver(new WPILOGWriter()); // Log to a USB stick ("/U/logs")
    //   Logger.addDataReceiver(new NT4Publisher()); // Publish data to NetworkTables
    //   new PowerDistribution(1, ModuleType.kRev); // Enables power distribution logging
    // } else {
    //   setUseTiming(false); // Run as fast as possible
    //   String logPath = LogFileUtil.findReplayLog(); // Pull the replay log from AdvantageScope (or prompt the user)
    //   Logger.setReplaySource(new WPILOGReader(logPath)); // Read replay log
    //   Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim"))); // Save outputs to a new log
    // }

    // // Logger.disableDeterministicTimestamps() // See "Deterministic Timestamps" in the "Understanding Data Flow" page
    // Logger.start(); // Start logging! No more data receivers, replay sources, or metadata values may be added.

    lights.init();
    peripherals.init();
    drive.init();

    PortForwarder.add(5800, "limelight.local", 5800);
    PortForwarder.add(5801, "limelight.local", 5801);
    
    PortForwarder.add(5800, "limelight-front.local", 5800);
    PortForwarder.add(5801, "limelight-front.local", 5801);
    
    PortForwarder.add(5800, "limelight-back.local", 5800);
    PortForwarder.add(5801, "limelight-back.local", 5801);

    PortForwarder.add(5800, "limelight-left.local", 5800);
    PortForwarder.add(5801, "limelight-left.local", 5801);

    PortForwarder.add(5800, "limelight-right.local", 5800);
    PortForwarder.add(5801, "limelight-right.local", 5801);

    PortForwarder.add(5800, "10.44.99.41", 5800);
    PortForwarder.add(5801, "10.44.99.41", 5801);

    PortForwarder.add(5800, "10.44.99.42", 5800);
    PortForwarder.add(5801, "10.44.99.42", 5801);

    PortForwarder.add(5800, "10.44.99.43", 5800);
    PortForwarder.add(5801, "10.44.99.43", 5801);

    PortForwarder.add(5800, "10.44.99.44", 5800);
    PortForwarder.add(5801, "10.44.99.44", 5801);

    //Creating jsons for all autos
    try {
      fourPiecePart1PathingFile = new File("/home/lvuser/deploy/2PieceCenterPart1.json");
      FileReader fourPiecePart1Scanner = new FileReader(fourPiecePart1PathingFile);
      fourPiecePart1PathRead = new JSONObject(new JSONTokener(fourPiecePart1Scanner));
      fourPiecePart1PathJSON = (JSONArray) fourPiecePart1PathRead.get("sampled_points");
    } catch (Exception e) {
      System.out.println("ERROR WITH PATH FILE " + e);
    }
    try {
      fourPiece1FarPart1PathingFile = new File("/home/lvuser/deploy/4Piece1FarPart1.json");
      FileReader fourPiece1FarPart1Scanner = new FileReader(fourPiece1FarPart1PathingFile);
      fourPiece1FarPart1PathRead = new JSONObject(new JSONTokener(fourPiece1FarPart1Scanner));
      fourPiece1FarPart1PathJSON = (JSONArray) fourPiece1FarPart1PathRead.get("sampled_points");
    } catch (Exception e) {
      System.out.println("ERROR WITH PATH FILE " + e);
    }
    try {
      threePieceBottomPart1PathingFile = new File("/home/lvuser/deploy/3PieceBottomPart1.json");
      FileReader threePieceBottomPart1Scanner = new FileReader(threePieceBottomPart1PathingFile);
      threePieceBottomPart1PathRead = new JSONObject(new JSONTokener(threePieceBottomPart1Scanner));
      threePieceBottomPart1PathJSON = (JSONArray) threePieceBottomPart1PathRead.get("sampled_points");
    } catch (Exception e) {
      System.out.println("ERROR WITH PATH FILE " + e);
    }
    try {
      fourPiece2FarPart1PathingFile = new File("/home/lvuser/deploy/4Piece2FarPart1.json");
      FileReader fourPiece2FarPart1Scanner = new FileReader(fourPiece2FarPart1PathingFile);
      fourPiece2FarPart1PathRead = new JSONObject(new JSONTokener(fourPiece2FarPart1Scanner));
      fourPiece2FarPart1PathJSON = (JSONArray) fourPiece2FarPart1PathRead.get("sampled_points");
    } catch (Exception e) {
      System.out.println("ERROR WITH PATH FILE " + e);
    }

    // this.auto = new AutoParser(drive, intake, feeder, shooter, peripherals, "CommandTest");
    // this.auto.schedule();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    // Logger.recordOutput("Odometry", drive.getOdometry());

    lights.periodic();

    drive.periodic(); // remove for competition
    peripherals.periodic();
  }

  @Override
  public void disabledInit() {
    OI.driverController.setRumble(RumbleType.kBothRumble, 0);
    OI.operatorController.setRumble(RumbleType.kBothRumble, 0);
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {

    // if (OI.isBlueSide()) {
    //   System.out.println("ON BLUE SIDE");
    //   fieldSide = "blue";
    //   drive.setFieldSide(fieldSide);
    //   lights.setFieldSide(fieldSide);
    // } else {
    //   System.out.println("ON RED SIDE");
    //   fieldSide = "red";
    //   drive.setFieldSide(fieldSide);
    //   lights.setFieldSide(fieldSide);
    // }

    // //Auto selection
    // if (OI.is4PieceCloseAuto()) {
    //   this.drive.autoInit(this.fourPiecePart1PathJSON);
    //   this.auto = new FourPieceCloseAuto(drive, peripherals);
    //   auto.schedule();
    // } else if (OI.is5PieceAuto()) {
    //   this.drive.autoInit(this.fourPiecePart1PathJSON);
    //   this.auto = new FivePieceAuto(drive, peripherals, intake, feeder, shooter, lights, tof);
    //   auto.schedule();
    // } else if (OI.is4Piece1FarAuto()){
    //   this.drive.autoInit(this.fourPiece1FarPart1PathJSON);
    //   this.auto = new FourPieceOneFarAuto(drive, peripherals);
    //   auto.schedule();
    // } else if (OI.is3PieceBottomAuto()) {
    //   this.drive.autoInit(this.threePieceBottomPart1PathJSON);
    //   this.auto = new ThreePieceBottomAuto();
    //   auto.schedule();
    // } else if (OI.is4Piece2FarAuto()){
    //   this.drive.autoInit(this.fourPiece2FarPart1PathJSON);
    //   this.auto = new FourPieceTwoFarAuto();
    //   auto.schedule();
    // } else {
    //   System.out.println("NO AUTO SELECTED");
    // }

    //THIS MUST BE LAST!!!
    try {
      this.auto.schedule();
    } catch (Exception e){
      System.out.println("No auto is selected");
    }
    //THIS MUST BE LAST!!!
  }

  @Override
  public void autonomousPeriodic() {}

  @Override 
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    //CONTROLS

    //Driver
    OI.driverX.whileTrue(new DriveAutoAligned(drive, peripherals));
    OI.driverViewButton.whileTrue(new ZeroAngleMidMatch(drive));
    // OI.driverRT.whileTrue(new SmartIntake(intake, feeder, lights, tof, Constants.SetPoints.IntakePosition.kDOWN, 1200,  600));
    // OI.driverLT.whileTrue(new RunIntakeAndFeeder(intake, feeder, Constants.SetPoints.IntakePosition.kUP, -800, -800));
    // // OI.driverA.whileTrue(new SmartShoot(shooter, feeder, peripherals, lights, tof, 50, 4000, 600));
    // OI.driverB.whileTrue(new SmartShoot(shooter, feeder, peripherals, lights, tof, 30, 5500, 600));
    // // OI.driverX.whileTrue(new RunClimber(climber, 0.6, 0.6));
    // // OI.driverY.whileTrue(new RunClimber(climber, 0.0, 0.6));
    // OI.driverRB.whileTrue(new RunClimber(climber, 0.0, 0.6));
    // OI.driverLB.onTrue(new ToggleBrake(climber));
    // OI.driverY.whileTrue(new AutoShoot(drive, shooter, feeder, peripherals, lights, tof, 600));
    // OI.driverA.whileTrue(new RunFeeder(feeder, 600));
    OI.driverA.whileTrue(new MoveToPiece(drive, peripherals));
    //Operator
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
