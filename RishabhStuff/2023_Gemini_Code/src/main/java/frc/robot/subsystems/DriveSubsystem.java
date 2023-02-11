// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.commands.ArcadeDrive;

public class DriveSubsystem extends SubsystemBase {
  public TalonFX frontRight = new TalonFX(1);
  public TalonFX backRight = new TalonFX(2);
  public TalonFX frontLeft = new TalonFX(3);
  public TalonFX backLeft = new TalonFX(4);
  public TalonFXSensorCollection rightFront = new TalonFXSensorCollection(frontRight);
  public TalonFXSensorCollection rightBack = new TalonFXSensorCollection(backRight);
  public TalonFXSensorCollection leftFront = new TalonFXSensorCollection(frontLeft);
  public TalonFXSensorCollection leftBack = new TalonFXSensorCollection(backLeft);
  boolean velocityDriveMode;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {}
  public void init(){
    setDrivePercents(0, 0);
    frontLeft.setInverted(true);
    backLeft.setInverted(InvertType.FollowMaster);
    frontLeft.follow(frontRight, FollowerType.AuxOutput1);
    backLeft.follow(frontLeft);
    backRight.follow(frontRight);
    frontRight.configFactoryDefault();
    backRight.configFactoryDefault();
    frontLeft.configFactoryDefault();
    backLeft.configFactoryDefault();
    setDefaultCommand(new ArcadeDrive(this));
    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);
  }
  public void setVelocityDriveModeTrue(){
    velocityDriveMode = true;
  }
  public void setVelocityDriveModeFalse(){
    velocityDriveMode = false;
  }
  public void outputDriveMode(){
    SmartDashboard.putBoolean("Velocity Drive Mode", velocityDriveMode);
  }
  public void setDrivePercents(double left, double right){
    frontLeft.set(ControlMode.PercentOutput, left);
    frontRight.set(ControlMode.PercentOutput, right);
  }
  public void setDriveVelocity(double leftMPS, double rightMPS){
    frontRight.set(ControlMode.Velocity, Constants.MPS_To_TicksPer100MS(rightMPS));
    frontLeft.set(ControlMode.Velocity, Constants.MPS_To_TicksPer100MS(leftMPS));
  }
  public void setMotorPIDF(TalonFX motor, double p, double i, double d, double f, double maxOutput, int slot){
    motor.config_kP(slot, p);
    motor.config_kI(slot, i);
    motor.config_kD(slot, d);
    motor.config_kF(slot, f);
    motor.configClosedLoopPeakOutput(slot, maxOutput);
  }
  public void arcadeDrive(double power, double direction){
    double right = power + direction;
    double left = power - direction;
    right *= Math.abs(right);
    left *= Math.abs(left);
    double divisor = 1;
    if(left > 1) {
      divisor = Math.abs(left);
    } else if (right > 1){
      divisor = Math.abs(right);
    }
    right /= divisor;
    left /= divisor;
    setDrivePercents(left, right);
  }
  public void setSpeed(){
    setDriveVelocity(Constants.setDriveSpeed, Constants.setDriveSpeed);
  }
  public void velocityDrive(double power, double direction){
    double right = power + direction;
    double left = power - direction;
    right *= Math.abs(right);
    left *= Math.abs(left);
    double divisor = 1;
    if(left > 1) {
      divisor = Math.abs(left);
    } else if (right > 1){
      divisor = Math.abs(right);
    }
    right /= divisor;
    left /= divisor;
    right *= Constants.setDriveSpeed;
    left *= Constants.setDriveSpeed;
    setDriveVelocity(left, right);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
