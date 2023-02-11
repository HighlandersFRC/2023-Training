package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Peripherals;

public class SwitchPipeline extends CommandBase {

  private Peripherals peripherals;

  public SwitchPipeline(Peripherals peripherals) {
    this.peripherals = peripherals;
    addRequirements(peripherals);
  }

  @Override
  public void initialize() {
      peripherals.togglePipeline();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
