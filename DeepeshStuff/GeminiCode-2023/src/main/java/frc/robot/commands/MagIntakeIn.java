// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;



import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.MagIntakeSubsystem;

public class MagIntakeIn extends CommandBase {
  /** Creates a new magDefault. */
  int plcHldr;
  boolean ballAt1;
  double ball1time;
  boolean previous1;
  
  
  double ball2time;
  boolean ballPassed2;
  
  boolean ballAt2;
  boolean previous3;
  double ball3time;
  
  boolean plcHldr6;
  boolean ballAt3;
  boolean previous4;
  
  double ball4time;
  boolean plcHldr7;
  boolean ran;
  boolean previous5;
  double ball5time;
  
  double currentTime;
  MagIntakeSubsystem magintake;
  boolean plcHldr1;
  boolean plcHldr2;
  boolean plcHldr3;
  boolean plcHldr4;
  boolean plcHldr5;
  double beam3delay;
  double beam3timeHolder;
  Double delay;
  boolean plcHldr8;
  boolean plcHldr9;
  double beam1timeHolder;
  double beam1delay;
  public MagIntakeIn(MagIntakeSubsystem magintake) {
    this.magintake = magintake;
    ballPassed2 = false;
    ran = false;
    plcHldr=0;
    plcHldr1=false;
    plcHldr2=false;
    plcHldr3=false;
    plcHldr4=false;
    plcHldr5=false;
    plcHldr6=false;
    plcHldr7=false;
    plcHldr8=false;
    plcHldr9=false;
    delay = 0.8;
    addRequirements(magintake); 
  }
  
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    magintake.setMagPercent(0);
    magintake.intake();
    magintake.extendPistons();
    beam3delay=0.5;
    beam3timeHolder=100000;
    beam1delay=0.5;
    beam1timeHolder=100000;
    plcHldr7=false;
    plcHldr9=false;
    plcHldr5=false;
    plcHldr1 = magintake.plcHldr1;
    plcHldr2 = magintake.plcHldr2;
    plcHldr3 = magintake.plcHldr3;
    plcHldr4 = magintake.plcHldr4;
    plcHldr5 = magintake.plcHldr5;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    magintake.plcHldr1 = plcHldr1;
    magintake.plcHldr2 = plcHldr2;
    magintake.plcHldr3 = plcHldr3;
    magintake.plcHldr4 = plcHldr4;
    magintake.plcHldr5 = plcHldr5;
    SmartDashboard.putNumber("ball1time", currentTime-ball1time);
    SmartDashboard.putNumber("BallCount", magintake.ballCount);
    currentTime = Timer.getFPGATimestamp();
    plcHldr8= !magintake.beamBreak1.get();
    ballAt2 = !magintake.beamBreak2.get();
    plcHldr6= !magintake.beamBreak3.get();
    
    if(plcHldr6){
      if(!plcHldr7){
        beam3timeHolder=currentTime;
        plcHldr7=true;
      }
      if(currentTime-beam3timeHolder>=beam3delay){
        ballAt3=true;
      }
    }else{
      ballAt3=false;
      plcHldr7=false;
    }
    if(plcHldr8){
      if(!plcHldr9){
        beam1timeHolder=currentTime;
        plcHldr9=true;
      }
      if(currentTime-beam1timeHolder>=beam1delay){
        ballAt1=true;
      }
    }else{
      ballAt1=false;
      plcHldr9=false;
    }
    
    if (!ballAt1&&previous1){
      magintake.ballArrives();;
    }
    
    
    if (ballAt2){
      ballPassed2 = true;
    }

    if (!ballAt3){
      if (!ran){
        magintake.setMagPercent(0.0);
        ran = true;
      }
      if(magintake.ballCount ==1&&!ballAt1|| magintake.ballCount == 0&&ballAt1){
          if(!plcHldr1&&!ballAt1){
            ball1time = Timer.getFPGATimestamp();
            plcHldr1 = true;
          }
          if(ballAt1){
            magintake.setMagPercent(0.4);
          }else if(currentTime-ball1time<=delay){
            magintake.setMagPercent(0.4);
          }else{
            magintake.setMagPercent(0.0);
          }
      } else if(magintake.ballCount ==2&&!ballAt1|| magintake.ballCount == 1&&ballAt1){
        if(!plcHldr2&&!ballAt1){
          ball2time = Timer.getFPGATimestamp();
          plcHldr2 = true;
        }
        if(ballAt1){
          magintake.setMagPercent(0.4);
        }else if(currentTime-ball2time<=delay){
          magintake.setMagPercent(0.4);
        }else{
          magintake.setMagPercent(0.0);
        }
      } else if(magintake.ballCount ==3&&!ballAt1|| magintake.ballCount == 2&&ballAt1){
        if(!plcHldr3&&!ballAt1){
          ball3time = Timer.getFPGATimestamp();
          plcHldr3 = true;
        }
        if(ballAt1){
          magintake.setMagPercent(0.4);
        }else if(currentTime-ball3time<=delay){
          magintake.setMagPercent(0.4);
        }else{
          magintake.setMagPercent(0.0);
        }
      } else if(magintake.ballCount ==4&&!ballAt1|| magintake.ballCount == 3&&ballAt1){
        if(!plcHldr4&&!ballAt1){
          ball4time = Timer.getFPGATimestamp();
          plcHldr4 = true;
        }
        if(ballAt1){
          magintake.setMagPercent(0.4);
        }else if(currentTime-ball4time<=delay){
          magintake.setMagPercent(0.4);
        }else{
          magintake.setMagPercent(0.0);
        }
      } else if(magintake.ballCount ==5&&!ballAt1|| magintake.ballCount == 4&&ballAt1){
        if (ballAt1){
          if(!plcHldr5){
            ball5time = Timer.getFPGATimestamp();
            plcHldr5 = true;
          }
        }
          if (currentTime-ball5time >= 0.4){
            magintake.setMagPercent(0.0);
          }else {
            magintake.setMagPercent(0.4);
          }
        
      }
    //}
    } else {
    magintake.setMagPercent(0.0);
    }
    previous1 = ballAt1;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    magintake.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (OI.driverController.getRightBumperReleased()){
      return true;
    }else{
      return false;
    }  
  }
}
