// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  private final CANSparkFlex elevator = new CANSparkFlex(0, MotorType.kBrushless);
  private final SparkPIDController elevatorPID = elevator.getPIDController();
  /** Creates a new Elevator. */
  public Elevator() {}

  public void init(){
    // elevatorPID.setP(1.0);
    // elevatorPID.setI(0.0);
    // elevatorPID.setD(0.0);
  }

  public void moveElevator(double speed){
    // elevatorPID.setReference(5.0, ControlType.kPosition);
    elevator.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
