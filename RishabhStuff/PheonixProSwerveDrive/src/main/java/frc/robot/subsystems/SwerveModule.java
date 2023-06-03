// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenixpro.hardware.CANcoder;
import com.ctre.phoenixpro.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveModule extends SubsystemBase {
  private final TalonFX angleMotor;
  private final TalonFX driveMotor;
  private final int moduleNumber = 0;
  private final CANcoder canCoder;
  /** Creates a new SwerveModule. */
  public SwerveModule(int moduleNum, TalonFX mAngleMotor, TalonFX mDriveMotor, CANcoder mCanCoder) {
    moduleNum = moduleNumber;
    angleMotor = mAngleMotor;
    driveMotor = mDriveMotor;
    canCoder = mCanCoder;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
