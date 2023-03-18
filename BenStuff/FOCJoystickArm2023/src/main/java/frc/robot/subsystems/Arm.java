package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OI;

public class Arm extends SubsystemBase {

  private double navXAngle;
  private double armLocation;
  public Arm() {}

  public void init() {
    //SmartDashboard.putNumber("NavX", navXAngle);
  }
/* The armDirection returns if the arm should go forward or backward based on the left joystick's angle and the navx's angle
 * The armDirection function needs the functions getTrueDegree and joystickAngle to function
 */
  private double getTrueDegree(double angle) {
    // converts angle to 0-360 range
    angle = angle % 360;
    if (angle < 0) {
      angle += 360;
    }
    return angle;
  }
  public boolean armDirection(double navxAngle, XboxController joystick){
    //gets angle of controller's left stick
    double joy = joystickToAngle(joystick.getLeftX(), joystick.getLeftY());
    //converts navxAngle to a 0-360 degree angle
    double nav = getTrueDegree(navxAngle);
    //sets upper bound and lower bound
    double upperBound = nav + 90;
    double lowerBound = nav - 90;
    //converts bounds to a 0-360 degree angle
    upperBound = getTrueDegree(upperBound);
    lowerBound = getTrueDegree(lowerBound);
    if (upperBound < lowerBound) {
    //if the upper bound ends up lower than the lower bound, then the joystick should not be between them to return true
      return joy <= upperBound || joy >= lowerBound;
    }else{
    //if the upper bound is higher than the lower bound, then the joystick needs to be between them to return true
      return joy <= upperBound && joy >= lowerBound;
    }
  }
  private double joystickToAngle(double x, double y){
    //returns the angle of the joystick in degrees
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
    SmartDashboard.putBoolean("Arm Be Forward", armDirection(navXAngle, OI.opController));
    SmartDashboard.putNumber("Arm Position", armLocation);
    SmartDashboard.putNumber("Joystick Angle", joystickToAngle(OI.getLeftOpX(), OI.getLeftOpY()));
  }
}
