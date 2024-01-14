// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Defaults.TailDefault;

public class TailSubsystem extends SubsystemBase {
  /** Creates a new TailSubsystem. */
  public TailSubsystem() {}

  public void init(){
    setTailPercent(0.0);
    setDefaultCommand(new TailDefault(this));
  }
  
  public final TalonSRX tailMotor = new TalonSRX(6);

  public void setTailPercent(double TailPower){
    tailMotor.set(ControlMode.PercentOutput, TailPower);
  }

  public double getTailCurrent() {
    return tailMotor.getStatorCurrent();
  }

  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }
  public void TailMotorForward() {
    setTailPercent(0.5);
  }
  public void TailMotorReverse() {
    setTailPercent(-0.5);
  }
  public void stopTailMotor(){
    setTailPercent(0.0);
  }
}
