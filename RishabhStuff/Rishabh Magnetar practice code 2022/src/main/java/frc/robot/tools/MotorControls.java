package frc.robot.tools;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class MotorControls {
   public final static TalonSRX frontRight = new TalonSRX(3);
   public final static TalonSRX frontLeft = new TalonSRX(1);
   public final static TalonSRX backRight = new TalonSRX(4);
public final static TalonSRX backLeft = new TalonSRX(2);

public static void setUpDriveMotors() {
   frontRight.setInverted(true);
   backRight.setInverted(true);
   backLeft.set(ControlMode.Follower, 1);
   backRight.set(ControlMode.Follower, 3);
}
public static void setDrivePercents(double left, double right){
   frontRight.set(ControlMode.PercentOutput, right);
   frontLeft.set(ControlMode.PercentOutput, left);
}
}
