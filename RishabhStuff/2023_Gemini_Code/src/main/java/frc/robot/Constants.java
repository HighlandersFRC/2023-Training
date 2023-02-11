// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final double RIGHT_TRIGGER_DEADZONE = 0.1;
    public static final double LEFT_TRIGGER_DEADZONE = 0.1;
  }
  public static double metersToTicks(double meters) {
    double ticks = meters * 55237.82546;
    return ticks;
  }
  public static double ticksToMeters(double ticks) {
    double meters = ticks / 55237.82546;
    return meters;
  }
  public static double MPS_To_TicksPer100MS(double FPS){
    return FPS * 5523.78254;
  }
  public static double TicksPer100MS_To_MPS(double TP100MS){
    return TP100MS / 5523.78254;
  }
  public static double RPMtoTPM(double rotations){
    return rotations * 1024;
  }
  public static double TPMtoRPM(double ticks){
    return ticks / 1024;
  }
  public static double RPMtoTicksPer100ms(double RPM){
    return RPM * 2.844444433;
  }
  public static double TicksPer100mstoRPM(double TP100ms){
    return TP100ms / 2.844444433;
  }
  final public static double setDriveSpeed = 4;
  final public static double shooterSpeedRPM = 1000;
}
