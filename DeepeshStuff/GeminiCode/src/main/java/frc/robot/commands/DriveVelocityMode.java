// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.DriveSubsystem;

public class DriveVelocityMode extends CommandBase {
  /** Creates a new DriveVelocityMode. */
  DriveSubsystem drive;
  double forwardSpeedAuton;
  boolean teleop;
  public DriveVelocityMode(DriveSubsystem drive, double forwardSpeedAuton, boolean teleop) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drive = drive;
    this.forwardSpeedAuton = forwardSpeedAuton;
    this.teleop = teleop;

    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.init();
    System.out.println("Velocity Mode ran");
    drive.setMotorPID(drive.frontLeft, 0.047, 0, 0.0, 0.04547, 0, 1);
    drive.setMotorPID(drive.frontRight, 0.047, 0, 0.0, 0.04547, 0, 1);
    drive.setMotorPID(drive.backLeft, 0.047, 0, 0.0, 0.04547, 0, 1);
    drive.setMotorPID(drive.backRight, 0.047, 0, 0.0, 0.04547, 0, 1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!teleop) {
      drive.driveVelocityMode(forwardSpeedAuton, 0);
    } else {
      drive.driveVelocityMode(Constants.MAX_SPEED_METERS_PER_SECOND * OI.getDriverLeftY(), Constants.MAX_SPEED_METERS_PER_SECOND * OI.getDriverRightX());
    }
    //SmartDashboard.setPersistent(key);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      if (teleop) {
        return false;
      } else {
        return true;
      }
  }
}
