package frc.robot.tank;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;


public class Tank {
    public static VictorSPX leftBack, leftFront, rightBack, rightFront;
    public static Joystick Driverstick;
    public static double leftSpeed;
    public static double rightSpeed;

    public static void init(){
        leftBack = new VictorSPX(1);
        leftFront = new VictorSPX(5);

        rightBack = new VictorSPX(6);
        rightFront = new VictorSPX(10);
        Driverstick = new Joystick(0);
        leftSpeed=0;
        rightSpeed = 0;
    }

    /**former params: double leftSpeed, double rightSpeed*/

    public static void drive() {
        
        leftSpeed = Driverstick.getRawAxis(0);
        rightSpeed = Driverstick.getRawAxis(4);

        leftBack.set(VictorSPXControlMode.PercentOutput, leftSpeed);
        leftFront.set(VictorSPXControlMode.PercentOutput, leftSpeed);
        rightBack.set(VictorSPXControlMode.PercentOutput, rightSpeed);
        rightFront.set(VictorSPXControlMode.PercentOutput, rightSpeed);
    }

}