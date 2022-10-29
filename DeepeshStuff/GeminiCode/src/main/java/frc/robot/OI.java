package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class OI {
    public static XboxController driverController = new XboxController(0);
    public static JoystickButton buttonA = new JoystickButton(driverController, 1);
    public static double  getDriverLeftY(){
        return driverController.getLeftY();
    }
    public static double getDriverLeftX(){
        return driverController.getLeftX();
    }
    public static double getDriverRightY(){
        return driverController.getRightY();
    }
    public static double getDriverRightX(){
        return driverController.getRightX();
    }
}
