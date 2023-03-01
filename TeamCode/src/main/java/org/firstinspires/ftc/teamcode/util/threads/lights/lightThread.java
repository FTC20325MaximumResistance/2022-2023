package org.firstinspires.ftc.teamcode.util.threads.lights;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.myUtil.Hardware;

public class lightThread extends Thread{
    Hardware r;
    OpMode opMode;
    panicLights.movementState state;



    @Override
    public void run(){
        super.run();
        ElapsedTime Time = new ElapsedTime();
        switch (state){
            case FORWARD:
                r.backleftR.setState(true);
                r.backrightR.setState(true);
                break;
            case STOPPED:
                r.backleftR.setState(false);
                r.backrightR.setState(false);
                break;
            case LEFTTURN:
                r.backrightR.setState(false);
                while (!this.isInterrupted()){
                    Time.reset();
                    while (!this.isInterrupted() && Time.milliseconds() <834);
                    r.backleftR.setState(!r.backleftR.getState());
                }
                break;
            case RIGHTTURN:
                r.backleftR.setState(false);

                while (!this.isInterrupted()){
                    Time.reset();
                    while (!this.isInterrupted() && Time.milliseconds() <834);
                    r.backrightR.setState(!r.backrightR.getState());

                }
                break;
            case REVERSE:
                r.backleftR.setState(true);
                r.backrightR.setState(true);

                while (!this.isInterrupted()){
                    Time.reset();
                    while (!this.isInterrupted() && Time.milliseconds() <834);
                    r.backrightR.setState(!r.backrightR.getState());
                    r.backleftR.setState(!r.backleftR.getState());

                }
                break;
        }



    }

    public void updateLights(panicLights.movementState state){
        this.state = state;
        start();
    }

    public lightThread(OpMode opMode, Hardware r){
        this.r = r;
        this.opMode = opMode;
    }




}
