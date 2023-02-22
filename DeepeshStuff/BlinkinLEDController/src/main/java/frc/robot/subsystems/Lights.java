// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.LightsDefault;

public class Lights extends SubsystemBase {
  /** Creates a new Lights. */
  Spark led = new Spark(0);
  public Lights() {}
  // public void init(){
  //   setDefaultCommand(new LightsDefault(this));
  // }
  public void setLights(double sparkValue){
    //To see the colors and patterns, look at this pdf: https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
    led.set(sparkValue);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
