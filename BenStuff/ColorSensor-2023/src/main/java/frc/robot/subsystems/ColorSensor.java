/*-------------------------------------------------------------------------------------
commented code is for plugging the colorsensor into the mxp port or navx
uncommented code is for running two colorsensors at the same time on a raspberry pi pico
---------------------------------------------------------------------------------------*/



package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ColorSensorDefault;
import frc.robot.subsystems.PicoColorSensor.RawColor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import edu.wpi.first.wpilibj.I2C;
// import edu.wpi.first.wpilibj.util.Color;

// import com.revrobotics.ColorMatch;
// import com.revrobotics.ColorMatchResult;
// import com.revrobotics.ColorSensorV3;
public class ColorSensor extends SubsystemBase {

//   private final I2C.Port i2cPort = I2C.Port.kMXP; // creates I2c port object
//   private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort); // created colorsensor object
//   private final ColorMatch colorMatcher = new ColorMatch(); // creates color matcher object, able to compare RGB values and determine the closest color

//   // creates colors for the colorMatcher to compare to it's detected RGB values
//   private final Color Blue = new Color(0.143, 0.427, 0.429);
//   private final Color Green = new Color(0.197, 0.561, 0.240);
//   private final Color Red = new Color(0.561, 0.232, 0.114);
//   private final Color Yellow = new Color(0.361, 0.524, 0.113);

  public void init() {
    // adds colors to the colorMatcher
    // colorMatcher.addColorMatch(Blue);
    // colorMatcher.addColorMatch(Green);
    // colorMatcher.addColorMatch(Red);
    // colorMatcher.addColorMatch(Yellow);  
setDefaultCommand(new ColorSensorDefault(this)); // sets default command 
}

public final PicoColorSensor pico = new PicoColorSensor();

public Boolean getSensor0Attached() {
  Boolean detected = pico.isSensor0Connected();
  return detected;
}

public Boolean getSensor1Attached() {
  Boolean detected = pico.isSensor1Connected();
  return detected;
}

public int getDistance0() {
  int prox = pico.getProximity0();
  return prox;
}

public int getDistance1() {
  int prox = pico.getProximity1();
  return prox;
}

  public ColorSensor() {}

  public RawColor getSensor0Color() {
    RawColor sensor0Color = pico.getRawColor0();
    return sensor0Color;
  }

  public RawColor getSensor1Color() {
    RawColor sensor1Color = pico.getRawColor1();
    return sensor1Color;
  }

  public int getSpecificColor(RawColor raw, String color) {
    switch (color) {
      case "red":
        return raw.red/128;
      case "green":
        return raw.green/128;
      case "blue":
        return raw.blue/128;
    
      default:
      return 0;
    }
  }

  public double[] getRGB(RawColor raw) {
    double[] rgb = {raw.red, raw.green, raw.green};
    return rgb;
      }
        


  
//   public double[] getHSVValues(RawColor raw) {
// double[] hsv = RGBtoHSV(raw.red, raw.green, raw.blue);
// return hsv;
    
//   }

  double[] nothing = {0.0, 0.0, 0.0};

public boolean sensorHasCube(double h, boolean detecting) {
if(h < 0.5 && h > 0.38 && detecting) {
  return true;
} else return false;
}

public boolean sensorHasCone(double h, boolean detecting) {
  if(h < 0.77 && h > 0.71 && detecting) {
    return true;
  } else return false;
}

public boolean get0Detecting() {
  if(pico.getProximity0() > 100) {
    return true;
  } else return false;
}

public boolean get1Detecting() {
  if(pico.getProximity1() > 100) {
    return true;
  } else return false;
}

public double getH(RawColor raw) {
float[] hsv = java.awt.Color.RGBtoHSB(raw.red, raw.green, raw.blue, null);
return hsv[0];
}


  @Override
  public void periodic() {
    SmartDashboard.putBoolean("sensor 0 connected", getSensor0Attached());
    SmartDashboard.putBoolean("sensor 1 connected", getSensor1Attached());
    SmartDashboard.putNumber("Distance 0", getDistance0()); 
    SmartDashboard.putNumber("Distance 1", getDistance1()); 
    SmartDashboard.putNumber("Sensor 0 Red", getSpecificColor(getSensor0Color(), "red"));
    SmartDashboard.putNumber("Sensor 0 Green", getSpecificColor(getSensor0Color(), "green"));
    SmartDashboard.putNumber("Sensor 0 Blue", getSpecificColor(getSensor0Color(), "blue"));
    SmartDashboard.putNumber("Sensor 1 Red", getSpecificColor(getSensor1Color(), "red"));
    SmartDashboard.putNumber("Sensor 1 Green", getSpecificColor(getSensor1Color(), "green"));
    SmartDashboard.putNumber("Sensor 1 Blue", getSpecificColor(getSensor1Color(), "blue"));
    SmartDashboard.putBoolean("sensor 0 found object", get0Detecting());
    SmartDashboard.putBoolean("sensor 1 found object", get1Detecting());
    SmartDashboard.putBoolean("sensor 0 found cube", sensorHasCube(getH(getSensor0Color()), get0Detecting()));
    SmartDashboard.putBoolean("sensor 0 found cone", sensorHasCone(getH(getSensor0Color()), get0Detecting()));
    SmartDashboard.putBoolean("sensor 1 found cube", sensorHasCube(getH(getSensor1Color()), get1Detecting()));
    SmartDashboard.putBoolean("sensor 1 found cone", sensorHasCone(getH(getSensor1Color()), get1Detecting()));
    SmartDashboard.putNumber("H 0", getH(getSensor0Color())); 
    SmartDashboard.putNumber("H 1", getH(getSensor1Color())); 

    // Color detectedColor = colorSensor.getColor(); // creates variable which holds detected color in hex
    // String colorString; // creates string that will eventually be printed out
    // ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

    // // logic block that returns string of color that is detected by color sensor and matcher. when none of these red, yellow, blue, or green colors are detected by the colorsensor, it will usually return green
    // if (match.color == Blue) {
    //   colorString = "Blue";
    // } else if (match.color == Red) {
    //   colorString = "Red";
    // } else if (match.color == Green) {
    //   colorString = "Green";
    // } else if (match.color == Yellow) {
    //   colorString = "Yellow";
    // } else {
    //   colorString = "Unknown";
    // }

    // // prints values to smartdashboard
    // SmartDashboard.putNumber("Ambiguity", match.confidence); // confidence in detected color
    // SmartDashboard.putString("Detected Color", colorString); // colors detected, either red, blue, yellow, green, or unkown.
    // SmartDashboard.putNumber("Red", detectedColor.red); // amount of red detected
    // SmartDashboard.putNumber("Green", detectedColor.green); // amount of green detected
    // SmartDashboard.putNumber("Blue", detectedColor.blue); // amount of blue detected
    // SmartDashboard.putString("Color", detectedColor.toString()); // color hex

  }
}
