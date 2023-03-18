// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DistanceSensor extends SubsystemBase {
  /** Creates a new DistanceSensor. */
  public DistanceSensor() {}
  
  public void init(){
    distanceSensor.setAutomaticMode(true);
  }

  private final Rev2mDistanceSensor distanceSensor = new Rev2mDistanceSensor(Port.kOnboard);
  public void outputSensor(){
    SmartDashboard.putNumber("Distance Sensor", distanceSensor.getRange());
    SmartDashboard.putBoolean("Cone", cone());
    SmartDashboard.putBoolean("sensor", distanceSensor.isEnabled());
  }
  public boolean cone(){
    if (distanceSensor.getRange() < 8){
      return true;
    } else {
      return false;
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
