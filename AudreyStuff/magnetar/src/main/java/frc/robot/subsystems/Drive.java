// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package Drive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
public class Drive extends SubsystemBase {
  public TalonSRX motor1=new TalonSRX (3);
  public TalonSRX motor2=new TalonSRX (4);
  public TalonSRX motor3=new TalonSRX (1);
  public TalonSRX motor4=new TalonSRX (2);

  /** Creates a new Drive. */
  public Drive() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
