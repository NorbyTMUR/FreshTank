package frc.subsystems;


import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;


public class Tank extends TimedRobot{
    public static VictorSPX leftBack, leftFront, rightBack, rightFront;
    public static Joystick Driverstick;
    public static double leftSpeed;
    public static double rightSpeed;
     // trackwidth; inches
    public static double l = 27;
    public static double omega;
    public static double Vr;
    public static double Vl;
    public static double displacement;
    public static double currentX;
    public static double currentY;
    public static double currentTheta;
    public static double[] newPosition = new double[3];

    public static void init(){
        leftBack = new VictorSPX(1);
        leftFront = new VictorSPX(5);

        rightBack =new  VictorSPX(6);
        rightFront = new VictorSPX(10);
        Driverstick = new Joystick(0);
        
        leftSpeed=0;
        rightSpeed = 0;

        double omega = 0;
        

    }

    /**former params: double leftSpeed, double rightSpeed*/

    public static void drive() {
        leftSpeed = Driverstick.getRawAxis(1);
        rightSpeed = Driverstick.getRawAxis(6);
        
        leftBack.set(VictorSPXControlMode.PercentOutput, leftSpeed);
        leftFront.set(VictorSPXControlMode.PercentOutput, leftSpeed);
        rightBack.set(VictorSPXControlMode.PercentOutput, rightSpeed);
        rightFront.set(VictorSPXControlMode.PercentOutput, rightSpeed);
    }

    /**public static void driveToPoint(double x, double y){
        double angle = Math.atan2(y,x) + (Math.PI/2 - currentPosition([2]));
        //since pi/2 is apparently straight forwards??

    }*/

    public static void groupMotors(VictorSPX motor1, VictorSPX motor2){
        motor1.follow(motor2);
    }

    //returns currentX, currentY, currentHeading, by faking odometry 
    //(integrating velocity over an interval of time using a time-recording class 
    // To-Do: (FIGURE OUT time-recording class - download?), FIGURE OUT HOW TO INCORPORATE ANGLE/jeading into the calculations.
    //in auto, we could probably clear position every frame to reduce the error.
    //returns a double array in the form: [currentX, currentY, currentTheta]
    public static double[] currentPosition(double previousX, double previousY, double previousTheta){

        omega = (Vl - Vr)/l;
        double[] newPosition = new double[3];
        //omega cosTheta finds the x component of angular velocity omega.
        currentX = previousX + omega*Math.cos(currentTheta);
        currentY = previousY + omega*Math.sin(currentTheta);
        //omega* timeBetweenNowAndTheLastFrame
        currentTheta = previousTheta + (omega * 30);

        if(Vr == Vl){
            //find the time between now and the last frame, idk how
            //difference between frames is 30 millisecs
            currentY = previousY + (Vr * 30);
        }
        newPosition[0] = currentX;
        newPosition[1] = currentY;
        newPosition[2] = currentTheta;     
        return newPosition;
    }

}