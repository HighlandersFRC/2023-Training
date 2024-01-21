package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Motor;

public class MotorVelocity extends Command {
    Motor motor;
    double velocity;
  public MotorVelocity(Motor motor, double rpm) {
    this.motor = motor;
    velocity = rpm;
    addRequirements(motor);
  }

  @Override
  public void initialize() {
    motor.setVortexVelocity(velocity);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    motor.setVortexVelocity(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
