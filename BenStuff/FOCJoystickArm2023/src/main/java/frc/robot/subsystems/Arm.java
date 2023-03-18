package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OI;
import frc.robot.commands.ArmDefault;

public class Arm extends SubsystemBase {

  private double navXAngle;
  private double armLocation;
  public Arm() {}

  public void init() {
    SmartDashboard.putNumber("NavX", navXAngle);
    setDefaultCommand(new ArmDefault(this));
  }

  public double getNavX() {
    return navXAngle;
  }

  public enum armPosition{
    FORWARD(90), BACKWARD(270), UP(180);
    public final double value;
    private armPosition(double value){
      this.value = value;
    }
  }

  public void setArm(armPosition position) {
    switch (position) {
      case FORWARD:
        armLocation = position.value;
        //moves arm to 90 degrees
        break;
      case BACKWARD:
      armLocation = position.value;
        //move arm to 270 degrees
        break;
      case UP:
      armLocation = position.value;
        //move arm to 180 degrees
        break;
      default:
      armLocation = position.value;
        //move arm to 90 degrees
        break;
    }
  }

  public double getNavXTrueDegree() {
    double angle = navXAngle % 360;
    if (angle < 0) {
      angle += 360;
    }
  return angle;
  }
  
  public boolean armDirection(){
    double joy = joystickToAngle(OI.getLeftOpX(), OI.getLeftOpY());
    double nav = getNavXTrueDegree();
    double upperBound = nav + 90;
    double lowerBound = nav - 90;
    boolean armSide = true;

    if(upperBound >= 360) {
      upperBound -= 360;
    }
    
    if(lowerBound < 0) {
      lowerBound += 360;
    }
    SmartDashboard.putNumber("upper bound", upperBound);
    SmartDashboard.putNumber("lower bound", lowerBound);

    if(upperBound < lowerBound) {
      if(joy <= upperBound || joy >= lowerBound) {
        armSide = true;
      } else if (joy >= upperBound && joy <= lowerBound) {
        armSide = false;
      }
    } else if(upperBound >= lowerBound) {
      if(joy <= upperBound && joy >= lowerBound) {
        armSide = true;
      } else if (joy >= upperBound || joy <= lowerBound) {
        armSide = false;
      }
    }

    return armSide;

  }



  public double joystickToAngle(double x, double y){
  y *= -1;
  double angle =Math.atan2(y, x);
  angle = Math.toDegrees(angle);
  angle += 270;
  angle %= 360;
  return angle;
  }

  @Override
  public void periodic() {
    navXAngle = SmartDashboard.getNumber("NavX", 0);
    SmartDashboard.putBoolean("Arm Be Forward", armDirection());
    SmartDashboard.putNumber("Arm Position", armLocation);
    SmartDashboard.putNumber("Joystick Angle", joystickToAngle(OI.getLeftOpX(), OI.getLeftOpY()));
  }
}
