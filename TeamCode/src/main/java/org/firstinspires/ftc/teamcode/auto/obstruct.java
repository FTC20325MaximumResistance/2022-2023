package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.myUtil.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.threads.auton.customDetectLibrary;
import org.firstinspires.ftc.teamcode.util.threads.crane.RobotArm;
@Disabled
@Autonomous(name="Obstruct", group="Auto")
public class obstruct extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Hard_Auto r = new Hard_Auto();
        r.init_robot(this);
        RobotArm arm = new RobotArm(this, r);
        customDetectLibrary v = new customDetectLibrary(this, r);
        int num = v.scanAprilTag(1000);
        waitForStart();
        arm.moveArm(4500, 8500);
        r.distance_move(3, Hard_Auto.direction.FORWARD, 0.9);
        r.distance_move(24, Hard_Auto.direction.LEFT, 0.9);
        r.distance_move(72, Hard_Auto.direction.FORWARD, 0.9);
        //while (getRuntime() < 23000);
        //r.distance_move(24, Hard_Auto.direction.BACKWARD, 0.9);
        //r.distance_move((num-1)*30, Hard_Auto.direction.RIGHT, 0.9);
        Hard_Auto.waitUntil(arm);
    }
}
