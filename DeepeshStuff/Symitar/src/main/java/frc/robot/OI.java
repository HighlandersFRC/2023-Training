// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.tools.TriggerButton;

/** Add your docs here. */
public class OI {
    public static XboxController driverController = new XboxController(0);
    public static JoystickButton a = new JoystickButton(driverController, 1);
    public static JoystickButton b = new JoystickButton(driverController, 2);
    public static JoystickButton x = new JoystickButton(driverController, 3);
    public static JoystickButton y = new JoystickButton(driverController, 4);
    public static JoystickButton rb = new JoystickButton(driverController, 6);
    public static JoystickButton lb = new JoystickButton(driverController, 5);
    public static TriggerButton rt = new TriggerButton(driverController, 3);
    public static TriggerButton lt = new TriggerButton(driverController, 2);
}
