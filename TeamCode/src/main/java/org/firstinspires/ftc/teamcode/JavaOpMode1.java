package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp
public class JavaOpMode1 extends LinearOpMode {

    private DcMotor motorLeftFront;
    private DcMotor motorLeftRear;
    private DcMotor motorRightFront;
    private DcMotor motorRightRear;
    private DcMotor motorLinearActuator;
    private DcMotor motorLinearSlideX;
    private DcMotor motorLinearSlideY;
    private DigitalChannel digitalTouch;
    private Servo servoTest;
    private TouchSensor limitSwitchTest;
    private int thisIsANumber = 420;

    @Override
    public void runOpMode() {

        motorLinearActuator = hardwareMap.get(DcMotor.class, "linearActuator");
        motorLeftFront = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorLeftRear = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorRightFront = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorRightRear = hardwareMap.get(DcMotor.class, "motorBackRight");
        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        servoTest = hardwareMap.get(Servo.class, "servoTest");
        limitSwitchTest = hardwareMap.get(TouchSensor.class, "limitSwitchTest");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        double tgtPowerRightFront = 0;
        double tgtPowerRightRear = 0;
        double tgtPowerLeftFront = 0;
        double tgtPowerLeftRear = 0;
        double servoPosition = 0.5;
        boolean moveForward = false;
        boolean moveBackwards = false;
        boolean strafeRight = false;
        boolean strafeLeft = false;

        if (moveForward == true) {
            motorRightFront.setPower(1);
            motorRightRear.setPower(1);
            motorLeftFront.setPower(1);
            motorLeftRear.setPower(1);
        }
        if (moveForward == false) {
            motorRightFront.setPower(0);
            motorRightRear.setPower(0);
            motorLeftFront.setPower(0);
            motorLeftRear.setPower(0);
        }
        if (moveBackwards == true) {
            motorRightFront.setPower(-1);
            motorRightRear.setPower(-1);
            motorLeftFront.setPower(-1);
            motorLeftRear.setPower(-1);
        }
        if (moveBackwards == false) {
            motorRightFront.setPower(0);
            motorRightRear.setPower(0);
            motorLeftFront.setPower(0);
            motorLeftRear.setPower(0);
        }
        if (strafeLeft == true) {
            motorRightFront.setPower(1);
            motorRightRear.setPower(-1);
            motorLeftFront.setPower(1);
            motorLeftRear.setPower(-1);
        }
        if (strafeLeft == false) {
            motorRightFront.setPower(0);
            motorRightRear.setPower(0);
            motorLeftFront.setPower(0);
            motorLeftRear.setPower(0);
        }
        if (strafeRight == true) {
            motorRightFront.setPower(-1);
            motorRightRear.setPower(1);
            motorLeftFront.setPower(-1);
            motorLeftRear.setPower(1);
        }
        if (strafeRight == false) {
            motorRightFront.setPower(0);
            motorRightRear.setPower(0);
            motorLeftFront.setPower(0);
            motorLeftRear.setPower(0);
        }
        limitSwitchTest.equals(false);
/*
        motorLinearActuator.setDirection(DcMotor.Direction.REVERSE);
        motorLinearActuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLinearActuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
*/
        motorLinearActuator.setDirection((DcMotor.Direction.FORWARD));
    while (opModeIsActive()) {
            telemetry.addData("Target Power Right Front", tgtPowerRightFront);
            telemetry.addData("Target Power Right Rear", tgtPowerRightRear);
            telemetry.addData("Motor Power Right Front", motorRightFront.getPower());
            telemetry.addData("Motor Power Right Rear", motorRightRear.getPower());
            telemetry.addData("Target Power Left Front", tgtPowerLeftFront);
            telemetry.addData("Target Power Left Rear", tgtPowerLeftRear);
            telemetry.addData("Motor Power Left Front", motorLeftFront.getPower());
            telemetry.addData("Motor Power Left Rear", motorLeftRear.getPower());

            motorLeftFront.setPower(-tgtPowerLeftFront);
            motorLeftRear.setPower(-tgtPowerLeftRear);
            motorRightFront.setPower(-tgtPowerRightFront);
            motorRightRear.setPower(-tgtPowerRightRear);

            {
                if (Math.abs(this.gamepad1.right_stick_y) > 0) ;
                motorRightFront.setPower(this.gamepad1.right_stick_y);
                motorRightRear.setPower(this.gamepad1.right_stick_y);
            }
            {
                if (Math.abs(this.gamepad1.left_stick_y) > 0) ;
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

            while (this.gamepad1.dpad_down == true) {
                motorRightFront.setPower(-1);
                motorRightRear.setPower(-1);
                motorLeftFront.setPower(-1);
                motorLeftRear.setPower(-1);
            }
            while (this.gamepad1.dpad_up == true) {
                motorRightFront.setPower(1);
                motorRightRear.setPower(1);
                motorLeftFront.setPower(1);
                motorLeftRear.setPower(1);
            }
            while (this.gamepad1.dpad_left == true) {
                motorRightFront.setPower(1);
                motorRightRear.setPower(-1);
                motorLeftFront.setPower(1);
                motorLeftRear.setPower(-1);
            }
            while (this.gamepad1.dpad_right == true) {
                motorLeftFront.setPower(-1);
                motorLeftRear.setPower(1);
                motorRightFront.setPower(-1);
                motorRightRear.setPower(1);
            }

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

            if (this.gamepad1.a = true) {
                motorLinearActuator.setPower(1);
            }
            if (this.gamepad1.b = true) {
                motorLinearActuator.setPower(-1);
            }

        }

    }
}