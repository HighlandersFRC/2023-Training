// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.DriveVelocityMode;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class driveXFeet extends SequentialCommandGroup {
  /** Creates a new driveXFeet. */
  public driveXFeet(DriveSubsystem drive, double seconds, double feetPerSecond) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    if(feetPerSecond > 9) {
      feetPerSecond = 9;
    }
    if (feetPerSecond < -9) {
      feetPerSecond = -9;
    }
    DriveVelocityMode driveXFeet = new DriveVelocityMode(drive, feetPerSecond, false);
    addCommands(
      driveXFeet,
      new WaitCommand(seconds)
    );
  }
}
