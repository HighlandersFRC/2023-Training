// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.controls.TorqueCurrentFOC;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools.Vector;

public class SwerveModule extends SubsystemBase {
  private final TalonFX angleMotor;
  private final TalonFX driveMotor;
  private final int moduleNumber = 0;
  private final CANcoder canCoder;

  PositionTorqueCurrentFOC positionTorqueFOCRequest = new PositionTorqueCurrentFOC(0, 0, 0, false);
  VelocityTorqueCurrentFOC velocityTorqueFOCRequest = new VelocityTorqueCurrentFOC(0, 0, 1, false);
  /** Creates a new SwerveModule. */
  public SwerveModule(int moduleNum, TalonFX mAngleMotor, TalonFX mDriveMotor, CANcoder mCanCoder) {
    moduleNum = moduleNumber;
    angleMotor = mAngleMotor;
    driveMotor = mDriveMotor;
    canCoder = mCanCoder;
  }

  public void init(){
    TalonFXConfiguration angleMotorConfig = new TalonFXConfiguration();
    TalonFXConfiguration driveMotorConfig = new TalonFXConfiguration();

    angleMotorConfig.Slot0.kP = 0.8;
    angleMotorConfig.Slot0.kI = 0.0;
    angleMotorConfig.Slot0.kD = 0.001;
    angleMotorConfig.TorqueCurrent.PeakForwardTorqueCurrent = 700;
    angleMotorConfig.TorqueCurrent.PeakReverseTorqueCurrent = -700;

    driveMotorConfig.Slot1.kP = 0.5;
    driveMotorConfig.Slot1.kI = 0.0;
    driveMotorConfig.Slot1.kD = 0.0;
    driveMotorConfig.TorqueCurrent.PeakForwardTorqueCurrent = 700;
    driveMotorConfig.TorqueCurrent.PeakReverseTorqueCurrent = -700;

    double absolutePosition = canCoder.getAbsolutePosition().getValue();
    angleMotor.setRotorPosition(absolutePosition);
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
    double speed = driveMotorToWheelRotations(driveMotor.getPosition().getValue());
    return speed;
  }

  public double getAngleMotorPosition(){
    double degrees = rotationsToDegrees(wheelToSteerMotorRotations(angleMotor.getPosition().getValue()));
    return (Math.toRadians(degrees));
  }

  public double getJoystickAngle(double joystickY, double joystickX) {
    double joystickAngle = Math.atan2(-joystickX, -joystickY);
    return joystickAngle;
  }

  public double getJoystickPosition(double joystickY, double joystickX){
    double position = joystickY * joystickY + joystickX * joystickX;
    return position;
  }
  
  public void drive(Vector vector){
    if(Math.abs(vector.getI()) < 0.0001 && Math.abs(vector.getJ()) < 0.0001) {
      driveMotor.set(0.0);
      angleMotor.set(0.0);
    }
    else {
      double angleWanted = -Math.atan2(vector.getJ(), vector.getI());
      double wheelPower = Math.sqrt(Math.pow(vector.getI(), 2) + Math.pow(vector.getJ(), 2));

      if (wheelPower > Constants.TOP_SPEED){
        wheelPower = Constants.TOP_SPEED;
      }

      double velocityRPS = (MPSToRPS(wheelPower));
      SmartDashboard.putNumber("Velocity", wheelPower);
      SmartDashboard.putNumber("Angle Wanted", Math.toDegrees(angleWanted));

      double currentAngle = getWheelPosition();
      double currentAngleBelow360 = (getWheelPosition()) % (Math.toRadians(360));

      // setWheelPID(Math.toDegrees(angleWanted), velocityRPS);
      double typeOne = angleWanted;
      if(angleWanted > currentAngleBelow360){
        typeOne = angleWanted - Math.toRadians(360);
      }

      double typeTwo = angleWanted;
      if(angleWanted < currentAngleBelow360){
        typeTwo = angleWanted + Math.toRadians(360);
      }

      double typeThree = angleWanted + Math.toRadians(180);
      
      double distanceTypeOne = Math.abs(currentAngleBelow360 - typeOne);
      double distanceTypeTwo = Math.abs(currentAngleBelow360 - typeTwo);
      double distanceTypeThree = Math.abs(currentAngleBelow360 - typeThree);

      if((distanceTypeOne <= distanceTypeTwo) && (distanceTypeOne <= distanceTypeThree)){
        setWheelPID((typeOne - currentAngleBelow360 + currentAngle), velocityRPS);
      } else if((distanceTypeTwo <= distanceTypeOne) && (distanceTypeTwo <= distanceTypeThree)){
        setWheelPID((typeTwo - currentAngleBelow360 + currentAngle), velocityRPS);
      } else if((distanceTypeThree <= distanceTypeOne) && (distanceTypeThree <= distanceTypeTwo)){
        setWheelPID((typeThree - currentAngleBelow360 + currentAngle),  -velocityRPS);
      }
  }
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
