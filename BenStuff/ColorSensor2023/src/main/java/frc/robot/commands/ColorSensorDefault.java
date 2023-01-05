
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSensor;

public class ColorSensorDefault extends CommandBase {

  ColorSensor colorSensor;

  public ColorSensorDefault(ColorSensor colorSensor) {
this.colorSensor = colorSensor;
addRequirements(colorSensor);
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
