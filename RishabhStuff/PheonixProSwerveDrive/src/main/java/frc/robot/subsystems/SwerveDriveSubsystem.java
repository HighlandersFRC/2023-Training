// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenixpro.StatusSignalValue;
import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.controls.PositionVoltage;
import com.ctre.phoenixpro.hardware.CANcoder;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.FeedbackSensorSourceValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwerveDriveSubsystem extends SubsystemBase {
  private final TalonFX angleMotor = new TalonFX(8);
  private final TalonFXConfiguration angleMotorConfig = new TalonFXConfiguration();

  private final CANcoder canCoder = new CANcoder(4, "rio");
  
  private final PositionVoltage voltage = new PositionVoltage(0, true, 0, 0, false);

  /** Creates a new SwerveDriveSubsystem. */
  public SwerveDriveSubsystem() {}

  public void init(){
    angleMotor.getConfigurator().apply(angleMotorConfig);

    angleMotorConfig.Slot0.kP = 1.0;
    angleMotorConfig.Slot0.kI = 0.0;
    angleMotorConfig.Slot0.kD = 0.0;

    angleMotorConfig.Voltage.PeakForwardVoltage = 8;
    angleMotorConfig.Voltage.PeakReverseVoltage = -8;

    angleMotorConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
    angleMotorConfig.Feedback.FeedbackRemoteSensorID = 4;
  }

  public void wheelToAngle(double degrees){
    angleMotor.setControl(voltage.withPosition(Constants.DegreesToTicks(degrees)));
  }

  public StatusSignalValue<Double> getAngleValue(double value){
    return canCoder.getAbsolutePosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
