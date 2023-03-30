// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HoodSubsystem;

public class RetractHood extends CommandBase {
  HoodSubsystem hood;
  double percent;
  /** Creates a new RetractHood. */
  public RetractHood(HoodSubsystem hood, double percent) {
    this.hood = hood;
    this.percent = percent;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(hood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    hood.setHoodPercent(percent);
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
    return false;
  }
}
