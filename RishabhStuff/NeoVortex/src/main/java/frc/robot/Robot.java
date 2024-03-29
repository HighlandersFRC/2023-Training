package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.MotorVelocity;
import frc.robot.commands.MoveMotor;
import frc.robot.commands.MovePIDMotor;
import frc.robot.commands.MovePIDMotorBack;
import frc.robot.subsystems.Motor;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  Motor motor = new Motor();

  @Override
  public void robotInit() {
   m_robotContainer = new RobotContainer();
    motor.init();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    // OI.driverX.onTrue(new MovePIDMotor(motor));
    OI.driverA.whileTrue(new MoveMotor(motor, 1.0));
    OI.driverB.whileTrue(new MoveMotor(motor, -1.0));
    // OI.driverY.onTrue(new MovePIDMotorBack(motor));
    OI.driverRT.whileTrue(new MotorVelocity(motor, 60));
    OI.driverLT.whileTrue(new MotorVelocity(motor, -60));
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
