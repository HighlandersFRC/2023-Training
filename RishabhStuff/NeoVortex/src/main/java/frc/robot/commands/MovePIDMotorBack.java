package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Motor;

public class MovePIDMotorBack extends Command {
  Motor motor;
  public MovePIDMotorBack(Motor motor) {
    this.motor = motor;
    addRequirements(motor);
  }

  @Override
  public void initialize() {
    // motor.zeroVortexPosition();
    motor.setVortexPosition(-10);
    System.out.println("Starting PID");
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("End command");
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
