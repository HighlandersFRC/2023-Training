// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools.Vector;

public class SwerveModule extends SubsystemBase {
  private final TalonFX angleMotor;
  private final TalonFX driveMotor;
  private final int moduleNumber;
  private final CANcoder canCoder;

  private Peripherals peripherals;

  PositionTorqueCurrentFOC positionTorqueFOCRequest = new PositionTorqueCurrentFOC(0, 0, 0, false);
  VelocityTorqueCurrentFOC velocityTorqueFOCRequest = new VelocityTorqueCurrentFOC(0, 0, 1, false);
  /** Creates a new SwerveModule. */
  public SwerveModule(int mModuleNum, TalonFX mAngleMotor, TalonFX mDriveMotor, CANcoder mCanCoder) {
    moduleNumber = mModuleNum;
    angleMotor = mAngleMotor;
    driveMotor = mDriveMotor;
    canCoder = mCanCoder;
  }

  public double torqueAngle(){
    double length = Constants.ROBOT_LENGTH/2, width = Constants.ROBOT_WIDTH/2, angle;
    length -= Constants.SWERVE_MODULE_OFFSET;
    width -= Constants.SWERVE_MODULE_OFFSET;

    switch(moduleNumber){
      case 1:
      angle = Math.atan(-width/-length);
      break;
      case 2:
      angle =  Math.PI + Math.atan(-width/length);
      break;
      case 3:
      angle =  Math.PI + Math.atan(width/width);
      break;
      case 4:
      angle =  2 * Math.PI + Math.atan(width/-length);
      break;
      default: 
      angle = 1;
    }
    SmartDashboard.putNumber("Module" + moduleNumber, Math.toDegrees(angle));
    return angle;
  }

  public void init(){
    TalonFXConfiguration angleMotorConfig = new TalonFXConfiguration();
    TalonFXConfiguration driveMotorConfig = new TalonFXConfiguration();

    angleMotorConfig.Slot0.kP = 3.0;
    angleMotorConfig.Slot0.kI = 0.8;
    angleMotorConfig.Slot0.kD = 0.5;
    angleMotorConfig.TorqueCurrent.PeakForwardTorqueCurrent = 700;
    angleMotorConfig.TorqueCurrent.PeakReverseTorqueCurrent = -700;

    angleMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    angleMotorConfig.ClosedLoopRamps.TorqueClosedLoopRampPeriod = 0.1;

    driveMotorConfig.Slot1.kP = 3.5;
    driveMotorConfig.Slot1.kI = 0.7;
    driveMotorConfig.Slot1.kD = 0.0;
    driveMotorConfig.TorqueCurrent.PeakForwardTorqueCurrent = 700;
    driveMotorConfig.TorqueCurrent.PeakReverseTorqueCurrent = -700;

    driveMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    driveMotorConfig.ClosedLoopRamps.TorqueClosedLoopRampPeriod = 0.1;

    double absolutePosition = canCoder.getAbsolutePosition().getValue();
    angleMotor.setRotorPosition(wheelToSteerMotorRotations(absolutePosition));
    driveMotor.setRotorPosition(0.0);

    angleMotor.getConfigurator().apply(angleMotorConfig);
    driveMotor.getConfigurator().apply(driveMotorConfig);
  }

  public void setWheelPID(double angle, double velocity){
    angleMotor.setControl(positionTorqueFOCRequest.withPosition(wheelToSteerMotorRotations(degreesToRotations(Math.toDegrees(angle)))));
    driveMotor.setControl(velocityTorqueFOCRequest.withVelocity(wheelToDriveMotorRotations(velocity)));
  }

  public void setDrivePID(double velocity){
    driveMotor.setControl(velocityTorqueFOCRequest.withVelocity(wheelToDriveMotorRotations(velocity)));
  }

  public double wheelToSteerMotorRotations(double rotations){
    return (rotations * Constants.STEER_GEAR_RATIO);
  }

  public double steerMotorToWheelRotations(double rotations){
    return (rotations / Constants.STEER_GEAR_RATIO);
  }

  public double wheelToDriveMotorRotations(double rotations){
    return (rotations * Constants.GEAR_RATIO);
  }

  public double driveMotorToWheelRotations(double rotations){
    return (rotations / Constants.GEAR_RATIO);
  }

  public double degreesToRotations(double degrees){
    return degrees / 360;
  }

  public double rotationsToDegrees(double rotations){
    return rotations * 360;
  }

  public double MPSToRPS(double mps){
    return (mps * Constants.Wheel_Rotations_In_A_Meter);
  }

  public double RPSToMPS(double rps){
    return (rps / Constants.Wheel_Rotations_In_A_Meter);
  }

  public double angleToVectorI(double angle){
    return (Math.cos(angle));
  }

  public double angleToVectorJ(double angle){
    return (Math.sin(angle));
  }

  public void moveAngleMotor(){
    angleMotor.set(0.2);
  }

  public void moveDriveMotor(){
    driveMotor.set(0.2);
  }

  public double getWheelPosition(){
    double position = steerMotorToWheelRotations(angleMotor.getPosition().getValue());
    return Math.toRadians(rotationsToDegrees(position));
  }

  public double getWheelSpeed(){
    double speed = driveMotorToWheelRotations(driveMotor.getVelocity().getValue());
    return speed;
  }

  public double getAngleMotorPosition(){
    double degrees = rotationsToDegrees(wheelToSteerMotorRotations(angleMotor.getPosition().getValue()));
    return (Math.toRadians(degrees));
  }

  public double getCanCoderPosition(){
    return canCoder.getAbsolutePosition().getValue();
  }

  public double getGroundSpeed(){
    return RPSToMPS(getWheelSpeed());
  }

  public double getAngleMotorSetpoint(){
    return angleMotor.getClosedLoopReference().getValue() * 2 * Math.PI;
  }

  public double getDriveMotorSetpoint(){
    return RPSToMPS(driveMotor.getClosedLoopReference().getValue());
  }

  public double getJoystickAngle(double joystickY, double joystickX) {
    double joystickAngle = Math.atan2(-joystickX, -joystickY);
    return joystickAngle;
  }

  public double getJoystickPosition(double joystickY, double joystickX){
    double position = joystickY * joystickY + joystickX * joystickX;
    return position;
  }
  
  public void drive(Vector vector, double turnValue){
    if(Math.abs(vector.i) < 0.0001 && Math.abs(vector.j) < 0.0001) {
      driveMotor.set(0.0);
      angleMotor.set(0.0);
    }
    else {
      double angleWanted = -Math.atan2(vector.j, vector.i);
      double wheelPower = Math.sqrt(Math.pow(vector.i, 2) + Math.pow(vector.i, 2));

      angleWanted -= Math.toDegrees(peripherals.getNavxAngle());

      double xValueWithNavx = wheelPower * Math.cos(angleWanted);
      double yValueWithNavx = wheelPower * Math.sin(angleWanted);

      // turnValue *= Constants.ROBOT_RADIUS;
      // double turnX = turnValue * (angleToVectorI(torqueAngle()));
      // double turnY = turnValue * (angleToVectorJ(torqueAngle()));

      Vector finalVector = new Vector();
      finalVector.i = xValueWithNavx;
      finalVector.j = yValueWithNavx;

      double finalAngle = -Math.atan2(finalVector.j, finalVector.i);
      double finalVelocity = Math.sqrt(Math.pow(finalVector.i, 2) + Math.pow(finalVector.i, 2));

      if (finalVelocity > Constants.TOP_SPEED){
        finalVelocity = Constants.TOP_SPEED;
      }

      double velocityRPS = (MPSToRPS(finalVelocity));
      SmartDashboard.putNumber("Velocity", wheelPower);
      SmartDashboard.putNumber("Angle Wanted", Math.toDegrees(angleWanted));

      double currentAngle = getWheelPosition();
      double currentAngleBelow360 = (getWheelPosition()) % (Math.toRadians(360));

      double setpointAngle = findClosestAngle(currentAngleBelow360, finalAngle);
      double setpointAngleFlipped = findClosestAngle(currentAngleBelow360, finalAngle + Math.PI);

      if (Math.abs(setpointAngle) <= Math.abs(setpointAngleFlipped)){
        setWheelPID(currentAngle + setpointAngle, velocityRPS);
      } else {
        setWheelPID(currentAngle + setpointAngleFlipped, -velocityRPS);
      }
  }
}

  public double findClosestAngle(double angleA, double angleB){
    double direction = angleB - angleA;

    if (Math.abs(direction) > Math.PI){
      direction = -(Math.signum(direction) * (2 * Math.PI)) + direction;
    }
    return direction;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
