package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.IntakeCone;
import frc.robot.commands.IntakeCube;
import frc.robot.commands.SetBlue;
import frc.robot.commands.SetRed;
import frc.robot.subsystems.BeamBreak;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  
  public static Spark aFrameLeds = new Spark(1);
  public static Spark swerveLeds = new Spark(0);
  public static DigitalInput beamBreak1 = new DigitalInput(0);

  BeamBreak beamBreak = new BeamBreak();

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    beamBreak.init();
    beamBreak.periodic();
    OI.buttonA.whileTrue(new IntakeCube(beamBreak));
    OI.buttonY.whileTrue(new IntakeCone(beamBreak));
    OI.buttonB.whileTrue(new SetRed(beamBreak));
    OI.buttonX.whileTrue(new SetBlue(beamBreak));
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Swerve Leds", swerveLeds.get());
    SmartDashboard.putBoolean("Beam Break", beamBreak1.get());
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
