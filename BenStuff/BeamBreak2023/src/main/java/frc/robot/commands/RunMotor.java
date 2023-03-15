package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BeamBreak;
import frc.robot.subsystems.MotorTest;

public class RunMotor extends CommandBase {
MotorTest motorTest = new MotorTest();
BeamBreak beamBreak = new BeamBreak();
  public RunMotor(MotorTest motorTest) {
    this.motorTest = motorTest;
    addRequirements(motorTest);
  }

  
public double time;

  @Override
  public void initialize() {
    time = Timer.getFPGATimestamp();
    motorTest.setIntakeTorqueOutput(5, .2);
  }

  @Override
  public void execute() {
    if(Timer.getFPGATimestamp() - time > 0.2) {
    if(motorTest.getVelocity() < 10) {
beamBreak.setLightsToIntake();
    } else {
      beamBreak.setLightsToNormal();
    }
  }
  }

  @Override
  public void end(boolean interrupted) {
    motorTest.setIntakeTorqueOutput(0, 0);
    beamBreak.setLightsToNormal();
  }

  @Override
  public boolean isFinished() {
 return false;
}
}
