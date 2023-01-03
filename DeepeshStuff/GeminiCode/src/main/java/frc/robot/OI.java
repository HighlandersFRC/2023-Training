package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.tools.TriggerButton;


public class OI {
    public static XboxController driverController = new XboxController(0);
    public static JoystickButton buttonA = new JoystickButton(driverController, 1);
    public static JoystickButton buttonB = new JoystickButton(driverController, 2);
    public static JoystickButton buttonX = new JoystickButton(driverController, 3);
    public static JoystickButton buttonY = new JoystickButton(driverController, 4);
    public static JoystickButton rBumper = new JoystickButton(driverController, 6);
    public static JoystickButton lBumper = new JoystickButton(driverController, 5);
    public static TriggerButton rTrigger = new TriggerButton(driverController, 3);
    public static TriggerButton lTrigger = new TriggerButton(driverController, 2);
    
    
    public static double  getDriverLeftY(){
        if (Math.abs(driverController.getLeftY()) < 0.2 ){
            return 0;
        } else {
            return driverController.getLeftY();
        }
        
    }
    public static double getDriverLeftX(){
        if (Math.abs(driverController.getLeftX()) < 0.2 ){
            return 0;
        } else {
            return driverController.getLeftX();
        }
    }
    public static double getDriverRightY(){
        if (Math.abs(driverController.getRightY()) < 0.2 ){
            return 0;
        } else {
            return driverController.getRightY();
        }
    }
    public static double getDriverRightX(){
        if (Math.abs(driverController.getRightX()) < 0.2 ){
            return 0;
        } else {
            return driverController.getRightX();
        }
    }
}
