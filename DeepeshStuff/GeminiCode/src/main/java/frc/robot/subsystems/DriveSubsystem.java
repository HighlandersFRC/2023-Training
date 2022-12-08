// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveDefault;



public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  public TalonFX frontRight = new TalonFX(1);
  public TalonFX backRight = new TalonFX(2);
  public TalonFX frontLeft = new TalonFX(3);
  public TalonFX backLeft = new TalonFX(4);
  
  
  
  public DriveSubsystem() {}
  public void init(){
    setDefaultCommand(new DriveDefault(this));
    backLeft.follow(frontLeft);
    backRight.follow(frontRight);
    frontLeft.follow(frontRight, FollowerType.AuxOutput1);
    backLeft.follow(frontRight, FollowerType.AuxOutput1);

  }
  
  public void setDriveVelocity(double leftFPS, double rightFPS){
    
    SmartDashboard.putNumber("Left Theoretical Speed", -leftFPS);

    frontLeft.set(ControlMode.Velocity, Constants.MPS_To_TP100MS( -leftFPS));

    SmartDashboard.putNumber("Right Theoretical Speed", rightFPS);
    
    
    frontRight.set(ControlMode.Velocity, Constants.MPS_To_TP100MS( rightFPS));

  }
  public void setDrivePosition(double leftMeters, double rightMeters) {
    frontLeft.set(ControlMode.Position, frontLeft.getSelectedSensorPosition() + Constants.metersToTicks(-leftMeters));
    frontRight.set(ControlMode.Position, frontRight.getSelectedSensorPosition() + Constants.metersToTicks(rightMeters));
    
  }
  
  public void setMotorPID(TalonFX talon, double P, double I, double D, double F, int slotidx, double peakPercentOut){
    talon.config_kP(slotidx, P);
    talon.config_kI(slotidx, I);
    talon.config_kD(slotidx, D);
    talon.config_kF(slotidx, F);
    talon.configPeakOutputForward(peakPercentOut);
    talon.configPeakOutputReverse(-peakPercentOut);
  }
  public void setDrivePercents(double left, double right){
    frontLeft.set(ControlMode.PercentOutput,  -left);
    frontRight.set(ControlMode.PercentOutput,  right);
    

  }
  public void arcadeDrive(double power, double direction){
    double right = power + direction;
    double left = power - direction;
    right *= Math.abs(right);
    left *= Math.abs(left);
    double divisor = 1;
    if(left>1) {
      divisor = left;
    } else if (right > 1){
      divisor = right;
    }
    right /= divisor;
    left /= divisor;
    setDrivePercents(left, right);
  }
  public void driveVelocityMode(double power, double direction){
    double right = power + direction;
    double left = power - direction;
    double quotient = 1;
      right /= Constants.MAX_SPEED_METERS_PER_SECOND;
      left /= Constants.MAX_SPEED_METERS_PER_SECOND;
      right *= Math.abs(right);
      left *= Math.abs(left);
      right *= Constants.MAX_SPEED_METERS_PER_SECOND;
      left *= Constants.MAX_SPEED_METERS_PER_SECOND;
    if (Math.abs(left) > Constants.MAX_SPEED_METERS_PER_SECOND && left > right){
      quotient = Math.abs(left);
      left *=Constants.MAX_SPEED_METERS_PER_SECOND;
      right *=Constants.MAX_SPEED_METERS_PER_SECOND;
    } else if (Math.abs(right) > Constants.MAX_SPEED_METERS_PER_SECOND && right >= left) {
      quotient = Math.abs(right);
      left *=Constants.MAX_SPEED_METERS_PER_SECOND;
      right *=Constants.MAX_SPEED_METERS_PER_SECOND;
    }
    left /= quotient;
    right /= quotient;

    setDriveVelocity(left, right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
