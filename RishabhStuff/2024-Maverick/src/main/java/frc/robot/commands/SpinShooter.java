// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Peripherals;
import frc.robot.subsystems.Shooter;
import frc.robot.tools.ShotAdjuster;

public class SpinShooter extends Command {
  /** Creates a new SpinShooter. */
  private Shooter shooter;
  private Peripherals peripherals;
  private double initialRPM;
  private double rpm;
  private int hasConsistentRPM = 0;
  private ShotAdjuster adjuster;
  private Boolean neverEnds = false;
  private double startTime;

  private Boolean useList = false;

  //private double[] rpmList = {1400, 1400, 1400, 1430, 1430, 1440, 1440, 1470, 1470, 1470, 1500, 1530, 1530, 1530, 1530, 1530, 1530, 1560, 1560};
  //private double[] distanceList = {58, 63, 67, 73, 79, 84, 88, 93, 98, 104, 109, 116, 121, 129, 136, 142, 147, 157, 167};

  public SpinShooter(Shooter shooter, Peripherals peripherals, double rpm, ShotAdjuster adjuster, Boolean useList, Boolean neverEnds) {
    this.shooter = shooter;
    this.initialRPM = rpm;
    this.adjuster = adjuster;
    this.useList = useList;
    this.peripherals = peripherals;
    this.neverEnds = neverEnds;
    addRequirements(shooter);
  }

  public SpinShooter(Shooter shooter, Peripherals peripherals, double rpm, ShotAdjuster adjuster, Boolean useList) {
    this.shooter = shooter;
    this.initialRPM = rpm;
    this.adjuster = adjuster;
    this.useList = useList;
    this.peripherals = peripherals;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    hasConsistentRPM = 0;
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    //System.out.println("RPM: " + shooter.getShooterRPM());
    SmartDashboard.putNumber("hasConsistentRPM", hasConsistentRPM);
    rpm = initialRPM + adjuster.getRPMAdjustment();
    shooter.setShooterRPM(rpm);
    if(Math.abs(shooter.getShooterRPM() - rpm) < 50) {
      hasConsistentRPM++;
    }
    else {
      hasConsistentRPM = 0;
    }
    SmartDashboard.putNumber("Shooter RPM", rpm);
    
  }

  @Override
  public void end(boolean interrupted) {
    // shooter.setShooterPercent(0);
  }

  @Override
  public boolean isFinished() {
    if(hasConsistentRPM > 5 || Timer.getFPGATimestamp() - startTime > 1 && !neverEnds) {
      // System.out.println("00000000000000000000000000000000000");
      return true;
    }
    return false;
  }
}
