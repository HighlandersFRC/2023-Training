package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BeamBreak;

public class IntakeCube extends CommandBase {
BeamBreak beamBreak = new BeamBreak();
  public IntakeCube(BeamBreak beamBreak) {
    this.beamBreak = beamBreak;
    addRequirements(beamBreak);
  }

  @Override
  public void initialize() {
    boolean beam = Robot.beamBreak1.get();
    if(!beam) {
    beamBreak.setLightsToIntake();
    } else {
      beamBreak.setLightsToNormal();
    }
  }

  @Override
  public void execute() {
    boolean beam = Robot.beamBreak1.get();
    if(!beam) {
    beamBreak.setLightsToIntake();
    } else {
      beamBreak.setLightsToNormal();
    }
    SmartDashboard.putBoolean("Intaking Cube", true);
  }

  @Override
  public void end(boolean interrupted) {
beamBreak.setLightsToNormal();
SmartDashboard.putBoolean("Intaking Cube", false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
