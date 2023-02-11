// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.MagIntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootingSequence extends SequentialCommandGroup {
  MagIntakeSubsystem magintake;
  ShooterSubsystem shooter;
  HoodSubsystem hood;
  double rpm;
  double position;
  /** Creates a new ShootingSequence. */
  public ShootingSequence(MagIntakeSubsystem magintake, ShooterSubsystem shooter, HoodSubsystem hood, double rpm, double position) {
    this.magintake = magintake;
    this.shooter = shooter;
    this.hood = hood;
    this.rpm = rpm;
    this.position = position;
    addRequirements(magintake);
    addRequirements(shooter);
    addRequirements(hood);
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelRaceGroup(
        new SpinFlywheel(shooter, rpm),
        new ExtendHood(hood, position),
        new WaitCommand(0.6)
      ),
      new ShootMag(magintake));
  }
}
