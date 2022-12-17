
package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Tools.Constants;
import frc.robot.commands.Defaults.LimeLightDefault;

public class LimeLight extends SubsystemBase {




  NetworkTableEntry latencyMillisEntry;
  NetworkTableEntry hasTargetEntry;
  NetworkTableEntry targetAreaEntry;
  NetworkTableEntry targetPitchEntry;
  NetworkTableEntry targetYawEntry;
  NetworkTableEntry targetPixelsXEntry;
  NetworkTableEntry targetPixelsYEntry;
  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable limeLightTable = inst.getTable("/photonvision/LimeLight");

  
PhotonCamera camera = new PhotonCamera(inst, "LimeLight");

// public boolean getPVHasTarget() {
//   PhotonPipelineResult result = camera.getLatestResult();
//   return result.hasTargets();
// }

// public double getPVLatencyMillis() {
//   PhotonPipelineResult result = camera.getLatestResult();
//   return result.getLatencyMillis();
// }

public double getLatencyMillis() {
  PhotonPipelineResult result = camera.getLatestResult();
  return result.getLatencyMillis();
}

public boolean getHasTarget() {
  PhotonPipelineResult result = camera.getLatestResult();
  return result.hasTargets();
}

public double getTargetArea() {
  PhotonPipelineResult result = camera.getLatestResult();
  if(result.getBestTarget() == null) {
    return 0.0;
  } else {
    return result.getBestTarget().getArea();
  }
}

public double getTargetPitch() {
  PhotonPipelineResult result = camera.getLatestResult();
  if(result.getBestTarget() == null) {
    return 0.0;
  } else {
    return result.getBestTarget().getPitch();
  }
}

public double getTargetYaw() {
  PhotonPipelineResult result = camera.getLatestResult();
  if(result.getBestTarget() == null) {
    return 0.0;
  } else {
    return result.getBestTarget().getYaw();
  }
}

public double getTargetPixelsX() {
  targetPixelsXEntry = limeLightTable.getEntry("targetPixelsX");
  return targetPixelsXEntry.getDouble(0.0);
}

public double getTargetPixelsY() {
  targetPixelsYEntry = limeLightTable.getEntry("targetPixelsY");
  return targetPixelsYEntry.getDouble(0.0);
}


  public LimeLight() {}

  public void init() {
    setDefaultCommand(new LimeLightDefault(this));
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Has Target", getHasTarget());
    SmartDashboard.putNumber("Latency (Mills)", getLatencyMillis());
    SmartDashboard.putNumber("Target Area", getTargetArea());
    SmartDashboard.putNumber("Pitch", getTargetPitch());
    SmartDashboard.putNumber("Target Pixels X", getTargetPixelsX());
    SmartDashboard.putNumber("Target Pixels Y", getTargetPixelsY());
    SmartDashboard.putNumber("Yaw", getTargetYaw());
  }
}
