package frc.robot.subsystems;

import org.w3c.dom.css.RGBColor;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Tools.Exceptions.TooManyAnimationsException;
import frc.robot.commands.LightsDefault;
/*
 * This Subsystem has all of the methods that you will need to control the lights
 * Use the subsystem as
 */
public class Lights extends SubsystemBase{
    //Instantiate the candle with its correct id and canbus
    CANdle candle = new CANdle(0, "Canivore 2");

    public Lights(){}
    
    public void init(){
        //set your default command here
        setDefaultCommand(new LightsDefault(this));
    }
    
    public void setAnimation(Animation animation){
        //Go to API to see animation options: https://store.ctr-electronics.com/content/api/java/html/classcom_1_1ctre_1_1phoenix_1_1led_1_1_c_a_ndle.html
        clearAllAnimations();
        candle.animate(animation);
    }
    
    public void setRGB(int r, int g, int b){
        clearAllAnimations();
        candle.setLEDs(r, g, b);
    }
    
    public void setRange(int r, int g, int b, int w, int startIdx, int numLED){
        clearAllAnimations();
        candle.setLEDs(r, g, b, w, startIdx, numLED);
    }
    public void clearAllAnimations(){
        for (int i = 0; i < 10; i++)candle.clearAnimation(i);
    }
    public void multipleAnimations(Animation[] animations/*array of animations, each is assigned to its own animation slot*/){
        clearAllAnimations();
        int i = 0;
        for (Animation animation : animations) {
            candle.animate(animation, i);
            i++;
            if (i == candle.getMaxSimultaneousAnimationCount()){
                //The candle has a maximum number of animations. In 2023 it is 10
                break;
            }
        }
    }
}
