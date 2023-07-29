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
  
  /** Creates a new SwerveDriveSubsystem. */
  public Drive() {}

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

  public void drive(double forwardStrafe, double sidewaysStrafe, double turnAmount){
    // double position = getJoystickPosition(OI.getDriverLeftY(), OI.getDriverLeftX());
    // double speed = position * Constants.TOP_SPEED;
    // double angle = getJoystickAngle(-OI.getDriverLeftY(), OI.getDriverLeftX());
    // backRight.setWheelPID(angle, 0);
    // SmartDashboard.putNumber("Joystick Angle", Math.toDegrees(angle));

    double controllerX = -(Math.copySign(forwardStrafe * forwardStrafe, forwardStrafe));
    double controllerY = (Math.copySign(sidewaysStrafe * sidewaysStrafe, sidewaysStrafe));
    double rightStick = Math.copySign(turnAmount * turnAmount, turnAmount);

    double finalX = controllerX * Constants.TOP_SPEED;
    double finalY = controllerY * Constants.TOP_SPEED;

    Vector controllerVector = new Vector();
    controllerVector.i = finalX;
    controllerVector.j = finalY;

    backRight.drive(controllerVector, rightStick);
    backLeft.drive(controllerVector, rightStick);
    frontLeft.drive(controllerVector, rightStick);
    frontRight.drive(controllerVector, rightStick);
  }
  
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Front Right Position", Math.toDegrees(frontRight.getWheelPosition()));
    SmartDashboard.putNumber("Back Right Position", Math.toDegrees(backRight.getWheelPosition()));
    SmartDashboard.putNumber("Front Left Position", Math.toDegrees(frontLeft.getWheelPosition()));
    SmartDashboard.putNumber("Back Left Position", Math.toDegrees(backLeft.getWheelPosition()));
    SmartDashboard.putNumber("Back Left Cancoder position", Constants.rotationsToDegrees(backRightCanCoder.getAbsolutePosition().getValue()));
    // This method will be called once per scheduler run
  }
}
