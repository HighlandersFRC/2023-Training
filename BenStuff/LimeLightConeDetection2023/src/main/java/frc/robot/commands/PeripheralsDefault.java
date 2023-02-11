package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Peripherals;

public class PeripheralsDefault extends CommandBase {

  private Peripherals peripherals;

  public PeripheralsDefault(Peripherals peripherals) {
    this.peripherals = peripherals;
    addRequirements(peripherals);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    SmartDashboard.putNumber("LimeLight X", peripherals.getLimeLightX());
    SmartDashboard.putNumber("LimeLight Y", peripherals.getLimeLightY());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
