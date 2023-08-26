package frc.robot.subsystems;

import org.w3c.dom.css.RGBColor;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.LightsDefault;

public class Lights extends SubsystemBase{
    CANdle candle = new CANdle(0, "Canivore 2");

    public Lights(){}
    
    public void init(){
        setDefaultCommand(new LightsDefault(this));

    }
    
    public void setAnimation(Animation animation){
        //Go to API to see animation options: https://store.ctr-electronics.com/content/api/java/html/classcom_1_1ctre_1_1phoenix_1_1led_1_1_c_a_ndle.html
        candle.animate(animation);
    }
    
    public void setRGB(int r, int g, int b){
        candle.setLEDs(r, g, b);
    }
    
    public void setRange(int r, int g, int b, int w, int idx, int count){
        candle.setLEDs(r, g, b, w, idx, count);
    }
}
