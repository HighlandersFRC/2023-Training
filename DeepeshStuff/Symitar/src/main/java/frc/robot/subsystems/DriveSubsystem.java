// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ArcadeDrive;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  public TalonSRX leftMaster = new TalonSRX(7);
  public TalonSRX leftSlave1 = new TalonSRX(6);
  public TalonSRX leftSlave2 = new TalonSRX(8);
  public TalonSRX rightMaster = new TalonSRX(2);
  public TalonSRX rightSlave1 = new TalonSRX(1);
  public TalonSRX rightSlave2 = new TalonSRX(3);
  public DriveSubsystem() {}
  public void init(){
    rightSlave1.set(ControlMode.Follower, 2);
    leftSlave1.set(ControlMode.Follower, 7);
    leftSlave2.set(ControlMode.Follower, 7);
    rightSlave2.set(ControlMode.Follower, 2);
    setDefaultCommand(new ArcadeDrive(this));
  }
  public void arcadeDrive(double power, double direction){
    double right = power + direction;
    double left = power - direction;
    right *= Math.abs(right);
    left *= Math.abs(left);
    double quotient = 1;
    if (left > 1){
      quotient = left;
    }else if(right >1){
      quotient = right;
    }
    left /= quotient;
    right /= quotient;
    setPercents(left, right);
  }
  public void setPercents(double left, double right){
    rightMaster.set(ControlMode.PercentOutput, -right);
    leftMaster.set(ControlMode.PercentOutput, left);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
