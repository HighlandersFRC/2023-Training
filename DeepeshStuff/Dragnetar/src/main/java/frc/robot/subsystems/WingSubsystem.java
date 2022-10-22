// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Defaults.WingsDefault;

public class WingSubsystem extends SubsystemBase {
  /** Creates a new CatapultSubsystem. */
  public WingSubsystem() {}

 private final DoubleSolenoid wing = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 3);

  public void wingsUp(){
    wing.set(Value.kForward);
  }

  public void init(){
    wingsUp();
    setDefaultCommand(new WingsDefault(this));
  }

  public void wingsDown(){
    wing.set(Value.kReverse);
  }

public void ToggleWings() {
 
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
