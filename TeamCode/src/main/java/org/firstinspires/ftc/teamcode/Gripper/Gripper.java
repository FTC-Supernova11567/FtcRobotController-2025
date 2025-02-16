package org.firstinspires.ftc.teamcode.Gripper;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class Gripper {
    private final ServoEx gripperCatcherServo;
    private final ServoEx gripperAngleServo;

    public Gripper(HardwareMap constHardwareMap) {
        gripperCatcherServo = new SimpleServo(constHardwareMap, "catcher", 0 , 1, AngleUnit.DEGREES);
        gripperAngleServo = new SimpleServo(constHardwareMap, "angle", -1 , 1, AngleUnit.DEGREES);

    }

    public void moveHook(double angle){gripperCatcherServo.turnToAngle(angle);}

    public void turnToAngle(double angle){gripperAngleServo.turnToAngle(angle);}
    public void moveToAngle(double angleChange){
        gripperAngleServo.rotateByAngle(angleChange);
    }

    public double getPosition() {
        return gripperAngleServo.getPosition();
    }
    public double getAngle() {
        return gripperAngleServo.getAngle();
    }

}
