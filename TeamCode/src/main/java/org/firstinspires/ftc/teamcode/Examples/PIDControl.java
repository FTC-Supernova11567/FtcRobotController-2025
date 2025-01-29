package org.firstinspires.ftc.teamcode.Examples;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDControl {
    private double kP = 0;
    private double kI = 0;
    private double kD = 0;
    private double integralSum = 0;

    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    public PIDControl(double kP, double kI, double kD){
        timer.reset();
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public double calculatePID(double reference, double currecntState){
        double error = reference - currecntState;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();

        return (error * kP) + (integralSum * kI) + (derivative * kD);
    }
}


