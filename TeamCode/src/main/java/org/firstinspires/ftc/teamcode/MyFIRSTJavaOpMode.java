package org.firstinspires.ftc.teamcode;
import android.speech.tts.TextToSpeech;
import android.widget.EditText;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorController;
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
    // anon: peepeepoopoo
    // Coach M: I suggest using variable names such as motorLeftFront.  Drop the 'test'

    //private Gyroscope imu;

    private DcMotor motorTestLeftFront;
    private DcMotor motorTestLeftRear;
    private DcMotor motorTestRightFront;
    private DcMotor motorTestRightRear;
    private DcMotor motorGrabber;
    private DcMotor motorLifter;
    private int liftPosition;
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
        motorGrabber = hardwareMap.get(DcMotor.class, "Grabber");
        motorLifter = hardwareMap.get(DcMotor.class, "Lifto");
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

            // Coach M: this area of code needs some spacing and comments
            tgtPowerRightFront = this.gamepad1.right_stick_y;
            tgtPowerRightRear = this.gamepad1.right_stick_y;

            // simple movement using the joysticks
            if (Math.abs(this.gamepad1.right_stick_y) > 0) ;
            {
                motorTestRightFront.setPower(this.gamepad1.right_stick_y);
                motorTestRightRear.setPower(this.gamepad1.right_stick_y);
            }
            //this is a comment. comments are needed, so here they are.
            if (Math.abs(this.gamepad1.left_stick_y) > 0) ;
            {
                //this is another comment.
                motorTestLeftFront.setPower(-this.gamepad1.left_stick_y);
                motorTestLeftRear.setPower(-this.gamepad1.left_stick_y);
            }
            if (this.gamepad1.left_stick_x == 0 & this.gamepad1.left_stick_y == 0) {
                motorTestLeftFront.setPower(0);
                motorTestLeftRear.setPower(0);
                //unsurprisingly, a comment is here as well.
            }
            if (this.gamepad1.right_stick_x == 0 & this.gamepad1.right_stick_y == 0) {
                motorTestRightFront.setPower(0);
                motorTestRightRear.setPower(0);
            }
            //all jokes aside, this telemetry stuff doesn't actually... work. we should fix that.
            // TODO: fix telemetry stuff

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

            // D-Pad Controls (using logic and direction to create robojesus)
            while (this.gamepad1.dpad_down == true) {
                motorTestLeftFront.setPower(-1);
                motorTestLeftRear.setPower(-1);
                motorTestRightFront.setPower(1);
                motorTestRightRear.setPower(1);
            }
            //oo ee oo i look just like buddy holly
            while (this.gamepad1.dpad_up == true) {
                motorTestLeftFront.setPower(1);
                motorTestLeftRear.setPower(1);
                motorTestRightFront.setPower(-1);
                motorTestRightRear.setPower(-1);
            }
            //TODO: Give the robot a new name (like robojesus) because Jeebes is dead.
            //TODO: Post-Naming, make a new flag because
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

            while (this.gamepad1.left_bumper == true) {
                while (this.gamepad1.x == true) {
                    motorGrabber.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorGrabber.setTargetPosition(20);
                    sleep(1);
                    motorGrabber.setPower(0.25);
                }
                while (this.gamepad1.x == false) {
                    while (this.gamepad1.y == false) {
                        motorGrabber.setPower(0);
                    }
                }
            }
            while (this.gamepad1.left_bumper == true) {
                while (this.gamepad1.y == true) {
                    motorGrabber.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorGrabber.setTargetPosition(20);
                    sleep(1);
                    motorGrabber.setPower(-0.25);
                }
                while (this.gamepad1.y == false) {
                    while (this.gamepad1.x == false) {
                        motorGrabber.setPower(0);
                    }
                }
            }
                /*
            if (this.gamepad1.right_bumper = true){
                while (this.gamepad1.y = true) {
                    while (digitalStopper > 100) {
                        motorLifter.setPower(1);
                        digitalStopper++;
                    }
                }
                while (this.gamepad1.a =true){
                    while (digitalStopper <= 0)
                    motorLifter.setPower(-1);
                    digitalStopper--;
                }
            }
            while (digitalStopper >= 100)  {
                while (this.gamepad1.y = true) {
                    motorLifter.setPower (-1);
                }
                while (this.gamepad1.a = true) {
                    motorLifter.setPower (-1);
                }
            }
            while (digitalStopper <= -100) {
                while (this.gamepad1.y = true) {
                    motorLifter.setPower (1);
                }
                while (this.gamepad1.a = true) {
                    motorLifter.setPower (1);
                }
            }
            */
                if (this.gamepad1.right_bumper == true) {
                    motorLifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    motorLifter.setTargetPosition(20);
                    if (this.gamepad1.y == true) {
                        motorLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        motorLifter.setPower(0.25);
                        telemetry.addData("Does is Encoder work", motorLifter.getCurrentPosition());
                        telemetry.update();
                    }
                    while (this.gamepad1.y == false) {
                        motorLifter.setPower(0);
                    }
                }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}



