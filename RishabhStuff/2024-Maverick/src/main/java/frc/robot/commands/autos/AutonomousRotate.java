// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

public class AutonomousRotate extends Command {
  Drive drive;
  double angle;
  /** Creates a new AutonomousRotate. */
  public AutonomousRotate(Drive drive, double angle) {
    this.drive = drive;
    this.angle = angle;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.drive(0, 0, 0.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(drive.getNavxAngle() - angle) < 5){
      return true;
    } else {
      return false;
    }
  }
}
