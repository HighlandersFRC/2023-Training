// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator.Validity;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.ArmSubsystem;

public class ArmReverse extends CommandBase {
  /** Creates a new ArmReverse. */
  
  ArmSubsystem arm;
  public ArmReverse(ArmSubsystem arm) {
    this.arm = arm;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Reverse Started");
    arm.brake.set(Value.kReverse);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Rev Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(arm.armMaster.getClosedLoopError()) >= Constants.ARM_DEGREES_TO_TICKS(1)){
      return false;
    } else {
      return true;
    }
  }
}
