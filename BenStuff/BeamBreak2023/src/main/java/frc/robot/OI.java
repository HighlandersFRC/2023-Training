package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
    public static BooleanSupplier rtSupplier = () -> getRTPercent() > Constants.OperatorConstants.RIGHT_TRIGGER_DEADZONE;
    public static BooleanSupplier ltSupplier = () -> getLTPercent() > Constants.OperatorConstants.LEFT_TRIGGER_DEADZONE;
    
    public static TriggerButton rt = new TriggerButton(rtSupplier);
    public static TriggerButton lt = new TriggerButton(ltSupplier); 

public static XboxController driverController = new XboxController(0);

public static JoystickButton buttonA = new JoystickButton(driverController, 1);
public static JoystickButton buttonY = new JoystickButton(driverController, 4);
public static JoystickButton buttonB = new JoystickButton(driverController, 2);
public static JoystickButton buttonX = new JoystickButton(driverController, 3);

public static double getRTPercent() {
    return driverController.getRightTriggerAxis();
}

public static double getLTPercent() {
    return driverController.getLeftTriggerAxis();
}

}
