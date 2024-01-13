// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

package frc.robot.tools;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.button.Trigger;

/** Add your docs here. */
public class TriggerButton extends Trigger{
    public TriggerButton(BooleanSupplier bs) {
		super(bs);
	}
}

// Copyrights (c) 2018-2019 FIRST, 2020 Highlanders FRC. All Rights Reserved.

// package frc.robot.tools;

// import edu.wpi.first.wpilibj.GenericHID;

// public class TriggerButton extends Trigger{
//     private final double THRESHOLD = 0.5;
//     private GenericHID joystick;
//     private int axis;

//     public TriggerButton(GenericHID joystick, int axisNumber) {
//         this.joystick = joystick;
//         axis = axisNumber;
//     }

//     public boolean get() {
//         return joystick.getRawAxis(axis) >= THRESHOLD;
//     }
// }