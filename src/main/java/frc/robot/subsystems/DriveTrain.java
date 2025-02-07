package frc.robot.subsystems;

import frc.robot.Constants.DriveConstants;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class DriveTrain extends SubsystemBase {
    private WPI_TalonSRX leftMain = new WPI_TalonSRX(DriveConstants.leftMainCAN);
    private WPI_VictorSPX leftFollow = new WPI_VictorSPX(DriveConstants.leftFollowCAN);
    private WPI_TalonSRX rightMain = new WPI_TalonSRX(DriveConstants.rightMainCAN);
    private WPI_VictorSPX rightFollow = new WPI_VictorSPX(DriveConstants.rigthFollowCAN);

    public DriveTrain() {
        leftMain.configFactoryDefault();
        rightMain.configFactoryDefault();

        leftFollow.configFactoryDefault();
        rightFollow.configFactoryDefault();

        leftFollow.follow(leftMain);
        rightFollow.follow(rightMain);

        leftMain.setInverted(false);
        rightMain.setInverted(true);

        leftFollow.setInverted(InvertType.FollowMaster);
        rightFollow.setInverted(InvertType.FollowMaster);
    }

    public Command arcade_drive(DoubleSupplier xSupplier, DoubleSupplier ySupplier, CommandXboxController controller) {
        return run(() -> {
            // uses arcade drive to drive the robot

            double rotate = xSupplier.getAsDouble();
            double drive = ySupplier.getAsDouble();

            double leftSpeed = DriveConstants.driveSpeed * (drive - rotate / 2);
            double rightSpeed = DriveConstants.driveSpeed * (drive + rotate / 2) * 1.01;

            leftMain.setVoltage(leftSpeed);
            rightMain.setVoltage(rightSpeed);

            controller.setRumble(RumbleType.kLeftRumble, Math.abs(leftSpeed / 5));
            controller.setRumble(RumbleType.kRightRumble, Math.abs(rightSpeed / 5));

            SmartDashboard.putNumber("left-speed", leftSpeed);
            SmartDashboard.putNumber("right-speed", rightSpeed);
        });
    }

    public Command tank_drive(DoubleSupplier leftSupplier, DoubleSupplier rightSupplier) {
        // uses tank drive to drive the robot

        return run(() -> {
            double leftAxis = leftSupplier.getAsDouble();
            double rightAxis = rightSupplier.getAsDouble();

            double leftSpeed = leftAxis * DriveConstants.driveSpeed;
            double rightSpeed = rightAxis * DriveConstants.driveSpeed * 1.01;

            leftMain.setVoltage(leftSpeed);
            rightMain.setVoltage(rightSpeed);

            SmartDashboard.putNumber("left_speed", leftSpeed);
            SmartDashboard.putNumber("right_speed", rightSpeed);
        });
    }
}