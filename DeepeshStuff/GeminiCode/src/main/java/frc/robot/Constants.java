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
    public static double metersToTicks(double meters) {
      double ticks = meters * 55235.91546114;
      return ticks;
    }
    public static double ticksToMeters(double ticks) {
      double meters = ticks / 55235.91546114;
      return meters;
    }
    public static double MPS_To_TP100MS(double FPS){
      return FPS * 5523.591546114;
    }
    public static double TP100MS_To_MPS(double TP100MS){
      return TP100MS / 5523.591546114;
    }
    final public static double MAX_SPEED_METERS_PER_SECOND = 4;
    final static public double FALCON_ERROR_THRESHOLD = metersToTicks(0.05);
    
}
