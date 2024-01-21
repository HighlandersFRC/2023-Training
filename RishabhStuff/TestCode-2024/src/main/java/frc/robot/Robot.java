// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AmpScore;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.IntakeRing;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.MoveServo;
import frc.robot.commands.OuttakeRing;
import frc.robot.commands.Shoot;
import frc.robot.commands.ZeroIMU;
import frc.robot.commands.autos.FourPieceCenterAuto;
import frc.robot.subsystems.AmpScorer;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Peripherals;
import frc.robot.subsystems.Shooter;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private Peripherals peripherals = new Peripherals();
  private Intake intake = new Intake();
  private Drive drive = new Drive(peripherals);
  private Shooter shooter = new Shooter();
  private AmpScorer scorer = new AmpScorer();
  // private Feeder feeder = new Feeder();

  File pathingFile;
  String pathString;

  JSONObject pathRead;
  JSONArray pathJSON;

  // String fieldSide;

  SequentialCommandGroup auto;
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    intake.init();
    drive.init();
    peripherals.init();
    shooter.init();
    // feeder.init();

    try {
      pathingFile = new File("/home/lvuser/deploy/2PieceCenterPart1.json");
      FileReader scanner = new FileReader(pathingFile);
      pathRead = new JSONObject(new JSONTokener(scanner));
      pathJSON = (JSONArray) pathRead.get("sampled_points");
    } catch (Exception e) {
      System.out.println("ERROR WITH PATH FILE " + e);
    }
    
    this.auto = new FourPieceCenterAuto(drive, peripherals);
    auto.schedule();
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
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    OI.driverController.setRumble(RumbleType.kBothRumble, 0);
    OI.operatorController.setRumble(RumbleType.kBothRumble, 0);
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    try {
      this.auto.schedule();
    } catch (Exception e){
      System.out.println("No auto is selected");
    } 
    drive.autoInit(this.pathJSON);
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

    OI.driverViewButton.whileTrue(new ZeroIMU(drive));

    // OI.driverY.whileTrue(new IntakeUp(intake));   
    OI.driverB.whileTrue(new Shoot(shooter));
    // OI.driverA.whileTrue(new IntakeDown(intake));
    OI.driverLB.whileTrue(new IntakeRing(intake, 0.8));
    OI.driverRB.whileTrue(new IntakeRing(intake, -0.8)); 
    // OI.driverX.whileTrue(new AmpScore(scorer, -0.5));    
    // OI.driverY.whileTrue(new AmpScore(scorer, 0.5));
    // OI.driverX.whileHeld(new Feed(feeder));
    OI.driverX.whileTrue(new MoveServo(scorer, 0));
    OI.driverY.whileTrue(new MoveServo(scorer, 90));
    OI.driverA.whileTrue(new MoveServo(scorer, 180));
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
