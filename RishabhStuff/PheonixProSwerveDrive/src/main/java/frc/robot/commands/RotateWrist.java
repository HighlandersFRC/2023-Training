package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Constants.PRESET;
import frc.robot.subsystems.FlipChecker;
import frc.robot.subsystems.Peripherals;
import frc.robot.subsystems.Wrist;
import frc.robot.Tools.PID;

public class RotateWrist extends CommandBase {
  /** Creates a new RunIntake. */
  private Wrist wrist;
  private double angle = 180;
  private FlipChecker flipChecker;
  private Peripherals peripherals;
  private PRESET preset;
  private boolean useNavx = false;

  public RotateWrist(Wrist wrist, FlipChecker flipChecker, double angle) {
    this.wrist = wrist;
    this.angle = angle;
    this.flipChecker = flipChecker;
    this.useNavx = false;
    addRequirements(this.wrist);
  }

  public RotateWrist(Wrist wrist, FlipChecker flipChecker, Peripherals peripherals, PRESET preset){
    this.wrist = wrist;
    this.flipChecker = flipChecker;
    this.peripherals = peripherals;
    this.preset = preset;
    this.useNavx = true;
    addRequirements(this.wrist);
  }

  @Override
  public void initialize() {
    // System.out.println("Wrist Execute");
    double setAngle = this.angle;
    if (useNavx){
      double navxAngle = peripherals.getNavxAngle() % 360;
      while (navxAngle < 0) {
        navxAngle += 360;
      }
      if (navxAngle >= 90 && navxAngle <= 270){
        // Case where robot is facing towards driver
        switch(preset) {
          case HIGH_PLACEMENT:
            this.angle = Constants.HIGH_PLACEMENT_FRONTSIDE_WRIST_ROTATION;
            break;
          case MID_PLACEMENT:
            this.angle = Constants.MID_PLACEMENT_FRONTSIDE_WRIST_ROTATION;
            break;
          case LOW_PLACEMENT:
            this.angle = Constants.LOW_PLACEMENT_FRONTSIDE_WRIST_ROTATION;
            break;
          case UPRIGHT_CONE:
            this.angle = Constants.UPRIGHT_CONE_FRONTSIDE_WRIST_ROTATION;
            break;
          case TIPPED_CONE:
            this.angle = Constants.TIPPED_CONE_FRONTSIDE_WRIST_ROTATION;
            break;
          case CUBE:
            this.angle = Constants.CUBE_FRONTSIDE_WRIST_ROTATION;
            break;
          case SHELF:
            this.angle = Constants.SHELF_BACKSIDE_WRIST_ROTATION;
            break;
          default:
            this.angle = 180;
        }
      } else {
        // Case where robot is facing away from driver
        switch(preset) {
          case HIGH_PLACEMENT:
            this.angle = Constants.HIGH_PLACEMENT_BACKSIDE_WRIST_ROTATION;
            break;
          case MID_PLACEMENT:
            this.angle = Constants.MID_PLACEMENT_BACKSIDE_WRIST_ROTATION;
            break;
          case LOW_PLACEMENT:
            this.angle = Constants.LOW_PLACEMENT_BACKSIDE_WRIST_ROTATION;
            break;
          case UPRIGHT_CONE:
            this.angle = Constants.UPRIGHT_CONE_BACKSIDE_WRIST_ROTATION;
            break;
          case TIPPED_CONE:
            this.angle = Constants.TIPPED_CONE_BACKSIDE_WRIST_ROTATION;
            break;
          case CUBE:
            this.angle = Constants.CUBE_BACKSIDE_WRIST_ROTATION;
            break;
          case SHELF:
            this.angle = Constants.SHELF_FRONTSIDE_WRIST_ROTATION;
            break;
          default:
            this.angle = 180;
        }
      }
    } else {
      if (angle == Constants.UPRIGHT_CONE_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.UPRIGHT_CONE_BACKSIDE_WRIST_ROTATION;
        }
      } else if (angle == Constants.TIPPED_CONE_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.TIPPED_CONE_BACKSIDE_WRIST_ROTATION;
        }
      } else if (angle == Constants.HIGH_PLACEMENT_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.HIGH_PLACEMENT_BACKSIDE_WRIST_ROTATION;
        }
      } else if (angle == Constants.MID_PLACEMENT_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.MID_PLACEMENT_BACKSIDE_WRIST_ROTATION;
        }
      } else if (angle == Constants.CUBE_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.CUBE_BACKSIDE_WRIST_ROTATION;
        }
      } else if (angle == Constants.SHELF_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.SHELF_BACKSIDE_WRIST_ROTATION;
        }
      }
    }
    if (Math.abs(OI.operatorController.getRawAxis(5)) > 0.1) {
      this.wrist.setRotationMotorPercent(OI.operatorController.getRawAxis(5)/4);
    } else {
      wrist.setRotationPosition(setAngle + Constants.wristOffsetMidMatch);
    }
  }

  @Override
  public void execute() { 
    // System.out.println("Wrist Execute");
    double setAngle = this.angle;
    if (useNavx){
      double navxAngle = peripherals.getNavxAngle() % 360;
      while (navxAngle < 0) {
        navxAngle += 360;
      }
      if (navxAngle >= 90 && navxAngle <= 270){
        // Case where robot is facing towards driver
        switch(preset) {
          case HIGH_PLACEMENT:
            this.angle = Constants.HIGH_PLACEMENT_FRONTSIDE_WRIST_ROTATION;
            break;
          case MID_PLACEMENT:
            this.angle = Constants.MID_PLACEMENT_FRONTSIDE_WRIST_ROTATION;
            break;
          case LOW_PLACEMENT:
            this.angle = Constants.LOW_PLACEMENT_FRONTSIDE_WRIST_ROTATION;
            break;
          case UPRIGHT_CONE:
            this.angle = Constants.UPRIGHT_CONE_FRONTSIDE_WRIST_ROTATION;
            break;
          case TIPPED_CONE:
            this.angle = Constants.TIPPED_CONE_FRONTSIDE_WRIST_ROTATION;
            break;
          case CUBE:
            this.angle = Constants.CUBE_FRONTSIDE_WRIST_ROTATION;
            break;
          case SHELF:
            this.angle = Constants.SHELF_BACKSIDE_WRIST_ROTATION;
            break;
          default:
            this.angle = 180;
        }
      } else {
        // Case where robot is facing away from driver
        switch(preset) {
          case HIGH_PLACEMENT:
            this.angle = Constants.HIGH_PLACEMENT_BACKSIDE_WRIST_ROTATION;
            break;
          case MID_PLACEMENT:
            this.angle = Constants.MID_PLACEMENT_BACKSIDE_WRIST_ROTATION;
            break;
          case LOW_PLACEMENT:
            this.angle = Constants.LOW_PLACEMENT_BACKSIDE_WRIST_ROTATION;
            break;
          case UPRIGHT_CONE:
            this.angle = Constants.UPRIGHT_CONE_BACKSIDE_WRIST_ROTATION;
            break;
          case TIPPED_CONE:
            this.angle = Constants.TIPPED_CONE_BACKSIDE_WRIST_ROTATION;
            break;
          case CUBE:
            this.angle = Constants.CUBE_BACKSIDE_WRIST_ROTATION;
            break;
          case SHELF:
            this.angle = Constants.SHELF_FRONTSIDE_WRIST_ROTATION;
            break;
          default:
            this.angle = 180;
        }
      }
    } else {
      if (angle == Constants.UPRIGHT_CONE_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.UPRIGHT_CONE_BACKSIDE_WRIST_ROTATION;
        }
      } else if (angle == Constants.TIPPED_CONE_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.TIPPED_CONE_BACKSIDE_WRIST_ROTATION;
        }
      } else if (angle == Constants.HIGH_PLACEMENT_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.HIGH_PLACEMENT_BACKSIDE_WRIST_ROTATION;
        }
      } else if (angle == Constants.MID_PLACEMENT_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.MID_PLACEMENT_BACKSIDE_WRIST_ROTATION;
        }
      } else if (angle == Constants.CUBE_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.CUBE_BACKSIDE_WRIST_ROTATION;
        }
      } else if (angle == Constants.SHELF_FRONTSIDE_WRIST_ROTATION) {
        if (flipChecker.getFlip()) {
          setAngle = Constants.SHELF_BACKSIDE_WRIST_ROTATION;
        }
      }
    }
    if (Math.abs(OI.operatorController.getRawAxis(5)) > 0.1) {
      this.wrist.setRotationMotorPercent(OI.operatorController.getRawAxis(5)/4);
    } else {
      wrist.setRotationPosition(setAngle + Constants.wristOffsetMidMatch);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // System.out.println("Wrist End, Interrupted: " + interrupted);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // System.out.println("Wrist Error: " + Math.abs(wrist.getWristRotationPosition() - angle));
    if (Math.abs(wrist.getWristRotationPosition() - angle) < 2) {
      return true;
    }
    return false;
  }
}
