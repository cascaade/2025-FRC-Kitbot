package frc.robot.subsystems.algae;

public enum AlgaePosition {
    HOME("HOME", 1.1),
    INTAKE("INTAKE", 0.1),
    CARRY("CARRY", 0.27);

    public final String name;
    public final double angle;

    private AlgaePosition(String name, double angle) {
        this.name = name;
        this.angle = angle;
    }

    @Override
    public String toString() {
        return name;
    }
}
