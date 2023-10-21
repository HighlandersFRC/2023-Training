// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Peripherals extends SubsystemBase {
  /** Creates a new Peripherals. */
  private Pigeon2 pigeon = new Pigeon2(0, "Canivore");

  double pitchOffset = getRawPitch();
  double rollOffset = getRawRoll();

  public Peripherals() {}

  public void setYaw(double degrees){
    pigeon.setYaw(degrees);
  }

  public double getYaw(){
    return pigeon.getYaw().getValue();
  }

  public void setPitch(double degrees){
    pitchOffset = degrees-getRawPitch();
  }

  public double getRawPitch(){
    return pigeon.getPitch().getValue();
  }

  public double getPitch(){
    return pigeon.getPitch().getValue()+pitchOffset;
  }

  public void setRoll(double degrees){
    pitchOffset = degrees-getRawRoll();
  }

  public double getRawRoll(){
    return pigeon.getRoll().getValue();
  }

  public double getRoll(){
    return pigeon.getRoll().getValue()+rollOffset;
  }

  public double getZAcceleration(){
    return pigeon.getAccelerationZ().getValue();
  }

  public double getXAcceleration(){
    return pigeon.getAccelerationX().getValue();
  }

  public double getYAcceleration(){
    return pigeon.getAccelerationY().getValue();
  }

  public double getZAccumulation(){
    return pigeon.getAccumGyroZ().getValue();
  }

  public double getXAccumulation(){
    return pigeon.getAccumGyroX().getValue();
  }

  public double getYAccumulation(){
    return pigeon.getAccumGyroY().getValue();
  }

  public double getYawVelocity(){
    return pigeon.getAngularVelocityZ().getValue();
  }

  public double getPitchVelocity(){
    return pigeon.getAngularVelocityY().getValue();
  }

  public double getRollVelocity(){
    return pigeon.getAngularVelocityX().getValue();
  }

  public double getGravityVectorX(){
    return pigeon.getGravityVectorX().getValue();
  }

  public double getGravityVectorY(){
    return pigeon.getGravityVectorY().getValue();
  }

  public double getGravityVectorZ(){
    return pigeon.getGravityVectorZ().getValue();
  }

  public double getMagneticFieldX(){
    return pigeon.getMagneticFieldX().getValue();
  }

  public double getMagneticFieldY(){
    return pigeon.getMagneticFieldY().getValue();
  }

  public double getMagneticFieldZ(){
    return pigeon.getMagneticFieldZ().getValue();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // log angles
    SmartDashboard.putNumber("Yaw", getYaw());
    SmartDashboard.putNumber("Pitch", getPitch());
    SmartDashboard.putNumber("Roll", getRoll());

    // log acceleration
    SmartDashboard.putNumber("AccelerationX", getXAcceleration());
    SmartDashboard.putNumber("AccelerationY", getYAcceleration());
    SmartDashboard.putNumber("AccelerationZ", getZAcceleration());

    // log gravity vectors
    SmartDashboard.putNumber("GravityVectorX", getGravityVectorX());
    SmartDashboard.putNumber("GravityVectorY", getGravityVectorY());
    SmartDashboard.putNumber("GravityVectorZ", getGravityVectorZ());

    // log magnetic field vectors
    SmartDashboard.putNumber("MagneticFieldX", getMagneticFieldX());
    SmartDashboard.putNumber("MagneticFieldY", getMagneticFieldY());
    SmartDashboard.putNumber("MagneticFieldZ", getMagneticFieldZ());

    // log angular velocities
    SmartDashboard.putNumber("YawVelocity", getYawVelocity());
    SmartDashboard.putNumber("PitchVelocity", getPitchVelocity());
    SmartDashboard.putNumber("RollVelocity", getRollVelocity());
  }
}
