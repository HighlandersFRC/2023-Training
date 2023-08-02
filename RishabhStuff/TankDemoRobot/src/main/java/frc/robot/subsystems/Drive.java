// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveDefault;

public class Drive extends SubsystemBase {
  CANSparkMax frontRight = new CANSparkMax(0, MotorType.kBrushless);
  CANSparkMax frontLeft = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax backRight = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax backLeft = new CANSparkMax(3, MotorType.kBrushless);

  SparkMaxPIDController pid;


  /** Creates a new Drive. */
  public Drive() {}

  public void setUpDriveMotors(){
    backLeft.follow(frontLeft);
    backRight.follow(frontRight);
  }

  public void setPIDs(double p, double i, double d, double f, CANSparkMax neo, int slot){
    pid.setP(p, slot);
    pid.setI(i, slot);
    pid.setD(d, slot);
    pid.setFF(f, slot);
    pid = neo.getPIDController();
  }

  public void init(){
    setUpDriveMotors();
    setDefaultCommand(new DriveDefault(this));
    setPIDs(1, 0, 0, 1, frontRight, 0);
    setPIDs(1, 0, 0, 1, frontLeft, 1);
  }

  public void tankDrive(double left, double right) {
    left *= Math.abs(left);
    right *= Math.abs(right);
    setDrivePercents(left, right);
  }

  public void arcadeDrive(double power, double direction) {
    power *= Math.abs(power);
    direction *= Math.abs(direction);
    double right = power + direction;
    double left = power - direction;
    double front = 1;
    
    if (right > 1){
      front = right;
    } else if (left > 1){
      front = left;
    } 
      right = right / front;
      left = left / front;

    setDrivePercents(left, right);
  }

  public void setDrivePercents(double left, double right){
    frontRight.set(right);
    frontLeft.set(left);
  }

  public void velocityDrive(double power, double direction){
    power *= Math.abs(power);
    direction *= Math.abs(direction);
    double right = power + direction;
    double left = power - direction;
    double front = 1;
    
    if (right > 1){
      front = right;
    } else if (left > 1){
      front = left;
    } 
      right = right / front;
      left = left / front;

    right *= 4;
    left *= 4;

    setDriveVelocity(left, right);
  }

  public void setDriveVelocity(double leftRPM, double rightRPM){
    if (leftRPM > Constants.TOP_SPEED){
      leftRPM = Constants.TOP_SPEED;
    }
    if (rightRPM > Constants.TOP_SPEED){
      rightRPM = Constants.TOP_SPEED;
    }
    pid.setReference(rightRPM, ControlType.kVelocity, 0);
    pid.setReference(leftRPM, ControlType.kVelocity, 1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
