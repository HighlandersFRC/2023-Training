
public class Height {
    public double height(double armInches, double armRadians, double wristRadians) {
        double retval = 0, wristLength = 16.038, pivotHeight = 17.724;
        if (Math.PI/2 <= armRadians && armRadians <= 3*Math.PI/2 && -Math.PI/2 <= wristRadians-armRadians+Math.PI/2 && wristRadians-armRadians+Math.PI/2 >= Math.PI){
            retval = -(armInches*Math.cos(armRadians)+wristLength*Math.cos(wristRadians-armRadians+Math.PI/2))+pivotHeight;
        } else if (Math.PI/2 <= armRadians && armRadians <= 3*Math.PI/2){
            retval = -armInches*Math.cos(armRadians)+pivotHeight;
        } else if (0 <= -(armInches*Math.cos(armRadians)+wristLength*Math.cos(wristRadians-armRadians+Math.PI/2))){
            retval = -(armInches*Math.cos(armRadians)+wristLength*Math.cos(wristRadians-armRadians+Math.PI/2))+pivotHeight;
        } else {
            retval = pivotHeight;
        }
        return retval;
    }
}