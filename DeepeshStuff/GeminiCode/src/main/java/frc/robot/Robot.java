// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveForwardXMeters;
import frc.robot.commands.DriveVelocityMode;
import frc.robot.commands.TurnXDegrees;
import frc.robot.commands.magBackward;
import frc.robot.commands.Intake;
import frc.robot.commands.Outake;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.MagSubsystem;
import frc.robot.subsystems.NavXSensor;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  DriveSubsystem drive = new DriveSubsystem();
  MagSubsystem mag = new MagSubsystem(); 
  IntakeSubsystem intake = new IntakeSubsystem();
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    drive.init();
    intake.init();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    mag.beamBreaksOnDashboard(); 
    SmartDashboard.putNumber("NavX reading", NavXSensor.navX.currentYaw());
    SmartDashboard.putNumber("Left Movement Speed", Constants.TP100MS_To_MPS(drive._frontLeft.getIntegratedSensorVelocity()));
    SmartDashboard.putNumber("Right Movement Speed", Constants.TP100MS_To_MPS(drive._frontRight.getIntegratedSensorVelocity()));
    SmartDashboard.putNumber("Right Power", drive.frontRight.getMotorOutputPercent());
    SmartDashboard.putNumber("Left Power", drive.frontLeft.getMotorOutputPercent());
    SmartDashboard.putNumber("left position", Constants.ticksToMeters(drive.frontLeft.getSelectedSensorPosition()));
    SmartDashboard.putNumber("Right position", Constants.ticksToMeters(drive.frontRight.getSelectedSensorPosition())); 
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    drive.setDrivePercents(0, 0);
  }

  @Override
  public void disabledPeriodic() {
    SmartDashboard.putNumber("left position", Constants.ticksToMeters(drive.frontLeft.getSelectedSensorPosition()));
    SmartDashboard.putNumber("Right position", Constants.ticksToMeters(drive.frontRight.getSelectedSensorPosition())); 
    mag.beamBreaksOnDashboard(); 
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    TurnXDegrees turn180 = new TurnXDegrees(180, drive);
    TurnXDegrees turnNeg180 = new TurnXDegrees(-180, drive);
    DriveForwardXMeters driveForwardXMeters = new DriveForwardXMeters(drive, 4);
    ArcadeDrive arcadeDrive = new ArcadeDrive(drive);
    OI.buttonA.cancelWhenPressed(turn180);
    OI.buttonA.whenPressed(turn180);
    OI.buttonB.cancelWhenPressed(turnNeg180);
    OI.buttonB.whenPressed(turnNeg180);
    OI.buttonY.whenPressed(driveForwardXMeters);
    OI.lBumper.cancelWhenPressed(driveForwardXMeters);
    OI.rBumper.toggleWhenPressed(arcadeDrive);
    OI.lTrigger.whenPressed(new Outake(intake));
    OI.rTrigger.whenPressed(new Intake(intake));
    OI.lTrigger.whenPressed(new magBackward(mag));

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
