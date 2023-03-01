package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.myUtil.Hard_Auto;
@Disabled
@Autonomous(name="Diagnostics", group = "Auto")
public class testMotors extends LinearOpMode {

    Hard_Auto r = new Hard_Auto();
    @Override
    public void runOpMode() throws InterruptedException {
        r.init_robot(this);
        waitForStart();
        r.distance_move(24, Hard_Auto.direction.FORWARD, 0.8);
        while(!gamepad1.a);
        r.distance_move(120, Hard_Auto.direction.FORWARD, 0.8, true);
        telemetry.addLine("All done");
        telemetry.update();
        while(!isStopRequested());
    }
}
