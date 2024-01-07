// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.defaults.FeederDefault;

public class Feeder extends SubsystemBase {
  TalonFX feeder1 = new TalonFX(13, "Canivore");
  TalonFX feeder2 = new TalonFX(14, "Canivore");
  /** Creates a new Feeder. */
  public Feeder() {}

  public void init(){
    setDefaultCommand(new FeederDefault(this));
  }

  public void setPercent(double left, double right){
    feeder1.set(left);
    feeder2.set(-right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
