// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;

public class IntakeRing extends CommandBase {
  /** Creates a new IntakeRing. */
  Intake intake;
  Feeder feeder;
  public IntakeRing(Intake intake, Feeder feeder) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    this.feeder = feeder;
    addRequirements(intake, feeder);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("intake");
    intake.rotateIntake(0.4);
    feeder.setPercent(1.0, 1.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
