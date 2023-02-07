// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MagIntakeSubsystem;

public class ShootMag extends CommandBase {
  MagIntakeSubsystem magintake;
  /** Creates a new ShootMag. */
  public ShootMag(MagIntakeSubsystem magintake) {
    this.magintake = magintake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(magintake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    magintake.ballCount = 0;
    magintake.shootMagPercent(0.6);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
