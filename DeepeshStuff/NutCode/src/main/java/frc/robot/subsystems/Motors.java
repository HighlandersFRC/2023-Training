// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.MotorDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.*;

public class Motors extends SubsystemBase {
  /** Creates a new Motors. */
  TalonFX motor = new TalonFX(0);
 
  public Motors() {}
  public void init() {
    setDefaultCommand(new MotorDefault(this));
  }
  public void motorClockwise(){
    motor.set(ControlMode.PercentOutput, 0.5);
  }
  public void motorCounterClockwise(){
    motor.set(ControlMode.PercentOutput, -0.5);
  }
  public void motorOff(){
    motor.set(ControlMode.PercentOutput, 0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
