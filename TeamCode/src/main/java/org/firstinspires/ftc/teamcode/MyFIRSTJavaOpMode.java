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
    //private Gyroscope imu;
    private DcMotor motorTestLeftFront;
    private DcMotor motorTestLeftRear;
    private DcMotor motorTestRightFront;
    private DcMotor motorTestRightRear;
    private DcMotor motorTestLinearActuator;
    private DigitalChannel digitalTouch;
    //private DistanceSensor sensorColorRange;
    private Servo servoTest;
    private TextToSpeech tts;
    @Override
    public void runOpMode() {
        //imu = hardwareMap.get(Gyroscope.class, "imu");
        motorTestLeftFront = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorTestLeftRear = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorTestRightFront = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorTestRightRear = hardwareMap.get(DcMotor.class, "motorBackRight");
        motorTestLinearActuator = hardwareMap.get(DcMotor.class, "motorLinearActuator");
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
        while (opModeIsActive()) {
            // simple movement using the joysticks
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
            telemetry.addData("Right Front Motor Power", motorTestRightFront.getPower());
            telemetry.addData("Right Rear Motor Power", motorTestRightRear.getPower());
            motorTestLeftFront.setPower(tgtPowerLeftFront);
            motorTestLeftRear.setPower(tgtPowerLeftRear);
            telemetry.addData("Target Power Left Front", tgtPowerLeftFront);
            telemetry.addData("Target Power Left Rear", tgtPowerLeftRear);
            telemetry.addData("Left Front Motor Power", motorTestLeftFront.getPower());
            telemetry.addData("Left Rear Motor Power", motorTestLeftRear.getPower());
            motorTestLinearActuator.setPower(tgtPowerLinearActuator);
            telemetry.addData("Target Linear Actuator Power", tgtPowerLinearActuator );
            telemetry.addData("Linear Actuator Motor Power", motorTestLinearActuator.getPower());
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
            if (this.gamepad1.y == true) {
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
                
            while (this.gamepad1.a == true) {
                motorTestLinearActuator.setPower(1);
            }
                      
            while (this.gamepad1.b == true) {
                motorTestLinearActuator.setPower(-1);
            }
           
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
/* TODO Linear Actuator Final Code Is Done
TODO Diagonal Strafing (Maybe)
TODO Finish Grabber Code
 */