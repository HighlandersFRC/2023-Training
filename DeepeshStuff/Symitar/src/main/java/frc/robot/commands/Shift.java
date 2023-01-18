// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shifter;

public class Shift extends CommandBase {
  /** Creates a new Shift. */
  Shifter shifter;
  public Shift(Shifter shifter) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shifter = shifter;
    addRequirements(shifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(shifter.high){
      shifter.lowGear();
    } else {
      shifter.highGear();
    }
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
    return true;
  }
}
