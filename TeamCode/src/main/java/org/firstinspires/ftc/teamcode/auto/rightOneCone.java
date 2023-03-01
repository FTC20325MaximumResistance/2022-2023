package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.myUtil.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.threads.auton.customDetectLibrary;
import org.firstinspires.ftc.teamcode.util.threads.crane.RobotArm;

@Autonomous(name="Right One Cone", group ="Auto")
public class rightOneCone extends LinearOpMode {
    Hard_Auto r = new Hard_Auto();
    customDetectLibrary a;
    @Override
    public void runOpMode() throws InterruptedException {
        r.init_robot(this);
        r.initAuto(this);

        a = new customDetectLibrary(this, r);
        RobotArm manual = new RobotArm(this, r);
        r.spin.setPosition(1);
        int scanned = a.scanAprilTag(4000);
        waitForStart();
        r.distance_move(2, Hard_Auto.direction.FORWARD, 0.4);
        r.distance_move(29.5, Hard_Auto.direction.LEFT, 0.4);
        manual.moveArm(4729,0);

        r.distance_move(50, Hard_Auto.direction.FORWARD, 0.6);
        Hard_Auto.waitUntil(manual);

        r.distance_move(9, Hard_Auto.direction.RIGHT, 0.6);
        manual.moveArm(4729, 4225);
        Hard_Auto.waitUntil(manual);
        r.distance_move(6, Hard_Auto.direction.RIGHT, 0.6);
        manual.moveArm(4729, 3225);
        Hard_Auto.waitUntil(manual);
        r.spin.setPosition(0);
        r.waiter(750);
        manual.moveArm(4729, 610);
        r.distance_move(12, Hard_Auto.direction.LEFT, 0.6);
        r.distance_move((scanned-1)*24, Hard_Auto.direction.RIGHT, 0.6);
        Hard_Auto.waitUntil(manual);

    }
}
