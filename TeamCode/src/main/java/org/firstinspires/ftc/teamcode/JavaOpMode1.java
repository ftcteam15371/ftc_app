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
    // private Gyroscope imu;
    private DcMotor motorLeftFront;
    private DcMotor motorLeftRear;
    private DcMotor motorRightFront;
    private DcMotor motorRightRear;
    private DigitalChannel digitalTouch;
    // private DistanceSensor sensorColorRange;
    private Servo servoTest;


    @Override
    public void runOpMode() {
        // What? No comments? What is this code supposed to do???
        // Stuff.
        // imu = hardwareMap.get(Gyroscope.class, "imu");
        motorLeftFront = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorLeftRear = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorRightFront = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorRightRear = hardwareMap.get(DcMotor.class, "motorBackRight");
        
        // these are never used
        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        // sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
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
        double servoPosition = 0.5;
        while (opModeIsActive()) {
            //Change Notes: made the diagonals. they probably don't work, so just revert them if they don't.
            //yep.

            // simple forward - backwards movement, using the joystick,s
            
            //
            // The brackets are wrong on these first two
            //
            {
                if (Math.abs(this.gamepad1.right_stick_y) > 0) ;
                motorRightFront.setPower(this.gamepad1.right_stick_y);
                motorRightRear.setPower(this.gamepad1.right_stick_y);
            }
            {
              if (Math.abs(this.gamepad1.left_stick_y) > 0);
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

            motorRightFront.setPower(-tgtPowerRightFront);
            motorRightRear.setPower(-tgtPowerRightRear);
            telemetry.addData("Target Power Right Front", tgtPowerRightFront);
            telemetry.addData("Target Power Right Rear", tgtPowerRightRear);
            telemetry.addData("Motor Power Right Front", motorRightFront.getPower());
            telemetry.addData("Motor Power Right Rear", motorRightRear.getPower());
            motorLeftFront.setPower(-tgtPowerLeftFront);
            motorLeftRear.setPower(-tgtPowerLeftRear);
            telemetry.addData("Target Power Left Front", tgtPowerLeftFront);
            telemetry.addData("Target Power Left Rear", tgtPowerLeftRear);
            telemetry.addData("Motor Power Left Front", motorLeftFront.getPower());
            telemetry.addData("Motor Power Left Rear", motorLeftRear.getPower());
            // D-Psd controls.
            while (this.gamepad1.dpad_down == true) {
                motorLeftFront.setPower(1);
                motorLeftRear.setPower(1);
                motorRightFront.setPower(-1);
                motorRightRear.setPower(-1);
            }
            while (this.gamepad1.dpad_up == true) {
                motorLeftFront.setPower(-1);
                motorLeftRear.setPower(-1);
                motorRightFront.setPower(1);
                motorRightRear.setPower(1);
            }
            while (this.gamepad1.dpad_left == true) {
                motorLeftFront.setPower(1);
                motorLeftRear.setPower(-1);
                motorRightFront.setPower(1);
                motorRightRear.setPower(-1);
            }
            while (this.gamepad1.dpad_right == true) {
                motorLeftFront.setPower(-1);
                motorLeftRear.setPower(1);
                motorRightFront.setPower(-1);
                motorRightRear.setPower(1);
            }
            // Change position of servo. Left bumper up, right bumper down (the number)
            while (this.gamepad1.left_bumper == true) {
                servoPosition = servoPosition + 0.005555;
                if (servoPosition > 1) {
                    servoPosition = 1;
                }
                servoTest.setPosition(servoPosition);
            }
            while (this.gamepad1.right_bumper == true) {
                servoPosition = servoPosition - 0.005555;
                if (servoPosition < 0) {
                    servoPosition = 0;
                }
                servoTest.setPosition(servoPosition);
            }
            if (gamepad1.y) {
                servoPosition = 0.5;
                servoTest.setPosition(servoPosition);
            }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
