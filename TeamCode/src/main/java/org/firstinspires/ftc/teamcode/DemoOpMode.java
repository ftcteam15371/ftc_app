package org.firstinspires.ftc.teamcode;

import android.speech.tts.TextToSpeech;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Locale;

@TeleOp
public class DemoOpMode extends LinearOpMode {

    // Link to java docs https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html

    // Sample mecanum drive code:
    // https://ftcforum.usfirst.org/forum/ftc-technology/android-studio/60054-mecanum-wheels-programming
    // https://github.com/trc492/FtcSamples/blob/master/Ftc3543Lib/src/main/java/trclib/TrcMecanumDriveBase.java
    // https://www.google.com/search?safe=on&q=ftc+java+mecanum
    private Gyroscope imu;
    private DcMotor motorTestLeftFront;
    private DcMotor motorTestLeftRear;
    private DcMotor motorTestRightFront;
    private DcMotor motorTestRightRear;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;
    private TextToSpeech tts;

    @Override
    public void runOpMode() {
        // What? No comments? What is this code supposed to do???
        // Stuff.
        imu = hardwareMap.get(Gyroscope.class, "imu");
        motorTestLeftFront = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorTestLeftRear = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorTestRightFront = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorTestRightRear = hardwareMap.get(DcMotor.class, "motorBackRight");
        
        // these are never used
        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
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
        while (opModeIsActive()) {
            //Change Notes: made the diagonals. they probably don't work, so just revert them if they don't.
            //yep.

            // simple forward - backwards movement, using the joysticks
            
                if (Math.abs(this.gamepad1.right_stick_y) > 0) ; {
                motorTestRightFront.setPower(this.gamepad1.right_stick_y);
                motorTestRightRear.setPower(this.gamepad1.right_stick_y);
            }
              if (Math.abs(this.gamepad1.left_stick_y) > 0); {
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
            telemetry.addData("Motor Power Right Front", motorTestRightFront.getPower());
            telemetry.addData("Motor Power Right Rear", motorTestRightRear.getPower());
            motorTestLeftFront.setPower(tgtPowerLeftFront);
            motorTestLeftRear.setPower(tgtPowerLeftRear);
            telemetry.addData("Target Power Left Front", tgtPowerLeftFront);
            telemetry.addData("Target Power Left Rear", tgtPowerLeftRear);
            telemetry.addData("Motor Power Left Front", motorTestLeftFront.getPower());
            telemetry.addData("Motor Power Left Rear", motorTestLeftRear.getPower());
            // D-Psd controls.
            while (this.gamepad1.dpad_down == true) {
                telemetry.addData("TextToSpeech", "test");
                tts.setLanguage(Locale.US);
                tts.speak("Backwards Movement", TextToSpeech.QUEUE_ADD, null);
                motorTestLeftFront.setPower(-1);
                motorTestLeftRear.setPower(-1);
                motorTestRightFront.setPower(1);
                motorTestRightRear.setPower(1);
            }
            //left trigger go backwards
            while (this.gamepad1.dpad_up == true) {
                telemetry.addData("TextToSpeech", "test");
                tts.setLanguage(Locale.US);
                tts.speak("Forward Movement", TextToSpeech.QUEUE_ADD, null);
                motorTestLeftFront.setPower(1);
                motorTestLeftRear.setPower(1);
                motorTestRightFront.setPower(-1);
                motorTestRightRear.setPower(-1);
            }

            while (this.gamepad1.dpad_left == true) {
                telemetry.addData("TextToSpeech", "test");
                tts.setLanguage(Locale.US);
                tts.speak("Left Strafe", TextToSpeech.QUEUE_ADD, null);
                motorTestLeftRear.setPower(1);
                motorTestRightFront.setPower(-1);
                motorTestRightRear.setPower(1);
                motorTestLeftFront.setPower(-1);
            }
            while (this.gamepad1.dpad_right == true) {
                telemetry.addData("TextToSpeech", "test");
                tts.setLanguage(Locale.US);
                tts.speak("Right Strafe", TextToSpeech.QUEUE_ADD, null);
                motorTestRightFront.setPower(1);
                motorTestLeftFront.setPower(1);
                motorTestLeftRear.setPower(-1);
                motorTestRightRear.setPower(-1);
            }
            //DIAGONAL TESTING GROUNDS (PUBG theme plays)
           /* while (this.gamepad1.right_trigger == 0); {
                motorTestLeftFront.setPower(1);
                motorTestRightRear.setPower(-1);
            } */
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
