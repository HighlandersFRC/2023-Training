// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.MagIntakeSubsystem;

public class MagIntakeOut extends CommandBase {
  MagIntakeSubsystem magintake;
  boolean beam1;
  boolean beam2;
  boolean beam3;
  boolean plcHldr;
  /** Creates a new magBackward. */
  public MagIntakeOut(MagIntakeSubsystem magintake) {
    this.magintake = magintake;
    addRequirements(magintake); 
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    magintake.retractPistons();
    magintake.setMagPercent(-0.3);
    System.out.println("magBackward ran");
    plcHldr = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    beam1 = !magintake.beamBreak1.get();
    if (!beam1&&plcHldr){
      magintake.ballLeaves();
    }
    plcHldr = beam1;
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("magBackward ended");
    magintake.setMagPercent(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (OI.driverController.getLeftBumperReleased()){
      return true;
    }else{
      return false;
    }
  }
}
