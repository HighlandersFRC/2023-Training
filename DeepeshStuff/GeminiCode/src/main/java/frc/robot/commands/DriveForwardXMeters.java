// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorTerm;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NavXSensor;

public class DriveForwardXMeters extends CommandBase {
  /** Creates a new DriveForwardXMeters. */
  double meters;
  double initialYaw;
  double currentYaw;
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
    initialYaw = NavXSensor.navX.currentYaw();
    p = (0.5 * 1023)/Constants.metersToTicks(0.4);  
    

    drive.init();
    drive.frontLeft.follow(drive.frontRight, FollowerType.AuxOutput1);
    drive.frontRight.configRemoteFeedbackFilter(drive.frontLeft, 0);
    drive.frontRight.setSelectedSensorPosition(0);
    drive.frontRight.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.IntegratedSensor);
    drive.frontRight.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.RemoteSensor0);
    drive.frontLeft.setSelectedSensorPosition(0);
    drive.frontRight.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.IntegratedSensor);
    drive.frontRight.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0);
    drive.frontRight.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, 0, 0);
    drive.frontRight.configSelectedFeedbackSensor(FeedbackDevice.SensorDifference, 1, 0);
    drive.frontRight.configSelectedFeedbackCoefficient(0.5);
    // drive.backRight.configRemoteFeedbackFilter(drive.backLeft, 0);
    // drive.backRight.setSelectedSensorPosition(0);
    // drive.backRight.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.IntegratedSensor);
    // drive.backRight.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.RemoteSensor0);
    // drive.backLeft.setSelectedSensorPosition(0);
    // drive.backRight.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.IntegratedSensor);
    // drive.backRight.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0);
    // drive.backRight.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, 0, 0);
    // drive.backRight.configSelectedFeedbackSensor(FeedbackDevice.SensorDifference, 1, 0);
    // drive.backRight.configSelectedFeedbackCoefficient(0.5);
    
    drive.setMotorPID(drive.frontRight, p, 0, 0, 0, 0, 1);
    drive.setMotorPID(drive.frontRight, p, 0, 0, 0, 1, 1);
    
    // drive.setMotorPID(drive.backRight, p, 0, 0, 0, 0, 1);
    // drive.setMotorPID(drive.backRight, p, 0, 0, 0, 1, 1);
    drive.setDrivePosition(meters / 2, meters / 2);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentYaw = NavXSensor.navX.currentYaw();
    SmartDashboard.putBoolean("running", true);
    SmartDashboard.putNumber("right error", Constants.ticksToMeters(drive.frontRight.getClosedLoopError()));
    SmartDashboard.putNumber("left error", Constants.ticksToMeters(drive.frontLeft.getClosedLoopError()));
    SmartDashboard.putNumber("Drive Heading", currentYaw-initialYaw);
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
