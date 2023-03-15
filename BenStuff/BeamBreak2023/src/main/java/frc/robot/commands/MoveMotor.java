package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MotorTest;

public class MoveMotor extends CommandBase {
MotorTest motorTest;
  public MoveMotor(MotorTest motorTest) {
    this.motorTest = motorTest;
    addRequirements(motorTest);
  }

  @Override
  public void initialize() {
    motorTest.setIntakeTorqueOutput(5, .2);
    System.out.println("Moving Motor");
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
