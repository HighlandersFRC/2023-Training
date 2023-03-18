// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystem;

public class TestCommand extends CommandBase {
  double preset; // example command
  Subsystem subsystem;
  /** Creates a new getValue. */
  public TestCommand(Subsystem subsystem, double preset) {
    this.subsystem = subsystem;
    this.preset = preset;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("Preset", preset); // puts value on dashboard
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    preset = SmartDashboard.getNumber("Preset", preset); // gets value from dashboard and sets it to the preset
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
