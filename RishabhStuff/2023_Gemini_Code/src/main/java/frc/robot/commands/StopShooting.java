// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.MagIntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class StopShooting extends CommandBase {
  MagIntakeSubsystem magintake;
  ShooterSubsystem shooter;
  HoodSubsystem hood;
  /** Creates a new StopShooting. */
  public StopShooting(MagIntakeSubsystem magintake, ShooterSubsystem shooter, HoodSubsystem hood) {
    this.magintake = magintake;
    this.shooter = shooter;
    this.hood = hood;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(magintake);
    addRequirements(shooter);
    addRequirements(hood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    magintake.setMagPercent(0.0);
    shooter.setFlywheelPercent(0.0);
    hood.setHoodPosition(0.0);
  }

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
