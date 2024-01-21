package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Motor;

public class MovePIDMotor extends Command {
  Motor motor;
  double velocity;
  public MovePIDMotor(Motor motor, double velocity) {
    this.motor = motor;
    this.velocity = velocity;
    addRequirements(motor);
  }

  @Override
  public void initialize() {
    // motor.zeroVortexPosition();
    // motor.setVortexPosition(20);
    // System.out.println("Starting PID");
  }

  @Override
  public void execute() {
    motor.setVortexVelocity(velocity);
  }

  @Override
  public void end(boolean interrupted) {
    // System.out.println("End command");
    motor.moveVortex(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
