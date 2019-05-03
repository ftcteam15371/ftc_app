package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class Autonomous extends LinearOpMode {
    // Link to java docs https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html
    // CC: Todo: Autonomous opmode
    // CC: Todo: Let's get a servo working.
    // CC: Todo: Add code to limit the time travel of the linear actuator, such as explained here:
    // https://www.youtube.com/watch?v=Vgye8ZsW7uw&list=LLwEHC44E7sPhbNzBcmWoEgA
    private Gyroscope imu;
    private DcMotor motorLeftFront;
    private DcMotor motorLeftRear;
    private DcMotor motorRightFront;
    private DcMotor motorRightRear;
    private DcMotor motorLinearActuator;
    private DcMotor motorLinearSlide;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;

    @Override
    public void runOpMode() {
        // What? No comments? What is this code supposed to do???
        // Stuff.
        imu = hardwareMap.get(Gyroscope.class, "imu");
        motorLeftFront = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorLeftRear = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorRightFront = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorRightRear = hardwareMap.get(DcMotor.class, "motorBackRight");

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
        double tgtPowerLinearActuator = 0;
        double tgtPowerLinearSlide = 0;
        while (opModeIsActive()) {

            motorRightFront.setPower(tgtPowerRightFront);
            motorRightRear.setPower(tgtPowerRightRear);
            telemetry.addData("Target Power Right Front", tgtPowerRightFront);
            telemetry.addData("Target Power Right Rear", tgtPowerRightRear);
            telemetry.addData("Motor Power Right Front", motorRightFront.getPower());
            telemetry.addData("Motor Power Right Rear", motorRightRear.getPower());
            motorLeftFront.setPower(tgtPowerLeftFront);
            motorLeftRear.setPower(tgtPowerLeftRear);
            telemetry.addData("Target Power Left Front", tgtPowerLeftFront);
            telemetry.addData("Target Power Left Rear", tgtPowerLeftRear);
            telemetry.addData("Motor Power Left Front", motorLeftFront.getPower());
            telemetry.addData("Motor Power Left Rear", motorLeftRear.getPower());
            motorLinearActuator.setPower(tgtPowerLinearActuator);
            motorLinearSlide.setPower(tgtPowerLinearSlide);
            motorLeftFront.setPower(1);
            motorLeftRear.setPower(1);
            motorRightFront.setPower(1);
            motorRightRear.setPower(1);
            sleep(100);
            motorRightRear.setPower(1);
            motorRightFront.setPower(1);
            sleep(100);
            motorLeftFront.setPower(1);
            motorLeftRear.setPower(1);
            motorRightFront.setPower(1);
            motorRightRear.setPower(1);
            sleep(100);
            motorLeftFront.setPower(0);
            motorLeftRear.setPower(0);
            motorRightFront.setPower(0);
            motorRightRear.setPower(0);

        }
    }
}
