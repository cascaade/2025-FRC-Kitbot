package frc.robot.subsystems.lights;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDConstants;

public class Lights extends SubsystemBase {
    private Spark lights;
    
    public Lights() {
        lights = new Spark(LEDConstants.port);

        Preferences.initDouble("patternType", 0.49);
    }

    public void applyPattern(double pattern) {
        lights.set(pattern);
    }

    public Command setLights() {
        return runOnce(() -> {
            applyPattern(Preferences.getDouble("patternType", 0.37));
        });
    }
}