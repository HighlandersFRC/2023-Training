// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.IntakeDefault;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  // public CANSparkMax neo3 = new CANSparkMax(11, MotorType.kBrushless);// doesnt connect to a neo yet
  public TalonFX intakeTalon = new TalonFX(8);
  public IntakeSubsystem() {}
  public void init(){
    setDefaultCommand(new IntakeDefault(this));
  }
  public void intake(){
    intakeTalon.set(ControlMode.PercentOutput, 0.5);
  }
  public void stopIntake(){
    intakeTalon.set(ControlMode.PercentOutput, 0);
  }
  public void outake(){
    intakeTalon.set(ControlMode.PercentOutput, 0.5);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
