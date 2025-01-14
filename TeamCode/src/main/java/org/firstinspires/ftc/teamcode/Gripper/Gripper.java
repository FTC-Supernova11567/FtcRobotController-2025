package org.firstinspires.ftc.teamcode.Gripper;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class Gripper {
    private final ServoEx gripperCatcherServo;
    private final ServoEx gripperAngleServo;
    private Gamepad gripperGamepad;

    public Gripper(HardwareMap constHardwareMap, Gamepad constGamepad){
        gripperCatcherServo = new SimpleServo(constHardwareMap, "catcher", 0, 360, AngleUnit.DEGREES);

        gripperAngleServo = new SimpleServo(constHardwareMap, "angle", 215, 230,AngleUnit.DEGREES);

        gripperGamepad = constGamepad;
    }

    public void anglePlace(double angle){
        if(gripperGamepad.right_stick_y < 0){
            gripperAngleServo.turnToAngle(222);
        }else if(gripperGamepad.right_stick_y > 0){
            gripperAngleServo.turnToAngle(225);
        }
    }

    public double getAngle(){
        return gripperAngleServo.getAngle();
    }

    public void catcherDirection(){
        if(gripperGamepad.a){
            gripperCatcherServo.turnToAngle(360);
        }
        if(gripperGamepad.b){
            gripperCatcherServo.turnToAngle(0);
        }
    }
}
