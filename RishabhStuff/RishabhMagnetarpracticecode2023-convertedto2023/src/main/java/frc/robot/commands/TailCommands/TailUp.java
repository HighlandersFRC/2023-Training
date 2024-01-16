// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TailCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TailSubsystem;

public class TailUp extends CommandBase {
  TailSubsystem tail;
  /** Creates a new TailUp. */
  public TailUp(TailSubsystem tail) {
    this.tail = tail;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(tail);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(tail.getTailCurrent() < 20 && tail.getTailCurrent() > -20) {
      tail.TailMotorReverse();
    } else {
      tail.stopTailMotor();
    }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    tail.stopTailMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
return false;
  }
}
