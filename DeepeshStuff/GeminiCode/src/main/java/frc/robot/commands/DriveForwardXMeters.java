// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveForwardXMeters extends CommandBase {
  /** Creates a new DriveForwardXMeters. */
  double meters;
  DriveSubsystem drive;
  double p;
  double leftError;
  double rightError;
  int loops;
  public DriveForwardXMeters(DriveSubsystem drive, double meters) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.meters = meters;
    this.drive = drive;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    loops = 0;
    p = (0.5 * 1023)/Constants.metersToTicks(0.4);
    drive.setMotorPID(drive.frontRight, p, 0, 0, 0, 0, 0.2);
    drive.setMotorPID(drive.frontLeft, p, 0, 0, 0, 0, 0.2);
    drive.setMotorPID(drive.frontRight, p, 0, 0, 0, 1, 0.2);
    drive.setMotorPID(drive.frontLeft, p, 0, 0, 0, 1, 0.2);
    drive.setDrivePosition(-meters, -meters);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("running", true);
    SmartDashboard.putNumber("right error", drive.frontRight.getClosedLoopError());
    SmartDashboard.putNumber("left error", drive.frontLeft.getClosedLoopError());
    leftError = drive.frontLeft.getClosedLoopError();
    rightError = drive.frontRight.getClosedLoopError();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("running", false);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(rightError) < Constants.FALCON_ERROR_THRESHOLD &&  Math.abs(leftError) < Constants.FALCON_ERROR_THRESHOLD){
      loops++;
      if (loops > 50){
      return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}
