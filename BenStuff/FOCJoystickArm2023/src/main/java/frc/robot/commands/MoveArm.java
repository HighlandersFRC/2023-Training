package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Arm.armPosition;

public class MoveArm extends CommandBase {
Arm arm;
  public MoveArm(Arm arm) {
    this.arm = arm;
    addRequirements(arm);
  }

  @Override
  public void initialize() {
    // if (OI.getLeftOpY() > 0.1) {
    //  if(!arm.getNavXForward()) {
    //     arm.setArm(armPosition.FORWARD);
    //   } else {
    //    arm.setArm(armPosition.BACKWARD);
    //   }
    // } else if (OI.getLeftOpY() < -0.1) {
    //   if(arm.getNavXForward()) {
    //     arm.setArm(armPosition.FORWARD);
    //   } else {
    //    arm.setArm(armPosition.BACKWARD);
    //   }
    // } else {
    //   arm.setArm(armPosition.UP);
    // }
  }

  @Override
  public void execute() {
    // if (OI.getLeftOpY() > 0) {
    //   if(!arm.getNavXForward()) {
    //      arm.setArm(armPosition.FORWARD);
    //    } else {
    //     arm.setArm(armPosition.BACKWARD);
    //    }
    //  } else if (OI.getLeftOpY() < -0) {
    //    if(arm.getNavXForward()) {
    //      arm.setArm(armPosition.FORWARD);
    //    } else {
    //     arm.setArm(armPosition.BACKWARD);
    //    }
    //  } else {
    //    arm.setArm(armPosition.UP);
    //  }
  }

  @Override
  public void end(boolean interrupted) {
    arm.setArm(armPosition.UP);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
