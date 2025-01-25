package frc.robot.subsystems;

import frc.robot.Constants.DriveTrainConstants;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private WPI_TalonSRX leftMain = new WPI_TalonSRX(DriveTrainConstants.leftMainCAN);
    private WPI_VictorSPX leftFollow = new WPI_VictorSPX(DriveTrainConstants.leftFollowCAN);
    private WPI_TalonSRX rightMain = new WPI_TalonSRX(DriveTrainConstants.rightMainCAN);
    private WPI_VictorSPX rightFollow = new WPI_VictorSPX(DriveTrainConstants.rigthFollowCAN);

    public DriveTrain() {}

    public void rightDrive(double speed) {
        // rightMain.setVoltage(speed);
        rightFollow.setVoltage(speed);
        System.out.println("right drive");
        System.out.println(speed);
    }

    public void leftDrive(double speed) {
        // leftMain.setVoltage(-speed);
        leftFollow.setVoltage(-speed);
        System.out.println("left drive");
        System.out.println(speed);
    }

    public Command DriveRight(double speed) {
        return runOnce(() -> {
            rightDrive(speed);
        });
    }

    public Command DriveLeft(double speed) {
        return runOnce(() -> {
            leftDrive(speed);
        });
    }

    public Command drive(DoubleSupplier xSupplier, DoubleSupplier ySupplier) {
        return run(() -> {
            SmartDashboard.putNumber("x", xSupplier.getAsDouble());
            SmartDashboard.putNumber("y", ySupplier.getAsDouble());
        });
    }

    @Override
    public void periodic() {
        
    }
}