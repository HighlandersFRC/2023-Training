// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot;

// import java.util.function.BooleanSupplier;

// import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import frc.robot.Tools.TriggerButton;

// /** Add your docs here. */
// public class OI {
//     public static XboxController driverController = new XboxController(0);
//     public static JoystickButton buttonA = new JoystickButton(driverController, 1);
//     public static JoystickButton buttonB = new JoystickButton(driverController, 2);
//     public static JoystickButton buttonX = new JoystickButton(driverController, 3);
//     public static JoystickButton buttonY = new JoystickButton(driverController, 4);
//     public static JoystickButton rBumper = new JoystickButton(driverController, 6);
//     public static JoystickButton lBumper = new JoystickButton(driverController, 5);
//     public static BooleanSupplier rtSupplier = () -> getRTPercent() > Constants.OperatorConstants.RIGHT_TRIGGER_DEADZONE;
//     public static BooleanSupplier ltSupplier = () -> getLTPercent() > Constants.OperatorConstants.LEFT_TRIGGER_DEADZONE;
//     public static TriggerButton rt = new TriggerButton(rtSupplier);
//     public static TriggerButton lt = new TriggerButton(ltSupplier);
//     public static JoystickButton menuButton = new JoystickButton(driverController, 8);
//     public static JoystickButton viewButton = new JoystickButton(driverController, 7);


//     public static double getRTPercent() {
//         return driverController.getRightTriggerAxis();
//     }

//     public static double getLTPercent() {
//         return driverController.getLeftTriggerAxis();
//     }
    
    
//     public static double getDriverLeftY(){
//         if (Math.abs(driverController.getLeftY()) < 0.075){
//             return 0;
//         } else {
//             return driverController.getLeftY();
//         }
//     }
//     public static double getDriverLeftX(){
//         if (Math.abs(driverController.getLeftX()) < 0.075){
//             return 0;
//         } else {
//             return driverController.getLeftX();
//         }
//     }
//     public static double getDriverRightY(){
//         if (Math.abs(driverController.getRightY()) < 0.075){
//             return 0;
//         } else {
//             return driverController.getRightY();
//         }
//     }
//     public static double getDriverRightX(){
//         if (Math.abs(driverController.getRightX()) < 0.075){
//             return 0;
//         } else {
//             return driverController.getRightX();
//         }
//     }
// }

// Copyrights (c) 2018-2019 FIRST, 2020 Highlanders FRC. All Rights Reserved.
//hi om

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Tools.TriggerButton;

public class OI {
    public static XboxController driverController = new XboxController(0);
    public static XboxController operatorController = new XboxController(1);

    public static JoystickButton driverA = new JoystickButton(driverController, 1);
    public static JoystickButton driverB = new JoystickButton(driverController, 2);

    public static JoystickButton driverY = new JoystickButton(driverController, 4);
    public static JoystickButton driverX = new JoystickButton(driverController, 3);

    public static TriggerButton driverRT = new TriggerButton(driverController, 3);
    public static TriggerButton driverLT = new TriggerButton(driverController, 2);
    public static JoystickButton driverRB = new JoystickButton(driverController, 6);
    public static JoystickButton driverLB = new JoystickButton(driverController, 5);

    public static JoystickButton operatorX = new JoystickButton(operatorController, 3);
    public static JoystickButton operatorB = new JoystickButton(operatorController, 2);

    public static JoystickButton operatorY = new JoystickButton(operatorController, 4);
    public static JoystickButton operatorA = new JoystickButton(operatorController, 1);
    
    public static TriggerButton operatorRT = new TriggerButton(operatorController, 3);
    public static TriggerButton operatorLT = new TriggerButton(operatorController, 2);
    public static JoystickButton operatorRB = new JoystickButton(operatorController, 6);
    public static JoystickButton operatorLB = new JoystickButton(operatorController, 5);

    public static JoystickButton driverViewButton = new JoystickButton(driverController, 7);

    public static JoystickButton operatorViewButton = new JoystickButton(operatorController, 7);
    public static JoystickButton driverMenuButton = new JoystickButton(driverController, 8);

    public static JoystickButton operatorMenuButton = new JoystickButton(operatorController, 8);

    public static Joystick autoChooser = new Joystick(2);

    public static double getDriverLeftX() {
        return driverController.getLeftX();
    }

    public static double getDriverLeftY() {
        return driverController.getLeftY();
    }

    public static double getDriverRightX() {
        return driverController.getRightX();
    }

    public static double getDriverRightY() {
        return driverController.getRightY();
    }

    public static boolean getDriverA() {
        return driverController.getAButton();
    }

    public static boolean isRedSide() {
        return autoChooser.getRawButton(6);
    }

    public static boolean isBlueSide() {
        return autoChooser.getRawButton(8);
    }

    public static boolean is2Plus1ClearSideAuto(){
        return autoChooser.getRawButton(4);
    }

    public static boolean is1PieceAuto() {
        return autoChooser.getRawButton(1);
    }

    public static boolean is2PieceBumpSideAuto() {
        return autoChooser.getRawButton(2);
    }

    public static boolean is3PieceBumpSideAuto() {
        return autoChooser.getRawButton(3);
    }

    public static boolean is3PieceClearSideAuto(){
        return autoChooser.getRawButton(5);
    }

    public static boolean isDocking() {
        return autoChooser.getRawButton(7);
    }

    public static int getPOV() {
        return driverController.getPOV();
    }

}