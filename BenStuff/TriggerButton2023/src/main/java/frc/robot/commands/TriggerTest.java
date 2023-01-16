package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
public class TriggerTest extends CommandBase {

  public TriggerTest() {

  }

  @Override
  public void initialize() {
    System.out.println("Trigger Command Started");
  }

  @Override
  public void execute() {
    System.out.println("Trigger Is Pressed");
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Trigger Command Ended");
  }

  @Override
  public boolean isFinished() {
   return false;
  }
}
