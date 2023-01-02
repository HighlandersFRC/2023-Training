// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.magDefault;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  // public CANSparkMax neo3 = new CANSparkMax(11, MotorType.kBrushless);// doesnt connect to a neo yet
  public CANSparkMax neo2 = new CANSparkMax(12, MotorType.kBrushless);//the neo controlling the middle part of the mag
  public CANSparkMax neo1 = new CANSparkMax(14, MotorType.kBrushless);//the neo controlling the bottom part of the mag
  public TalonFX intakeTalon = new TalonFX(8);
  public VictorSPX victor = new VictorSPX(7);
  public IntakeSubsystem() 
  {}
  public void init(){
    setPID(0, 0, 0, 0);
    setDefaultCommand(new magDefault(this));
  }
  public void setPID(double P,double I,double D,double F){
    neo1.getPIDController().setP(P);
    neo1.getPIDController().setI(I);
    neo1.getPIDController().setD(D);
    neo1.getPIDController().setFF(F);
    neo2.getPIDController().setP(P);
    neo2.getPIDController().setI(I);
    neo2.getPIDController().setD(D);
    neo2.getPIDController().setFF(F);
    intakeTalon.config_kP(0, P);
    intakeTalon.config_kI(0, I);
    intakeTalon.config_kD(0, D);
    intakeTalon.config_kF(0, F);
    victor.config_kP(0, P);
    victor.config_kI(0, I);
    victor.config_kD(0, D);
    victor.config_kF(0, F);
    // neo3.getPIDController().setP(P);
    // neo3.getPIDController().setI(I);
    // neo3.getPIDController().setD(D);
    // neo3.getPIDController().setFF(F);
  }
  public void setSetpoint(double setPoint, boolean velocity){
    if (velocity){
      neo1.getPIDController().setReference(setPoint, ControlType.kVelocity);
      neo2.getPIDController().setReference(setPoint, ControlType.kVelocity);
      intakeTalon.set(ControlMode.Velocity, setPoint*-256);
      victor.set(ControlMode.Velocity, setPoint);
      // neo3.getPIDController().setReference(setPoint, ControlType.kVelocity);
    }else {
      neo1.getPIDController().setReference(setPoint, ControlType.kPosition);
      neo2.getPIDController().setReference(setPoint, ControlType.kPosition);
      intakeTalon.set(ControlMode.Position, setPoint*-256);
      victor.set(ControlMode.Position, setPoint);
      // neo3.getPIDController().setReference(setPoint, ControlType.kPosition);
    }
  }
  public void magIntake(double RPM){
    setSetpoint(RPM, true);
  }
  public void magStop(){
    setSetpoint(0, true);
  }
  public void magOutake(double RPM){
    setSetpoint(RPM, true);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
