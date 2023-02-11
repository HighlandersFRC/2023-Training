// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ShooterDefault;

public class ShooterSubsystem extends SubsystemBase {
  public TalonFX shooterMaster = new TalonFX(9);
  public TalonFX shooterFollower = new TalonFX(10);
  public TalonFXSensorCollection encoderMaster = new TalonFXSensorCollection(shooterMaster);
  public TalonFXSensorCollection encoderFollower = new TalonFXSensorCollection(shooterFollower);
  
  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {}
  public void init() {
    shooterMaster.configFactoryDefault();
    shooterFollower.configFactoryDefault();
    shooterFollower.set(ControlMode.Follower, 9);
    shooterMaster.config_kP(0, 0.4);
    shooterMaster.config_kI(0, 0.0);
    shooterMaster.config_kD(0, 8.5);
    shooterMaster.config_kF(0, 0.05);
    shooterMaster.configClosedLoopPeakOutput(0, 1.0);
    setDefaultCommand(new ShooterDefault(this));
  }
  public void setFlywheelVelocity(double rpm){
    shooterMaster.set(ControlMode.Velocity, Constants.RPMtoTicksPer100ms(rpm));
  }

  public void setFlywheelPercent(double percent){
    shooterMaster.set(ControlMode.PercentOutput, percent);
  }
  public void stopFlywheel(){
    shooterMaster.set(ControlMode.PercentOutput, 0.0);
  }

  public void set2FlywheelPercent(double percent){
    shooterMaster.set(ControlMode.PercentOutput, percent);
  }
  public void stop2Flywheel(){
    shooterMaster.set(ControlMode.PercentOutput, 0.0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
