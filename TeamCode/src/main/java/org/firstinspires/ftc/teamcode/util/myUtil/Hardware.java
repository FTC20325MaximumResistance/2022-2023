package org.firstinspires.ftc.teamcode.util.myUtil;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@SuppressWarnings("unused")
public class Hardware {
    OpMode opMode;
    public DcMotor frontLeft, frontRight, backLeft, backRight;
    public DcMotor[] drive;
    public TouchSensor touch1, touch2, touch3, touch4;
    public Servo claw, spin;
    public DcMotor arm1, arm2, arm3, test;
    public DistanceSensor distanceSensor;
    public ModernRoboticsI2cRangeSensor tom, jerry, ptoughneigh;
    public DigitalChannel backleftR, backrightG, backrightR, backleftG;

    public void init_robot(OpMode opMode, boolean rr) {
        this.opMode = opMode;
        initHardware(rr);
    }
    public void init_robot(OpMode opMode) {
        this.opMode = opMode;
        initHardware();
    }

    public void initHardware() {
        //test = opMode.hardwareMap.dcMotor.get("test");
        try {
            backleftR = opMode.hardwareMap.get(DigitalChannel.class, "BackLeftG");
            backrightG = opMode.hardwareMap.get(DigitalChannel.class, "BackRightR");
            backleftR.setMode(DigitalChannel.Mode.OUTPUT);
            backrightG.setMode(DigitalChannel.Mode.OUTPUT);
            backleftR.setState(true);
            backrightG.setState(true);
            backleftG = opMode.hardwareMap.get(DigitalChannel.class, "BackLeftR");
            backrightR = opMode.hardwareMap.get(DigitalChannel.class, "BackRightG");
            backleftG.setMode(DigitalChannel.Mode.OUTPUT);
            backrightR.setMode(DigitalChannel.Mode.OUTPUT);
            backleftG.setState(true);
            backrightR.setState(true);
        }catch (Exception e){
            opMode.telemetry.addLine("Useless LEDs not found");
        }
        try {
            tom = opMode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "tom");
            jerry = opMode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "jerry");
            ptoughneigh = opMode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "ptoughneigh");
        }catch (Exception e){
            opMode.telemetry.addLine("Distance Sensor is not found");
        }

        try {
            frontLeft = opMode.hardwareMap.dcMotor.get("FLM");
            frontRight = opMode.hardwareMap.dcMotor.get("FRM");
            backLeft = opMode.hardwareMap.dcMotor.get("BLM");
            backRight = opMode.hardwareMap.dcMotor.get("BRM");
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
            drive = new DcMotor[]{frontLeft, frontRight, backLeft, backRight};
            for (DcMotor motor : drive) {
                motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
        } catch (Exception e) {
            opMode.telemetry.addLine("Something went wrong when configuring the drive motors.");
        }
        try {
            touch1 = opMode.hardwareMap.touchSensor.get("kreg");
        } catch (Exception e) {
            opMode.telemetry.addLine("Touch Sensor1 were not found in config.");
        }



        try {
            touch3 = opMode.hardwareMap.touchSensor.get("keg");
            touch4 = opMode.hardwareMap.touchSensor.get("men");
            touch2 = opMode.hardwareMap.touchSensor.get("greg");
        } catch (Exception e) {
            opMode.telemetry.addLine("Touch Sensor2 were not found in config.");
        }


        try {
            //claw = opMode.hardwareMap.servo.get("claw");
            spin = opMode.hardwareMap.servo.get("spin");
            spin.setPosition(1);
        } catch (Exception e) {
            opMode.telemetry.addLine("Servo(s) were not found in config.");
        }

        try {
            arm1 = opMode.hardwareMap.dcMotor.get("egg");
            arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //arm1.setDirection(DcMotorSimple.Direction.REVERSE);

        } catch (Exception e) {
            opMode.telemetry.addLine("Crane Arm Motor1 was not found in config");
        }
        try {

            arm2 = opMode.hardwareMap.dcMotor.get("bread");
            arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //arm2.setDirection(DcMotorSimple.Direction.REVERSE);

        } catch (Exception e) {
            opMode.telemetry.addLine("Crane Arm Motor2 was not found in config");
        }


        opMode.telemetry.update();

        //arm1.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void initHardware(boolean rr) {
        //test = opMode.hardwareMap.dcMotor.get("test");
        try {
            backleftR = opMode.hardwareMap.get(DigitalChannel.class, "BackLeftG");
            backrightG = opMode.hardwareMap.get(DigitalChannel.class, "BackRightR");
            backleftR.setMode(DigitalChannel.Mode.OUTPUT);
            backrightG.setMode(DigitalChannel.Mode.OUTPUT);
            backleftR.setState(true);
            backrightG.setState(true);
            backleftG = opMode.hardwareMap.get(DigitalChannel.class, "BackLeftR");
            backrightR = opMode.hardwareMap.get(DigitalChannel.class, "BackRightG");
            backleftG.setMode(DigitalChannel.Mode.OUTPUT);
            backrightR.setMode(DigitalChannel.Mode.OUTPUT);
            backleftG.setState(true);
            backrightR.setState(true);
        }catch (Exception e){
            opMode.telemetry.addLine("Useless LEDs not found");
        }
        try {
            tom = opMode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "tom");
            jerry = opMode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "jerry");
            ptoughneigh = opMode.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "ptoughneigh");
        }catch (Exception e){
            opMode.telemetry.addLine("Distance Sensor is not found");
        }
        if (!rr) {
            try {
                frontLeft = opMode.hardwareMap.dcMotor.get("FLM");
                frontRight = opMode.hardwareMap.dcMotor.get("FRM");
                backLeft = opMode.hardwareMap.dcMotor.get("BLM");
                backRight = opMode.hardwareMap.dcMotor.get("BRM");
                frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                backRight.setDirection(DcMotorSimple.Direction.REVERSE);
                drive = new DcMotor[]{frontLeft, frontRight, backLeft, backRight};
                for (DcMotor motor : drive) {
                    motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                }
            } catch (Exception e) {
                opMode.telemetry.addLine("Something went wrong when configuring the drive motors.");
            }
        }
        try {
            touch1 = opMode.hardwareMap.touchSensor.get("kreg");
        } catch (Exception e) {
            opMode.telemetry.addLine("Touch Sensor1 were not found in config.");
        }



        try {
            touch3 = opMode.hardwareMap.touchSensor.get("keg");
            touch4 = opMode.hardwareMap.touchSensor.get("men");
            touch2 = opMode.hardwareMap.touchSensor.get("greg");
        } catch (Exception e) {
            opMode.telemetry.addLine("Touch Sensor2 were not found in config.");
        }


        try {
            //claw = opMode.hardwareMap.servo.get("claw");
            spin = opMode.hardwareMap.servo.get("spin");
            spin.setPosition(1);
        } catch (Exception e) {
            opMode.telemetry.addLine("Servo(s) were not found in config.");
        }

        try {
            arm1 = opMode.hardwareMap.dcMotor.get("egg");
            arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //arm1.setDirection(DcMotorSimple.Direction.REVERSE);

        } catch (Exception e) {
            opMode.telemetry.addLine("Crane Arm Motor1 was not found in config");
        }
        try {

            arm2 = opMode.hardwareMap.dcMotor.get("bread");
            arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //arm2.setDirection(DcMotorSimple.Direction.REVERSE);

        } catch (Exception e) {
            opMode.telemetry.addLine("Crane Arm Motor2 was not found in config");
        }


        opMode.telemetry.update();

        //arm1.setDirection(DcMotorSimple.Direction.REVERSE);
    }



    public void waiter(int time) {
        ElapsedTime Time = new ElapsedTime();
        Time.reset();
        while (true) {
            if (Time.milliseconds() > time) {
                break;
            }
        }
    }



    public void setDriverMotorMode(DcMotor.RunMode mode) {
        for (DcMotor dcMotor : drive) dcMotor.setMode(mode);
    }

    public boolean getTolerance(int other, int checked, int tolerance) {
        return checked - tolerance < other && checked + tolerance > other;

    }
    public boolean getTolerance(double other, double checked, double tolerance) {
        return checked - tolerance < other && checked + tolerance > other;

    }

    public boolean contains(String[] arr, String value) {
        for (String element : arr) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public void setToStill(){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public int ceil(double x){
        return ((int) x )+1;

    }


}
