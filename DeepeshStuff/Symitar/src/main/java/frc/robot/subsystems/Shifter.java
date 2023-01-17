// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shifter extends SubsystemBase {
  /** Creates a new Shifter. */
  public Shifter() {}
  public boolean high;
  public boolean low;
  DoubleSolenoid shifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
  public void init(){
    if(shifter.get() == Value.kForward){
      high = true;
      low = false;
    } else {
      high = false;
      low = true;
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void highGear(){
    shifter.set(Value.kForward);
    high = true;
    low = false;
  }
  public void lowGear(){
    shifter.set(Value.kReverse);
    high = false;
    low = true;
  }
}
