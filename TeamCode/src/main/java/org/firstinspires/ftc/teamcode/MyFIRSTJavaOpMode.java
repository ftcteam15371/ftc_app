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
    private Gyroscope imu;
    private DcMotor motorTest;
    private DcMotor motorTest2;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;


    @Override
    public void runOpMode() {
        imu = hardwareMap.get(Gyroscope.class, "imu");
        motorTest = hardwareMap.get(DcMotor.class, "motorTest");
        motorTest2 = hardwareMap.get(DcMotor.class, "motorTest2");
        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses START)
        waitForStart();

        // Run until the end of the match (driver presses STOP)
        double tgtPower = 0;
        double tgtPower2 = 0;
        while (opModeIsActive()) {
            tgtPower = -this.gamepad1.left_stick_y;
            motorTest.setPower(tgtPower);
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Motor Power", motorTest.getPower());
            tgtPower2 = -this.gamepad1.right_stick_y;
            motorTest2.setPower(tgtPower2);
            //tgtPower2 *= -1;
            telemetry.addData("Target Power", tgtPower2);
            telemetry.addData("Motor Power", motorTest2.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
