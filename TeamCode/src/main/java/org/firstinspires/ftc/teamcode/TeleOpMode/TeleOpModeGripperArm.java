package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;

@TeleOp
public class TeleOpModeGripperArm extends OpMode {
    Arm myArm;
    Gripper myGripper;

    @Override
    public void init() {
        myArm = new Arm(hardwareMap, gamepad2);

        myGripper = new Gripper(hardwareMap, gamepad2);
    }

    @Override
    public void loop() {
        myArm.rightTriggerExtension();
        myArm.rightBumperRetraction();
        myArm.dpadAngle();

        myGripper.anglePlace(215);
        myGripper.catcherDirection();

        telemetry.update();
        telemetry.addData("gripper angle", myGripper.getAngle());
//        telemetry.addData("")
    }
}
