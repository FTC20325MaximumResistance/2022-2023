package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.myUtil.Hardware;

@TeleOp(name="No Limits", group = "TeleOp")
public class restrictionless extends OpMode {
    Hardware r = new Hardware();
    @Override
    public void init() {
        r.init_robot(this);
    }

    @Override
    public void loop() {
        r.arm2.setPower(-gamepad2.right_stick_y*0.3);
    }
}
