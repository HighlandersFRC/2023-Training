
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ColorSensorDefault;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

public class ColorSensor extends SubsystemBase {

  private final I2C.Port i2cPort = I2C.Port.kOnboard; // creates I2c port object
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort); // created colorsensor object
  private final ColorMatch colorMatcher = new ColorMatch(); // creates color matcher object, able to compare RGB values and determine the closest color

  // creates colors for the colorMatcher to compare to it's detected RGB values
  private final Color Blue = new Color(0.143, 0.427, 0.429);
  private final Color Green = new Color(0.197, 0.561, 0.240);
  private final Color Red = new Color(0.561, 0.232, 0.114);
  private final Color Yellow = new Color(0.361, 0.524, 0.113);
  
  public ColorSensor() {}


  public void init() {
    setDefaultCommand(new ColorSensorDefault(this)); // sets default command

    // adds colors to the colorMatcher
    colorMatcher.addColorMatch(Blue);
    colorMatcher.addColorMatch(Green);
    colorMatcher.addColorMatch(Red);
    colorMatcher.addColorMatch(Yellow);  
  }

  @Override
  public void periodic() {

    Color detectedColor = colorSensor.getColor(); // creates variable which holds detected color in hex
    String colorString; // creates string that will eventually be printed out
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

    // logic block that returns string of color that is detected by color sensor and matcher. when none of these red, yellow, blue, or green colors are detected by the colorsensor, it will usually return green
    if (match.color == Blue) {
      colorString = "Blue";
    } else if (match.color == Red) {
      colorString = "Red";
    } else if (match.color == Green) {
      colorString = "Green";
    } else if (match.color == Yellow) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    // prints values to smartdashboard
    SmartDashboard.putNumber("Ambiguity", match.confidence); // confidence in detected color
    SmartDashboard.putString("Detected Color", colorString); // colors detected, either red, blue, yellow, green, or unkown.
    SmartDashboard.putNumber("Red", detectedColor.red); // amount of red detected
    SmartDashboard.putNumber("Green", detectedColor.green); // amount of green detected
    SmartDashboard.putNumber("Blue", detectedColor.blue); // amount of blue detected
    SmartDashboard.putString("Color", detectedColor.toString()); // color hex

  }
}
