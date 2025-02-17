package org.firstinspires.ftc.teamcode.Autonomous;

import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.Examples.ImprovedMecanum;

import java.util.concurrent.TimeUnit;

@Autonomous
public class safetyHang1Parking extends LinearOpMode {
    public ImprovedMecanum mecanum;
    public Arm arm;
    private BNO055IMU imu;

    public Timing.Timer time;



    @Override
    public void runOpMode() throws InterruptedException {
        mecanum = new ImprovedMecanum(hardwareMap);
        arm = new Arm(hardwareMap, gamepad2);

        time = new Timing.Timer(30000, TimeUnit.MILLISECONDS);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;

        imu.initialize(parameters);

        waitForStart();
        time.start();

        //todo: add code for hang high level
        arm.angleUp();

        while (time.elapsedTime() < 1500 && !isStopRequested()){
            mecanum.smartDrive(0, 1, 0, Math.toRadians(0), imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }
        arm.stopAngle();

        while (time.elapsedTime() < 2300 && !isStopRequested()){
            arm.angleDown();
        }
        arm.stopAngle();

        while (time.elapsedTime() < 4300 && !isStopRequested()){
            mecanum.smartDrive(1, 1, 0, Math.toRadians(0), imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }

    }
}
