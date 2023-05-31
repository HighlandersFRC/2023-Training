// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class MoveWheelToAngle extends CommandBase {
  SwerveDriveSubsystem drive;
  double degrees;
  /** Creates a new MoveWheelToAngle. */
  public MoveWheelToAngle(SwerveDriveSubsystem drive, double degrees) {
    this.drive = drive;
    this.degrees = degrees;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("works");
    drive.wheelToAngle(degrees);
    // drive.moveMotor(degrees);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("end");
    // drive.moveMotor(0.0);
    // drive.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
