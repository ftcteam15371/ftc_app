package org.firstinspires.ftc.teamcode;
import android.speech.tts.TextToSpeech;
import android.widget.EditText;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
//import com.qualcomm.robotcore.hardware.DistanceSensor;
//import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.Locale;
@TeleOp
public class MyFIRSTJavaOpMode extends LinearOpMode {
    // Link to java docs https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html

// do you ever feel like a plastic bag
    // Sample mecanum drive code:
    // https://ftcforum.usfirst.org/forum/ftc-technology/android-studio/60054-mecanum-wheels-programming
    // https://github.com/trc492/FtcSamples/blob/master/Ftc3543Lib/src/main/java/trclib/TrcMecanumDriveBase.java
    // https://www.google.com/search?safe=on&q=ftc+java+mecanum

    // Coach M: I suggest using variable names such as motorLeftFront.  Drop the 'test'

    //private Gyroscope imu;

    private DcMotor motorTestLeftFront;
    private DcMotor motorTestLeftRear;
    private DcMotor motorTestRightFront;
    private DcMotor motorTestRightRear;
    private DcMotor motorGrabbyBoi;
    private DcMotor motorLiftyBoi;
    private DigitalChannel digitalTouch;
    private Servo servoTest;
    private TextToSpeech tts;

    @Override
    public void runOpMode() {
        //imu = hardwareMap.get(Gyroscope.class, "imu");
        motorTestLeftFront = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorTestLeftRear = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorTestRightFront = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorTestRightRear = hardwareMap.get(DcMotor.class, "motorBackRight");
        motorGrabbyBoi = hardwareMap.get(DcMotor.class, "Grabber");
        motorLiftyBoi = hardwareMap.get(DcMotor.class, "Lifto");
        // motorTestLinearActuator = hardwareMap.get(DcMotor.class, "motorLinearActuator");
        // motorTestGrabber = hardwareMap.get(DcMotor.class, "motorGrabber");
        // these are never used
        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        servoTest = hardwareMap.get(Servo.class, "servoTest");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses START)
        waitForStart();
        // Run until the end of the match (driver presses STOP)
        double tgtPowerRightFront = 0;
        double tgtPowerRightRear = 0;
        double tgtPowerLeftFront = 0;
        double tgtPowerLeftRear = 0;
        double tgtPowerLinearActuator = 0;
        double tgtPowerGrabber = 1;
        double digitalStopper = 0;
        while (opModeIsActive()) {

            /*CHANGES: Removed negative symbol from "tgtPower = this.gamepad1.left_stick_y;,
           Changed right and left for easier steering.
            Buttons added to do straight
             */

            // Coach M: this area of code needs some spacing and comments
            tgtPowerRightFront = this.gamepad1.right_stick_y;
            tgtPowerRightRear = this.gamepad1.right_stick_y;

            // simple movement using the joysticks
            if (Math.abs(this.gamepad1.right_stick_y) > 0) ;
            {
                motorTestRightFront.setPower(this.gamepad1.right_stick_y);
                motorTestRightRear.setPower(this.gamepad1.right_stick_y);
            }
            if (Math.abs(this.gamepad1.left_stick_y) > 0) ;
            {
                motorTestLeftFront.setPower(-this.gamepad1.left_stick_y);
                motorTestLeftRear.setPower(-this.gamepad1.left_stick_y);
            }
            if (this.gamepad1.left_stick_x == 0 & this.gamepad1.left_stick_y == 0) {
                motorTestLeftFront.setPower(0);
                motorTestLeftRear.setPower(0);
            }
            if (this.gamepad1.right_stick_x == 0 & this.gamepad1.right_stick_y == 0) {
                motorTestRightFront.setPower(0);
                motorTestRightRear.setPower(0);
            }

            motorTestRightFront.setPower(tgtPowerRightFront);
            motorTestRightRear.setPower(tgtPowerRightRear);
            telemetry.addData("Target Power Right Front", tgtPowerRightFront);
            telemetry.addData("Target Power Right Rear", tgtPowerRightRear);
            telemetry.addData("Right Front Motor Power", motorTestRightFront.getPower());
            telemetry.addData("Right Rear Motor Power", motorTestRightRear.getPower());
            motorTestLeftFront.setPower(tgtPowerLeftFront);
            motorTestLeftRear.setPower(tgtPowerLeftRear);
            telemetry.addData("Target Power Left Front", tgtPowerLeftFront);
            telemetry.addData("Target Power Left Rear", tgtPowerLeftRear);
            telemetry.addData("Left Front Motor Power", motorTestLeftFront.getPower());
            telemetry.addData("Left Rear Motor Power", motorTestLeftRear.getPower());

            // D-Psd controls.
            while (this.gamepad1.dpad_down == true) {
                motorTestLeftFront.setPower(-1);
                motorTestLeftRear.setPower(-1);
                motorTestRightFront.setPower(1);
                motorTestRightRear.setPower(1);
            }
            //left trigger go backwards
            while (this.gamepad1.dpad_up == true) {
                motorTestLeftFront.setPower(1);
                motorTestLeftRear.setPower(1);
                motorTestRightFront.setPower(-1);
                motorTestRightRear.setPower(-1);
            }
            while (this.gamepad1.dpad_left == true) {
                motorTestLeftRear.setPower(1);
                motorTestRightFront.setPower(-1);
                motorTestRightRear.setPower(1);
                motorTestLeftFront.setPower(-1);
            }
            while (this.gamepad1.dpad_right == true) {
                motorTestRightFront.setPower(1);
                motorTestLeftFront.setPower(1);
                motorTestLeftRear.setPower(-1);
                motorTestRightRear.setPower(-1);
            }

            if (this.gamepad1.left_bumper = true) {
                while (this.gamepad1.x = true) {
                    motorGrabbyBoi.setPower(0.5);
                }
                while (this.gamepad1.x=true);
                while (this.gamepad1.b = true) {
                    motorGrabbyBoi.setPower(-0.5);
                }
            }
            if (this.gamepad1.right_bumper = true){
                while (this.gamepad1.y = true) {
                    while (digitalStopper >= 100) {
                        motorLiftyBoi.setPower(1);
                        digitalStopper++;
                    }
                }
                while (this.gamepad1.a =true){
                    while (digitalStopper <= 0)
                    motorLiftyBoi.setPower(-1);
                    digitalStopper--;
                }
            }
            /* if (this.gamepad1.y == true) {
                motorTestLeftFront.setPower(1);
                motorTestLeftRear.setPower(1);
                motorTestRightFront.setPower(-1);
                motorTestRightRear.setPower(-1);
                sleep(1);
                motorTestLeftFront.setPower(-1);
                motorTestLeftRear.setPower(-1);
                motorTestRightFront.setPower(1);
                motorTestRightRear.setPower(-1);
                sleep(1);
                motorTestLeftFront.setPower(-1);
                motorTestLeftRear.setPower(-1);
                motorTestRightFront.setPower(-1);
                motorTestRightRear.setPower(-1);
                sleep(1);
            }
         //   while (this.gamepad1.a == true) {
                motorTestLeftFront.setPower(0);
                motorTestLeftRear.setPower(0);
                motorTestRightFront.setPower(0);
                motorTestRightRear.setPower(0);
              //  motorTestLinearActuator.setPower(-this.gamepad1.left_stick_y);
            }

             */


            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}

/*
TODO Finish Grabber Code
*/
