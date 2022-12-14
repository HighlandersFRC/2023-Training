// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.Motors;

public class MotorsClockwise extends CommandBase {
  /** Creates a new MotorsClockwise. */
  Motors motors;
  public MotorsClockwise(Motors motors) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.motors = motors;
    addRequirements(motors);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    motors.motorsClockwise();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (OI.buttonA.get()) {
      return false;
    } else {
      return true;
    }
  }
}
