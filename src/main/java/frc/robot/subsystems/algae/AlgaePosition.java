package frc.robot.subsystems.algae;

public enum AlgaePosition {
    UP("UP", 70),
    OUT("OUT", 0);

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
