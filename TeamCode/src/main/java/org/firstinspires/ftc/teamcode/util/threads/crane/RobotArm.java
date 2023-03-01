package org.firstinspires.ftc.teamcode.util.threads.crane;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.myUtil.Hardware;

import java.util.Random;

public class RobotArm extends Thread{
    double arm_offset = 2.0;
    double arm1_l = 14.5;
    double arm2_l = 20;
    double ticks_per_degrees1 = 53.33;
    double ticks_per_degrees2 = 1.94;
    double height_offset = 4;
    LinearOpMode opMode;
    int target;
    boolean degree;

    double arm1_angle;
    double arm2_angle;
    int height;
    boolean stopPower;
    int armNum;
    boolean full;
    public boolean folded = true;
    int position2;
    Hardware r;
    int position;
    String[] splash_texts = {"please feed your local programmer.", "check on Evan's mental state.", "donations are appreciated, but only accepted in the form of drugs.", "lets hope it did it right!", "you stopped reading these by this point, right?", "crane is ready to go.", "I need drugs.", "how charged is the controller? It's low... isn't it?", "na na na na na na na na Robot!", "Jesse, we need to code."};
    Random rand = new Random();







    public RobotArm (LinearOpMode opMode, Hardware r){
        this.opMode = opMode;
        this.r = r;

    }

    public void moveArm(int position, int position2, boolean stopPower){
        this.position = position;
        this.position2 = position2;
        this.stopPower = false;
        this.start();

    }
    public void moveArm(int position, int position2){
        this.position = position;
        this.position2 = position2;
        this.stopPower = true;
        this.start();

    }


    @Override
    public void run(){
        super.run();
        r.arm1.setTargetPosition(position);
        r.arm2.setTargetPosition(position2);
        r.arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.arm1.setPower(0.8);
        r.arm2.setPower(0.9);
        while ((!r.getTolerance(r.arm1.getCurrentPosition(), r.arm1.getTargetPosition(), 50) || !r.getTolerance(r.arm2.getCurrentPosition(), r.arm2.getTargetPosition(), 70)) && !opMode.isStopRequested()){
            if (r.getTolerance(r.arm1.getCurrentPosition(), r.arm1.getTargetPosition(), 50)){
                r.arm1.setPower(0);
            }
            if (r.getTolerance(r.arm2.getCurrentPosition(), r.arm2.getTargetPosition(), 70)){
                r.arm2.setPower(0);
            }
        }

    }

}
