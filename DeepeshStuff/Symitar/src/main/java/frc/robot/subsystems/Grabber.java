// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.GrabberDefault;

public class Grabber extends SubsystemBase {
  /** Creates a new Grabber. */
  public Grabber() {

  }
  TalonSRX leftIntake = new TalonSRX(9);
  TalonSRX rightIntake = new TalonSRX(10);
  DoubleSolenoid intakeRight = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
  DoubleSolenoid intakeLeft = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 7, 6);
  public void init(){
    setDefaultCommand(new GrabberDefault(this));
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setIntakePercent(double percent){
    leftIntake.set(ControlMode.PercentOutput, percent);
    rightIntake.set(ControlMode.PercentOutput, percent);
  }
  public void open(){
    intakeRight.set(Value.kForward);
    intakeLeft.set(Value.kForward);
  }
  public void close(){
    intakeRight.set(Value.kReverse);
    intakeLeft.set(Value.kReverse);
  }
}
