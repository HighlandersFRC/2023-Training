package frc.robot.subsystems;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.ConnectionInfo;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.defaults.PeripheralsDefault;
import frc.robot.sensors.Navx;

public class Peripherals extends SubsystemBase {
  private NetworkTable backCam = NetworkTableInstance.getDefault().getTable("limelight-back");
  private NetworkTableEntry backCamJSON = backCam.getEntry("json");
  private NetworkTableEntry backCamTx = backCam.getEntry("tx");
  private NetworkTableEntry backCamTa = backCam.getEntry("ta");
  private NetworkTable frontCam = NetworkTableInstance.getDefault().getTable("limelight-front");
  private NetworkTableEntry frontCamJSON = frontCam.getEntry("json");
  private NetworkTableEntry frontCamTy = frontCam.getEntry("ty");
  private NetworkTableEntry frontCamTx = frontCam.getEntry("tx");
  private NetworkTable leftCam = NetworkTableInstance.getDefault().getTable("limelight-left");
  private NetworkTableEntry leftCamJSON = leftCam.getEntry("json");
  private NetworkTable rightCam = NetworkTableInstance.getDefault().getTable("limelight-right");
  private NetworkTableEntry rightCamJSON = rightCam.getEntry("json");

  private double[] noTrackLimelightArray = new double[6];

  private Pigeon2 pigeon = new Pigeon2(0, "Canivore");
  private Pigeon2Configuration pigeonConfig = new Pigeon2Configuration();

  private final static AHRS ahrs = new AHRS(Port.kMXP);
  private final static Navx navx = new Navx(ahrs);
  
  public Peripherals() {}

  public void init() {
    pigeonConfig.MountPose.MountPosePitch = 85.25395965576172;
    pigeonConfig.MountPose.MountPoseRoll = 126.1924057006836;
    pigeonConfig.MountPose.MountPoseYaw = -0.5872021913528442;
    pigeon.getConfigurator().apply(pigeonConfig);
    zeroPigeon();
    noTrackLimelightArray[0] = 0;
    noTrackLimelightArray[1] = 0;
    noTrackLimelightArray[2] = 0;
    noTrackLimelightArray[3] = 0;
    noTrackLimelightArray[4] = 0;
    noTrackLimelightArray[5] = 0;
    setDefaultCommand(new PeripheralsDefault(this));
  }

  public double getFrontCamTargetTy(){
    JSONObject results = new JSONObject(this.frontCamJSON.getString("{Results: {}}")).getJSONObject("Results");
    if (results.isNull("Fiducial")){
      return 100;
    }
    JSONArray fiducials = results.getJSONArray("Fiducial");
    for (int i = 0; i < fiducials.length(); i ++){
      int id = ((JSONObject) fiducials.get(i)).getInt("fID");
      if (id == 7 || id == 4){
        return ((JSONObject) fiducials.get(i)).getDouble("ty");
      }
    }
    return 100;
  }

  public double getFrontCamTargetTx(){
    JSONObject results = new JSONObject(this.frontCamJSON.getString("{Results: {}}")).getJSONObject("Results");
    if (results.isNull("Fiducial")){
      return 100;
    }
    JSONArray fiducials = results.getJSONArray("Fiducial");
    for (int i = 0; i < fiducials.length(); i ++){
      int id = ((JSONObject) fiducials.get(i)).getInt("fID");
      if (id == 7 || id == 4){
        return ((JSONObject) fiducials.get(i)).getDouble("tx");
      }
    }
    return 100;
  }

  public double getBackCamTargetTx(){
    return backCamTx.getDouble(0.0) * Math.PI / 180;
  }

  public double getBackCamTargetTa(){
    return backCamTa.getDouble(0.0);
  }

  public ArrayList<Integer> getFrontCamIDs(){
    JSONObject results = new JSONObject(this.frontCamJSON.getString("{Results: {}}")).getJSONObject("Results");
    if (results.isNull("Fiducial")){
      return new ArrayList<Integer>();
    }
    ArrayList<Integer> ids = new ArrayList<Integer>();
    JSONArray fiducials = results.getJSONArray("Fiducial");
    for (int i = 0; i < fiducials.length(); i ++){
      ids.add(((JSONObject) fiducials.get(i)).getInt("fID"));
    }
    return ids;
  }

  public void setFrontCamPipeline(int pipeline){
    frontCam.getEntry("pipeline").setNumber(pipeline);
  }

  public void setBackCamPipeline(int pipeline){
    backCam.getEntry("pipeline").setNumber(pipeline);
  }

  public void setLeftCamPipeline(int pipeline){
    leftCam.getEntry("pipeline").setNumber(pipeline);
  }

  public void setRightCamPipeline(int pipeline){
    rightCam.getEntry("pipeline").setNumber(pipeline);
  }

  public int getFrontCamPipeline(){
    return (int) frontCam.getEntry("pipeline").getInteger(5);
  }

  public int getBackCamPipeline(){
    return (int) backCam.getEntry("pipeline").getInteger(5);
  }

  public int getLeftCamPipeline(){
    return (int) leftCam.getEntry("pipeline").getInteger(5);
  }

  public int getRightCamPipeline(){
    return (int) rightCam.getEntry("pipeline").getInteger(5);
  }

  public void zeroPigeon(){
    // setPigeonAngle(0.0);
    navx.softResetYaw();
    navx.softResetAngle();
  }

  public void setPigeonAngle(double degrees){
    // pigeon.setYaw(degrees);
    navx.setNavxAngle(degrees);
  }

  public double getPigeonAngle(){
    // return pigeon.getYaw().getValue();
    return navx.currentAngle();
  }

  public JSONObject getCameraMeasurements(){
    JSONObject allCamResults = new JSONObject();
    JSONObject backCamResults = new JSONObject(backCamJSON.getString("{'Results': {}}")).getJSONObject("Results");
    JSONObject frontCamResults = new JSONObject(frontCamJSON.getString("{'Results': {}}")).getJSONObject("Results");
    JSONObject leftCamResults = new JSONObject(leftCamJSON.getString("{'Results': {}}")).getJSONObject("Results");
    JSONObject rightCamResults = new JSONObject(rightCamJSON.getString("{'Results': {}}")).getJSONObject("Results");
    if (!backCamResults.isNull("Fiducial")){
      allCamResults.put("BackCam", backCamResults);
    }
    if (!frontCamResults.isNull("Fiducial")){
      allCamResults.put("FrontCam", frontCamResults);
    }
    if (!leftCamResults.isNull("Fiducial")){
      allCamResults.put("LeftCam", leftCamResults);
    }
    if (!rightCamResults.isNull("Fiducial")){
      allCamResults.put("RightCam", rightCamResults);
    }
    return allCamResults;
  }

  @Override
  public void periodic() {
    // ConnectionInfo[] info = NetworkTableInstance.getDefault().getConnections();
    // for (ConnectionInfo i : info){
    //   System.out.println(i.remote_ip);
    //   System.out.println(i.remote_id);
    // }
    SmartDashboard.putNumber("Navx", getPigeonAngle());
    SmartDashboard.putNumber("target size", getBackCamTargetTa());
    SmartDashboard.putNumber("target x", getBackCamTargetTx());
  }
}
