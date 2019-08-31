package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutonomousOpMode extends LinearOpMode {
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
    //private DigitalChannel digitalTouch;
    //private DistanceSensor sensorColorRange;
    @Override
    public void runOpMode() {

        //imu = hardwareMap.get(Gyroscope.class, "imu");
        motorLeftFront = hardwareMap.dcMotor.get("motorFrontLeft");
        motorLeftRear = hardwareMap.dcMotor.get("motorBackLeft");
        motorRightFront = hardwareMap.dcMotor.get("motorFrontRight");
        motorRightRear = hardwareMap.get(DcMotor.class, "motorBackRight");
        motorLinearActuator = hardwareMap.get(DcMotor.class, "motorLinearActuator");
        motorLinearSlide = hardwareMap.get(DcMotor.class, "motorLinearSlide");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses START)
        waitForStart();
        // Run until the end of the match (driver presses STOP)
       motorLinearActuator.setDirection(DcMotor.Direction.REVERSE);
       motorLinearActuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while   (opModeIsActive()) {


            telemetry.addData("Status", "Running");
                telemetry.update();
            }
        }
    }
