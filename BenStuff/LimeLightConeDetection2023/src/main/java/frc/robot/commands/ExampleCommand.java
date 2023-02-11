package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ExampleCommand extends CommandBase {
  public ExampleCommand(ExampleSubsystem subsystem) {
    addRequirements(subsystem);
  }
  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
