// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.MotorsDefault;

public class Motors extends SubsystemBase {
  /** Creates a new Motors. */
  CANSparkMax neo = new CANSparkMax(9, MotorType.kBrushless);
  TalonFX talon = new TalonFX(10);
  public Motors() {}
  public void init(){
    this.setDefaultCommand(new MotorsDefault(this));
  }
  public void motorsClockwise(){
    neo.set(0.5);
    talon.set(ControlMode.PercentOutput, 0.5);
  }
  public void motorsCounterClockwise(){
    neo.set(-0.5);
    talon.set(ControlMode.PercentOutput, -0.5);
  }
  public void motorsOff(){
    neo.set(0.0);
    talon.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
