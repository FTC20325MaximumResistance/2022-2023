package org.firstinspires.ftc.teamcode.util.myUtil;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.HashMap;
import java.util.List;

public class Hard_Auto extends Hardware{

    double dperr = 9.62;

    public void initAuto(LinearOpMode opMode){
        opModeCheck = opMode;

    }
    public final double TICKS_PER_INCH = 52;
    LinearOpMode opModeCheck;
    @Override
    public void init_robot(OpMode opMode) {
        super.init_robot(opMode);

    }

    public void degree_rotate(double degrees, direction dir, double power) {
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waiter(50);
        setDriverMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        degrees = degrees * dperr;
            switch (dir) {
                case LEFT:
                 frontLeft.setTargetPosition((int) -degrees);
                 frontRight.setTargetPosition((int) -degrees);
                 backLeft.setTargetPosition((int) degrees);
                 backRight.setTargetPosition((int) degrees);
                 break;
                case RIGHT:
                 frontLeft.setTargetPosition((int) degrees);
                 frontRight.setTargetPosition((int) degrees);
                 backLeft.setTargetPosition((int) -degrees);
                 backRight.setTargetPosition((int) -degrees);
                 break;
         }
        setDriverMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        boolean done = false;
        while (!done && !opModeCheck.isStopRequested()) {
            done = getTolerance(frontLeft.getCurrentPosition(), frontLeft.getTargetPosition(), 3);
            opMode.telemetry.addData("Motor Ticks", frontLeft.getCurrentPosition());
            opMode.telemetry.update();
        }
        setToStill();
    }
    public void moveMotors(double flm, double frm, double blm, double brm){
        frontLeft.setPower(flm);
        backLeft.setPower(-blm);
        frontRight.setPower(-frm);
        backRight.setPower(brm);
    }
    public double getDistance(ModernRoboticsI2cRangeSensor distance, DistanceUnit unit){
        double dist = 0;
        while (dist > 100.2 || dist < 0.0001){dist=distance.getDistance(unit);}
        return dist;
    }
    public double getDistance(ModernRoboticsI2cRangeSensor distance, DistanceUnit unit, int round){
        double dist = 0;
        while (dist > 100.2 || dist < 0.0001){dist=distance.getDistance(unit);}
        return (double) Math.round(dist*(double)Math.pow(10,round))/(double)Math.pow(10,round);
    }
    public void distance_move(double inches, direction dir, double power, boolean curved){
        inches *= TICKS_PER_INCH;
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int inches1 =  (int)-inches;
        int inches2 = (int) inches;
        int inches3 = (int) inches;
        int inches4 = (int) -inches;
        //inchess = new int[]{inches1, inches2, inches3, inches4};
        switch (dir){
            case RIGHT:
                inches1 = (int)(1.15*inches1);
                inches2 = (int)(-1.15*inches2);
                inches3 = (int)(-1.15*inches3);
                inches4 = (int)(1.15*inches4);
                break;
            case LEFT:
                inches1 = (int)(-1.15*inches1);
                inches2 = (int)(1.15*inches2);
                inches3 = (int)(1.15*inches3);
                inches4 = (int)(-1.15*inches4);
                break;
            case BACKWARD:
                inches1 *= -1;
                inches2 *= -1;
                inches3 *= -1;
                inches4 *= -1;
                break;
            default:
                inches1 *= 1;
                inches2 *= 1;
                inches3 *= 1;
                inches4 *= 1;
                break;
        }
        //opMode.telemetry.addData("Ticks", inches1);
        //opMode.telemetry.update();
        //waiter(5000);
        frontLeft.setTargetPosition(inches1);
        frontRight.setTargetPosition(inches2);
        backLeft.setTargetPosition(inches3);
        backRight.setTargetPosition(inches4);
        setDriverMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        double curve = 1;
        double min = 0.4/power;
        boolean all_curved = false;
        while (!getTolerance(frontLeft.getCurrentPosition(), frontLeft.getTargetPosition(), 3)){
            if (!all_curved) {
                curve = 1.0-((double)frontLeft.getCurrentPosition() / (double)frontLeft.getTargetPosition());
                if (curve < min){curve=min;all_curved=true;}
            }
            opMode.telemetry.addData("Curve", curve);
            opMode.telemetry.addData("What should the curve should be", 1.0-((double)frontLeft.getCurrentPosition() / (double)frontLeft.getTargetPosition()));
            opMode.telemetry.addData("Curve Done", all_curved);
            opMode.telemetry.addData("Percent Done", (double)frontLeft.getCurrentPosition() / (double)frontLeft.getTargetPosition());
            opMode.telemetry.update();
            frontLeft.setPower(power*curve);
            frontRight.setPower(power*curve);
            backLeft.setPower(power*curve);
            backRight.setPower(power*curve);

        }
        setToStill();
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriverMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waiter(100);
    }

    public void distance_move(double inches, direction dir, double power){
        inches *= TICKS_PER_INCH;
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int inches1 =  (int)-inches;
        int inches2 = (int) inches;
        int inches3 = (int) inches;
        int inches4 = (int) -inches;
        //inchess = new int[]{inches1, inches2, inches3, inches4};
        switch (dir){
            case RIGHT:
                inches1 = (int)(1.15*inches1);
                inches2 = (int)(-1.15*inches2);
                inches3 = (int)(-1.15*inches3);
                inches4 = (int)(1.15*inches4);
                break;
            case LEFT:
                inches1 = (int)(-1.15*inches1);
                inches2 = (int)(1.15*inches2);
                inches3 = (int)(1.15*inches3);
                inches4 = (int)(-1.15*inches4);
                break;
            case BACKWARD:
                inches1 *= -1;
                inches2 *= -1;
                inches3 *= -1;
                inches4 *= -1;
                break;
            default:
                inches1 *= 1;
                inches2 *= 1;
                inches3 *= 1;
                inches4 *= 1;
                break;
        }
        //opMode.telemetry.addData("Ticks", inches1);
        //opMode.telemetry.update();
        //waiter(5000);
        frontLeft.setTargetPosition(inches1);
        frontRight.setTargetPosition(inches2);
        backLeft.setTargetPosition(inches3);
        backRight.setTargetPosition(inches4);
        setDriverMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        backRight.setPower(power);
        backLeft.setPower(power);
        frontLeft.setPower(power);

        frontRight.setPower(power);




        //while (!getTolerance(frontLeft.getCurrentPosition(), frontLeft.getTargetPosition(), 20));
        //frontLeft.setPower(power*0.5);
        //frontRight.setPower(power*0.5);
        //backLeft.setPower(power*0.5);
        //backRight.setPower(power*0.5);

        while (!getTolerance(frontLeft.getCurrentPosition(), frontLeft.getTargetPosition(), 3) && !opModeCheck.isStopRequested());
        setToStill();
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriverMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waiter(100);
    }
    public static void waitUntil(Thread thread){
        while (thread.isAlive());
    }








    public enum direction{
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT

    }




        //Computer Vision

        /*
         * Specify the source for the Tensor Flow Model.
         * If the TensorFlowLite object model is included in the Robot Controller App as an "asset",
         * the OpMode must to load it using loadModelFromAsset().  However, if a team generated model
         * has been downloaded to the Robot Controller's SD FLASH memory, it must to be loaded using loadModelFromFile()
         * Here we assume it's an Asset.    Also see method initTfod() below .
         */
        //private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";

         private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/prototypeModel.tflite";

        HashMap<String, String> correction;

        private static final String[] LABELS = {
                "1",
                "2",
                "3",
                "wire",
                "computer",
                "engine",
                "power"
        };

        private static final String VUFORIA_KEY =
                "AbPlqTr/////AAABmdW9GwFZ8E3Kt/btYVgy+hBMLd/G/emPF/6jLYzmo9hMgEldkQ4YzCzrs7Rqz5Pnh7RwdT+VFIPS8czES3UnsLv33hPsr/DhZ73T7r+0MJPx43fx4PP5ncgoqEToOaK04iZr6kDjQLZMSxaGqz2jx4WrQpO7F6su9GQ4H/qr3ZydWXVgv1YYEZny93GubLpD59a33+NPedV+HgLkSOH7/ksG3hRelkD3f1yjTGNjAK8C9wd30njnjN7QmLmCUghe/5Wj8coc3eCHB5InNR3VJk7NNd2r+MnxYfB8N1vznX0OHYA7NydPvlwnrZ8Zi7nXC38oiEWRABDN4sFEwzc58TloW4uTA9SLA5EJSxjZC1kJ";

        private VuforiaLocalizer vuforia;
        private TFObjectDetector tfod;
        public void ready_vision () {
        try {
            correction = new HashMap<String, String>();
            initVuforia();
            initTfod();
            correction.put("1", "1");
            correction.put("2","3");
            correction.put("3","2");
            correction.put("wire","computer");
            correction.put("computer","engine");
            correction.put("engine","power");
            correction.put("power","wire");

            if (tfod != null) {
                tfod.activate();
                tfod.setZoom(1.0, 16.0 / 9.0);

            }

        } catch (Exception e) {
            opMode.telemetry.addLine("Something went wrong, vision software will aim for top level");
            opMode.telemetry.addLine("Why? Because Screw You!");
            opMode.telemetry.addLine("THe HashMap messed up");
            opMode.telemetry.update();
            waiter(2000);
        }
    }


        private void initVuforia () {
        try {

            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

            parameters.vuforiaLicenseKey = VUFORIA_KEY;
            parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

            //  Instantiate the Vuforia engine
            vuforia = ClassFactory.getInstance().createVuforia(parameters);

            // Loading trackable is not necessary for the TensorFlow Object Detection engine.
        } catch (Exception e) {
            opMode.telemetry.addLine("Something went wrong, vision software will aim for top level");
            opMode.telemetry.addLine("Why? Because Screw You!");
            opMode.telemetry.update();
        }
    }

        /**
         * Initialize the TensorFlow Object Detection engine.
         */
        private void initTfod () {
            try {
                int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
                        "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
                TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
                tfodParameters.minResultConfidence = 0.4f;
                tfodParameters.isModelTensorFlow2 = true;
                tfodParameters.inputSize = 320;
                tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
                tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
            } catch (Exception e) {

            }
        }
        public String scan_object (String[] accept, int wait) {
            //ready_vision();
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while (true) {
                opMode.telemetry.addData("Time", Math.round(wait - time.seconds()));
                opMode.telemetry.update();
                if(time.seconds() > wait){
                    opMode.telemetry.addLine("Out of time");
                    opMode.telemetry.update();
                    return "1";
                }
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {


                        // step through the list of recognitions and display image position/size information for each one
                        // Note: "Image number" refers to the randomized image orientation/number
                        String done;
                        for (Recognition recognition : updatedRecognitions) {

                            if (contains(accept, recognition.getLabel())) {
                                done = correction.get(recognition.getLabel());
                                opMode.telemetry.addData("Label", done);
                                opMode.telemetry.update();
                                return done;

                            }

                        }

                    }
                }
            }

        }


        public void end_vision () {
        try {
            tfod.shutdown();
            vuforia.close();
            correction.clear();
        } catch (Exception e) {
            opMode.telemetry.addLine("Something went wrong, vision software will aim for top level");
            opMode.telemetry.addLine("Why? Because Screw You!");
            opMode.telemetry.update();
        }

        }



}
