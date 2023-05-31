// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.controls.NeutralOut;
import com.ctre.phoenixpro.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.MoveWheelToAngle;
import frc.robot.commands.WheelToAngleDefault;

public class SwerveDriveSubsystem extends SubsystemBase {
  private final TalonFX angleMotor = new TalonFX(10, "Canivore");
  private final TalonFXConfiguration angleMotorConfig = new TalonFXConfiguration();
  
  private final PositionTorqueCurrentFOC torqueCurrentFOC = new PositionTorqueCurrentFOC(0, 0, 0, false);
  private final NeutralOut brake = new NeutralOut();
  /** Creates a new SwerveDriveSubsystem. */
  public SwerveDriveSubsystem() {}

  public void init(){
    angleMotorConfig.Slot0.kP = 20.0;
    angleMotorConfig.Slot0.kI = 0.0;
    angleMotorConfig.Slot0.kD = 1.5;

    angleMotorConfig.TorqueCurrent.PeakForwardTorqueCurrent = 700;
    angleMotorConfig.TorqueCurrent.PeakReverseTorqueCurrent = -700;

    angleMotor.getConfigurator().apply(angleMotorConfig);
    setDefaultCommand(new WheelToAngleDefault(this));
    angleMotor.setRotorPosition(0);
  }

  public void wheelToAngle(double degrees){
    angleMotor.setControl(torqueCurrentFOC.withPosition(Constants.DegreesToRotations(degrees)));
  }

  public void stopMotor(){
    angleMotor.setControl(brake);
  }

  public void moveMotor(double power){
    angleMotor.set(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
