package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    // SmartDashboard.putBoolean("Switch 1", OI.isSwitch1());
    // SmartDashboard.putBoolean("Switch 2", OI.isSwitch2());
    // SmartDashboard.putBoolean("Switch 3", OI.isSwitch3());
    // SmartDashboard.putBoolean("Dial 1", OI.isDial1());
    // SmartDashboard.putBoolean("Dial 2", OI.isDial2());
    // SmartDashboard.putBoolean("Dial 3", OI.isDial3());
    // SmartDashboard.putBoolean("Dial 4", OI.isDial4());
    // SmartDashboard.putBoolean("Dial 5", OI.isDial5());

    // SmartDashboard.putBoolean("dA", OI.getDriverA());
    // SmartDashboard.putBoolean("dB", OI.getDriverB());
    // SmartDashboard.putBoolean("dX", OI.getDriverX());
    // SmartDashboard.putBoolean("dY", OI.getDriverY());
    // SmartDashboard.putBoolean("dRB", OI.getDriverRB());
    // SmartDashboard.putBoolean("dLB", OI.getDriverLB());
    // SmartDashboard.putBoolean("dRT", OI.getDriverRT());
    // SmartDashboard.putBoolean("dLT", OI.getDriverLT());
    // SmartDashboard.putNumber("dLX", OI.getDriverLeftX());
    // SmartDashboard.putNumber("dLY", OI.getDriverLeftY());
    // SmartDashboard.putNumber("dRX", OI.getDriverRightX());
    // SmartDashboard.putNumber("dRY", OI.getDriverRightY());
    // SmartDashboard.putNumber("dPOV", OI.getDriverPOV());

    // SmartDashboard.putBoolean("oA", OI.getOperatorA());
    // SmartDashboard.putBoolean("oB", OI.getOperatorB());
    // SmartDashboard.putBoolean("oX", OI.getOperatorX());
    // SmartDashboard.putBoolean("oY", OI.getOperatorY());
    // SmartDashboard.putBoolean("oRB", OI.getOperatorRB());
    // SmartDashboard.putBoolean("oLB", OI.getOperatorLB());
    // SmartDashboard.putBoolean("oRT", OI.getOperatorRT());
    // SmartDashboard.putBoolean("oLT", OI.getOperatorLT());
    // SmartDashboard.putNumber("oLX", OI.getOperatorLeftX());
    // SmartDashboard.putNumber("oLY", OI.getOperatorLeftY());
    // SmartDashboard.putNumber("oRX", OI.getOperatorRightX());
    // SmartDashboard.putNumber("oRY", OI.getOperatorRightY());
    // SmartDashboard.putNumber("oPOV", OI.getOperatorPOV());
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
