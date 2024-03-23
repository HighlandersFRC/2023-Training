package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.tools.TriggerButton;

public class OI {
    public static XboxController driverController = new XboxController(0);
    public static XboxController operatorController = new XboxController(1);

    public static BooleanSupplier driverRTSupplier = () -> getDriverRTPercent() > 0.1;
    public static BooleanSupplier driverLTSupplier = () -> getDriverLTPercent() > 0.1;

    public static TriggerButton driverRT = new TriggerButton(driverRTSupplier);
    public static TriggerButton driverLT = new TriggerButton(driverLTSupplier);

    public static double getDriverRTPercent() {
        return driverController.getRightTriggerAxis();
    }

    public static double getDriverLTPercent() {
        return driverController.getLeftTriggerAxis();
    }

    public static BooleanSupplier operatorRTSupplier = () -> getOperatorRTPercent() > 0.1;
    public static BooleanSupplier operatorLTSupplier = () -> getOperatorLTPercent() > 0.1;

    public static TriggerButton operatorRT = new TriggerButton(operatorRTSupplier);
    public static TriggerButton operatorLT = new TriggerButton(operatorLTSupplier);

    public static double getOperatorRTPercent() {
        return operatorController.getRightTriggerAxis();
    }

    public static double getOperatorLTPercent() {
        return operatorController.getLeftTriggerAxis();
    }


    public static JoystickButton driverA = new JoystickButton(driverController, 1);
    public static JoystickButton driverB = new JoystickButton(driverController, 2);

    public static JoystickButton driverX = new JoystickButton(driverController, 3);
    public static JoystickButton driverY = new JoystickButton(driverController, 4);

    public static JoystickButton driverLB = new JoystickButton(driverController, 5);
    public static JoystickButton driverRB = new JoystickButton(driverController, 6);

    public static JoystickButton driverViewButton = new JoystickButton(driverController, 7);
    public static JoystickButton driverMenuButton = new JoystickButton(driverController, 8);

    public static JoystickButton operatorA = new JoystickButton(operatorController, 1);
    public static JoystickButton operatorB = new JoystickButton(operatorController, 2);

    public static JoystickButton operatorX = new JoystickButton(operatorController, 3);
    public static JoystickButton operatorY = new JoystickButton(operatorController, 4);

    public static JoystickButton operatorLB = new JoystickButton(operatorController, 5);
    public static JoystickButton operatorRB = new JoystickButton(operatorController, 6);

    public static JoystickButton operatorViewButton = new JoystickButton(operatorController, 7);
    public static JoystickButton operatorMenuButton = new JoystickButton(operatorController, 8);

    public static Joystick autoChooser = new Joystick(2);

    public static boolean getDriverRT() {
        if(getDriverRTPercent() > 0.1) {
            return true;
        } else return false;
    }

    public static boolean getDriverLT() {
        if(getDriverLTPercent() > 0.1) {
            return true;
        } else return false;
    }

    public static boolean getDriverRB() {
        return driverController.getRightBumper();
    }

    public static boolean getDriverLB() {
        return driverController.getLeftBumper();
    }

    public static boolean getDriverA() {
        return driverController.getAButton();
    }

    public static boolean getDriverB() {
        return driverController.getBButton();
    }

    public static boolean getDriverX() {
        return driverController.getXButton();
    }

    public static boolean getDriverY() {
        return driverController.getYButton();
    }

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

    public static int getDriverPOV() {
        return driverController.getPOV();
    }

    public static boolean getOperatorRT() {
        if(getOperatorRTPercent() > 0.1) {
            return true;
        } else return false;
    }

    public static boolean getOperatorLT() {
        if(getOperatorLTPercent() > 0.1) {
            return true;
        } else return false;
    }

    public static boolean getOperatorRB() {
        return operatorController.getRightBumper();
    }

    public static boolean getOperatorLB() {
        return operatorController.getLeftBumper();
    }

    public static boolean getOperatorA() {
        return operatorController.getAButton();
    }

    public static boolean getOperatorB() {
        return operatorController.getBButton();
    }

    public static boolean getOperatorX() {
        return operatorController.getXButton();
    }

    public static boolean getOperatorY() {
        return operatorController.getYButton();
    }

    public static double getOperatorLeftX() {
        return operatorController.getLeftX();
    }

    public static double getOperatorLeftY() {
        return operatorController.getLeftY();
    }

    public static double getOperatorRightX() {
        return operatorController.getRightX();
    }

    public static double getOperatorRightY() {
        return operatorController.getRightY();
    }

    public static int getOperatorPOV() {
        return operatorController.getPOV();
    }

    public static boolean isDial1(){
        return autoChooser.getRawButton(1);
    }

    public static boolean isDial2(){
        return autoChooser.getRawButton(2);
    }

    public static boolean isDial3(){
        return autoChooser.getRawButton(3);
    }
    
    public static boolean isDial4(){
        return autoChooser.getRawButton(4);
    }
    
    public static boolean isDial5(){
        return autoChooser.getRawButton(5);
    }

    public static boolean isSwitch1() {
        return autoChooser.getRawButton(6);
    }

    public static boolean isSwitch3() {
        return autoChooser.getRawButton(8);
    }

    public static boolean isSwitch2() {
        return autoChooser.getRawButton(7);
    }


}