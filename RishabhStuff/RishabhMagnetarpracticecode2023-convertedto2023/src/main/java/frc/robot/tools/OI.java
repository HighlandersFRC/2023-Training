package frc.robot.tools;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
    public static BooleanSupplier rtSupplier = () -> getDriverRightTrigger() > 0.1;
    public static BooleanSupplier ltSupplier = () -> getDriverLeftTrigger() > 0.1;
 
    public static XboxController driverController = new XboxController(0);
    public static TriggerButton rightTrigger = new TriggerButton(rtSupplier);
    public static TriggerButton leftTrigger = new TriggerButton(ltSupplier);

    public static JoystickButton rightBummper = new JoystickButton(driverController, 6);

    public static JoystickButton driverA = new JoystickButton(driverController, 1);
    
    public static JoystickButton driverY = new JoystickButton(driverController, 4);

    public static double getDriverLeftX() {
        return driverController.getLeftX();
    }
    public static double getDriverRightX() {
        return driverController.getRightX();
    }
    public static double getDriverLeftY() {
        return driverController.getLeftY();
    }
    public static double getDriverRightY() {
        return driverController.getRightY();
    }
    public static double getDriverRightTrigger() {
        return driverController.getRightTriggerAxis();
    }
    public static double getDriverLeftTrigger() {
        return driverController.getLeftTriggerAxis();
    }
    public static boolean getDriverA(){
        return driverController.getAButton();
    }
    public static boolean getDriverY(){
        return driverController.getYButton();
    }
}
