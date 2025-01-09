
package org.firstinspires.ftc.teamcode.Arm.ArmAngle;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class AngleControl extends OpMode {

    private DcMotor angleControl;

    @Override
    public void init() {
        angleControl = hardwareMap.get(DcMotor.class, "angleControl");
    }

    @Override
    public void loop() {
        boolean upDegreeControl = gamepad1.dpad_up;
        boolean downDegreeControl = gamepad1.dpad_down;
        if (upDegreeControl == true) {
            angleControl.setPower(0.6);
        } else if (upDegreeControl == false) {
            angleControl.setPower(0);
        }
        if (downDegreeControl== true){
            angleControl.setPower(-0.6);
        }
        else if (downDegreeControl == false) {
            angleControl.setPower(0);
        }
    }
}
