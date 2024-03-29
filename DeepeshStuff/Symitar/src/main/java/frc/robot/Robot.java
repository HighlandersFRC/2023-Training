// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Grab;
import frc.robot.commands.GrabberIn;
import frc.robot.commands.GrabberOut;
import frc.robot.commands.LetGo;
import frc.robot.commands.Shift;
import frc.robot.commands.setArmPostition;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Shifter;

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
  ArmSubsystem arm = new ArmSubsystem();
  Grabber grabber = new Grabber();
  Shifter shifter = new Shifter();
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
    arm.init();
    grabber.init();
    shifter.init();
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
    arm.whenFwdLimitCloses();
    arm.whenRevLimitCloses();
    SmartDashboard.putNumber("Arm Encoder", Constants.ARM_TICKS_TO_DEGREES(arm.armMaster.getSelectedSensorPosition()));
    SmartDashboard.putBoolean("Reverse Limit", arm.armMaster.getSensorCollection().isRevLimitSwitchClosed());
    SmartDashboard.putBoolean("High Gear?", shifter.high);
    SmartDashboard.putBoolean("Low Gear?", shifter.low);
    SmartDashboard.putBoolean("Forward Limit", arm.armMaster.getSensorCollection().isFwdLimitSwitchClosed());
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    
    SmartDashboard.putNumber("Arm Encoder", Constants.ARM_TICKS_TO_DEGREES(arm.armMaster.getSelectedSensorPosition()));
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

    OI.b.onTrue(new setArmPostition(arm, 60));
    OI.y.onTrue(new setArmPostition(arm, 5));
    OI.x.onTrue(new setArmPostition(arm, 90));
    OI.a.onTrue(new setArmPostition(arm, 175));
    OI.rb.onTrue(new Grab(grabber));
    OI.lb.onTrue(new LetGo(grabber));
    OI.rt.onTrue(new GrabberIn(grabber));
    OI.lt.onTrue(new GrabberOut(grabber));
    OI.menu.onTrue(new Shift(shifter));
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Arm Encoder", Constants.ARM_TICKS_TO_DEGREES(arm.armMaster.getSelectedSensorPosition()));
  }


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
