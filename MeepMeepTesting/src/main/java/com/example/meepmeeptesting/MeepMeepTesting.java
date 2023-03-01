package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
//Distance from wheelbase center to inside claw: 12.1 in
public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);
        int scanned = 3;
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(32, 32, 1.9050273895263672, 1.9050273895263672, 12.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(
                                ///*
                                //Right Side
                                new Pose2d( 40, -64.5, Math.toRadians(90)))
                                .forward(1)
                                .strafeLeft(15)
                                .splineToConstantHeading(new Vector2d(12, -14), Math.toRadians(90))
                                .lineTo(new Vector2d(24.5, -14))
                                .waitSeconds(1)
                                .forward(2)
                                .waitSeconds(1)
                                .back(2)
                                .strafeRight(6)
                                .waitSeconds(0.25)
                                .splineTo(new Vector2d(52.6,-14), Math.toRadians(0))
                                .forward(3.5)
                                .waitSeconds(1)
                                .back(6)
                                .turn(Math.toRadians(90))
                                .lineTo(new Vector2d(24.5, -14))
                                .waitSeconds(1)
                                .forward(2)
                                .waitSeconds(1)
                                .back(2)
                                .lineTo(new Vector2d((12+(scanned-1)*24),-14))
                                .build()
                                //*/

                                /*
                                new Pose2d(-29.5, -64.5, Math.toRadians(90)))

                                 //Left Side
                                .forward(1)
                                .strafeRight(9)
                                //.forward(46.5)
                                .splineToConstantHeading(new Vector2d(-12,-14), Math.toRadians(90))
                                .strafeLeft(10)
                                .waitSeconds(1)
                                .forward(2)
                                .waitSeconds(1)
                                .back(2)
                                .strafeLeft(6)

                                .waitSeconds(0.25)
                                .splineTo(new Vector2d(-52.6, -14), Math.toRadians(180))
                                .strafeRight(4.5)
                                .waitSeconds(0.5)
                                .forward(3.5)
                                .waitSeconds(1)
                                .back(3.5)
                                .waitSeconds(0.25)
                                .turn(Math.toRadians(-90))
                                .lineTo(new Vector2d(-22, -14))
                                .waitSeconds(1)
                                .forward(2)
                                .waitSeconds(1)
                                .back(2)
                                .lineTo(new Vector2d(((3-scanned)*-24)-12, -14))

                                .build()
                                */





                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}