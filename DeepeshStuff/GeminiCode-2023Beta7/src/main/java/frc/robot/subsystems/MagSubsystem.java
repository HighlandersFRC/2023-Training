// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MagSubsystem extends SubsystemBase {
  /** Creates a new MagSubsystem. */
  public CANSparkMax neo1 = new CANSparkMax(14, MotorType.kBrushless);//the neo controlling the bottom part of the mag
  public CANSparkMax neo2 = new CANSparkMax(12, MotorType.kBrushless);//the neo controlling the middle part of the mag
  DigitalInput beamBreak1 = new DigitalInput(0);
  DigitalInput beamBreak2 = new DigitalInput(1);
  DigitalInput beamBreak3 = new DigitalInput(3);
  DigitalInput beamBreak4 = new DigitalInput(4);

  public MagSubsystem() {}
  public void beamBreaksOnDashboard(){
    SmartDashboard.putBoolean("beamBreak1", beamBreak1.get());
    SmartDashboard.putBoolean("beamBreak2", beamBreak2.get());
    SmartDashboard.putBoolean("beamBreak3", beamBreak3.get());
    SmartDashboard.putBoolean("beamBreak4", beamBreak4.get());
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
