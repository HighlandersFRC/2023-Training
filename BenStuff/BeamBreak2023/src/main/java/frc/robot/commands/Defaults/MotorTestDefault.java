package frc.robot.commands.Defaults;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MotorTest;

public class MotorTestDefault extends CommandBase {
MotorTest motorTest;
  public MotorTestDefault(MotorTest motorTest) {
    this.motorTest = motorTest;
    addRequirements(motorTest);
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
