// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.ColorFlowAnimation;
import com.ctre.phoenix.led.LarsonAnimation;
import com.ctre.phoenix.led.RainbowAnimation;
import com.ctre.phoenix.led.RgbFadeAnimation;
import com.ctre.phoenix.led.SingleFadeAnimation;
import com.ctre.phoenix.led.StrobeAnimation;
import com.ctre.phoenix.led.TwinkleAnimation;
import com.ctre.phoenix.led.ColorFlowAnimation.Direction;
import com.ctre.phoenix.led.LarsonAnimation.BounceMode;
import com.ctre.phoenix.led.TwinkleAnimation.TwinklePercent;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.Lights;
/* 
 * This command showcases most of the different animations that you can use
 * and shows you how to implement them based on the functions in lights subsystem.
 * BEFORE SETTING UP YOUR OWN STUFF, LOOK THROUGH Lights.java TO GAIN UNDERSTANDING
 */
public class LightsDefault extends CommandBase {
  /** Creates a new LightsDefault. */
  //Declares an array to be fed to the multipleAnimations() method
  Animation[] animations = new Animation[3];
  Lights lights;
  public LightsDefault(Lights lights) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(lights);
    this.lights = lights;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //You can add your own values to 
    animations[0] = new ColorFlowAnimation(0, 255, 0, 0, 0.8, 100, Direction.Forward, 8);
    animations[1] = new LarsonAnimation(255, 0, 0, 0, 0.5, 100, BounceMode.Back, 1, 108);
    animations[2] = new StrobeAnimation(0, 0, 255, 0, 0.5, 41, 208);
    lights.multipleAnimations(animations);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (OI.buttonA.getAsBoolean()){
      //Strobe/Blinking Animation. For speed it is a number between 0 and 1
      lights.setAnimation(new ColorFlowAnimation(0, 255, 0, 0, 0.8, 100, Direction.Forward, 8));
    } else if (OI.buttonB.getAsBoolean()){
      //Larson has a color flying around the strip. size is the number of leds on at once
      lights.setAnimation(new LarsonAnimation(255, 0, 0, 0, 0.5, 100, BounceMode.Back, 1, 108));
    } else if (OI.buttonX.getAsBoolean()){
      //Slowly turns on all of the lights
      lights.setAnimation(new StrobeAnimation(0, 0, 255, 0, 0.5, 41, 208));
    } else if (OI.buttonY.getAsBoolean()){
      //Brightness goes low then high then low with only one color
      lights.setAnimation(new SingleFadeAnimation(255, 255, 0, 0, 0.5, 241, 8));
    } else if (OI.lBumper.getAsBoolean()){
      //Random LEDs turn on and off
      lights.setAnimation(new TwinkleAnimation(0, 255, 255, 0, 0.5, 241, TwinklePercent.Percent42, 8));
    } else if (OI.rBumper.getAsBoolean()){
      //Rainbow moving around the strip
      lights.setAnimation(new RainbowAnimation(0.8, 0.5, 241, false, 8));
    } else if (OI.rt.getAsBoolean()){
      //Same as Fade, but color changes after every low in brightness
      lights.setAnimation(new RgbFadeAnimation(0.5, 0.5, 241, 8));
    } else if (OI.lt.getAsBoolean()){
      //You can mix a bunch of animations on different parts of the strip
      lights.multipleAnimations(animations);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
