package frc.robot.subsystems.algae;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AlgaeConstants;

public class Algae extends SubsystemBase {
    private WPI_TalonSRX wristMotor;
    private WPI_VictorSPX indexMotor;

    // private PIDController pid;

    // private boolean intake = false; //! make this work
    // private boolean pushout = false;

    public Algae() {
        wristMotor = new WPI_TalonSRX(AlgaeConstants.kWristMotorCanId);
        indexMotor = new WPI_VictorSPX(AlgaeConstants.kIndexMotorCanId);

        wristMotor.configFactoryDefault();
        indexMotor.configFactoryDefault();
        
        // pid = new PIDController(
        //     AlgaeConstants.kP,
        //     AlgaeConstants.kI,
        //     AlgaeConstants.kD
        // );
    }

    public Command takeIn() {
        return runOnce(() -> {
            indexMotor.setVoltage(-AlgaeConstants.kIndexMotorSpeed);
        });
    }

    public Command release() {
        return runOnce(() -> {
            indexMotor.setVoltage(AlgaeConstants.kIndexMotorSpeed);
        });
    }

    public Command stop() {
        return runOnce(() -> {
            indexMotor.setVoltage(0);
        });
    }
}