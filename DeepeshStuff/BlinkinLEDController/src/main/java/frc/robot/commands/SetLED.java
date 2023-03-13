// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lights;

public class SetLED extends CommandBase {
  /** Creates a new SetLED. */
  double sparkValue;
  Lights light;
  public SetLED(Lights light, double sparkValue) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.sparkValue = sparkValue;
    this.light = light;
    addRequirements(light);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    light.setLights(sparkValue);
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
    return false;
  }
}
