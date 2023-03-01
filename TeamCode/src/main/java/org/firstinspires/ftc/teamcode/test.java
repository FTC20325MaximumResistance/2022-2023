package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.myUtil.Hardware;
import org.firstinspires.ftc.teamcode.util.threads.crane.RobotArm;
import org.firstinspires.ftc.teamcode.util.threads.lights.panicLights;

@SuppressWarnings("unused")
@Disabled
@TeleOp(name = "Test", group = "TeleOp")
public class test extends OpMode {
    Hardware r = new Hardware();
    RobotArm a;
    RobotArm a1;
    double r1a = 0;
    double r2a = 360;
    double deflator;
    double deflator1;
    double deflator2;
    int heightSelect = 1;
    panicLights l;
    ElapsedTime Time;
    ElapsedTime Time2;
    ElapsedTime Time3;
    ElapsedTime lightTime;
    boolean armMoving;
    boolean start = false;
    @Override
    public void init() {
        r.init_robot(this);
        //a = new RobotArm(this, r);
        //a1 = new RobotArm(this, r);
        l = new panicLights(r, this);
        Time = new ElapsedTime();
        Time.reset();
        Time2 = new ElapsedTime();
        Time2.reset();
        Time3 = new ElapsedTime();
        Time3.reset();
        lightTime = new ElapsedTime();
        lightTime.reset();
        armMoving = false;
        //l.start();
        r.arm2.setTargetPosition(0);
        r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.arm2.setPower(0.9);

    }

    @Override
    public void loop() {
        {
            deflator = gamepad1.left_bumper && gamepad1.right_bumper ? 0.9 : gamepad1.left_bumper ? 0.4 : 0.7;




           /*Message to future Evan: Don't mess with this
           Everything down here you will not understand
           Let Ben mess with this and get to learning how to use java
           stop scrolling down here
           I said stop!
           No Evan...
           Don't
           This is painful down here
           Stop
           Do you like black holes?
           You will somehow create one if you
           mess with this
           code that runs our mecanum drive wheels
           */
            //this first section creates the variables that will be used later

            //first we must translate the rectangular values of the joystick into polar coordinates;
            double y = -gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x;
            double angle = 0;

            if (y > 0 && x > 0)//quadrant 1
                angle = Math.atan(y / x);
            else {
                double angle1 = Math.toRadians(180) + Math.atan(y / x);
                if (y > 0 && x < 0)//quadrant 2
                    angle = angle1;
                else if (y < 0 && x < 0)//quadrant 3
                    angle = angle1;
                else if (y < 0 && x > 0)//quadrant 4
                    angle = Math.toRadians(360) + Math.atan(y / x);
            }

            if (y == 0 && x > 1) {
                angle = 0;
            }
            if (y > 0 && x == 0) {
                angle = Math.PI / 2;
            }
            if (y == 0 && x < 0) {
                angle = Math.PI;
            }
            if (y < 0 && x == 0) {
                angle = 3 * Math.PI / 2;
            }

            double velocity = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));
            double rotation = gamepad1.right_stick_x;

            //equations taking the polar coordinates and turing them into motor powers
            double v1 = velocity * Math.cos(angle + (Math.PI / 4));
            double v2 = velocity * Math.sin(angle + (Math.PI / 4));
            double power1 = v1 + rotation;
            double power2 = v2 + rotation;
            double power3 = v2 - rotation;
            double power4 = v1 - rotation;

            r.frontLeft.setPower(power1 * deflator);
            r.frontRight.setPower(power2 * deflator);
            r.backLeft.setPower(power3 * deflator);
            r.backRight.setPower(power4 * deflator);
        }




        deflator1 = gamepad2.left_bumper ? gamepad2.right_bumper? 0.9: 0.4: 0.8;
        //deflator2 = gamepad2.right_bumper ? gamepad2.left_bumper ? 0.9: 0.4: 0.8;

        r.arm1.setPower(r.touch1.isPressed() ? gamepad2.left_stick_x > 0 ? gamepad2.left_stick_x : 0 : r.touch2.isPressed() ? gamepad2.left_stick_x < 0 ? gamepad2.left_stick_x * deflator1 : 0 : gamepad2.left_stick_x * deflator1);
        //r.arm2.setPower(r.arm2.getCurrentPosition()  >4750 ? -gamepad2.right_stick_y < 0 ? -gamepad2.right_stick_y : 0 : r.touch4.isPressed() || r.arm2.getCurrentPosition() < 10 ? -gamepad2.right_stick_y > 0 ? -gamepad2.right_stick_y * deflator2 : 0 : -gamepad2.right_stick_y * deflator2);



        if (gamepad2.right_trigger > 0.25 && Time.milliseconds() > 250){
            r1a = r1a == 0 ? 1: 0;
            Time.reset();
        }
       /*
       if (Time2.milliseconds() > 250 && !armMoving) {
           heightSelect = gamepad2.x ? 2 : gamepad2.a ? 1 : gamepad2.y ? 3 : 0;
           start = true;
           Time2.reset();

       }
       */

        ///*
        if (gamepad2.left_bumper && gamepad2.right_bumper){
            r.arm2.setTargetPosition(0);
            r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            r.arm2.setPower(0.9);
        }
        if (gamepad2.a){
            r.arm2.setTargetPosition(2299);
            r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            r.arm2.setPower(0.9);
        }
        if (gamepad2.x){
            r.arm2.setTargetPosition(4050);
            r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            r.arm2.setPower(0.9);
        }
        if (gamepad2.y){
            r.arm2.setTargetPosition(5600);
            r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            r.arm2.setPower(0.9);
        }



       /*
       if (gamepad2.dpad_up&& Time2.milliseconds() > 250){
           heightSelect++;
           Time2.reset();
       }else if(gamepad2.dpad_down&& Time2.milliseconds() > 250){
           heightSelect--;
           Time2.reset();
       }

       if (heightSelect > 4){heightSelect = 1;}
       if (heightSelect < 1){heightSelect = 4;}

       if (gamepad2.y && !armMoving){
           armMoving = true;
           switch (heightSelect){
               //Low
               case 1:
                   r.arm1.setTargetPosition(686);
                   r.arm2.setTargetPosition(2736);
                   r.arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                   r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                   break;
               //Medium
               case 2:
                   r.arm1.setTargetPosition(1096);
                   r.arm2.setTargetPosition(5329);
                   r.arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                   r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                   break;
               //Cone Grab
               case 4:
                   r.arm1.setTargetPosition(391);
                   r.arm2.setTargetPosition(202);
                   r.arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                   r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                   break;
               //Default/High
               default:
                   r.arm1.setTargetPosition(4729);
                   r.arm2.setTargetPosition(8705);
                   r.arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                   r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                   break;
           }
           r.arm1.setPower(0.9);
           r.arm2.setPower(0.9);
           start = false;
           Time3.reset();
       }
       //*/



        r.spin.setPosition(r1a);
        composeTelemetry();



    }

    public void composeTelemetry(){

        //telemetry.addData("Auto-Height", height);
        telemetry.addData("Precision Mode", deflator < 0.7);
        telemetry.addData("Speed Mode", deflator >0.7);
        telemetry.addData("Arm 1 Position", r.arm1.getCurrentPosition());
        telemetry.addData("Arm 2 Position", r.arm2.getCurrentPosition());
        if (r.touch1.isPressed()){
            telemetry.addLine("First arm front limit reached");
        }else if(r.touch2.isPressed()){
            telemetry.addLine("First arm back limit reached");
        }else{
            telemetry.addLine("First arm within limits");
        }
        telemetry.addData("First Arm Deflator", deflator1);

        //telemetry.addData("Movement State", l.CurrentState);
        telemetry.update();
    }
}

