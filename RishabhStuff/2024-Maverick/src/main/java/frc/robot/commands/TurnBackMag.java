// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MagIntake;

public class TurnBackMag extends Command {

  private MagIntake magIntake;
  private double endPosition;
  private double startTime;
  private double angle;
  private Boolean isQueue = false;
  /** Creates a new TurnBackMag. */
  public TurnBackMag(MagIntake magIntake, double angle, Boolean queuing) {
    this.magIntake = magIntake;
    this.angle = angle;
    isQueue = queuing;
    addRequirements(magIntake);
  }

  public TurnBackMag(MagIntake magIntake, double angle) {
    this.magIntake = magIntake;
    this.angle = angle;
    addRequirements(magIntake);
  }

  @Override
  public void initialize() {
    endPosition = magIntake.rotateBackMag(angle);
    if(isQueue) {
      magIntake.setFrontMagazine(0.5);
    }
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    if (Math.abs(magIntake.getBackMagPosition() - endPosition) < 100 || Timer.getFPGATimestamp() - startTime > 1) {
      magIntake.setFrontMagazine(0.0);
      return true;
    } else {
      return false;
    }
  }
}
