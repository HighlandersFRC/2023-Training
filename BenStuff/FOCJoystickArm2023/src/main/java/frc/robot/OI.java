package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
    public static XboxController opController = new XboxController(1);
    public static JoystickButton aButton = new JoystickButton(opController, 1);
    
    public static double getLeftOpY() {
        return opController.getLeftY();
    }
    public static double getLeftOpX(){
        return opController.getLeftX();
    }
}
