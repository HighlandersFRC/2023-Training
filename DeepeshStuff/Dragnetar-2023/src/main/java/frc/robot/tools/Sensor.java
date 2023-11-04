package frc.robot.tools;
import edu.wpi.first.wpilibj.*;
/** Add your docs here. */
public class Sensor {
    public static AnalogInput pressure = new AnalogInput(0);
  public static double getPressure(){
    return (5.0550 * Math.pow(pressure.getVoltage(), 3)  - 19.5933 * Math.pow(pressure.getVoltage(), 2) + 70.3363 * pressure.getVoltage() - 18.2925);
  }
}