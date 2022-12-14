// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
  /** Creates a new Pneumatics. */
  DoubleSolenoid piston;
  public Pneumatics() {
  piston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 3);
   System.out.println("stupidity");
  }
  

  public void extendPiston(){
    piston.set(Value.kReverse);
  }
  public void retractPiston(){
    piston.set(Value.kForward);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
