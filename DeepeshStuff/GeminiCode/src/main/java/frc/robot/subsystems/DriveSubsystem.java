// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveVelocityMode;



public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  public TalonFX frontRight = new TalonFX(1);
  public TalonFX backRight = new TalonFX(2);
  public TalonFX frontLeft = new TalonFX(3);
  public TalonFX backLeft = new TalonFX(4);
  public TalonFXSensorCollection _frontRight = new TalonFXSensorCollection(frontRight);
  public TalonFXSensorCollection _frontLeft = new TalonFXSensorCollection(frontLeft);
  public TalonFXSensorCollection _backLeft = new TalonFXSensorCollection(backLeft);
  public TalonFXSensorCollection _backRight = new TalonFXSensorCollection(backRight);
  
  
  public DriveSubsystem() {}
  public void init(){
    setDefaultCommand(new DriveVelocityMode(this));
     setDrivePercents(0, 0);
     frontLeft.setInverted(true);
     backLeft.setInverted(InvertType.FollowMaster);
     frontRight.configFactoryDefault();
     frontLeft.configFactoryDefault();
     backLeft.configFactoryDefault();
     backRight.configFactoryDefault();
     _backLeft.setIntegratedSensorPosition(0, 0);
     _frontLeft.setIntegratedSensorPosition(0, 0);
     _backRight.setIntegratedSensorPosition(0, 0);
     _frontRight.setIntegratedSensorPosition(0, 0);
    
     frontRight.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 35, 35, 0.5));
     frontLeft.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 35, 35, 0.5));
     backRight.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 35, 35, 0.5));
     backLeft.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 35, 35, 0.5));
     frontRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 35, 35, 0.5));
     frontLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 35, 35, 0.5));
     backRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 35, 35, 0.5));
     backLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 35, 35, 0.5));


    frontLeft.follow(frontRight, FollowerType.AuxOutput1);
    backLeft.follow(frontLeft);
    backRight.follow(frontRight);
  }
  
  public void setDriveVelocity(double leftMPS, double rightMPS){
    SmartDashboard.putNumber("Left Theoretical Speed", leftMPS);
     frontLeft.set(ControlMode.Velocity, Constants.MPS_To_TP100MS(leftMPS));
    SmartDashboard.putNumber("Right Theoretical Speed", rightMPS);
     frontRight.set(ControlMode.Velocity, Constants.MPS_To_TP100MS(rightMPS));
  }

  public void setDrivePosition(TalonFX talon, double pid0Target, double pid1Target) {
    frontRight.set(ControlMode.Position, (frontRight.getSelectedSensorPosition() + Constants.metersToTicks(-pid0Target)), DemandType.AuxPID, pid1Target);
  }
  
  public void setMotorPID(TalonFX talon, double P, double I, double D, double F, int slotidx, double peakPercentOut){
    talon.config_kP(slotidx, P);
    talon.config_kI(slotidx, I);
    talon.config_kD(slotidx, D);
    talon.config_kF(slotidx, F);
    talon.configClosedLoopPeakOutput(slotidx, peakPercentOut);
  }

  public void setDrivePercents(double left, double right){
     frontLeft.set(ControlMode.PercentOutput,  left);
     frontRight.set(ControlMode.PercentOutput,  right);
  }

  public void arcadeDrive(double power, double direction){
    double right = power + direction;
    double left = power - direction;
    right *= Math.abs(right);
    left *= Math.abs(left);
    double divisor = 1;
    if(left>1) {
      divisor = Math.abs(left);
    } else if (right > 1){
      divisor = Math.abs(right);
    }
    right /= divisor;
    left /= divisor;
    setDrivePercents(left, right);
  }

  public void driveVelocityMode(double power, double direction){
    double right = power + direction;
    double left = power - direction;
    right *= Math.abs(right);
    left *= Math.abs(left);
    double divisor = 1;
    if(left>1) {
      divisor = Math.abs(left);
    } else if (right > 1){
      divisor = Math.abs(right);
    }
    right /= divisor;
    left /= divisor;
    left *= Constants.MAX_SPEED_METERS_PER_SECOND;
    right *= Constants.MAX_SPEED_METERS_PER_SECOND;

    setDriveVelocity(left, right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
