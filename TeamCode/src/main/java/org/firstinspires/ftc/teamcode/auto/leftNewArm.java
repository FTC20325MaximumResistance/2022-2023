package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.myUtil.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.threads.auton.autoNavigate;
import org.firstinspires.ftc.teamcode.util.threads.auton.customDetectLibrary;
import org.firstinspires.ftc.teamcode.util.threads.crane.RobotArm;
@Disabled
@Autonomous(name="Left Auto.", group = "Auto")
public class leftNewArm extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Hard_Auto r = new Hard_Auto();
        r.init_robot(this);
        RobotArm manual = new RobotArm(this, r);

        autoNavigate arm = new autoNavigate(this, r, 1, 2);
        autoNavigate a = new autoNavigate(this, r, 1, 2);
        customDetectLibrary v = new customDetectLibrary(this, r);
        int num = v.scanAprilTag(9000);
        waitForStart();

        //Move arm up slightly (This happens after cone is scanned)
        manual.moveArm(r.arm1.getCurrentPosition(),r.arm2.getCurrentPosition()+800);
        while (manual.isAlive());

        //Move off the wall
        r.distance_move(3, Hard_Auto.direction.FORWARD, 0.6);

        //Since we are slightly not in the center at first, we move a bit left before starting
        //r.distance_move(2, Hard_Auto.direction.LEFT, 0.6);

        //Start raising the arm and move to the pole to drop off preloaded cone
        manual.moveArm(4729, 8795);
        r.distance_move(54, Hard_Auto.direction.FORWARD, 0.9);
        r.distance_move(6, Hard_Auto.direction.BACKWARD, 0.9);
        while(manual.isAlive());

        //Rotate into position, move forward just a little, lower arm a bit, and drop the cone.
        r.degree_rotate(47.5, Hard_Auto.direction.RIGHT, 0.6);
        r.distance_move(2.5, Hard_Auto.direction.FORWARD, 0.5);
        manual.moveArm(r.arm1.getCurrentPosition(),7995);
        Hard_Auto.waitUntil(manual);
        r.waiter(100);
        r.spin.setPosition(0);
        r.waiter(100);
        double firstStop = getRuntime();
        manual.moveArm(r.arm1.getCurrentPosition(),8795);
        Hard_Auto.waitUntil(manual);
        r.waiter(250);

        //Move arm to 5-cone-height
        manual.moveArm(0,277);

        //Move back into the middle of the square
        r.distance_move(2.5, Hard_Auto.direction.BACKWARD, 0.5);
        r.degree_rotate(137.5, Hard_Auto.direction.LEFT, 0.7);
        //r.distance_move(4.5, Hard_Auto.direction.LEFT, 0.7);
        Hard_Auto.waitUntil(manual);

        //Move to the cone stack and grab one
        r.distance_move(18.5, Hard_Auto.direction.FORWARD, 0.8);
        r.waiter(50);
        r.distance_move(1.75, Hard_Auto.direction.FORWARD, 0.5);
        r.waiter(400);
        r.spin.setPosition(1);
        r.waiter(150);

        //Start raising the arm again and move back to the middle of the square
        manual.moveArm(r.arm1.getCurrentPosition(), 1277);
        r.waiter(150);
        r.distance_move(1, Hard_Auto.direction.BACKWARD, 0.3);
        Hard_Auto.waitUntil(manual);
        r.waiter(175);
        manual.moveArm(4729, 8795);
        r.distance_move(19.25, Hard_Auto.direction.BACKWARD, 0.8);
        //r.distance_move(4.5, Hard_Auto.direction.RIGHT, 0.7);

        //Rotate to face the pole and drop another cone
        r.degree_rotate(132.5, Hard_Auto.direction.RIGHT, 0.5);
        Hard_Auto.waitUntil(manual);
        r.distance_move(2, Hard_Auto.direction.FORWARD, 0.5);
        manual.moveArm(r.arm1.getCurrentPosition(),7995);
        Hard_Auto.waitUntil(manual);
        r.waiter(100);
        r.spin.setPosition(0);
        r.waiter(100);

        //Move into the center of the square facing the wall
        manual.moveArm(r.arm1.getCurrentPosition(),8795);
        Hard_Auto.waitUntil(manual);
        r.distance_move(2, Hard_Auto.direction.BACKWARD, 0.5);
        r.degree_rotate(137.5, Hard_Auto.direction.RIGHT, 0.8);

        //Move 30 inches in the direction that the signal sleeve says
        manual.moveArm(202, 391);
        r.distance_move((2-num)*30, Hard_Auto.direction.RIGHT, 0.8);

        while (manual.isAlive());
    }
}
