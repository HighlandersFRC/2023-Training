package frc.robot.commands.Defaults;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BeamBreak;

public class BeamBreakDefault extends CommandBase {
BeamBreak beamBreak = new BeamBreak();
  public BeamBreakDefault(BeamBreak beamBreak) {
this.beamBreak = beamBreak;
addRequirements(beamBreak);
  }

  @Override
  public void initialize() {
  }
  
  @Override
  public void execute() {
  }
  
  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
