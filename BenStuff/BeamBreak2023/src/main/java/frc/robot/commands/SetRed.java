package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BeamBreak;

public class SetRed extends CommandBase {

  BeamBreak beamBreak = new BeamBreak();
  public SetRed(BeamBreak beamBreak) {
this.beamBreak = beamBreak;
addRequirements(beamBreak);
  }

  @Override
  public void initialize() {
    beamBreak.isBlue = false;
    beamBreak.setLightsToNormal();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
