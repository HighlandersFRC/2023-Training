// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.controls.PositionVoltage;
import com.ctre.phoenixpro.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveDriveSubsystem extends SubsystemBase {
  private final TalonFX angleMotor = new TalonFX(6);
  private final TalonFXConfiguration angleMotorConfig = new TalonFXConfiguration();
  private final PositionVoltage voltage = new PositionVoltage(0, false, 0, 0, false);
  /** Creates a new SwerveDriveSubsystem. */
  public SwerveDriveSubsystem() {}

  public void init(){
    angleMotorConfig.Slot0.kP = 0.0;
    angleMotorConfig.Slot0.kI = 0.0;
    angleMotorConfig.Slot0.kD = 0.0;
  }

  public void wheelToAngle(){
    angleMotor.setControl(voltage.withPosition(0));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
