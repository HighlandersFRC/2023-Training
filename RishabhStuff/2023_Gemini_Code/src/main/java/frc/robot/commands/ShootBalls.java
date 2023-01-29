// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MagIntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootBalls extends CommandBase {
  ShooterSubsystem shooter;
  MagIntakeSubsystem mag;
  double rpm;
  /** Creates a new ShootBalls. */
  public ShootBalls(ShooterSubsystem shooter, MagIntakeSubsystem mag, double rpm) {
    this.shooter = shooter;
    this.mag = mag;
    this.rpm = rpm;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    addRequirements(mag);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mag.setMagPercent(0.4);
    shooter.setFlywheelVelocity(rpm);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mag.setMagPercent(0.0);
    shooter.setFlywheelPercent(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
