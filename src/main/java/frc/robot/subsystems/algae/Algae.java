package frc.robot.subsystems.algae;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AlgaeConstants;

public class Algae extends SubsystemBase {
    private WPI_TalonSRX wristMotor = new WPI_TalonSRX(AlgaeConstants.kWristMotorCanId);
    private WPI_VictorSPX indexMotor = new WPI_VictorSPX(AlgaeConstants.kIndexMotorCanId);

    private DutyCycleEncoder encoder = new DutyCycleEncoder(AlgaeConstants.kWristEncoderDIOport);

    private PIDController pid = new PIDController(
        AlgaeConstants.kP,
        AlgaeConstants.kI,
        AlgaeConstants.kD
    );
    private ArmFeedforward feedforward = new ArmFeedforward(0, AlgaeConstants.kG, 0);

    // private boolean intake = false; //! make this work
    // private boolean pushout = false;

    private boolean bumperOn = false;

    public Algae() {
        Preferences.removeAll();
        Preferences.initDouble("testvoltage", 0);
        wristMotor.configFactoryDefault();
        indexMotor.configFactoryDefault();

        wristMotor.setInverted(true);

        pid.setSetpoint(AlgaePosition.HOME.angle);
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

    public Command angle(AlgaePosition setpoint) {
        return runOnce(() -> {
            SmartDashboard.putNumber("setpoint", setpoint.angle);
            System.out.println(setpoint.name);
            pid.setSetpoint(setpoint.angle);
        });
    }

    public Command stop() {
        return runOnce(() -> {
            indexMotor.setVoltage(0);
        });
    }

    public Command goPosition() {
        return runOnce(() -> {
            bumperOn = true;
        });
    }

    public Command stopPosition() {
        return runOnce(() -> {
            bumperOn = false;
            wristMotor.setVoltage(0);
        });
    }

    @Override
    public void periodic() {
        if (!bumperOn) return;

        double encoderPos = encoder.get();

        double outputVoltage = MathUtil.clamp(
            pid.calculate(encoderPos) + feedforward.calculate(pid.getSetpoint(), 0),
            -2, 3
        );

        wristMotor.setVoltage(outputVoltage);

        SmartDashboard.putNumber("encoder-position", encoder.get());
        SmartDashboard.putBoolean("encoder-connected", encoder.isConnected());
        SmartDashboard.putNumber("arm-voltage", outputVoltage);
    }
}