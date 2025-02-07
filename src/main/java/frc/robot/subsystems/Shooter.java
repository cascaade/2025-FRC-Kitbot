package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants; 

public class Shooter extends SubsystemBase {
    private WPI_TalonSRX motor; 

    private boolean shootOut = false;
    private boolean shootIn = false;
    
    public Shooter() {
        motor = new WPI_TalonSRX(ShooterConstants.shooterCAN); 
    }

    public void ShootCoral() {
        if (!shootIn) {
            motor.setVoltage(-ShooterConstants.shooterSpeed);
            shootOut = true;
        } else {
            motor.setVoltage(0);
            shootOut = false;
            shootIn = false;
        }
    }

    public void unShootCoral() {
        if (!shootOut) {
            motor.setVoltage(ShooterConstants.shooterSpeed); 
            shootIn = true;
        } else {
            motor.setVoltage(0);
            shootOut = false;
            shootIn = false;
        }
    }

    public void StopShooter() {
        motor.setVoltage(0);
        shootOut = false;
        shootIn = false;
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