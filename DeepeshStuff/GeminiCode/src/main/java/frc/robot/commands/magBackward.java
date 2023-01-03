// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.MagSubsystem;

public class magBackward extends CommandBase {
  MagSubsystem mag; 
  /** Creates a new magBackward. */
  public magBackward(MagSubsystem mag) {
    this.mag = mag;
    addRequirements(mag); 
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mag.setPercent(-0.3);
    System.out.println("magBackward ran");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("magBackward ended");
    mag.setPercent(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (OI.lTrigger.getAsBoolean()){
      return false;
    }else{
      return true;
    }
  }
}
