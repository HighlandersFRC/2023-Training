// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
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
    System.out.println("Velocity Mode ran");
    drive.setMotorPID(drive.frontLeft, 0.047, 0, 0.0, 0.04547);
    drive.setMotorPID(drive.frontRight, 0.047, 0, 0.0, 0.04547);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!teleop) {
      drive.driveVelocityMode(forwardSpeedAuton, 0);
    } else {
      drive.driveVelocityMode(4.5 * OI.getDriverLeftY(), 4.5 * OI.getDriverRightX());
    }
    //SmartDashboard.setPersistent(key);
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
