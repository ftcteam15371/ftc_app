package org.firstinspires.ftc.teamcode;
import android.speech.tts.TextToSpeech;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
//import com.qualcomm.robotcore.hardware.DistanceSensor;
//import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class JavaOpMode1 extends LinearOpMode {
    // Link to java docs https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html
    //private Gyroscope imu;
    private DcMotor motorLeftFront;
    private DcMotor motorLeftRear;
    private DcMotor motorRightFront;
    private DcMotor motorRightRear;
    private DcMotor motorLinearActuator;
    private DcMotor motorLinearSlide;
    private DigitalChannel digitalTouch;
    //private DistanceSensor sensorColorRange;
    private Servo servoTest;
    private TextToSpeech tts;
    @Override
    public void runOpMode() {
        //imu = hardwareMap.get(Gyroscope.class, "imu");
        motorLeftFront = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorLeftRear = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorRightFront = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorRightRear = hardwareMap.get(DcMotor.class, "motorBackRight");
        motorLinearActuator = hardwareMap.get(DcMotor.class, "motorLinearActuator");
        motorLinearSlide = hardwareMap.get(DcMotor.class, "motorLinearActuator");
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
        while (opModeIsActive()) {
            // simple movement using the joysticks
            if (Math.abs(this.gamepad1.right_stick_y) > 0) ; {
                motorRightFront.setPower(this.gamepad1.right_stick_y);
                motorRightRear.setPower(this.gamepad1.right_stick_y);
            }
            if (Math.abs(this.gamepad1.left_stick_y) > 0); {
                motorLeftFront.setPower(-this.gamepad1.left_stick_y);
                motorLeftRear.setPower(-this.gamepad1.left_stick_y);
            }
            if (this.gamepad1.left_stick_x == 0 & this.gamepad1.left_stick_y == 0) {
                motorLeftFront.setPower(0);
                motorLeftRear.setPower(0);
            }
            if (this.gamepad1.right_stick_x == 0 & this.gamepad1.right_stick_y == 0) {
                motorRightFront.setPower(0);
                motorRightRear.setPower(0);
            }
            motorRightFront.setPower(tgtPowerRightFront);
            motorRightRear.setPower(tgtPowerRightRear);
            telemetry.addData("Target Power Right Front", tgtPowerRightFront);
            telemetry.addData("Target Power Right Rear", tgtPowerRightRear);
            telemetry.addData("Right Front Motor Power", motorRightFront.getPower());
            telemetry.addData("Right Rear Motor Power", motorRightRear.getPower());
            motorLeftFront.setPower(tgtPowerLeftFront);
            motorLeftRear.setPower(tgtPowerLeftRear);
            telemetry.addData("Target Power Left Front", tgtPowerLeftFront);
            telemetry.addData("Target Power Left Rear", tgtPowerLeftRear);
            telemetry.addData("Left Front Motor Power", motorLeftFront.getPower());
            telemetry.addData("Left Rear Motor Power", motorLeftRear.getPower());
            motorLinearActuator.setPower(tgtPowerLinearActuator);
            motorLinearSlide.setPower(tgtPowerGrabber);
            telemetry.addData("Target Linear Actuator Power", tgtPowerLinearActuator );
            telemetry.addData("Target Grabber Power", tgtPowerGrabber );
            telemetry.addData("Linear Actuator Motor Power", motorLinearActuator.getPower());
            telemetry.addData("Grabber Power", motorLinearSlide.getPower());
            // D-Psd controls.
            while (this.gamepad1.dpad_down == true) {
                motorLeftFront.setPower(-1);
                motorLeftRear.setPower(-1);
                motorRightFront.setPower(1);
                motorRightRear.setPower(1);
            }
            //left trigger go backwards
            while (this.gamepad1.dpad_up == true) {
                motorLeftFront.setPower(1);
                motorLeftRear.setPower(1);
                motorRightFront.setPower(-1);
                motorRightRear.setPower(-1);
            }
            while (this.gamepad1.dpad_left == true) {
                motorLeftRear.setPower(1);
                motorRightFront.setPower(-1);
                motorRightRear.setPower(1);
                motorLeftFront.setPower(-1);
            }
            while (this.gamepad1.dpad_right == true) {
                motorRightFront.setPower(1);
                motorLeftFront.setPower(1);
                motorLeftRear.setPower(-1);
                motorRightRear.setPower(-1);
            }
            if (this.gamepad1.y == true) {
                motorLeftFront.setPower(1);
                motorLeftRear.setPower(1);
                motorRightFront.setPower(-1);
                motorRightRear.setPower(-1);
                sleep(1);
                motorLeftFront.setPower(-1);
                motorLeftRear.setPower(-1);
                motorRightFront.setPower(1);
                motorRightRear.setPower(-1);
                sleep(1);
                motorLeftFront.setPower(-1);
                motorLeftRear.setPower(-1);
                motorRightFront.setPower(-1);
                motorRightRear.setPower(-1);
                sleep(1);
            }
            while (this.gamepad1.a == true) {
                motorLeftFront.setPower(0);
                motorLeftRear.setPower(0);
                motorRightFront.setPower(0);
                motorRightRear.setPower(0);
                motorLinearActuator.setPower(-this.gamepad1.left_stick_y);
            }
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
/*
TODO Finish Grabber Code
 */