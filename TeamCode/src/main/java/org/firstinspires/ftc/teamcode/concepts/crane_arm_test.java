package org.firstinspires.ftc.teamcode.concepts;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Hardware;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
@Disabled

@TeleOp(name = "crane_arm_test", group = "TeleOp")
public class crane_arm_test extends OpMode {
    double deflator1;
    double deflator2;
    Hardware r = new  Hardware();
    FileWriter write;
    @Override
    public void init() {
        r.init_robot(this);
        System.out.println("This is a test");
    }

    @Override

    public void loop() {

        deflator1 = gamepad2.left_bumper ? gamepad2.right_bumper? 0.9: 0.4: 0.8;
        deflator2 = gamepad2.right_bumper ? gamepad2.left_bumper ? 0.9: 0.4: 0.8;
        r.arm1.setPower(r.touch1.isPressed() ? gamepad2.left_stick_x < 0 ? gamepad2.left_stick_x : 0 : r.touch2.isPressed() ? gamepad2.left_stick_x > 0 ?gamepad2.left_stick_x * deflator1 :0:gamepad2.left_stick_x * deflator1);
        r.arm2.setPower(r.touch3.isPressed() ? gamepad2.right_stick_x < 0 ? gamepad2.right_stick_x : 0 : r.touch4.isPressed() ? gamepad2.right_stick_x > 0 ?gamepad2.right_stick_x * deflator2 :0:gamepad2.right_stick_x * deflator2);
        composeTelemetry();
    }
    void composeTelemetry(){
        if (r.touch2.isPressed()){
            telemetry.addLine("First arm front limit reached");
        }else if(r.touch1.isPressed()){
            telemetry.addLine("First arm back limit reached");

        }else{
            telemetry.addLine("First arm within limits");
        }
        if (r.touch4.isPressed()){
            telemetry.addLine("Second arm front limit reached");
        }else if(r.touch3.isPressed()){
            telemetry.addLine("Second arm back limit reached");

        }else{
            telemetry.addLine("Second arm within limits");
        }
        telemetry.addData("First Arm Deflator", deflator1);
        telemetry.addData("Second Arm Deflator", deflator2);
        telemetry.update();


    }
}
