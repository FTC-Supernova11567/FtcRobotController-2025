/**
 * This is an example op Mode - stands for an operational mode.
 *
 * Here you will write the operations that the robot will preform during the
 * Autonomous,Teleop and the end phase of each game.
 *
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@gamePhase(name = "myOpModeName", group = "myOpGroup")
public class ExampleOpMode extends OpMode {
    /**
     * Here you will create your motor objects as private
     */

    @Override
    public void init() {
        /**
         * Here you will intialize your motor
         */
    }

    @Override
    public void loop() {
        /**
        * here you will write an action that will repeat itself during the Auto/Tele/End phases
        */
    }
}
