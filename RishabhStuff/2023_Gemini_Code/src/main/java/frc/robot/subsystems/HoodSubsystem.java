// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.HoodDefault;

public class HoodSubsystem extends SubsystemBase {

  /** Creates a new HoodSubsystem. */
  public HoodSubsystem() {}
  public CANSparkMax hoodMotor = new CANSparkMax(13, MotorType.kBrushless);
  public SparkMaxLimitSwitch lowerLimitSwitch;
  public SparkMaxLimitSwitch upperLimitSwitch;
  public RelativeEncoder hoodEncoder;
  public SparkMaxPIDController pidController;
  boolean hoodDown = false;
  boolean hoodUp = false;

  public void init(){
    hoodMotor.restoreFactoryDefaults();
    hoodEncoder = hoodMotor.getEncoder();
    pidController = hoodMotor.getPIDController();
    lowerLimitSwitch = hoodMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    upperLimitSwitch = hoodMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    setDefaultCommand(new HoodDefault(this));
    hoodEncoder.setPosition(0.0);
    lowerLimitSwitch.enableLimitSwitch(false);
    upperLimitSwitch.enableLimitSwitch(false);
    pidController.setP(0.0, 0);
    pidController.setI(0.0, 0);
    pidController.setD(0.0, 0);
    pidController.setFF(0.0045, 0);
    pidController.setOutputRange(-1, 1);
    pidController.setSmartMotionMaxVelocity(275, 0);
    pidController.setSmartMotionMinOutputVelocity(-275, 0);
    pidController.setSmartMotionMaxAccel(275, 0);
    pidController.setSmartMotionAllowedClosedLoopError(0.2, 0);
  }
  
  public void outputSwitches(){
    SmartDashboard.putBoolean("Lower Limit Switch", lowerLimitSwitch.isPressed());
    SmartDashboard.putBoolean("Upper Limit Switch", upperLimitSwitch.isPressed());
  }
  public void setHoodPosition(double position){
    pidController.setReference(position, ControlType.kSmartMotion);
  }
  public void encoderPosition(){
    SmartDashboard.putNumber("Hood Position", hoodEncoder.getPosition());
  }
  public void whenLowerLimitSwitchPressed(){
    if (lowerLimitSwitch.isPressed()){
      hoodEncoder.setPosition(0.0);
      hoodDown = true;
    } else {
      hoodDown = false;
    }
  }
  public void whenUpperLimitSwitchPressed(){
    if (upperLimitSwitch.isPressed()){
      hoodEncoder.setPosition(-34);
      hoodUp = true;
    } else {
      hoodUp = false;
    }
  }
  public void setHoodPercent(double percent){
    hoodMotor.set(percent);
  }
  public void stopHood(){
    hoodMotor.set(0.0);
  }
  public void testHood(double speed){
    hoodMotor.set(speed);
  }
  public double getHoodCurrent(){
    return hoodMotor.getOutputCurrent();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
