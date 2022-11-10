// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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
  }
  public void setDrivePercents(double left, double right){
    frontLeft.set(ControlMode.PercentOutput, -left);
    frontRight.set(ControlMode.PercentOutput, right);
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
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
