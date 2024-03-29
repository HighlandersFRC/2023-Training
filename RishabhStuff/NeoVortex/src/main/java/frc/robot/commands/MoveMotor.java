package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Motor;

public class MoveMotor extends Command {
  Motor motor;
  double speed;
  public MoveMotor(Motor motor, double speed) {
    this.motor = motor;
    this.speed = speed;
    addRequirements(motor);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    motor.moveVortex(speed);
  }

  @Override
  public void end(boolean interrupted) {
    motor.moveVortex(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
