
package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Defaults.LimeLightDefault;

public class LimeLight extends SubsystemBase {

//PhotonCamera camera = new PhotonCamera("LimeLight");
NetworkTableEntry getTarget;
NetworkTableInstance inst = NetworkTableInstance.getDefault();
NetworkTable table = inst.getTable("/photonvision/LimeLight/hasTarget");
  public LimeLight() {}
//causes errors, still trying to fix
// public boolean getTarget() {
//   PhotonPipelineResult result = camera.getLatestResult();
//   return result.hasTargets();
// }
boolean target;
public boolean getNetworkTablesTarget() {
getTarget = table.getEntry("hasTarget");
getTarget.setBoolean(target);
return target;
}

  public void init() {
    setDefaultCommand(new LimeLightDefault(this));
  }

  @Override
  public void periodic() {
SmartDashboard.putBoolean("Target Found", getNetworkTablesTarget());
  }
}
