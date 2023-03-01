package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.myUtil.Hardware;
@Disabled
@TeleOp(name="Rotation Test", group = "TeleOp")
public class rotational extends OpMode {
    Hardware r = new Hardware();

    @Override
    public void init() {
        r.init_robot(this);
    }

    @Override
    public void loop() {
        r.frontLeft.setPower(gamepad1.right_stick_x*0.25);
        r.frontRight.setPower(gamepad1.right_stick_x*0.25);
        r.backLeft.setPower(-gamepad1.right_stick_x*0.25);
        r.backRight.setPower(-gamepad1.right_stick_x*0.25);
        telemetry.addData("Front Left", r.frontLeft.getCurrentPosition());
    }
}
