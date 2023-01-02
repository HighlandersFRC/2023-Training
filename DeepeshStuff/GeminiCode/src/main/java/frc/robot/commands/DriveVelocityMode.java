// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.SensorTerm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NavXSensor;

public class DriveVelocityMode extends CommandBase {
  /** Creates a new DriveVelocityMode. */
  DriveSubsystem drive;
  double p;
  double f;
  public DriveVelocityMode(DriveSubsystem drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drive = drive;

    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    p = (0.5 * 1023)/Constants.metersToTicks(0.2);  
    drive.setMotorPID(drive.frontRight, p, 0, 0, 0.004748, 0, 1);
    drive.setMotorPID(drive.frontRight, 0, 0, 0, 0, 1, 1);
    drive.setMotorPID(drive.frontLeft, p, 0, 0, 0.004748, 0, 1);
    drive.setMotorPID(drive.frontLeft, 0, 0, 0, 0, 1, 1);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
      drive.driveVelocityMode(OI.getDriverLeftY(), OI.getDriverRightX());
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
