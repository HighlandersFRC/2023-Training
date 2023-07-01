package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Defaults.DriveDefault;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
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
  public DriveSubsystem() {

  }

  public void initialize() {
    setUpDriveMotors();
    setDrivePercents(0.0, 0.0);
    setDefaultCommand(new DriveDefault(this));
  }

  @Override
  public void periodic() {  }

  public void tankDrive(double left, double right) {
    left *= Math.abs(left);
    right *= Math.abs(right);
    setDrivePercents(left, right);
  }

  
  public void arcadeDrive(double power, double direction) {
    power *= Math.abs(power);
    direction *= Math.abs(direction);
    double right = power + direction;
    double left = power - direction;
    double front = 1;
    
    if (right > 1){
      front = right;
    } else if (left > 1){
      front = left;
    } 
      right = right / front;
      left = left / front;
  


    setDrivePercents(left, right);
  }

  public static void setDrivePercents(double left, double right){
    frontRight.set(ControlMode.PercentOutput, right);
    frontLeft.set(ControlMode.PercentOutput, left);
  }
}

