package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.MotorTest;

public class IntakeMotor extends SequentialCommandGroup {
MotorTest motorTest;
  public IntakeMotor(MotorTest motorTest) {
    System.out.println("Intaking command");
    this.motorTest = motorTest;
    new MoveMotor(motorTest);
new WaitCommand(0.1);
new RunMotor(motorTest);
  }
}
