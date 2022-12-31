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
    drive.init();
    loops = 0;
    initialYaw = NavXSensor.navX.currentYaw();
    p = (0.5 * 1023)/Constants.metersToTicks(0.4);  
    drive.frontRight.configRemoteFeedbackFilter(drive.frontLeft, 0);
    drive.frontRight.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.IntegratedSensor);
    drive.frontRight.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.RemoteSensor0);
    drive.frontRight.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.IntegratedSensor);
    drive.frontRight.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0);
    drive.frontRight.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, 0, 0);
    drive.frontRight.configSelectedFeedbackSensor(FeedbackDevice.SensorDifference, 1, 0);
    drive.frontRight.configSelectedFeedbackCoefficient(0.5);
    drive.frontRight.configAuxPIDPolarity(false);
    drive.setMotorPID(drive.frontRight, p, 0, 0.001, 0, 0, 0.3);
    drive.setMotorPID(drive.frontRight, p, 0, 0, 0, 1, 1);
    drive.setMotorPID(drive.frontLeft, 0, 0, 0, 0, 0, 1);
    drive.setMotorPID(drive.frontLeft, 0, 0, 0, 0, 1, 1);

    drive.setDrivePosition(drive.frontRight, meters, 0);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentYaw = NavXSensor.navX.currentYaw();
    SmartDashboard.putBoolean("running", true);
    SmartDashboard.putNumber("error", Constants.ticksToMeters(drive.frontRight.getClosedLoopError()));    SmartDashboard.putNumber("Drive Heading", currentYaw-initialYaw);
    SmartDashboard.putNumber("left position", Constants.ticksToMeters(drive.frontLeft.getSelectedSensorPosition()));
    SmartDashboard.putNumber("Right position", Constants.ticksToMeters(drive.frontRight.getSelectedSensorPosition())); 
    rightError = drive.frontRight.getClosedLoopError();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("running", false);
    drive.setDrivePercents(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(rightError) < Constants.FALCON_ERROR_THRESHOLD_TICKS){
      loops++;
      if (loops > 20){
      return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}
