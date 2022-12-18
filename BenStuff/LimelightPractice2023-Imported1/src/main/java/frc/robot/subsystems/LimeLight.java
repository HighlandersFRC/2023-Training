
package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Tools.Constants;
import frc.robot.commands.Defaults.LimeLightDefault;

public class LimeLight extends SubsystemBase {



// //creating variables that hold the x and y values for the target within the video frame
  NetworkTableEntry targetPixelsXEntry;
  NetworkTableEntry targetPixelsYEntry;

  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable limeLightTable = inst.getTable("/photonvision/LimeLight");

  
PhotonCamera camera = new PhotonCamera(inst, "LimeLight");

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

//pose estimate does not work yet
public double getCameraToTarget(String type) {
  PhotonPipelineResult result = camera.getLatestResult();
if(result.getBestTarget() == null) {
  return -1;
} else {
    PhotonTrackedTarget target = result.getBestTarget();
    Transform3d camToTarget = target.getBestCameraToTarget();
switch (type) {
  case "X": return camToTarget.getX();
  case "Y": return camToTarget.getY();
  case "Z": return camToTarget.getZ();
  case "Angle": return camToTarget.getRotation().getAngle();
  case "Norm": return camToTarget.getTranslation().getNorm();
  default: return -1;
  }
 }
}


public double getDistanceToTarget(double cameraHeightMeters, double targetHeightMeters, double cameraPitchRadians) {
  PhotonPipelineResult result = camera.getLatestResult();
  if(result.getBestTarget() == null) {return 0.0;} else {
  double range = PhotonUtils.calculateDistanceToTargetMeters(
          cameraHeightMeters,
          targetHeightMeters,
          cameraPitchRadians,
          Units.degreesToRadians(result.getBestTarget().getPitch())
          );
          return range;
  }
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
    SmartDashboard.putNumber("Distance to Target (Meters)", getDistanceToTarget(Units.inchesToMeters(26), Units.inchesToMeters(47), Units.degreesToRadians(0))); // enter target and camera heights off the ground
    SmartDashboard.putNumber("Pose X", getCameraToTarget("X"));
    SmartDashboard.putNumber("Pose Y", getCameraToTarget("Y"));
    SmartDashboard.putNumber("Pose z", getCameraToTarget("Z"));
    SmartDashboard.putNumber("Pose Angle", getCameraToTarget("Angle"));
    SmartDashboard.putNumber("Pose Norm", getCameraToTarget("Norm"));
  }
}
