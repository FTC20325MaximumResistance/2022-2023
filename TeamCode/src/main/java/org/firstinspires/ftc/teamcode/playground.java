package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.util.myUtil.Hard_Auto;

@Disabled
@Autonomous(name = "Playground", group = "Auto")
public class playground extends LinearOpMode {
    Hard_Auto r = new Hard_Auto();




    @Override
    public void runOpMode() throws InterruptedException {
        r.init_robot(this);

        waitForStart();

        r.distance_move(5, Hard_Auto.direction.FORWARD, 0.5);
        double tom = r.getDistance(r.tom, DistanceUnit.INCH, 3);
        double jerry = r.getDistance(r.jerry, DistanceUnit.INCH, 3);
        boolean turnleft = tom > jerry;
        boolean secondary = turnleft;

        while (!r.getTolerance(tom, jerry, 0.05) && !isStopRequested()) {

            if (secondary) {
                r.moveMotors(-0.1, 0.1, -0.1, 0.1);


            } else {
                r.moveMotors(0.1, -0.1, 0.1, -0.1);
            }
            telemetry.addData("Tom's output", tom);
            telemetry.addData("Jerry's output", jerry);
            telemetry.update();
            tom = r.getDistance(r.tom, DistanceUnit.INCH, 3);
            jerry = r.getDistance(r.jerry, DistanceUnit.INCH, 3);
            secondary = tom > jerry;
        }
        r.setToStill();

        int direction = turnleft ? -1 : 1;
        double horizontal = (Math.sqrt(132.5-Math.pow(tom,2))) * direction;
        telemetry.addData("Tom's output", tom);
        telemetry.addData("Jerry's output", jerry);
        telemetry.addData("Movement in inches", horizontal);
        telemetry.addData("Direction", turnleft ? "Left":"Right");
        telemetry.update();
        r.waiter(5000);
        r.distance_move(horizontal, Hard_Auto.direction.RIGHT, 0.5);
        //r.distance_move(24-r.tom.getDistance(DistanceUnit.INCH), Hard_Auto.direction.FORWARD, 0.3);
        while (!isStopRequested() && opModeIsActive()){
            tom = r.getDistance(r.tom, DistanceUnit.INCH, 3);
            jerry = r.getDistance(r.jerry, DistanceUnit.INCH, 3);
            telemetry.addData("Tom's output", tom);
            telemetry.addData("Jerry's output", jerry);
            telemetry.update();
        }
    }
}

