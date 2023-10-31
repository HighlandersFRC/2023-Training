// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Defaults;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WingSubsystem;

public class WingsDefault extends CommandBase {
  /** Creates a new WingsUp. */
  WingSubsystem wings;
  public WingsDefault(WingSubsystem wings) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.wings = wings;
    addRequirements(wings);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    wings.wingsUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
