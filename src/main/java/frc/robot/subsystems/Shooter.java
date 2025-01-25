package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants; 

public class Shooter extends SubsystemBase {
    private WPI_TalonSRX motor; 
    
    public Shooter() {
        motor = new WPI_TalonSRX(ShooterConstants.shooterCAN); 
    }

    public void ShootCoral() {
        motor.setVoltage(-ShooterConstants.shooterSpeed); 
    }

    public void unShootCoral() {
        motor.setVoltage(ShooterConstants.shooterSpeed); 
    }

    public void StopShooter() {
        motor.setVoltage(0);
    }

    public Command start() {
        return runOnce(() -> {
            ShootCoral();
        });
    }

    public Command astart() {
        return runOnce(() -> {
            unShootCoral();
        });
    }

    public Command stop() {
        return runOnce(() -> {
            StopShooter(); 
        });
    }
} 