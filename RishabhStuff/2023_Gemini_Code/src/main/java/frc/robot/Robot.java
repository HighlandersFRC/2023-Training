package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.MagIntakeOut;
import frc.robot.commands.RetractHood;
import frc.robot.commands.SetLightColor;
import frc.robot.commands.ShootingSequence;
import frc.robot.commands.SpinMag;
import frc.robot.commands.StopShooting;
import frc.robot.commands.velocityDrive;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ExtendHood;
import frc.robot.commands.MagIntakeIn;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.MagIntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.tools.OI;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private DriveSubsystem drive = new DriveSubsystem();
  private MagIntakeSubsystem magintake = new MagIntakeSubsystem();
  private ShooterSubsystem shooter = new ShooterSubsystem();
  private ArcadeDrive arcadeDrive = new ArcadeDrive(drive);
  private velocityDrive velocityDrive = new velocityDrive(drive);
  private HoodSubsystem hood = new HoodSubsystem();
  private Lights lights = new Lights();

  @Override
  public void robotInit() {

    m_robotContainer = new RobotContainer();
    drive.init();
    magintake.init();
    shooter.init();
    hood.init();
    lights.init();
  }


  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    magintake.beamBreaksOnDashboard();
    SmartDashboard.putNumber("Ball Count", magintake.ballCount);
    SmartDashboard.putNumber("Left Movement Speed", Constants.TicksPer100MS_To_MPS(drive.leftFront.getIntegratedSensorVelocity()));
    SmartDashboard.putNumber("Right Movement Speed", Constants.TicksPer100MS_To_MPS(drive.rightFront.getIntegratedSensorVelocity()));
    drive.outputDriveMode();
    SmartDashboard.putNumber("Shooter Speed", Constants.TicksPer100mstoRPM(shooter.encoderMaster.getIntegratedSensorVelocity()));
    hood.outputSwitches();
    hood.encoderPosition();
    hood.whenLowerLimitSwitchPressed();
    hood.whenUpperLimitSwitchPressed();
  }

  @Override
  public void disabledInit() {
    drive.setDrivePercents(0, 0);
  }

  @Override
  public void disabledPeriodic() {
  }

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
    OI.lt.whileTrue(new MagIntakeOut(magintake));
    OI.rt.whileTrue(new MagIntakeIn(magintake));
    OI.lt.whileTrue(new SetLightColor(lights, -0.15));
    OI.rBumper.toggleOnTrue(velocityDrive);
    OI.lBumper.whileTrue(new SpinMag(magintake, 0.2));
    OI.buttonA.whileTrue(new ShootingSequence(magintake, shooter, hood, 1500, -0));
    OI.buttonX.whileTrue(new ShootingSequence(magintake, shooter, hood, 2000, -10));
    OI.buttonY.whileTrue(new ShootingSequence(magintake, shooter, hood, 2500, -5));
    OI.buttonB.whileTrue(new ShootingSequence(magintake, shooter, hood, 2000, -5));
    OI.buttonA.onFalse(new StopShooting(magintake, shooter, hood));
    OI.buttonX.onFalse(new StopShooting(magintake, shooter, hood));
    OI.buttonY.onFalse(new StopShooting(magintake, shooter, hood));
    OI.buttonB.onFalse(new StopShooting(magintake, shooter, hood));
    OI.buttonA.whileTrue(new SetLightColor(lights, 0.77));
    OI.buttonX.whileTrue(new SetLightColor(lights, 0.77));
    OI.buttonY.whileTrue(new SetLightColor(lights, 0.77));
    OI.buttonB.whileTrue(new SetLightColor(lights, 0.77));
    OI.menuButton.whileTrue(new ExtendHood(hood, 0.2));
    OI.viewButton.whileTrue(new RetractHood(hood, -0.2));
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
