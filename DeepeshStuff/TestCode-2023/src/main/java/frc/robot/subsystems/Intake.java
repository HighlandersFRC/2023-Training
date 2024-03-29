// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.defaults.IntakeDefault;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  TalonFX intake = new TalonFX(10, "Canivore");
  TalonFX moveIntake = new TalonFX(9, "Canivore");

  TalonFX feederLeft = new TalonFX(13, "Canivore");
  TalonFX feederRight = new TalonFX(14, "Canivore");
  
  public Intake() {}

  public void init(){
    setDefaultCommand(new IntakeDefault(this));
  }

  public void moveIntake(double percent){
    moveIntake.set(percent);
  }

  public void rotateIntake(double percent, double feeder1, double feeder2){
    intake.set(percent);
    feederLeft.set(feeder1);
    feederRight.set(-feeder2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
