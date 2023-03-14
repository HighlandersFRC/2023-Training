package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BeamBreak;

public class TestLights extends CommandBase {

  BeamBreak beamBreak = new BeamBreak();
  public TestLights(BeamBreak beamBreak) {
    this.beamBreak = beamBreak;
    addRequirements(beamBreak);
  }

  @Override
  public void initialize() {
    Robot.aFrameLeds.set(SmartDashboard.getNumber("a", -0.97));
    Robot.swerveLeds.set(SmartDashboard.getNumber("a", -0.97));
  
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    Robot.aFrameLeds.set(-0.97 );
    Robot.swerveLeds.set(-0.97 );
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
