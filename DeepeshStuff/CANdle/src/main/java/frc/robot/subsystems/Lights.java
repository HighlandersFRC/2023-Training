// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.led.CANdle;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lights extends SubsystemBase {
  /** Creates a new Lights. */
  CANdle candle = new CANdle(0);
  public Lights() {}
  public void turnLightsWhite(){
    candle.setLEDs(255, 255, 255);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
