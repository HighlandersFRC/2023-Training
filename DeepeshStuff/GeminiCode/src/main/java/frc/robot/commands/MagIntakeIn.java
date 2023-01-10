// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.net.ssl.ExtendedSSLSession;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.MagIntakeSubsystem;

public class MagIntakeIn extends CommandBase {
  /** Creates a new magDefault. */
  int ballCount;
  int plcHldr;
  boolean ballAt1;
  double ball1time;
  boolean previous1;
  boolean plcHldr1;
  int ballsPast2;
  double ball2time;
  boolean ballPassed2;
  boolean previous2;
  boolean plcHldr2;
  boolean ballAt2;
  boolean previous3;
  double ball3time;
  boolean plcHldr3;
  int ballsPast3;
  boolean plcHldr6;
  boolean ballAt3;
  boolean previous4;
  boolean plcHldr4;
  double ball4time;
  boolean plcHldr7;
  boolean ran;
  boolean previous5;
  double ball5time;
  boolean plcHldr5;
  double currentTime;
  MagIntakeSubsystem magintake;
  double beam3delay;
  double beam3timeHolder;
  Double delay;
  boolean plcHldr8;
  boolean plcHldr9;
  double beam1timeHolder;
  double beam1delay;
  public MagIntakeIn(MagIntakeSubsystem magintake) {
    this.magintake = magintake;
    ballCount = 0;
    ballsPast3 = 0;
    ballPassed2 = false;
    ballsPast2 = 0;
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
    delay = 0.2;
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
    beam1delay=0.2;
    beam1timeHolder=100000;
    plcHldr7=false;
    plcHldr9=false;
    plcHldr5=false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("ball1time", currentTime-ball1time);
    SmartDashboard.putNumber("BallCount", ballCount);
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
      ballCount++;
    }
    
    if (!ballAt2&&previous2){
      ballsPast2++;
    }
    if (!ballAt3&&previous3){
      ballsPast3++;
    }
    if (ballAt2){
      ballPassed2 = true;
    }

    if (!ballAt3){
    // if (ballPassed2){
    //   if(ballCount == 1||(ballAt1&&ballCount==0)){
    //     if (!ballAt1){
    //       ball1Loops++;
    //       if (ball1Loops>=3){
    //         magintake.setNEOPercent(0.0);
    //       }
    //     } else {
    //       magintake.setNEOPercent(0.6);
    //     }
    //   } else if(ballCount == 2||(ballAt1&&ballCount==1)){
    //     if (!ballAt1){
    //       ball2Loops++;
    //       if (ball2Loops>=3){
    //         magintake.setNEOPercent(0.0);
    //       }
    //     } else {
    //       magintake.setNEOPercent(0.6);
    //     }
    //   } else if(ballCount == 3||(ballAt1&&ballCount==2)){
    //     if (!ballAt1){
    //       ball3Loops++;
    //       if (ball3Loops>=3){
    //         magintake.setNEOPercent(0.0);
    //       }
    //     } else {
    //       magintake.setNEOPercent(0.6);
    //     }
    //   } else if(ballCount == 4||(ballAt1&&ballCount==3)){
    //     if (!ballAt1){
    //       ball4Loops++;
    //       if (ball4Loops>=3){
    //         magintake.setNEOPercent(0.0);
    //       }
    //     } else {
    //       magintake.setNEOPercent(0.6);
    //     }
    //   } else if(ballCount == 3||(ballAt1&&ballCount==4)){
    //     if (!ballAt1){
    //       ball5Loops++;
    //       if (ball5Loops>=5){
    //         magintake.setNEOPercent(0.0);
    //       }
    //     } else {
    //       magintake.setNEOPercent(0.6);
    //     }
    //   }
    // }else{
      if (!ran){
        magintake.setMagPercent(0.0);
        ran = true;
      }
      if(ballCount ==1&&!ballAt1|| ballCount == 0&&ballAt1){
          if(!plcHldr1&&!ballAt1){
            ball1time = Timer.getFPGATimestamp();
            plcHldr1 = true;
          }
          if(ballAt1){
            magintake.setMagPercent(0.2);
          }else if(currentTime-ball1time<=delay){
            magintake.setMagPercent(0.2);
          }else{
            magintake.setMagPercent(0.0);
          }
      } else if(ballCount ==2&&!ballAt1|| ballCount == 1&&ballAt1){
        if(!plcHldr2&&!ballAt1){
          ball2time = Timer.getFPGATimestamp();
          plcHldr2 = true;
        }
        if(ballAt1){
          magintake.setMagPercent(0.2);
        }else if(currentTime-ball1time<=delay){
          magintake.setMagPercent(0.2);
        }else{
          magintake.setMagPercent(0.0);
        }
      } else if(ballCount ==3&&!ballAt1|| ballCount == 2&&ballAt1){
        if(!plcHldr3&&!ballAt1){
          ball3time = Timer.getFPGATimestamp();
          plcHldr3 = true;
        }
        if(ballAt1){
          magintake.setMagPercent(0.2);
        }else if(currentTime-ball1time<=delay){
          magintake.setMagPercent(0.2);
        }else{
          magintake.setMagPercent(0.0);
        }
      } else if(ballCount ==4&&!ballAt1|| ballCount == 3&&ballAt1){
        if(!plcHldr4&&!ballAt1){
          ball4time = Timer.getFPGATimestamp();
          plcHldr4 = true;
        }
        if(ballAt1){
          magintake.setMagPercent(0.2);
        }else if(currentTime-ball1time<=delay){
          magintake.setMagPercent(0.2);
        }else{
          magintake.setMagPercent(0.0);
        }
      } else if(ballCount ==5&&!ballAt1|| ballCount == 4&&ballAt1){
        if (ballAt1){
          if(!plcHldr5){
            ball5time = Timer.getFPGATimestamp();
            plcHldr5 = true;
          }
        }
          if (currentTime-ball5time >= 0.5){
            magintake.setMagPercent(0.0);
          }else {
            magintake.setMagPercent(0.6);
          }
        
      }
    //}
    } else {
    magintake.setMagPercent(0.0);
    }
    previous1 = ballAt1;
    previous2 = ballAt2;
    previous3 = ballAt3;
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
