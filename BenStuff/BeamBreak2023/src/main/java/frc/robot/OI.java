package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
    
public static XboxController driverController = new XboxController(0);
public static JoystickButton buttonA = new JoystickButton(driverController, 1);
public static JoystickButton buttonY = new JoystickButton(driverController, 4);
public static JoystickButton buttonB = new JoystickButton(driverController, 2);
public static JoystickButton buttonX = new JoystickButton(driverController, 3);
}
