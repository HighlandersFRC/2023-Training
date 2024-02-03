// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.tools.EMBrake;

public class Brake extends SubsystemBase {
  /** Creates a new Brake. */
  EMBrake brake = new EMBrake(0);
  public Brake() {}
  public void init(){

  }
  public void lock(){
    brake.lock();
  }
  public void unlock(){
    brake.unlock();
  }
  public void toggle(){
    brake.toggle();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Is Brake Locked", brake.isLocked());
  }
}
