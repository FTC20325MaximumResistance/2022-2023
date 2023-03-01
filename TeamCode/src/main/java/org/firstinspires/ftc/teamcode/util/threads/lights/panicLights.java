package org.firstinspires.ftc.teamcode.util.threads.lights;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.myUtil.Hardware;

public class panicLights extends Thread{
    public movementState CurrentState;
    Hardware r;
    OpMode opMode;
    lightThread l;
    @Override
    public void run(){
        ElapsedTime Time = new ElapsedTime();
        Time.reset();
        while (!this.isInterrupted()){
            while (Time.milliseconds() < 500);
            movementState lastState = CurrentState;



            if (opMode.gamepad1.right_stick_x > 0) {
                CurrentState = movementState.RIGHTTURN;
            }
            else if (opMode.gamepad1.right_stick_x < 0) {
                CurrentState = movementState.LEFTTURN;
            }
            else if (opMode.gamepad1.left_stick_y < 0) {
                CurrentState = movementState.FORWARD;
            }
            else if (opMode.gamepad1.left_stick_y > 0) {
                CurrentState = movementState.REVERSE;
            }else{CurrentState = movementState.STOPPED;}




            if (CurrentState != lastState) {

                if (l.isAlive()) {
                    l.interrupt();
                }
                l.updateLights(CurrentState);
            }
            Time.reset();
        }
    }



    public panicLights(Hardware r, OpMode opMode){
        this.opMode = opMode;
        this.r = r;
        l = new lightThread(this.opMode, this.r);
    }




    public enum movementState{
        STOPPED,
        RIGHTTURN,
        LEFTTURN,
        REVERSE,
        FORWARD
    }
}
