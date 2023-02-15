package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Joystick;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private Joystick m_stick;
  private static final int deviceID = 1;
  private CANSparkMax m_motor;
  private RelativeEncoder m_encoder;


  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    m_motor = new CANSparkMax(deviceID, MotorType.kBrushless);

    m_motor.restoreFactoryDefaults();

    m_encoder = m_motor.getEncoder();

    m_stick = new Joystick(0);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    m_motor.set(m_stick.getY());

    SmartDashboard.putNumber("Encoder Position", m_encoder.getPosition());

    SmartDashboard.putNumber("Encoder Velocity", m_encoder.getVelocity());
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
