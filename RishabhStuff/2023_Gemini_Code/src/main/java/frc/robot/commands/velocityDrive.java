// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.tools.OI;

public class velocityDrive extends CommandBase {
  DriveSubsystem drive;
  double p;
  /** Creates a new velocityDrive. */
  public velocityDrive(DriveSubsystem drive) {
    this.drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    p = (0.5 * 1023) / Constants.metersToTicks(0.5);
    drive.setMotorPIDF(drive.frontRight, p, 0, 0, 0.0719206, 1, 0);
    drive.setMotorPIDF(drive.frontLeft, p, 0, 0, 0.0719206, 1, 0);
    drive.setMotorPIDF(drive.backRight, p, 0, 0, 0, 1, 1);
    drive.setMotorPIDF(drive.backLeft, p, 0, 0, 0, 1, 1);
    drive.setVelocityDriveModeTrue();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.velocityDrive(OI.getDriverLeftY(), OI.getDriverRightX());
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
