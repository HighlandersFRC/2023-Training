// Copyrights (c) 2018-2019 FIRST, 2020 Highlanders FRC. All Rights Reserved.
//hi om

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.tools.TriggerButton;

public class OI {
    public static XboxController driverController = new XboxController(0);
    public static XboxController operatorController = new XboxController(1);

    public static BooleanSupplier driverRTSupplier = () -> getDriverRTPercent() > Constants.OperatorConstants.RIGHT_TRIGGER_DEADZONE;
    public static BooleanSupplier driverLTSupplier = () -> getDriverLTPercent() > Constants.OperatorConstants.LEFT_TRIGGER_DEADZONE;
    
    public static BooleanSupplier operatorRTSupplier = () -> getOperatorRTPercent() > Constants.OperatorConstants.RIGHT_TRIGGER_DEADZONE;
    public static BooleanSupplier operatorLTSupplier = () -> getOperatorLTPercent() > Constants.OperatorConstants.LEFT_TRIGGER_DEADZONE;

    public static JoystickButton driverA = new JoystickButton(driverController, 1);
    public static JoystickButton driverB = new JoystickButton(driverController, 2);

    public static JoystickButton driverY = new JoystickButton(driverController, 4);
    public static JoystickButton driverX = new JoystickButton(driverController, 3);

    public static TriggerButton driverRT = new TriggerButton(driverRTSupplier);
    public static TriggerButton driverLT = new TriggerButton(driverLTSupplier);
    public static JoystickButton driverRB = new JoystickButton(driverController, 6);
    public static JoystickButton driverLB = new JoystickButton(driverController, 5);

    public static JoystickButton operatorX = new JoystickButton(operatorController, 3);
    public static JoystickButton operatorB = new JoystickButton(operatorController, 2);

    public static JoystickButton operatorY = new JoystickButton(operatorController, 4);
    public static JoystickButton operatorA = new JoystickButton(operatorController, 1);
    
    public static TriggerButton operatorRT = new TriggerButton(operatorRTSupplier);
    public static TriggerButton operatorLT = new TriggerButton(operatorLTSupplier);
    public static JoystickButton operatorRB = new JoystickButton(operatorController, 6);
    public static JoystickButton operatorLB = new JoystickButton(operatorController, 5);

    public static JoystickButton driverViewButton = new JoystickButton(driverController, 7);

    public static JoystickButton operatorViewButton = new JoystickButton(operatorController, 7);
    public static JoystickButton driverMenuButton = new JoystickButton(driverController, 8);

    public static JoystickButton operatorMenuButton = new JoystickButton(operatorController, 8);

    public static Joystick autoChooser = new Joystick(2);

    public static double getDriverLeftY(){
        if (Math.abs(driverController.getLeftY()) < 0.075){
            return 0;
        } else {
            return driverController.getLeftY();
      }
    }
    public static double getDriverLeftX(){
        if (Math.abs(driverController.getLeftX()) < 0.075){
            return 0;
        } else {
            return driverController.getLeftX();
        }
    }
    public static double getDriverRightY(){
        if (Math.abs(driverController.getRightY()) < 0.075){
            return 0;
        } else {
            return driverController.getRightY();
        }
    }
    public static double getDriverRightX(){
        if (Math.abs(driverController.getRightX()) < 0.075){
            return 0;
        } else {
            return driverController.getRightX();
        }
    }

    public static double getDriverRTPercent() {
        return driverController.getRightTriggerAxis();
    }

    public static double getDriverLTPercent() {
        return driverController.getLeftTriggerAxis();
    }

    public static double getOperatorRTPercent() {
        return operatorController.getRightTriggerAxis();
    }

    public static double getOperatorLTPercent() {
        return operatorController.getLeftTriggerAxis();
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