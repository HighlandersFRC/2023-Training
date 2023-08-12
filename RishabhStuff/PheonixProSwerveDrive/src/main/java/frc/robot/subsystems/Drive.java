// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools.PID;
import frc.robot.Tools.Vector;
import frc.robot.commands.DriveDefault;

public class Drive extends SubsystemBase {
  private final TalonFX frontRightDriveMotor = new TalonFX(1, "Canivore");
  private final TalonFX frontRightAngleMotor = new TalonFX(2, "Canivore");
  private final TalonFX frontLeftDriveMotor = new TalonFX(3, "Canivore");
  private final TalonFX frontLeftAngleMotor = new TalonFX(4, "Canivore");
  private final TalonFX backLeftDriveMotor = new TalonFX(5, "Canivore");
  private final TalonFX backLeftAngleMotor = new TalonFX(6, "Canivore");
  private final TalonFX backRightDriveMotor = new TalonFX(7, "Canivore");
  private final TalonFX backRightAngleMotor = new TalonFX(8, "Canivore");

  private final CANcoder frontRightCanCoder = new CANcoder(1, "Canivore");
  private final CANcoder frontLeftCanCoder = new CANcoder(2, "Canivore");
  private final CANcoder backLeftCanCoder = new CANcoder(3, "Canivore");
  private final CANcoder backRightCanCoder = new CANcoder(4, "Canivore");

  private final SwerveModule frontRight = new SwerveModule(1, frontRightAngleMotor, frontRightDriveMotor, frontRightCanCoder);
  private final SwerveModule frontLeft = new SwerveModule(2, frontLeftAngleMotor, frontLeftDriveMotor, frontLeftCanCoder);
  private final SwerveModule backLeft = new SwerveModule(3, backLeftAngleMotor, backLeftDriveMotor, backLeftCanCoder);
  private final SwerveModule backRight = new SwerveModule(4, backRightAngleMotor, backRightDriveMotor, backRightCanCoder);

  Peripherals peripherals;

  private final double moduleX = ((Constants.ROBOT_LENGTH)/2) - Constants.SWERVE_MODULE_OFFSET;
  private final double moduleY = ((Constants.ROBOT_WIDTH)/2) - Constants.SWERVE_MODULE_OFFSET;

  Translation2d m_frontLeftLocation = new Translation2d(moduleX, moduleY);
  Translation2d m_frontRightLocation = new Translation2d(moduleX, -moduleY);
  Translation2d m_backLeftLocation = new Translation2d(-moduleX, moduleY);
  Translation2d m_backRightLocation = new Translation2d(-moduleX, -moduleY);

  private double currentX = 0;
  private double currentY = 0;
  private double currentTheta = 0;
  private double estimatedX = 0.0;
  private double estimatedY = 0.0;
  private double estimatedTheta = 0.0;

  private double previousEstimateX = 0.0;
  private double previousEstimateY = 0.0;
  private double previousEstimateTheta = 0.0;

  private double averagedX = 0.0;
  private double averagedY = 0.0;
  private double averagedTheta = 0.0;

  private double initTime;
  private double currentTime;
  private double previousTime;
  private double timeDiff;

  private double previousX = 0;
  private double previousY = 0;
  private double previousTheta = 0;


  private double[] currentFusedOdometry = new double[3];

  SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
  m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation
  );

  SwerveDrivePoseEstimator m_odometry; 
  Pose2d m_pose;

  double initAngle;
  double setAngle;
  double diffAngle;

  private double xP = 4;
  private double xI = 0;
  private double xD = 1.2;

  private double yP = 4;
  private double yI = 0;
  private double yD = 1.2;

  private double thetaP = 3.1;
  private double thetaI = 0;
  private double thetaD = 0.8;

  private PID xPID = new PID(xP, xI, xD);
  private PID yPID = new PID(yP, yI, yD);
  private PID thetaPID = new PID(thetaP, thetaI, thetaD);
  
  /** Creates a new SwerveDriveSubsystem. */
  public Drive(Peripherals peripherals) {
    this.peripherals = peripherals;

    SwerveModulePosition[] swerveModulePositions = new SwerveModulePosition[4];
    swerveModulePositions[0] = new SwerveModulePosition(0, new Rotation2d(frontRight.getCanCoderPositionRadians()));
    swerveModulePositions[1] = new SwerveModulePosition(0, new Rotation2d(frontLeft.getCanCoderPositionRadians()));
    swerveModulePositions[2] = new SwerveModulePosition(0, new Rotation2d(backLeft.getCanCoderPositionRadians()));
    swerveModulePositions[3] = new SwerveModulePosition(0, new Rotation2d(backRight.getCanCoderPositionRadians()));

    Pose2d m_pose = new Pose2d();

    m_odometry = new SwerveDrivePoseEstimator(m_kinematics, new Rotation2d(peripherals.getNavxAngle()), swerveModulePositions, m_pose);
  }

  public void zeroNavx(){
    peripherals.zeroNavx();
    SwerveModulePosition[] swerveModulePositions = new SwerveModulePosition[4];
    swerveModulePositions[0] = new SwerveModulePosition(frontRight.getModuleDistance(), new Rotation2d(frontRight.getCanCoderPositionRadians()));
    swerveModulePositions[1] = new SwerveModulePosition(frontLeft.getModuleDistance(), new Rotation2d(frontLeft.getCanCoderPositionRadians()));
    swerveModulePositions[2] = new SwerveModulePosition(backLeft.getModuleDistance(), new Rotation2d(backLeft.getCanCoderPositionRadians()));
    swerveModulePositions[3] = new SwerveModulePosition(backRight.getModuleDistance(), new Rotation2d(backRight.getCanCoderPositionRadians()));
    m_odometry.resetPosition(new Rotation2d(peripherals.getNavxAngle()), swerveModulePositions, new Pose2d(new Translation2d(getFusedOdometryX(), getFusedOdometryY()), new Rotation2d(peripherals.getNavxAngle())));
  }

  public void init(){
    frontRight.init();
    frontLeft.init();
    backRight.init();
    backLeft.init();

    frontRightAngleMotor.setInverted(true);
    frontLeftAngleMotor.setInverted(true);
    backRightAngleMotor.setInverted(true);
    backLeftAngleMotor.setInverted(true);

    frontRightDriveMotor.setInverted(true);
    frontLeftDriveMotor.setInverted(true);
    backRightDriveMotor.setInverted(true);
    backLeftDriveMotor.setInverted(true);

    xPID.setMinOutput(-4.9);
    xPID.setMaxOutput(4.9);

    yPID.setMinOutput(-4.9);
    yPID.setMaxOutput(4.9);

    thetaPID.setMinOutput(-(Constants.TOP_SPEED)/(Constants.ROBOT_RADIUS));
    thetaPID.setMaxOutput((Constants.TOP_SPEED)/(Constants.ROBOT_RADIUS));

    setDefaultCommand(new DriveDefault(this));
  }

  public void autoInit(){

  }

  public void updateOdometry(){
    double navxOffset = Math.toRadians(peripherals.getNavxAngle());

    SwerveModulePosition[] swerveModulePositions = new SwerveModulePosition[4];
    swerveModulePositions[0] = new SwerveModulePosition(frontRight.getModuleDistance(), new Rotation2d(frontRight.getCanCoderPositionRadians()));
    swerveModulePositions[1] = new SwerveModulePosition(frontLeft.getModuleDistance(), new Rotation2d(frontLeft.getCanCoderPositionRadians()));
    swerveModulePositions[2] = new SwerveModulePosition(backLeft.getModuleDistance(), new Rotation2d(backLeft.getCanCoderPositionRadians()));
    swerveModulePositions[3] = new SwerveModulePosition(backRight.getModuleDistance(), new Rotation2d(backRight.getCanCoderPositionRadians()));
        
    m_pose = m_odometry.update(new Rotation2d((navxOffset)), swerveModulePositions);

    currentX = getOdometryX();
    currentY = getOdometryY();
    currentTheta = navxOffset;

    averagedX = (currentX + averagedX)/2;
    averagedY = (currentY + averagedY)/2;
    averagedTheta = (currentTheta + averagedTheta)/2;

    previousX = averagedX;
    previousY = averagedY;
    previousTheta = averagedTheta;
    previousTime = currentTime;
    previousEstimateX = estimatedX;
    previousEstimateY = estimatedY;
    previousEstimateTheta = estimatedTheta;

    currentFusedOdometry[0] = averagedX;
    currentFusedOdometry[1] = averagedY;
    currentFusedOdometry[2] = currentTheta;
  }

  public void moveWheel(double value){
    backRight.setDrivePID(value);
    frontRight.setDrivePID(value);
    backLeft.setDrivePID(value);
    frontLeft.setDrivePID(value);    
  }

  public double[] getModuleStates(){
    double[] states = {
      frontRight.getCanCoderPosition() * 360, frontRight.getGroundSpeed(),
      frontLeft.getCanCoderPosition() * 360, frontLeft.getGroundSpeed(),
      backLeft.getCanCoderPosition() * 360, frontRight.getGroundSpeed(),
      backRight.getCanCoderPosition() * 360, frontRight.getGroundSpeed(),
    };
    return states;
  }

  public double[] getModuleSetpoints(){
    double[] setpoints = {
      frontRight.getAngleMotorSetpoint(), frontRight.getDriveMotorSetpoint(),
      frontLeft.getAngleMotorSetpoint(), frontLeft.getDriveMotorSetpoint(),
      backLeft.getAngleMotorSetpoint(), backLeft.getDriveMotorSetpoint(),
      backRight.getAngleMotorSetpoint(), backRight.getDriveMotorSetpoint(),
    };
    return setpoints;
  }

  public double getFusedOdometryX() {
    return currentFusedOdometry[0];
  }

  public double getFusedOdometryY() {
    return currentFusedOdometry[1];
  }

  public double getOdometryX() {
    return m_odometry.getEstimatedPosition().getX();
  }

  public double getOdometryY() {
    return m_odometry.getEstimatedPosition().getY();
  }

  public double getJoystickAngle(double joystickY, double joystickX) {
    double joystickAngle = Math.atan2(-joystickX, -joystickY);
    return joystickAngle;
  }

  public double getJoystickPosition(double joystickY, double joystickX){
    double position = joystickY * joystickY + joystickX * joystickX;
    return position;
  }

  public void moveDriveMotor(double speed){
    backRight.moveDriveMotor(speed);
  }

  public void setTurnAngle(){
    frontLeft.testTurnAngle();
    frontRight.testTurnAngle();
    backLeft.testTurnAngle();
    backRight.testTurnAngle();
  }

  public double adjustX(double originalX, double originalY){
    double adjustedX = originalX * Math.sqrt((1-(Math.pow(originalY, 2))/2));
    return adjustedX;
  }

  public double adjustY(double originalX, double originalY){
    double adjustedY = originalY * Math.sqrt((1-(Math.pow(originalX, 2))/2));
    return adjustedY;
  }

  public void drive(double forwardStrafe, double sidewaysStrafe, double turnAmount){
    double controllerX = -Math.copySign(forwardStrafe * forwardStrafe, forwardStrafe);
    double controllerY = Math.copySign(sidewaysStrafe * sidewaysStrafe, sidewaysStrafe);
    double rightStick = Math.copySign(turnAmount * turnAmount, turnAmount);

    double adjustedX = adjustX(controllerX, controllerY);
    double adjustedY = adjustY(controllerX, controllerY);

    double finalX = adjustedX * Constants.TOP_SPEED;
    double finalY = adjustedY * Constants.TOP_SPEED;
    rightStick *= Constants.TOP_SPEED;

    Vector controllerVector = new Vector();
    controllerVector.i = finalX;
    controllerVector.j = finalY;

    double navxAngle = Math.toRadians(peripherals.getNavxAngle());

    backRight.drive(controllerVector, rightStick, navxAngle);
    backLeft.drive(controllerVector, rightStick, navxAngle);
    frontLeft.drive(controllerVector, rightStick, navxAngle);
    frontRight.drive(controllerVector, rightStick, navxAngle);
  }
  
  @Override
  public void periodic() {
    SmartDashboard.putNumber("1 Position", Math.toDegrees(frontRight.getWheelPosition()));
    SmartDashboard.putNumber("2 Position", Math.toDegrees(frontLeft.getWheelPosition()));
    SmartDashboard.putNumber("3 Position", Math.toDegrees(backLeft.getWheelPosition()));
    SmartDashboard.putNumber("4 Position", Math.toDegrees(backRight.getWheelPosition()));
    // This method will be called once per scheduler run
  }
}
