// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
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
  
  /** Creates a new SwerveDriveSubsystem. */
  public Drive(Peripherals peripherals) {
    this.peripherals = peripherals;
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

    setDefaultCommand(new DriveDefault(this));
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

  public void zeroNavx(){
    peripherals.zeroNavx();
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
