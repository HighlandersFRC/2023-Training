// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.MagIntakeSubsystem;

public class magDefault extends CommandBase {
  /** Creates a new magDefault. */
  int ballCount;
  boolean ballAt1;
  boolean ballReached1;
  boolean ballReached2;
  boolean ballReached3;
  int ballsPast2;
  int ballsPast3;
  boolean ballAt3;
  boolean ballAt2;
  MagIntakeSubsystem magintake;
  public magDefault(MagIntakeSubsystem magintake) {
    this.magintake = magintake;
    addRequirements(magintake); 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ballCount = 0;
    ballsPast3 = 0;
    ballsPast2 = 0;
    ballReached1 = false;
    ballReached2 = false;
    ballReached3 = false;
    magintake.setPercent(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("BallCount", ballCount);
    ballAt1 = !magintake.beamBreak1.get();
    ballAt2 = !magintake.beamBreak2.get();
    ballAt3 = !magintake.beamBreak3.get();
    if (ballAt1||ballReached1){
      if (magintake.beamBreak1.get()){
        ballCount++;
        ballReached1 = false;
      }
      ballReached1 = true;
    }
    if (ballAt2||ballReached2){
      if (magintake.beamBreak2.get()){
        ballsPast2++;
        ballReached2 = false;
      }
      ballReached2 = true;
    }
    if (ballAt3||ballReached3){
      if (magintake.beamBreak3.get()){
        ballsPast3++;
        ballReached3 = false;
      }
      ballReached3 = true;
    }
    if(ballCount == 1||(ballAt1&&ballCount==0)){
      if (ballAt1){
        magintake.neo1.set(0.1);
        magintake.neo2.set(0.1);
      }
    } else if(ballCount == 2||(ballAt1&&ballCount==1)){
      if (ballAt1){
        magintake.neo1.set(0.1);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (OI.driverController.getRightTriggerAxis() > 0.2){
      return false;
    }else{
      return true;
    }  
  }
}
