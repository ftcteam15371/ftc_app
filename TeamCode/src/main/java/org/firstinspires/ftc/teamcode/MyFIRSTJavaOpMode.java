package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MyFIRSTJavaOpMode extends LinearOpMode {

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


    @Override
    public void runOpMode() {
        // What? No comments? What is this code supposed to do???
        // Stuff.
        imu = hardwareMap.get(Gyroscope.class, "imu");
        motorTestLeftFront = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorTestLeftRear = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorTestRightFront = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorTestRightRear = hardwareMap.get(DcMotor.class, "motorBackRight");
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
            /*
            CHANGES: Removed negative symbol from "tgtPower = this.gamepad1.left_stick_y;,
           Changed right and left for easier steering.
            Buttons added to do straight
             */
            if (Math.abs(this.gamepad1.right_stick_x) > Math.abs(this.gamepad1.right_stick_y)) {
                motorTestRightFront.setPower(this.gamepad1.right_stick_x);
                motorTestRightRear.setPower(-this.gamepad1.right_stick_x);
            }
            if (Math.abs(this.gamepad1.left_stick_x) > Math.abs(this.gamepad1.left_stick_y)) {
                motorTestLeftFront.setPower(this.gamepad1.left_stick_x);
                motorTestLeftRear.setPower(-this.gamepad1.left_stick_x);
            }
            if (Math.abs(this.gamepad1.right_stick_y) > Math.abs(this.gamepad1.right_stick_x)) {
                motorTestRightFront.setPower(this.gamepad1.right_stick_y);
                motorTestRightRear.setPower(this.gamepad1.right_stick_y);
            }
            if (Math.abs(this.gamepad1.left_stick_y) > Math.abs(this.gamepad1.left_stick_x)) {
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
            //right trigger go forward.
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
                motorTestLeftFront.setPower(-1);
                motorTestLeftRear.setPower(1);
                motorTestRightFront.setPower(-1);
                motorTestRightRear.setPower(1);
            }
            while (this.gamepad1.dpad_right == true) {
                motorTestLeftFront.setPower(1);
                motorTestLeftRear.setPower(-1);
                motorTestRightFront.setPower(1);
                motorTestRightRear.setPower(-1);
            }
            if (this.gamepad1.a == true) {
                motorTestLeftFront.setPower(1);
                motorTestLeftRear.setPower(1);
                motorTestRightFront.setPower(-1);
                motorTestRightRear.setPower(-1);
            }
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
