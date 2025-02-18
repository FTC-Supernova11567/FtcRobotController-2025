package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.DriveTrain.ImprovedMecanum;
import org.firstinspires.ftc.teamcode.Utils.Utils;

@TeleOp
public class TestingMode extends LinearOpMode {

    public ImprovedMecanum mecanum;

    @Override
    public void runOpMode() throws InterruptedException {
        mecanum = new ImprovedMecanum(hardwareMap);

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;

        imu.initialize(parameters);

        waitForStart();

        while(opModeIsActive()){
            double deadbend = 0.4;
            double y = Utils.deadbend(-gamepad1.left_stick_y, deadbend);
            double x = Utils.deadbend(gamepad1.left_stick_x, deadbend);
            double rx = Utils.deadbend(gamepad1.right_stick_x, deadbend);

            mecanum.driveWithBuffer(y, x, rx, 1);

            telemetry.addData("current angle", imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
            telemetry.update();

        }
    }

}
