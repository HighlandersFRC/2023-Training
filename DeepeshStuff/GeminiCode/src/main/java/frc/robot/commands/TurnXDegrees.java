// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.reflect.Constructor;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  double target;
  double circledTarget;
  boolean has180Run;
  int correctLoops;


  public TurnXDegrees(double turnDegrees, DriveSubsystem drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    radians = Math.toRadians(turnDegrees);
    this.drive = drive;
    pid = new PID(0.85, 0.04, 0.1);
    pid.setContinuous(true);
    pid.setMaxOutput(0.8);
    pid.setMinOutput(-0.8);
    pid.setMaxInput(Math.toRadians(180));
    pid.setMinInput(Math.toRadians(-180));
    System.out.println("Constructor worked");
    addRequirements(drive);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    correctLoops = 0;
    has180Run = false;
    originalYaw = Math.toRadians(NavXSensor.navX.currentYaw());
    currentYaw = Math.toRadians(NavXSensor.navX.currentYaw());
    target = originalYaw + radians;
    circledTarget = target;
    
    if (circledTarget > Math.toRadians(180)) {
      circledTarget -= Math.toRadians(360);
    }
    if (circledTarget < Math.toRadians(-180)) {
      circledTarget += Math.toRadians(360);
    }
    SmartDashboard.putNumber("Target Angle", Math.toDegrees(circledTarget));
    SmartDashboard.putBoolean("Running", true);
    pid.setSetPoint(target);
    System.out.println("initialize works");
    
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentYaw = Math.toRadians(NavXSensor.navX.currentYaw());
    
    pidResult = pid.updatePID(currentYaw);
    SmartDashboard.putNumber("Target Proximity", Math.toDegrees(circledTarget-currentYaw));
    SmartDashboard.putNumber("Turn direction" , -pidResult);
    
      
    if (Math.toDegrees(radians) == 180 && pidResult > 0 && Math.abs(pid.getError()) > Math.toRadians(90)) {
      
      drive.arcadeDrive(0.0, 0.5);
      has180Run = true;
      
    } else if (Math.toDegrees(radians) == -180 && pidResult < 0 && Math.abs(pid.getError()) > Math.toRadians(90)) {
      
      drive.arcadeDrive(0.0, -0.5);
      has180Run = true;
      
    } else {
      drive.arcadeDrive(0.0, -pidResult);
    }
    
      
    } 
    
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Running", false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {    
    if ((Math.abs(circledTarget-currentYaw)>Math.toRadians(1)) || (pidResult > 0.3)){
    return false;
    } else {
      correctLoops++;
      if (correctLoops > 50){
        return true;
      } else {
        return false;
      }
    }
    
  }
}
