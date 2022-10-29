// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Defaults;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TailSubsystem;

public class TailDefault extends CommandBase {
  /** Creates a new TailDefault. */
  private static TailSubsystem tail = new TailSubsystem();
 
  public TailDefault(TailSubsystem tail) {
 
    TailDefault.tail = tail;
  
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(TailDefault.tail);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
