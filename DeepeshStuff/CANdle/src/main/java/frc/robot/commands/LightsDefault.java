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

public class LightsDefault extends CommandBase {
  /** Creates a new LightsDefault. */
  Lights lights;
  public LightsDefault(Lights lights) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(lights);
    this.lights = lights;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lights.setAnimation(new StrobeAnimation(255, 255, 255));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (OI.buttonA.getAsBoolean()){
      //Strobe/Blinking Animation. For speed it is a number between 0 and 1
      lights.setAnimation(new StrobeAnimation(0, 255, 0, 0, 0.5, 4, 4));
    } else if (OI.buttonB.getAsBoolean()){
      //Larson has a color flying around the strip. size is the number of leds on at once
      lights.setAnimation(new LarsonAnimation(255, 0, 0, 0, 0.5, 8, BounceMode.Back, 1, 0));
    } else if (OI.buttonX.getAsBoolean()){
      //Slowly turns on all of the lights
      lights.setAnimation(new ColorFlowAnimation(0, 0, 255, 0, 0.5, 8, Direction.Forward, 0));
    } else if (OI.buttonY.getAsBoolean()){
      //Brightness goes low then high then low with only one color
      lights.setAnimation(new SingleFadeAnimation(255, 255, 0, 0, 0.5, 8));
    } else if (OI.lBumper.getAsBoolean()){
      //Random LEDs turn on and off
      lights.setAnimation(new TwinkleAnimation(0, 255, 255, 0, 0.5, 8, TwinklePercent.Percent42, 0));
    } else if (OI.rBumper.getAsBoolean()){
      //Rainbow moving around the strip
      lights.setAnimation(new RainbowAnimation(0.8, 0.5, 8, false, 0));
    } else if (OI.rt.getAsBoolean()){
      //Same as Fade, but color changes after every low in brightness
      lights.setAnimation(new RgbFadeAnimation(0.5, 0.5, 8, 0));
    } else {
      //sending this signal has no effect
      lights.setRGB(0,0,0);
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
