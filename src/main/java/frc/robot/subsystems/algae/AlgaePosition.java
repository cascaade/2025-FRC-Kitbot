package frc.robot.subsystems.algae;

public enum AlgaePosition {
    HOME("HOME", 0.65),
    INTAKE("INTAKE", 0.5);

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
