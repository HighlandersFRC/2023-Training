
package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Defaults.LimeLightDefault;

public class LimeLight extends SubsystemBase {



//creating variables that hold the x and y values for the target within the video frame 
  NetworkTableEntry targetPixelsXEntry;
  NetworkTableEntry targetPixelsYEntry;

  NetworkTableInstance inst = NetworkTableInstance.getDefault(); // creating an instance of network tables
  NetworkTable limeLightTable = inst.getTable("/photonvision/LimeLight"); //accessing the limelight table

  
PhotonCamera camera = new PhotonCamera(inst, "LimeLight"); //creating camera object

public double getLatencyMillis() {
  PhotonPipelineResult result = camera.getLatestResult(); // creates object which holds latest result data
  return result.getLatencyMillis(); // returns the latency of the camera in milliseconds
}

public boolean getHasTarget() {
  PhotonPipelineResult result = camera.getLatestResult(); // creates object which holds latest result data
  return result.hasTargets(); // returns true if camera has detected a target
}

public double getTargetArea() {
  PhotonPipelineResult result = camera.getLatestResult(); // creates object which holds latest result data
  if(result.getBestTarget() == null) { // makes sure result isn't null
    return 0.0;
  } else {
    return result.getBestTarget().getArea(); // returns area of target (larger means it's closer)
  }
}

public double getTargetPitch() {
  PhotonPipelineResult result = camera.getLatestResult(); // creates object which holds latest result data
  if(result.getBestTarget() == null) { // makes sure result isn't null
    return 0.0;
  } else {
    return result.getBestTarget().getPitch(); // returns the pitch of the target
  }
}

public double getTargetYaw() {
  PhotonPipelineResult result = camera.getLatestResult(); // creates object which holds latest result data
  if(result.getBestTarget() == null) { // makes sure result isn't null
    return 0.0;
  } else {
    return result.getBestTarget().getYaw(); // returns the yaw of the target
  }
}

public double getTargetPixelsX() {
  targetPixelsXEntry = limeLightTable.getEntry("targetPixelsX"); // grabs targetPixelsX value from networktables
  return targetPixelsXEntry.getDouble(0.0); // returns X value of target in frame (mins ans maxs are the resolution)
}

public double getTargetPixelsY() {
  targetPixelsYEntry = limeLightTable.getEntry("targetPixelsY"); // grabs targetPixelsY value from networktables
  return targetPixelsYEntry.getDouble(0.0); // returns Y value of target in frame (mins and maxs are the resolution)
}

//pose estimation DOES NOT WORK YET!
public double getCameraToTarget(String type) {
  PhotonPipelineResult result = camera.getLatestResult(); // creates object which holds latest result data
if(result.getBestTarget() == null) { // makes sure result isn't null
  return -1;
} else {
    PhotonTrackedTarget target = result.getBestTarget(); // creates target object
    Transform3d camToTarget = target.getBestCameraToTarget(); // creates Transform3d from camera to target
switch (type) { // input type when calling function, just so I don't have to write a different function for each pose value
  case "X": return camToTarget.getX(); // returns X
  case "Y": return camToTarget.getY(); // returns Y
  case "Z": return camToTarget.getZ(); // returns Z
  case "Angle": return camToTarget.getRotation().getAngle(); // returns angle
  case "Norm": return camToTarget.getTranslation().getNorm(); // returns translation norm
  default: return -1;
  }
 }
}


public double getDistanceToTarget(double cameraHeightMeters, double targetHeightMeters, double cameraPitchRadians) {
  PhotonPipelineResult result = camera.getLatestResult(); // creates object which holds latest result data
  if(result.getBestTarget() == null) { // makes sure result isn't null
    return 0.0;
  } else {
  double range = PhotonUtils.calculateDistanceToTargetMeters( // uses photonlib distance function to caculation distance to target
          cameraHeightMeters, // input camera height from ground in meters
          targetHeightMeters, // input target height from ground in meters
          cameraPitchRadians, // input target pitch/angle from ground in radians (This needs to be precise or distance will be inaccurate)
          Units.degreesToRadians(result.getBestTarget().getPitch()) // input target pitch from camera (uses getPitch function)
          );
          return range; // returns the distance to the target
  }
}

  public LimeLight() {}

  public void init() {
    setDefaultCommand(new LimeLightDefault(this)); // sets default command
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Has Target", getHasTarget()); // puts has target value on SmartDashboard/ShuffleBoard
    SmartDashboard.putNumber("Latency (Mills)", getLatencyMillis()); // puts latency value on SmartDashboard/ShuffleBoard
    SmartDashboard.putNumber("Target Area", getTargetArea()); // puts target area value on SmartDashboard/ShuffleBoard
    SmartDashboard.putNumber("Pitch", getTargetPitch()); // puts pitch value on SmartDashboard/ShuffleBoard
    SmartDashboard.putNumber("Target Pixels X", getTargetPixelsX()); // puts pixels X value on SmartDashboard/ShuffleBoard
    SmartDashboard.putNumber("Target Pixels Y", getTargetPixelsY()); // puts pixels Y value on SmartDashboard/ShuffleBoard
    SmartDashboard.putNumber("Yaw", getTargetYaw()); // puts Yaw value on SmartDashboard/ShuffleBoard
    SmartDashboard.putNumber("Distance to Target (Meters)", getDistanceToTarget( // puts distance value on SmartDashboard/ShuffleBoard
      Units.inchesToMeters(26), // input target height from ground
      Units.inchesToMeters(47), // input camera height from ground
      Units.degreesToRadians(0))); // input camera pitch/angle from ground
    SmartDashboard.putNumber("Pose X", getCameraToTarget("X")); // puts pose X value on SmartDashboard/ShuffleBoard
    SmartDashboard.putNumber("Pose Y", getCameraToTarget("Y")); // puts pose Y value on SmartDashboard/ShuffleBoard
    SmartDashboard.putNumber("Pose z", getCameraToTarget("Z")); // puts pose Z value on SmartDashboard/ShuffleBoard
    SmartDashboard.putNumber("Pose Angle", getCameraToTarget("Angle")); // puts pose angle value on SmartDashboard/ShuffleBoard
    SmartDashboard.putNumber("Pose Norm", getCameraToTarget("Norm")); // puts pose translation norm on SmartDashboard/ShuffleBoard
  }
}
