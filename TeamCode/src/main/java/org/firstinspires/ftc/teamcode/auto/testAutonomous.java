package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.myUtil.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.threads.auton.customDetectLibrary;
import org.firstinspires.ftc.teamcode.util.threads.crane.RobotArm;

@Autonomous(name="Left One Cone", group ="Auto")
public class testAutonomous extends LinearOpMode {
    Hard_Auto r = new Hard_Auto();
    customDetectLibrary a;
    //Start at 610, lower by 70

    @Override
    public void runOpMode() throws InterruptedException {
        r.init_robot(this);
        r.initAuto(this);
        a = new customDetectLibrary(this, r);
        RobotArm manual = new RobotArm(this, r);
        r.spin.setPosition(1);
        int loops = 0;
        int scanned = a.scanAprilTag(4000);
        waitForStart();
        r.distance_move(2, Hard_Auto.direction.FORWARD, 0.4);
        r.distance_move(18.5, Hard_Auto.direction.RIGHT, 0.65);
        manual.moveArm(4729,0);

        r.distance_move(52, Hard_Auto.direction.FORWARD, 0.6);
        Hard_Auto.waitUntil(manual);

        r.distance_move(7, Hard_Auto.direction.LEFT, 0.6);
        manual.moveArm(4729, 4225);
        Hard_Auto.waitUntil(manual);
        r.distance_move(4.5, Hard_Auto.direction.LEFT, 0.6);
        manual.moveArm(4729, 3225);
        Hard_Auto.waitUntil(manual);
        r.spin.setPosition(0);
        r.waiter(750);
        manual.moveArm(4729, 610);
        r.distance_move(12, Hard_Auto.direction.RIGHT, 0.6);
        r.distance_move((3-scanned)*24, Hard_Auto.direction.LEFT, 0.6);
        Hard_Auto.waitUntil(manual);
        //r.distance_move((3-scanned)*24, Hard_Auto.direction.LEFT, 0.6);
        /*
        r.distance_move(14, Hard_Auto.direction.LEFT, 0.6);
        r.degree_rotate(90, Hard_Auto.direction.LEFT, 0.1);
        r.distance_move(22.5, Hard_Auto.direction.FORWARD, 0.6);
        r.spin.setPosition(1);
        r.waiter(500);
        manual.moveArm(4729, 4225);
        Hard_Auto.waitUntil(manual);

         */
    }
}
