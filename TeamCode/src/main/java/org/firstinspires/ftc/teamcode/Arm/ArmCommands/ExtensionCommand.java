package org.firstinspires.ftc.teamcode.Arm.ArmCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Arm.Arm;

public class ExtensionCommand extends CommandBase {

    Arm arm = new Arm();

    @Override
    public void initialize() {
        arm.setExtension();
    }

    @Override
    public void end(boolean interrupted) {
        arm.stopExtension();
    }

}
