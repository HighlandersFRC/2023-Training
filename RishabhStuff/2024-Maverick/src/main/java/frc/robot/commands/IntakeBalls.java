// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.MagIntake;
import frc.robot.subsystems.Lights.LEDMode;

public class IntakeBalls extends Command {
  private MagIntake magIntake;
  private Lights lights;

  public IntakeBalls(MagIntake magIntake, Lights lights) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.magIntake = magIntake;
    this.lights = lights;
    addRequirements(this.magIntake, this.lights);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // magIntake.rotateBackMag(270.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Intaking");
    //lights.setMode(LEDMode.YELLOW);
    magIntake.setIntakeDown();
    //magIntake.setFrontMagRPM(-1500);
    // magIntake.setBackMagazine(0.5);
    
    //SmartDashboard.putNumber("front mag rpm", magIntake.getFrontMagRPM());
    magIntake.setIntakePercent(0.7);
    magIntake.moveMagazine();
    if (!magIntake.getLowerBackBeamBreak()) {
      lights.setMode(LEDMode.YELLOW);
    } else {
      lights.setMode(LEDMode.BLUE);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    magIntake.setIntakeUp();
    magIntake.setIntakePercent(0.0);
    magIntake.stopMagazine();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
