
package frc.robot.tools;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class OI {

   public static BooleanSupplier rtSupplier = () -> getRTPercent() > Constants.OperatorConstants.RIGHT_TRIGGER_DEADZONE;
   public static BooleanSupplier ltSupplier = () -> getLTPercent() > Constants.OperatorConstants.LEFT_TRIGGER_DEADZONE;

    public static XboxController driverController = new XboxController(0);
    public static TriggerButton rt = new TriggerButton(rtSupplier);
    public static TriggerButton lt = new TriggerButton(ltSupplier);

    public static double getRTPercent() {
        return driverController.getRightTriggerAxis();
    }

    public static double getLTPercent() {
        return driverController.getLeftTriggerAxis();
    }

    public static boolean isRT() {
        if(getRTPercent() > Constants.OperatorConstants.RIGHT_TRIGGER_DEADZONE) {
            return true;
        } else return false;
    }

    public static boolean isLT() {
        if(getLTPercent() > Constants.OperatorConstants.LEFT_TRIGGER_DEADZONE) {
            return true;
        } else return false;
    }

}