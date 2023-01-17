// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ArmDefault;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  public TalonSRX armMaster = new TalonSRX(5);
  TalonSRX armSlave = new TalonSRX(4);
  public DoubleSolenoid brake = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 5);
  boolean hit0 = false;
  boolean hit180 = false;

  public ArmSubsystem() {}
  public void engageBreak(){
    brake.set(Value.kForward);
  }
  public void stopBreak(){
    brake.set(Value.kReverse);
  }
  public void editPIDF(){
    setArmPID(0.25, 0, 0, 1, 0);

  }
  public void init(){
    armSlave.set(ControlMode.Follower, 5);
    setDefaultCommand(new ArmDefault(this));
    armMaster.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    armMaster.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    setArmPercent(0);
    armMaster.setSelectedSensorPosition(0.0);
    displayPIDF();
  }
  public void setArmVelocity(double TP100MS){
    if (TP100MS > 100000){
      TP100MS = 100000;
    } else if(TP100MS < -100000){
      TP100MS = -100000;
    }
    armMaster.set(ControlMode.Velocity, TP100MS);
  }
  public void setArmPosition(double degrees){
    armMaster.set(ControlMode.Position, Constants.ARM_DEGREES_TO_TICKS(degrees));
  }
  
  public void whenFwdLimitCloses(){
    if (armMaster.getSensorCollection().isFwdLimitSwitchClosed()&&!hit0){
      armMaster.setSelectedSensorPosition(Constants.ARM_DEGREES_TO_TICKS(3.0));
      hit0 = true;
    } else {
      hit0 = false;
    }
  }
  public void whenRevLimitCloses(){
    if (armMaster.getSensorCollection().isRevLimitSwitchClosed()&&!hit180){
      armMaster.setSelectedSensorPosition(Constants.ARM_DEGREES_TO_TICKS(177.0));
      hit180 = true;
    } else {
      hit180 = false;
    }
  }
  public void setArmPercent(double power){
    armMaster.set(ControlMode.PercentOutput, power);
  }
  public void setArmPID(double p, double i, double d, double f, int slotidx){
    armMaster.config_kP(slotidx, p);
    armMaster.config_kI(slotidx, i);
    armMaster.config_kD(slotidx, d);
    armMaster.config_kF(slotidx, f);
  }
  public void displayPIDF(){
    SmartDashboard.putNumber("P", 0);
    SmartDashboard.putNumber("I", 0);
    SmartDashboard.putNumber("D", 0);
    SmartDashboard.putNumber("F", 0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
