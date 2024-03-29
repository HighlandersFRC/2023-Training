// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Peripherals;
import frc.robot.tools.ShotAdjuster;

public class SetHoodPosition extends Command {
  /** Creates a new SetHoodPosition. */
  private Hood hood;
  private Peripherals peripherals;
  private double initialPosition;
  private double position;
  private ShotAdjuster adjuster;
  private Boolean useList = false;
  private Boolean neverEnds = false;

  public SetHoodPosition(Hood hood, Peripherals peripherals, double wantedPosition, ShotAdjuster adjuster, Boolean useList, Boolean neverEnds) {
    this.hood = hood;
    this.peripherals = peripherals;
    this.initialPosition = wantedPosition;
    this.adjuster = adjuster;
    this.useList = useList;
    this.neverEnds = neverEnds;
    addRequirements(hood);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public SetHoodPosition(Hood hood, Peripherals peripherals, double wantedPosition, ShotAdjuster adjuster, Boolean useList) {
    this.hood = hood;
    this.peripherals = peripherals;
    this.initialPosition = wantedPosition;
    this.adjuster = adjuster;
    this.useList = useList;
    addRequirements(hood);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    position = initialPosition + adjuster.getHoodAdjustment();
    hood.setHoodPosition(position);
    SmartDashboard.putNumber("WANTED HOOD", position);
    // hood.setHoodPercent(position);
    // hood.setHoodPercent(-0.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(hood.getHoodPosition() - (position)) < 0.5 && !neverEnds){
      // System.out.println("`````````````````````````");
      return true;
    }
    return false;
  }
}
