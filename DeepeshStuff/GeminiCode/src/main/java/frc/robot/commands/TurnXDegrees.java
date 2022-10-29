// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NavXSensor;
import frc.robot.tools.PID;

public class TurnXDegrees extends CommandBase {
  /** Creates a new TurnXDegrees. */
  double radians;
  DriveSubsystem drive;
  PID pid;
  double currentYaw;
  double pidResult;
  double originalYaw;

  public TurnXDegrees(double turnDegrees, DriveSubsystem drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    radians = Math.toRadians(turnDegrees);
    this.drive = drive;
    pid = new PID(1, 0, 0);
    pid.setContinuous(true);
    pid.setMaxOutput(0.4);
    pid.setMinOutput(-0.4);
    pid.setMaxInput(Math.toRadians(180));
    pid.setMinInput(Math.toRadians(-180));
    addRequirements(drive);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    originalYaw = Math.toRadians(NavXSensor.navX.currentYaw());
    currentYaw = Math.toRadians(NavXSensor.navX.currentYaw());
    pid.setSetPoint(currentYaw + radians);
    System.out.println("Turn direction " + pidResult);
    System.out.println("initialize works");
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentYaw = Math.toRadians(NavXSensor.navX.currentYaw());
    
    pidResult = pid.updatePID(currentYaw);
    
    System.out.println(pid.getError());
    System.out.println("Turn direction " + pidResult);
    drive.arcadeDrive(0.0, pidResult);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs((originalYaw + radians) - currentYaw) < 1){
      return true;
    } else {
      return false;
    }
    
  }
}
