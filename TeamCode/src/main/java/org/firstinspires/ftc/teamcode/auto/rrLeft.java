package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.myUtil.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.threads.auton.customDetectLibrary;
import org.firstinspires.ftc.teamcode.util.threads.crane.RobotArm;

@Autonomous(name="Left Multi Cone", group = "Auto")
public class rrLeft extends LinearOpMode
{
    Hard_Auto r = new Hard_Auto();
    TrajectorySequence thread1;
    TrajectorySequence thread2;
    TrajectorySequence thread3;
    TrajectorySequence thread4;
    TrajectorySequence thread5;
    TrajectorySequence thread6;
    @Override
    public void runOpMode() throws InterruptedException {

        r.init_robot(this, true);
        RobotArm manual = new RobotArm(this, r);
        customDetectLibrary a = new customDetectLibrary(this, r);
        r.spin.setPosition(1);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-29.5, -64.5, Math.toRadians(90));

        drive.setPoseEstimate(startPose);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        int scanned = a.scanAprilTag(10000);
        thread1 = drive.trajectorySequenceBuilder(startPose)
                .forward(1)
                .strafeRight(9)
                //.forward(46.5)
                .splineToConstantHeading(new Vector2d(-12,-13.5), Math.toRadians(90))
                .strafeLeft(10)
                /*
                .splineToConstantHeading(new Vector2d(-12,-64.5),Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-12,-20), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-23.5, -19), Math.toRadians(90))
                */
                .build();
        thread2 = drive.trajectorySequenceBuilder(thread1.end())
                .forward(2.5)
                .build();
        thread3 = drive.trajectorySequenceBuilder(thread2.end())
                .back(2)
                .strafeLeft(6)
                //.turn(Math.toRadians(90))
                //.lineTo(new Vector2d(-54.6, -12))
                .waitSeconds(0.25)
                .splineTo(new Vector2d(-52.6, -14), Math.toRadians(180))
                .strafeRight(4.5)
                .waitSeconds(0.5)
                .forward(3.5)
                .build();
        thread4 = drive.trajectorySequenceBuilder(thread3.end())
                .back(6)
                .waitSeconds(0.25)
                .turn(Math.toRadians(-90))
                .lineTo(new Vector2d(-19, -14))
                .build();
        thread6 = drive.trajectorySequenceBuilder(thread4.end())
                .forward(2)
                .build();
        thread5 = drive.trajectorySequenceBuilder(thread6.end())
                .back(2)
                .build();

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
        drive.followTrajectorySequence(thread6);
        manual.moveArm(4729, 3225);
        Hard_Auto.waitUntil(manual);
        r.spin.setPosition(0);
        r.waiter(750);
        manual.moveArm(4729, 490);
        //while (!isStopRequested());
        drive.followTrajectorySequence(thread5);
        switch (scanned){
            case 1:
                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(thread5.end())
                        .strafeLeft(36)
                        .build());
                break;
            case 2:
                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(thread5.end())
                        .strafeLeft(12)
                        .build());
                break;
            case 3:
                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(thread5.end())
                        .strafeRight(12)
                        .build());
                break;
        }
        while (!isStopRequested());
    }
}
