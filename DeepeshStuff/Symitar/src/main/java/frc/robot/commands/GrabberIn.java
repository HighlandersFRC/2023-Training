// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.Grabber;

public class GrabberIn extends CommandBase {
  /** Creates a new SetGrabberPercent. */
  Grabber grabber;
  public GrabberIn(Grabber grabber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.grabber = grabber;
    addRequirements(grabber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    grabber.setIntakePercent(-0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    grabber.setIntakePercent(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(OI.driverController.getRightTriggerAxis()>= 0.2){
      return false;
    } else{
      return true;
    }
  }
}
