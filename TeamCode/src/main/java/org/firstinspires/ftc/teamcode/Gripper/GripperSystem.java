package org.firstinspires.ftc.teamcode.Gripper;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class GripperSystem {
    private final ServoEx gripperCatcherServo;
    private final ServoEx gripperAngleServo;
    private Gamepad gripperGamepad;

    public GripperSystem(HardwareMap constHardwareMap, Gamepad constGamepad){
        gripperCatcherServo = new SimpleServo(constHardwareMap, "catcher", 0, 360, AngleUnit.DEGREES);

        gripperAngleServo = new SimpleServo(constHardwareMap, "angle", 200, 270,AngleUnit.DEGREES);

        gripperGamepad = constGamepad;
    }

    public void anglePlace(double angle){
        angle = gripperGamepad.right_stick_y * 55 + 215;
        if(gripperGamepad.right_stick_button){
            gripperAngleServo.turnToAngle(angle);
        }
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
