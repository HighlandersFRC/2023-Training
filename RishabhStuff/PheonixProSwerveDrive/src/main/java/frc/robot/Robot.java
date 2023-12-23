// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveAutoAligned;
import frc.robot.commands.MoveWheelToAngle;
import frc.robot.commands.RunGroundCubeIntake;
import frc.robot.commands.ZeroNavx;
import frc.robot.commands.autos.MoveForwardAuto;
import frc.robot.commands.autos.ThreePieceAuto;
import frc.robot.commands.presets.CubePreset;
import frc.robot.commands.presets.HighPlacementPreset;
import frc.robot.commands.presets.LowPreset;
import frc.robot.commands.presets.MidPlacementPreset;
import frc.robot.commands.presets.ShelfPreset;
import frc.robot.commands.presets.TippedConePreset;
import frc.robot.commands.presets.UprightConePreset;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.ArmRotation;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.FlipChecker;
import frc.robot.subsystems.GroundCubeIntake;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Peripherals;
import frc.robot.subsystems.Wrist;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends LoggedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  public Peripherals peripherals = new Peripherals();
  public Drive drive = new Drive(peripherals);
  private Logger logger = Logger.getInstance();
  private Lights lights = new Lights();
  private ArmExtension armExtension = new ArmExtension();
  private Wrist wrist = new Wrist();
  private Intake intake = new Intake(lights);
  private FlipChecker flipChecker = new FlipChecker(peripherals);
  private ArmRotation armRotation = new ArmRotation(armExtension, flipChecker);
  private GroundCubeIntake sideIntake = new GroundCubeIntake();

  private UsbCamera frontDriverCam;
  private UsbCamera backDriverCam;

  private boolean dpadClicked = false;
  
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
    drive.init();
    peripherals.init();
    lights.init();
    armExtension.init();
    armRotation.init();
    wrist.init();
    intake.init();
    sideIntake.init();

    logger.addDataReceiver(new WPILOGWriter("/home/lvuser/logs/mostRecent.wpilog"));
    logger.addDataReceiver(new NT4Publisher());
    logger.start();

    try {
      pathingFile = new File("/home/lvuser/deploy/2PiecePart1.json");
      FileReader scanner = new FileReader(pathingFile);
      pathRead = new JSONObject(new JSONTokener(scanner));
      pathJSON = (JSONArray) pathRead.get("sampled_points");
    } catch (Exception e) {
      System.out.println("ERROR WITH PATH FILE " + e);
    }
    
    this.auto = new ThreePieceAuto(drive, peripherals);
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
    flipChecker.periodic();
    lights.periodic();
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    peripherals.getNavxAngle();
    logger.recordOutput("Swerve Module States", drive.getModuleStates());
    logger.recordOutput("Swerve Module Setpoints", drive.getModuleSetpoints());
    logger.recordOutput("Angle Motor Velocity", drive.getAngleMotorVelocity());
    logger.recordOutput("Navx", Math.toRadians(peripherals.getNavxAngle()));
    logger.recordOutput("Odometry", drive.getOdometry());
    // logger.recordOutput("Y Value", drive.getFusedOdometryY());
    // logger.recordOutput("Theta Value", drive.getFusedOdometryTheta());
     // SmartDashboard.putNumber("Extension", armExtension.getExtensionPosition());
    // SmartDashboard.putBoolean("ARM LIMIT SWITCH", armExtension.getExtensionLimitSwitch());

    // System.out.println(Constants.wristOffsetMidMatch);

    if(OI.operatorController.getPOV() == 0) {
      if(!dpadClicked) {
        Constants.increaseWristOffset();
      }
      dpadClicked = true;
    }
    else if(OI.operatorController.getPOV() == 180) {
      if(!dpadClicked) {
        Constants.decreaseWristOffset();
      }
      dpadClicked = true;
    }
    else {
      dpadClicked = false;
    }

    if (armExtension.getExtensionLimitSwitch()) {
      armExtension.setExtensionEncoderPosition(0);
    }

    // System.out.println(sideIntake.getPosition());

    // SmartDashboard.putNumber("EXTENSION", armExtension.getExtensionPosition());
    // System.out.println("ARM: " + armRotation.getRotationPosition());
    // System.out.println("WRIST: " + wrist.getWristRotationPosition());

    //DO NOT COMMENT OUT!!!
    wrist.periodic();
    //DO NOT COMMENT OUT!!!
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

    // OI.buttonA.whileTrue(new MoveWheelToAngle(drive, 0.5));
    // OI.buttonB.whileTrue(new MoveWheelToAngle(drive, -0.5));
    OI.driverViewButton.whileTrue(new ZeroNavx(drive));
    armExtension.teleopInit();
    wrist.teleopInit();
    flipChecker.setTeleop();

    OI.driverX.whileHeld(new DriveAutoAligned(drive, peripherals));
    // OI.driverX.whileActiveContinuous(new DriveAutoAligned(drive, peripherals, lights));

    OI.driverRB.whileHeld(new RunGroundCubeIntake(sideIntake, 130, 0.51));
    OI.driverLB.whileHeld(new RunGroundCubeIntake(sideIntake, 0, -0.5));

    // OI.driverA.whileHeld(new SetArmExtensionPosition(lights, armExtension, armRotation, 18));
    // OI.driverY.whileHeld(new ExtendArm(armExtension, 3));

    // OI.driverB.whenPressed(new AprilTagBalance(drive, peripherals, lights, 2.25, true));
    // OI.driverA.whenPressed(new AprilTagBalance(drive, peripherals, lights, 2.25, false));
    // OI.driverA.whileHeld(new MoveToPieceForwards(drive, peripherals, lights));

    // // COMPETITION CONTROLS - DO NOT DELETE
    // // shelf intake position
    // OI.driverX.whileHeld(new DriveAutoAligned(drive, peripherals, lights));
    // OI.driverY.whileHeld(new AprilTagAlignment(drive, peripherals, lights));
    // OI.driverA.whenPressed(new MoveToPieceForwards(drive, peripherals, lights));

    // OI.driverY.whileActiveOnce(new AprilTagBalance(drive, peripherals, lights, 1.5, true));

    // // ramp intake position
    OI.operatorMenuButton.whileHeld(new ShelfPreset(armExtension, armRotation, flipChecker, wrist, lights, peripherals));

    // // placement position mid
    OI.operatorA.whileHeld(new MidPlacementPreset(armExtension, armRotation, flipChecker, wrist, lights, peripherals));

    // // placement position high
    OI.operatorY.whileHeld(new HighPlacementPreset(armExtension, armRotation, flipChecker, wrist, lights, peripherals));
    
    // // intake position for upright cone
    OI.operatorX.whileHeld(new UprightConePreset(armExtension, armRotation, flipChecker, wrist, lights));
   
    // // intake position for a tipped cone
    OI.operatorB.whileHeld(new TippedConePreset(armExtension, armRotation, flipChecker, wrist, lights));

    // // intake position for cube
    OI.operatorRB.whileHeld(new CubePreset(armExtension, armRotation, flipChecker, wrist, lights));

    OI.operatorLB.whileHeld(new LowPreset(armExtension, armRotation, peripherals, flipChecker, wrist, lights));

    // // drive rotationally aligned to 0 or 180
    // OI.driverX.whileHeld(new DriveAutoAligned(drive, peripherals, lights));

    // // operator change light color
    // OI.operatorViewButton.whenPressed(new SetLightMode(lights, "cube"));
    // OI.operatorMenuButton.whenPressed(new SetLightMode(lights, "cone"));
    // // COMPETITION CONTROLS - DO NOT DELETE

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    lights.periodic();
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
