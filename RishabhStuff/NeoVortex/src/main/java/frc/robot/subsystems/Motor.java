package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkPIDController.AccelStrategy;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkPIDController;

public class Motor extends SubsystemBase {
  CANSparkFlex vortex = new CANSparkFlex(1, MotorType.kBrushless);
  public Motor() {}
  // private final SparkAbsoluteEncoder vortexEncoder = vortex.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle);
  private final RelativeEncoder vortexEncoder = vortex.getEncoder();
  private final SparkPIDController vortexPID = vortex.getPIDController();
  private double position; //rotations
  private double velocity; //rpm

  public void init() {
    // position = 0;
    // vortexPID.setP(0.00005, 0);
    // vortexPID.setI(0.0, 0);
    // vortexPID.setD(0.0, 0);
    // vortexPID.setFF(0.003, 0);
    // vortexPID.setOutputRange(-1, 1);
    // vortexPID.setSmartMotionMaxVelocity(255, 0);
    // vortexPID.setSmartMotionMinOutputVelocity(-255, 0);
    // vortexPID.setSmartMotionMaxAccel(100, 0);
    // vortexPID.setSmartMotionAccelStrategy(AccelStrategy.kTrapezoidal, 0);
    // vortexPID.setSmartMotionAllowedClosedLoopError(0.2, 0);
    // vortexEncoder.setPositionConversionFactor(1);
    // vortexEncoder.setVelocityConversionFactor(1);
    // vortex.setIdleMode(IdleMode.kCoast);
    velocity = 0;
    vortexPID.setP(0.00012, 0);
    vortexPID.setI(0.000001, 0);
    vortexPID.setD(0.0, 0);
    vortexPID.setFF(0.00011, 0);
    vortexPID.setOutputRange(-1, 1);
    vortexEncoder.setPositionConversionFactor(1);
    vortexEncoder.setVelocityConversionFactor(1);
    vortex.setIdleMode(IdleMode.kCoast);
    // vortex.restoreFactoryDefaults();
  }

  public void setVortexPosition(double newPosition){
    position = newPosition;
  }

  public void setVortexVelocity(double rpm) {
    velocity = rpm;
  }

  public double getVortexVelocity() {
    return vortexEncoder.getVelocity();
  }

  // public double getVortexOffset() {
  //   return vortexEncoder.getZeroOffset();
  // }

  public void moveVortex(double speed){
    vortex.set(speed);
  }

  // public void zeroVortexPosition() {
  //   vortexEncoder.setZeroOffset(getVortexPosition());
  // }

  public double getVortexPosition() {
    return vortexEncoder.getPosition();
  }

  @Override
  public void periodic() {
    // vortexPID.setReference(position, CANSparkBase.ControlType.kSmartMotion);
    vortexPID.setReference(velocity, CANSparkBase.ControlType.kVelocity);
    SmartDashboard.putNumber("velocity-graph", vortexEncoder.getVelocity());
    SmartDashboard.putNumber("velocity-number", vortexEncoder.getVelocity());
  // System.out.println(vortexEncoder.getVelocity());
 }
}
