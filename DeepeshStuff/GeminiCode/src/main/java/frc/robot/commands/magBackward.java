// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.MagIntakeSubsystem;

public class magBackward extends CommandBase {
  MagIntakeSubsystem magintake;
  /** Creates a new magBackward. */
  public magBackward(MagIntakeSubsystem magintake) {
    this.magintake = magintake;
    addRequirements(magintake); 
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    magintake.outake();
    magintake.retractPistons();
    magintake.setPercent(-0.3);
    System.out.println("magBackward ran");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("magBackward ended");
    magintake.setPercent(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (OI.driverController.getLeftTriggerAxis() > 0.2){
      return false;
    }else{
      return true;
    }
  }
}
