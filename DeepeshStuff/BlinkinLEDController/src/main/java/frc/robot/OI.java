// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/** Add your docs here. */
public class OI {
    public static XboxController driverController = new XboxController(0);
    public static JoystickButton aButton = new JoystickButton(driverController, 1);
    public static JoystickButton bButton = new JoystickButton(driverController, 2);
    public static JoystickButton xButton = new JoystickButton(driverController, 3);
    public static JoystickButton yButton = new JoystickButton(driverController, 4);
}
