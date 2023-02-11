package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLight;

public class LimeLightDefault extends CommandBase {

  private LimeLight limeLight;
  public LimeLightDefault(LimeLight limeLight) {
    this.limeLight = limeLight;
    addRequirements(limeLight);
    
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
