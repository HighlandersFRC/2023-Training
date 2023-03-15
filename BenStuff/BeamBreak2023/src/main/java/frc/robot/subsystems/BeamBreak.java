package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.commands.Defaults.BeamBreakDefault;

public class BeamBreak extends SubsystemBase {
  
  public boolean isBlue = false;
  
public void setLightsToIntake() {
  Robot.aFrameLeds.set(0.35);
  Robot.swerveLeds.set(0.43);
}

  public void setLightsToNormal() {
    if(isBlue) {
Robot.aFrameLeds.set(0.87);
Robot.swerveLeds.set(0.87);
    } else {
Robot.aFrameLeds.set(0.61);
Robot.swerveLeds.set(0.61);
    }
  }
  

  public BeamBreak() {}

  public void init() {
    setLightsToNormal();
    setDefaultCommand(new BeamBreakDefault(this));
  }

  @Override
  public void periodic() {
  }
}

