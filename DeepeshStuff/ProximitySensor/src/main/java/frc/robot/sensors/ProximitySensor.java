// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

/** Add your docs here. */
public class ProximitySensor {
    private int portIdx;
    private DigitalInput dio;
    
    public ProximitySensor(int portIdx){
        // portNum is the number of the Digital port (DIO ports on the roboRIO) that the sensor was plugged into.
        this.portIdx = portIdx;
        dio = new DigitalInput(portIdx);
    }

    public boolean isTripped(){
        //Returns true when there is an object in the sensor's Range
        //The sensor's range must be tuned manually by adjusting the screw next to the light on the sensor
        boolean retval = true;
        retval = !dio.get();
        return retval;
    }

    public int getPortIdx(){
        return portIdx;
    }
}
