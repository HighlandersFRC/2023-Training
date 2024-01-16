// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AmpScorer extends SubsystemBase {
  CANSparkMax sparkMax = new CANSparkMax(1, MotorType.kBrushless);
  Servo servo = new Servo(0);
  /** Creates a new AmpScorer. */
  public AmpScorer() {}

  public void intakeOrOutakeRing(double speed){
    sparkMax.set(speed);
  }

  public void moveServo(double position){
    servo.setAngle(position);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
