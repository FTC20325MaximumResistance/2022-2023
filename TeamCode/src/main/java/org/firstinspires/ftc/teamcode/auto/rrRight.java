package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.myUtil.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.threads.auton.customDetectLibrary;
import org.firstinspires.ftc.teamcode.util.threads.crane.RobotArm;
//      @Disabled
@Autonomous(name="Right Multi Cone")
public class rrRight extends LinearOpMode {
    TrajectorySequence thread1;
    TrajectorySequence thread2;
    TrajectorySequence thread3;
    TrajectorySequence thread4;
    TrajectorySequence thread5;
    TrajectorySequence thread6;
    Hard_Auto r = new Hard_Auto();
    @Override
    public void runOpMode() throws InterruptedException {
        r.init_robot(this,true);
        r.initAuto(this);
        customDetectLibrary a = new customDetectLibrary(this, r);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(new Pose2d( 40, -64.5, Math.toRadians(90)));
        thread1 = drive.trajectorySequenceBuilder(new Pose2d( 40, -64.5, Math.toRadians(90)))
                        .forward(1)
                        .strafeLeft(15)
                        .splineToConstantHeading(new Vector2d(12, -14), Math.toRadians(90))
                        .lineTo(new Vector2d(21.5, -15))
                        .build();
        thread2 = drive.trajectorySequenceBuilder(thread1.end())
                        .forward(3)
                        .build();
        thread3 = drive.trajectorySequenceBuilder(thread2.end())
                        .back(3)
                        .strafeRight(6)
                        .waitSeconds(0.25)
                        .splineTo(new Vector2d(51.1,-12), Math.toRadians(0))
                        .forward(3.5)
                        .build();
        thread4 = drive.trajectorySequenceBuilder(thread3.end())
                        .back(6)
                        .turn(Math.toRadians(90))
                        .lineTo(new Vector2d(19.5, -14))
                        .build();
        thread5 = drive.trajectorySequenceBuilder(thread4.end())
                        .forward(2)
                        .build();
        int scanned = a.scanAprilTag(10000);
        thread6 = drive.trajectorySequenceBuilder(thread5.end())
                        .back(2)
                        .lineTo(new Vector2d((10+((scanned-1)*24)),-14))
                        .build();
        RobotArm manual = new RobotArm(this, r);
        waitForStart();
        manual.moveArm(4729, r.arm2.getCurrentPosition());

        drive.followTrajectorySequence(thread1);
        manual.moveArm(4729, 3925);
        Hard_Auto.waitUntil(manual);
        drive.followTrajectorySequence(thread2);
        manual.moveArm(4729, 3225);
        Hard_Auto.waitUntil(manual);
        r.spin.setPosition(0);
        r.waiter(750);
        manual.moveArm(4729, 560);
        drive.followTrajectorySequence(thread3);
        r.waiter(750);
        r.spin.setPosition(1);
        r.waiter(750);
        manual.moveArm(4729, 1500);
        Hard_Auto.waitUntil(manual);
        drive.followTrajectorySequence(thread4);
        manual.moveArm(4729, 3925);
        Hard_Auto.waitUntil(manual);
        drive.followTrajectorySequence(thread5);
        manual.moveArm(4729, 3225);
        Hard_Auto.waitUntil(manual);
        r.spin.setPosition(0);
        r.waiter(750);
        manual.moveArm(4729, 490);
        //while (!isStopRequested());
        drive.followTrajectorySequence(thread6);
        Hard_Auto.waitUntil(manual);

    }
}
