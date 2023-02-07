// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HoodSubsystem;

public class ExtendHood extends CommandBase {
  HoodSubsystem hood;
  double position;
  /** Creates a new ExtendHood. */
  public ExtendHood(HoodSubsystem hood, double position) {
    this.hood = hood;
    this.position = position;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(hood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    hood.setHoodPosition(position);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hood.setHoodPercent(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (hood.getHoodCurrent() > 20 || hood.getHoodCurrent() < -20){
      return true;
    } else {
      return false;
    }
  }
}
