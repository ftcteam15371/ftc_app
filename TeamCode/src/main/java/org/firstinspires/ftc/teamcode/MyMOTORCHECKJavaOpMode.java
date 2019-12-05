package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class MyMOTORCHECKJavaOpMode extends LinearOpMode {


    private DcMotor motorLeftFront;
    private DcMotor motorLeftRear;
    private DcMotor motorRightFront;
    private DcMotor motorRightRear;
    private DcMotor motorElevator;
    private DcMotor motorGrabber;

    @Override
    public void runOpMode() {

        motorLeftFront = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorLeftRear = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorRightFront = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorRightRear = hardwareMap.get(DcMotor.class, "motorBackRight");
        motorElevator = hardwareMap.get(DcMotor.class, "Lifto");
        motorGrabber = hardwareMap.get(DcMotor.class, "Grabber");

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

        if (Math.abs(this.gamepad1.right_stick_y) > 0) ;
        {
            motorRightFront.setPower(this.gamepad1.right_stick_y);
            motorRightRear.setPower(this.gamepad1.right_stick_y);
        }
        //this is a comment. comments are needed, so here they are.
        if (Math.abs(this.gamepad1.left_stick_y) > 0) ;
        {
            //this is another comment.
            motorLeftFront.setPower(-this.gamepad1.left_stick_y);
            motorLeftRear.setPower(-this.gamepad1.left_stick_y);
        }
        if (this.gamepad1.left_stick_x == 0 & this.gamepad1.left_stick_y == 0) {
            motorLeftFront.setPower(0);
            motorLeftRear.setPower(0);
            //unsurprisingly, a comment is here as well.
        }
        if (this.gamepad1.right_stick_x == 0 & this.gamepad1.right_stick_y == 0) {
            motorRightFront.setPower(0);
            motorRightRear.setPower(0);
        }
        if (this.gamepad1.right_bumper == true) {
            motorElevator.setPower(Math.abs(this.gamepad1.left_stick_x));
        }
        /*
        while (this.gamepad1.right_bumper == true & this.gamepad1.y == true) {
            motorElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorElevator.setTargetPosition(20);
            motorElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorElevator.setPower(0.25);
            telemetry.addData("Does is Encoder work", motorElevator.getCurrentPosition());
            telemetry.update();
            }
            while (this.gamepad1.y == false) {
                motorElevator.setPower(0);
            }
        }
*/

    }
}
