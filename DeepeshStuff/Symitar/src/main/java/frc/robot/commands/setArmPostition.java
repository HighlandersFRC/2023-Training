// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.tools.PIDF;

public class setArmPostition extends CommandBase {
  /** Creates a new setArmPostition. */
  double radians;
  boolean correct;
  double currentTime;
  double onTargetTime;
  ArmSubsystem arm;
  double endAccuracy = Math.toRadians(1);
  double endDelay = 0.5;
  PIDF pid = new PIDF(0.15, 0.0, 0.0, 0.00001,  20);
  public setArmPostition(ArmSubsystem arm, double degrees) {
    // Use addRequirements() here to declare subsystem dependencies.
    pid.setMinOutput(-1);;
    pid.setMaxOutput(1);
    radians = Math.toRadians(degrees);
    this.arm = arm;
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
        pid.setSetPoint(radians);
        System.out.println("Arm Started");
        correct = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    arm.stopBreak();
    currentTime = Timer.getFPGATimestamp();
    pid.updatePID(Constants.ARM_TICKS_TO_DEGREES(Math.toRadians(arm.armMaster.getSelectedSensorPosition())));
    SmartDashboard.putNumber("Arm Error", Math.toDegrees(pid.getError()));
    SmartDashboard.putNumber("Arm Power", pid.getResult());
    arm.setArmPercent(-pid.getResult());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Arm ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      if(Math.abs(pid.getError())<=endAccuracy){
        if(!correct){
          onTargetTime = currentTime;
          correct = true;
        }
        if(currentTime-onTargetTime>=endDelay){
          return true;
        }
        return false;
      }else{
        return false;
      }
  }
}
