package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Peripherals extends SubsystemBase {
  DigitalInput proximitySensor = new DigitalInput(Constants.PROXIMITY_SENSOR_DIO_PORT);
  public Peripherals() {}

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Proximity Sensor", proximitySensor.get());
  }

  public void init() {

  }

}
