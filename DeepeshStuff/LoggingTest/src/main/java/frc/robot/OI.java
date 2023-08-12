package frc.robot;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.wpilibj.XboxController;

@AutoLog
public class OI {
    static XboxController driver = new XboxController(0);
    static double driverLeftX = driver.getLeftX();
    static double driverLeftY = driver.getLeftY();
    static double driverRightX = driver.getRightX();
    static double driverRightY = driver.getRightY();
    static boolean driverA = driver.getAButton();
    static boolean driverB = driver.getBButton();
    static boolean driverX = driver.getXButton();
    static boolean driverY = driver.getYButton();
    static void periodic(){
        driverLeftX = driver.getLeftX();
        driverLeftY = driver.getLeftY();
        driverRightX = driver.getRightX();
        driverRightY = driver.getRightY();
        driverA = driver.getAButton();
        driverB = driver.getBButton();
        driverX = driver.getXButton();
        driverY = driver.getYButton();
    }
}
