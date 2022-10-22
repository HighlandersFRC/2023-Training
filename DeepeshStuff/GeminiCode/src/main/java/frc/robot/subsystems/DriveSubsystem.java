// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  public static TalonFX frontRight = new TalonFX(1);
  public static TalonFX backRight = new TalonFX(2);
  public static TalonFX frontLeft = new TalonFX(3);
  public static TalonFX backLeft = new TalonFX(4);
  public DriveSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
