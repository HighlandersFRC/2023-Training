package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.tools.Vector;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Peripherals;


public class AutonomousBalance extends Command {

  private Peripherals peripherals;
  private Drive drive;
  private boolean end;
  Vector forwardVector = new Vector(0.3, 0);
  Vector backwardVector = new Vector(-0.3, 0);
  Vector stopVector = new Vector(0.0, 0.0);

  Vector balanceVector;
  public AutonomousBalance(Drive drive, Peripherals peripherals) {
    this.drive = drive;
    this.peripherals = peripherals;
    addRequirements(this.drive);
  }

  @Override
  public void initialize() {
    end = false;
    System.out.println("Init");
  }

  @Override
  public void execute() {    
    if (peripherals.getNavxPitch()>5){
      drive.autoDrive(forwardVector, 0);
    } else if (peripherals.getNavxPitch()<-5){
      drive.autoDrive(backwardVector, 0);
    } else {
      drive.autoDrive(stopVector, 0);
      end = true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    // drive.autoRobotCentricDrive(stopVector, 0);
    drive.lockWheels();
    System.out.println("End");
  }

  @Override
  public boolean isFinished() {
    return end;
  }
}