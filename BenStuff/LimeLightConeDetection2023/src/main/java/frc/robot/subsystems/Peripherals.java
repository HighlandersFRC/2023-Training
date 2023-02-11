package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.PeripheralsDefault;

public class Peripherals extends SubsystemBase {

  private NetworkTable limeLightTable = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry tableX = limeLightTable.getEntry("tx");
  private NetworkTableEntry tableY = limeLightTable.getEntry("ty");

  private double limeLightX = -1.0;
  private double limeLightY = -1.0;

  public double getLimeLightX() {
    limeLightX = Math.PI * (tableX.getDouble(0))/180;
    return limeLightX;
  }

  public double getLimeLightY() {
    limeLightY = tableY.getDouble(-100);
    return limeLightY;
  }

  public Peripherals() {}

  public void init() {
    
    setDefaultCommand(new PeripheralsDefault(this));
  }

  @Override
  public void periodic() {
  }













  
}
