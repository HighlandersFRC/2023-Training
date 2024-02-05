package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Peripherals;

public class PeripheralsDefault extends CommandBase {
  private Peripherals peripherals;

  public PeripheralsDefault(Peripherals peripherals) {
    this.peripherals = peripherals;
    addRequirements(this.peripherals);
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
