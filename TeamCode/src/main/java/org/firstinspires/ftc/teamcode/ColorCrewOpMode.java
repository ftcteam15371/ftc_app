package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class ColorCrewOpMode extends LinearOpMode {


    private DcMotor motorLeftFront;
    private DcMotor motorLeftRear;
    private DcMotor motorRightFront;
    private DcMotor motorRightRear;
    private DcMotor linearSlideProportional;
    private DcMotor linearRotatoProportional;
    @Override
    public void runOpMode() {

        motorLeftFront = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorLeftRear = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorRightFront = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorRightRear = hardwareMap.get(DcMotor.class, "motorBackRight");

        waitForStart();

        boolean moveForward = false;
        boolean moveBackwards = false;
        boolean strafeRight = false;
        boolean strafeLeft = false;
        double tgtPowerRightFront = 0;
        double tgtPowerRightRear = 0;
        double tgtPowerLeftFront = 0;
        double tgtPowerLeftRear = 0;

        telemetry.addData("Target Power Right Front", tgtPowerRightFront);
        telemetry.addData("Target Power Right Rear", tgtPowerRightRear);
        telemetry.addData("Target Power Left Front", tgtPowerLeftFront);
        telemetry.addData("Target Power Left Rear", tgtPowerLeftRear);
        telemetry.addData("Motor Power Right Front", motorRightFront.getPower());
        telemetry.addData("Motor Power Right Rear", motorRightRear.getPower());
        telemetry.addData("Motor Power Left Front", motorLeftFront.getPower());
        telemetry.addData("Motor Power Left Rear", motorLeftRear.getPower());

        motorLeftFront.setPower(-tgtPowerLeftFront);
        motorLeftRear.setPower(-tgtPowerLeftRear);
        motorRightFront.setPower(-tgtPowerRightFront);
        motorRightRear.setPower(-tgtPowerRightRear);
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
        if (strafeRight  == false) {
            motorRightFront.setPower(0);
            motorRightRear.setPower(0);
            motorLeftFront.setPower(0);
            motorLeftRear.setPower(0);
        }



    }
}