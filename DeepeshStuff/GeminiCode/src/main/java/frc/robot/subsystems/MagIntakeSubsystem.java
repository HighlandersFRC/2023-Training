// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.IntakeDefault;





public class MagIntakeSubsystem extends SubsystemBase {
  /** Creates a new MagIntakeSubsyste. */
  public MagIntakeSubsystem() {}
  public DoubleSolenoid intakePiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1); 
  public TalonFX intakeTalon = new TalonFX(8);
  public CANSparkMax neo2 = new CANSparkMax(12, MotorType.kBrushless);//the neo controlling the middle part of the mag
  public CANSparkMax neo1 = new CANSparkMax(14, MotorType.kBrushless);//14the neo controlling the bottom part of the mag
  public VictorSPX victor = new VictorSPX(7);
  public DigitalInput beamBreak1 = new DigitalInput(3);
  public DigitalInput beamBreak2 = new DigitalInput(1);
  public DigitalInput beamBreak3 = new DigitalInput(6);
  public void init(){
    setDefaultCommand(new IntakeDefault(this));
  }
  public Boolean getBeam(DigitalInput num) {

    if (num.get()) {
      return true;
    } else if(!num.get()) {
      return false;
    } else {
      return null;
    }
  }

  
  public void beamBreaksOnDashboard(){

    SmartDashboard.putBoolean("beamBreak1", getBeam(beamBreak1));
    SmartDashboard.putBoolean("beamBreak2", getBeam(beamBreak2));
    SmartDashboard.putBoolean("beamBreak3", getBeam(beamBreak3));
    
  }
  public void setMagPercent(double setPoint ){
      neo1.set(setPoint);
      neo2.set(setPoint);
      victor.set(ControlMode.PercentOutput, 1.1*setPoint);
  }
  public void setNEOPercent(double setPoint ){
    neo1.set(setPoint);
    neo2.set(setPoint);
  }
  
  public void extendPistons(){
    intakePiston.set(Value.kForward);
  }
  public void retractPistons(){
    intakePiston.set(Value.kReverse);
  }  
  public void intake(){
   intakeTalon.set(ControlMode.PercentOutput, -0.7); 
  }
  public void stopIntake(){
   intakeTalon.set(ControlMode.PercentOutput, 0); 
  }
  public void outake(){
   intakeTalon.set(ControlMode.PercentOutput, 0.7); 
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
