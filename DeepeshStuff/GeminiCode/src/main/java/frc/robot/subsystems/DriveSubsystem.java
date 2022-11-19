// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DriveDefault;
import frc.robot.commands.DriveVelocityMode;



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
  }
  public double FPS_To_TP100MS(double FPS){
    return FPS * 1716.46723;
  }
  public double TP100MS_To_FPS(double TP100MS){
    return TP100MS / 1716.46723;
  }
  public void setDriveVelocity(double leftFPS, double rightFPS){
    
    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    SmartDashboard.putNumber("Left Movement Speed", TP100MS_To_FPS(frontLeft.getSelectedSensorVelocity()));

    frontLeft.set(ControlMode.Velocity, FPS_To_TP100MS(-leftFPS));
    frontRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    SmartDashboard.putNumber("Right Movement Speed", TP100MS_To_FPS(frontRight.getSelectedSensorVelocity()));
    
    frontRight.set(ControlMode.Velocity, FPS_To_TP100MS(rightFPS));

  }
  public void setMotorPID(TalonFX talon, double P, double I, double D, double F){
    talon.config_kP(0, P);
    talon.config_kI(0, I);
    talon.config_kD(0, D);
    talon.config_kF(0, F);
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
    setDriveVelocity(left, right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
