// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.tools.OI;

public class IntakeBalls extends CommandBase {
  IntakeSubsystem intake;
  /** Creates a new Intake. */
  public IntakeBalls(IntakeSubsystem intake) {
    this.intake = intake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.intakeDown();
    intake.intakeMotorForward();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.intakeUp();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
return false;
  }
}
