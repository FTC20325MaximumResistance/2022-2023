package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.Hard_Auto;
import org.firstinspires.ftc.teamcode.util.threads.crane.RobotArm;

//@Disabled
@Autonomous(name = "Reset Arm", group = "Auto")
public class arm_reset extends LinearOpMode {
    Hard_Auto r = new Hard_Auto();
    @Override
    public void runOpMode() throws InterruptedException {
        r.init_robot(this);
        r.initAuto();
        waitForStart();
        r.spin.setPosition(0);
        r.arm1.setPower(-0.8);
        r.arm2.setPower(-0.8);
        while ((!r.touch1.isPressed() || !r.touch4.isPressed()) && !isStopRequested()){
            if (r.touch1.isPressed()){
                r.arm1.setPower(0.0);

            }
            if (r.touch4.isPressed()){
                r.arm2.setPower(0.0);

            }

        }
        r.arm1.setPower(0.0);
        r.arm2.setPower(0.0);
        r.arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        r.arm1.setTargetPosition(2305);
        r.arm2.setTargetPosition(0);

        r.arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.arm1.setPower(0.8);
        r.arm2.setPower(0.8);
        while (!r.getTolerance(r.arm1.getCurrentPosition(), r.arm1.getTargetPosition(), 10) || !r.getTolerance(r.arm2.getCurrentPosition(), r.arm2.getTargetPosition(), 10)){
            if (r.getTolerance(r.arm1.getCurrentPosition(), r.arm1.getTargetPosition(), 10)){
                r.arm1.setPower(0);
            }
            if (r.getTolerance(r.arm2.getCurrentPosition(), r.arm2.getTargetPosition(), 10)){
                r.arm2.setPower(0);
            }
        }
        r.arm1.setPower(0.0);
        r.arm2.setPower(0.0);




    }

}
