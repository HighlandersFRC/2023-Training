package frc.robot.commands.Defaults;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.tools.OI;

public class DriveDefault extends CommandBase {
  
  private DriveSubsystem drive;
  public DriveDefault(DriveSubsystem drive) {
    this.drive = drive;
    addRequirements(drive);
    
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
    drive.arcadeDrive(OI.getDriverLeftY(), OI.getDriverRightX(), OI.getDriverRightTrigger(), OI.getDriverLeftTrigger());
  }

  
  @Override
  public void end(boolean interrupted) {}

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
