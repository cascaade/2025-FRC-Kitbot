package frc.robot.subsystems;

import frc.robot.Constants.DriveConstants;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private WPI_TalonSRX leftMain = new WPI_TalonSRX(DriveConstants.leftMainCAN);
    private WPI_VictorSPX leftFollow = new WPI_VictorSPX(DriveConstants.leftFollowCAN);
    private WPI_TalonSRX rightMain = new WPI_TalonSRX(DriveConstants.rightMainCAN);
    private WPI_VictorSPX rightFollow = new WPI_VictorSPX(DriveConstants.rigthFollowCAN);

    public DriveTrain() {
        leftMain.configFactoryDefault();
        rightMain.configFactoryDefault();

        leftFollow.follow(leftMain);
        rightFollow.follow(rightMain);

        leftMain.setInverted(true); //! Make sure these are the correct way
        rightMain.setInverted(false);

        leftFollow.setInverted(InvertType.FollowMaster); //! Make sure this doesn't step the motors
        rightFollow.setInverted(InvertType.FollowMaster);
    }

    public Command drive(DoubleSupplier xSupplier, DoubleSupplier ySupplier) {
        return run(() -> {
            double axisX = xSupplier.getAsDouble(); // Determines the DIFFERENCE in velocity between wheelsets
            double axisY = ySupplier.getAsDouble(); // Determines the speed of the robot (both wheelsets)

            // Account for stick drift in controllers
            if (axisY > -0.05 && axisY < 0.05) axisY = 0;

            double leftSpeed = 0;
            double rightSpeed = 0;

            double mainSpeed = DriveConstants.driveSpeed * axisY;
            double difference = DriveConstants.turnSpeed * axisX;

            // INNER -> Only the inner set of wheels change velocity
            // OUTER -> Only the outer set of wheels change velocity
            // MIDDLE -> Both sets of wheels change velocity

            // Adjusting for MIDDLE
            leftSpeed = mainSpeed + difference / 2;
            rightSpeed = mainSpeed - difference / 2;

            // Set speeds
            leftMain.setVoltage(leftSpeed);
            rightMain.setVoltage(rightSpeed);

            // SmartDashboard
            SmartDashboard.putNumber("x", axisX);
            SmartDashboard.putNumber("y", axisY);
            SmartDashboard.putNumber("leftSpeed", leftSpeed);
            SmartDashboard.putNumber("rightSpeed", rightSpeed);
        });
    }
}