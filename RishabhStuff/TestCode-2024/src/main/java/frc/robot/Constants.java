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
  
  public static final double ROBOT_LENGTH = inchesToMeters(29);

  public static final double ROBOT_WIDTH = inchesToMeters(25);

  public static final double SWERVE_MODULE_OFFSET = inchesToMeters(2.5);

  public static final double ROBOT_RADIUS = inchesToMeters(15.375);

  public static final double GEAR_RATIO = 6.75;

  public static final double STEER_GEAR_RATIO = (150.0/7);

  public static final double TOP_SPEED = feetToMeters(20);

  public static final double WHEEL_DIAMETER = inchesToMeters(4);

  public static final double WHEEL_Radius = inchesToMeters(2);

  public static final double WHEEL_CIRCUMFRENCE = Math.PI * WHEEL_DIAMETER;

  public static final double Wheel_Rotations_In_A_Meter = 1 / WHEEL_CIRCUMFRENCE;

  public static final double FIELD_WIDTH = 8.2;
  
  public static final double FIELD_LENGTH = 16.63;

  public static final double SIDE_INTAKE_GEAR_RATIO = 75;

  public static final double FALCON_TICS_PER_ROTATION = 2048;

  public static final double CANCODER_TICS_PER_ROTATION = 4096;

  public static final double MAX_FALCON_TICS_PER_SECOND = (25 * FALCON_TICS_PER_ROTATION)/10; // raw sensor units/100 ms

  public static final double MAX_FALCON_TICS_PER_SECOND_PER_SECOND = (75 * FALCON_TICS_PER_ROTATION)/10; // raw sensor units/100 ms

  // max of falcon with regular firmware = 6380 rpm, with phoenix pro ~ 6000 rpm, converting to rps
  public static final double MAX_FALCON_ROTATIONS_PER_SECOND = 80;
  // max acceleration of a falcon - calculated by deciding that we need to reach max speed in 1 second
  public static final double MAX_FALCON_ROTATIONS_PER_SECOND_PER_SECOND = 200;
  // max jerk of a falcon - calculated by deciding that we need to reach max acceleration in 1 second
  public static final double MAX_FALCON_ROTATIONS_PER_SECOND_PER_SECOND_PER_SECOND = 2000;

  public static final double EXTENSION_GEAR_RATIO = 10.13;
  public static final double EXTENSION_INCHES_PER_ROTATION = 1.5038 * Math.PI;
  public static final double MAX_EXTENSION = 40;

  public static final double TOTAL_TICS_PER_INCH = (2.0 * ((1.0 / FALCON_TICS_PER_ROTATION) / EXTENSION_GEAR_RATIO) * EXTENSION_INCHES_PER_ROTATION);

  public static double wristOffsetMidMatch = 0;

  public static double wristOffsetDiffArm = 0;

  public static enum PRESET {
    HIGH_PLACEMENT,
    MID_PLACEMENT,
    LOW_PLACEMENT,
    UPRIGHT_CONE,
    TIPPED_CONE,
    CUBE,
    SHELF
  }

  // FRONT SIDE (battery), extensions are same on both sides
  // High row placement
  public static final double HIGH_PLACEMENT_FRONTSIDE_ARM_ROTATION = 135;
  public static final double HIGH_PLACEMENT_FRONTSIDE_WRIST_ROTATION = 86 + wristOffsetDiffArm;
  public static final double HIGH_PLACEMENT_ARM_EXTENSION = 36.5;

  // Mid row placement
  public static final double MID_PLACEMENT_FRONTSIDE_ARM_ROTATION = 138.5;
  public static final double MID_PLACEMENT_FRONTSIDE_WRIST_ROTATION = 79 + wristOffsetDiffArm;
  public static final double MID_PLACEMENT_ARM_EXTENSION = 17.5;

  // low placement
  public static final double LOW_PLACEMENT_FRONTSIDE_WRIST_ROTATION = 62 + wristOffsetDiffArm;
  public static final double LOW_PLACEMENT_FRONTSIDE_ARM_ROTATION = 150;

  // Upright cone intaking
  public static final double UPRIGHT_CONE_FRONTSIDE_ARM_ROTATION = 101;
  public static final double UPRIGHT_CONE_FRONTSIDE_WRIST_ROTATION = 120 + wristOffsetDiffArm;

  // Tipped cone intaking
  public static final double TIPPED_CONE_FRONTSIDE_ARM_ROTATION = 69.5;
  public static final double TIPPED_CONE_FRONTSIDE_WRIST_ROTATION = 183 + wristOffsetDiffArm;

  // Cube intaking
  public static final double CUBE_FRONTSIDE_ARM_ROTATION = 93.2;
  public static final double CUBE_FRONTSIDE_WRIST_ROTATION = 118 + wristOffsetDiffArm;

  // Shelf intaking
  public static final double SHELF_FRONTSIDE_ARM_ROTATION = 162.5;
  public static final double SHELF_FRONTSIDE_WRIST_ROTATION = 66 + wristOffsetDiffArm;
  public static final double SHELF_ARM_EXTENSION = 17.5;

  // BACK SIDE (worm gear)
  // High row placement
  public static final double HIGH_PLACEMENT_BACKSIDE_ARM_ROTATION = 223.5;
  public static final double HIGH_PLACEMENT_BACKSIDE_WRIST_ROTATION = 273 + wristOffsetDiffArm;

  // Mid row placement
  public static final double MID_PLACEMENT_BACKSIDE_ARM_ROTATION = 220.8;
  public static final double MID_PLACEMENT_BACKSIDE_WRIST_ROTATION = 281 + wristOffsetDiffArm;

  // low placement
  public static final double LOW_PLACEMENT_BACKSIDE_WRIST_ROTATION = 290 + wristOffsetDiffArm;
  public static final double LOW_PLACEMENT_BACKSIDE_ARM_ROTATION = 210;

  // Upright cone intaking
  public static final double UPRIGHT_CONE_BACKSIDE_ARM_ROTATION = 263;
  public static final double UPRIGHT_CONE_BACKSIDE_WRIST_ROTATION = 231 + wristOffsetDiffArm;

  // Tipped cone intaking
  public static final double TIPPED_CONE_BACKSIDE_ARM_ROTATION = 290;
  public static final double TIPPED_CONE_BACKSIDE_WRIST_ROTATION = 175.4 + wristOffsetDiffArm;

  // Cube intaking
  public static final double CUBE_BACKSIDE_ARM_ROTATION = 268.5;
  public static final double CUBE_BACKSIDE_WRIST_ROTATION = 241 + wristOffsetDiffArm;

  // Shelf intaking
  public static final double SHELF_BACKSIDE_ARM_ROTATION = 199;
  public static final double SHELF_BACKSIDE_WRIST_ROTATION = 296 + wristOffsetDiffArm;

  public static final double[][] TAG_LOCATIONS = new double[][] {
    {15.513558, 1.071626, 0.462788},
    {15.513558, 2.748026, 0.462788},
    {15.513558, 4.424426, 0.462788},
    {16.178784, 6.749796, 0.695452},
    {0.36195, 6.749796, 0.695452},
    {1.02743, 4.4224426, 0.462788},
    {1.02743, 2.748026, 0.462788},
    {1.02743, 1.071626, 0.462788},
  };

  public static double degreesToRotations(double degrees){
    return degrees / 360;
  }

  public static double rotationsToDegrees(double rotations){
    return rotations * 360;
  }

  public static double radiansToRotations(double radians){
    return radians / (2 * Math.PI);
  }

  public static double rotationsToRadians(double rotations){
    return rotations * (2 * Math.PI);
  }

  public static double inchesToMeters(double inches){
    return inches * 0.0254;
  }

  public static double metersToInches(double meters){
    return meters / 0.0254;
  }

  public static double feetToMeters(double feet){
    double inches = feet * 12;
    return inchesToMeters(inches);
  }

  public static double angleToUnitVectorI(double angle){
    return (Math.cos(angle));
  }

  public static double angleToUnitVectorJ(double angle){
    return (Math.sin(angle));
  }

  public double MPSToRPS(double mps){
    return (mps * Constants.Wheel_Rotations_In_A_Meter);
  }

  public double RPSToMPS(double rps){
    return (rps / Constants.Wheel_Rotations_In_A_Meter);
  }

  public static double getSideIntakeDegreesFromRotations(double rotations) {
    return 360 * (rotations/SIDE_INTAKE_GEAR_RATIO);
  }

  public static double getSideIntakeRotationsFromDegrees(double degrees) {
    return (degrees * SIDE_INTAKE_GEAR_RATIO)/360;
  }

  public static double convertArmRotationDegreesToTics(double degrees) {
    return (CANCODER_TICS_PER_ROTATION * degrees)/360;
  }

  public static double convertArmRotationTicsToDegrees(double tics) {
    return (360 * tics)/CANCODER_TICS_PER_ROTATION;
  }

  public static double getArmExtensionInches(double tics) {
    return tics * TOTAL_TICS_PER_INCH;
  }

  public static double getArmExtensionTics(double inches) {
    return inches/TOTAL_TICS_PER_INCH;
  }

  public static double getArmExtensionRotations(double inches) {
    return getArmExtensionTics(inches)/FALCON_TICS_PER_ROTATION;
  }

  public static double getArmExtensionInchesFromRotations(double rotations) {
    return getArmExtensionInches(rotations) * FALCON_TICS_PER_ROTATION;
  }

  public static void increaseWristOffset() {
    wristOffsetMidMatch += 3.75;
  }

  public static void decreaseWristOffset() {
    wristOffsetMidMatch -= 3.75;
  }
}
