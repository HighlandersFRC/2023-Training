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
import frc.robot.commands.Defaults.IntakeDefault;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  public static DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 5, 2); 
  
  public void intakeUp(){
      intakeSolenoid.set(Value.kReverse);
  }
  public void intakeDown(){
      intakeSolenoid.set(Value.kForward);
  }
  public final TalonSRX intakeMotor = new TalonSRX(5);

  public void init() {
    setIntakePercent(0.0);
    setDefaultCommand(new IntakeDefault(this));
  }

  public void setIntakePercent(double intakePower) {
    intakeMotor.set(ControlMode.PercentOutput, intakePower);
  }
  public IntakeSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void intakeMotorForward() {
    setIntakePercent(1);
  }
  public void intakeMotorReverse() {
    setIntakePercent(-1);
  }
  public void stopIntakeMotor(){
    setIntakePercent(0.0);
  }
}
