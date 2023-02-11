package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.PeripheralsDefault;

public class Peripherals extends SubsystemBase {

  private NetworkTable limeLightTable = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry tableX = limeLightTable.getEntry("tx");
  private NetworkTableEntry tableY = limeLightTable.getEntry("ty");

  private double limeLightX = -1.0;
  private double limeLightY = -1.0;

  public void switchPipeline(int pipeNum) {
		NetworkTableEntry pipelineEntry = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline");
    	pipelineEntry.setNumber(pipeNum);
  }

  public int getCurrentPipeline() {
    Number currentPipeNum = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").getNumber(1);
    int currentPipeInt = currentPipeNum.intValue();
    return currentPipeInt;
  }

  public void togglePipeline() {
     
      int pipeNum = 0;
      if(getCurrentPipeline() == 0) {
          pipeNum = 1;
      } else if(getCurrentPipeline() == 1) {
          pipeNum = 0;
      }
      switchPipeline(pipeNum);
  }

  public double getLimeLightX() {
    // limeLightX = Math.PI * (tableX.getDouble(0))/180;
    // return limeLightX;
    limeLightX = tableX.getDouble(-100);
    return limeLightX;
  }

public double getTestX() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);

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
    System.out.println("Pipeline: " + getCurrentPipeline() + ", X: " + getLimeLightX() + ", Y: " + getLimeLightY());
  }













  
}
