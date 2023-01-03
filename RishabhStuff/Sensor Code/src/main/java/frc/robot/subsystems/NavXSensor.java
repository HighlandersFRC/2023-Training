// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI.Port;
import frc.robot.tools.Navx;

public class NavXSensor extends SubsystemBase {
  /** Creates a new NavXSensor. */
  public NavXSensor() {}
  public final static AHRS ahrs = new AHRS(Port.kMXP);
  public final static Navx navX = new Navx(ahrs);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
