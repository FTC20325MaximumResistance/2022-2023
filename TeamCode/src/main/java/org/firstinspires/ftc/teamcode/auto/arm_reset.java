package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.myUtil.Hard_Auto;

//@Disabled
@Autonomous(name = "Reset Arm", group = "Auto")
public class arm_reset extends LinearOpMode {
    Hard_Auto r = new Hard_Auto();
    @Override
    public void runOpMode() throws InterruptedException {
        r.init_robot(this);
        //r.initAuto();
        waitForStart();
        r.spin.setPosition(0);
        r.arm1.setPower(-0.8);
        r.arm2.setPower(-0.8);
        boolean var1 = false;
        boolean var2 = false;

        while ((!var1 || !var2) && !isStopRequested()){
            if (r.touch1.isPressed()){
                r.arm1.setPower(0.0);
                var1 = true;

            }
            if (r.touch4.isPressed()){
                r.arm2.setPower(0.0);
                var2 = true;
            }

        }
        r.arm1.setPower(0.0);
        r.arm2.setPower(0.0);
        r.arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addLine("First done");
        telemetry.update();
        /*
        r.arm1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //r.arm2.setTargetPosition(0);

        //r.arm1.setMode(DcMotor.RunMode.RUN_TO
        // _POSITION);
        //r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.arm1.setPower(0.8);
        //r.arm2.setPower(0.8);
        while (!r.touch2.isPressed() && !isStopRequested()){telemetry.addLine("Currently in loop"); telemetry.update();}
        telemetry.addLine("Out of loop");
        telemetry.update();
        r.arm1.setPower(0.0);
        r.arm2.setPower(0.0);

         */




    }

}
