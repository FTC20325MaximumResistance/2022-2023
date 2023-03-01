package org.firstinspires.ftc.teamcode.concepts;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.util.myUtil.Hard_Auto;

import java.util.ArrayList;
import java.util.List;

@Disabled
@TeleOp(name="Distance Finding", group = "TeleOp")
public class ultraSonic extends OpMode {
    Hard_Auto r = new Hard_Auto();
    double lastTom = 0.0;
    double lastPtoughneigh = 0.0;
    double lastJerry = 0.0;
    List<Double> list = new ArrayList<>();
    @Override
    public void init() {
        r.init_robot(this);
    }

    @Override
    public void loop() {
        //updateTelemetry();
        test();
    }
    public void updateTelemetry(){
        boolean ptoughneigh_glitch = (float)r.ptoughneigh.getDistance(DistanceUnit.INCH) >(float) 100.2 ||(float)r.ptoughneigh.getDistance(DistanceUnit.INCH) <(float) 0.00001;
        boolean jerry_glitch = (float)r.jerry.getDistance(DistanceUnit.INCH) > (float)100.2||(float)r.jerry.getDistance(DistanceUnit.INCH) <(float) 0.00001;
        boolean tom_glitch = (float)r.tom.getDistance(DistanceUnit.INCH) >(float)100.2||(float)r.tom.getDistance(DistanceUnit.INCH) <(float) 0.00001;

        if (!tom_glitch){
            telemetry.addData("Tom's distance in inches", Math.round(r.tom.getDistance(DistanceUnit.INCH) * 100.0) / 100.0);
            lastTom = Math.round(r.tom.getDistance(DistanceUnit.INCH) * 100.0) / 100.0;
        }else{

            telemetry.addData("Tom's distance in inches", lastTom);
        }
        if (!ptoughneigh_glitch){
            telemetry.addData("Ptoughneigh's distance in inches", Math.round(r.ptoughneigh.getDistance(DistanceUnit.INCH) * 100.0) / 100.0);
            lastPtoughneigh = Math.round(r.ptoughneigh.getDistance(DistanceUnit.INCH) * 100.0) / 100.0;
        }else{
            telemetry.addData("Ptoughneigh's distance in inches", lastPtoughneigh);
        }
        if (!jerry_glitch){
            telemetry.addData("Jerry's distance in inches", Math.round(r.jerry.getDistance(DistanceUnit.INCH) * 100.0) / 100.0);
            lastJerry = Math.round(r.jerry.getDistance(DistanceUnit.INCH) * 100.0) / 100.0;
        }else{

            telemetry.addData("Jerry's distance in inches", lastJerry);
        }
        telemetry.update();
    }
    public void test(){

        telemetry.addData("Jerry", r.getDistance(r.jerry, DistanceUnit.INCH, 3));
        telemetry.addData("Tom", r.getDistance(r.tom, DistanceUnit.INCH, 3));
        telemetry.update();
    }
}
