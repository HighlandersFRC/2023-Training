package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.BeamBreakDefault;

public class BeamBreak extends SubsystemBase {

  // public DigitalInput beamBreak1 = new DigitalInput(0);

  public BeamBreak() {}

  public void init() {
    setDefaultCommand(new BeamBreakDefault(this));
  }

  @Override
  public void periodic() {
  }
}

