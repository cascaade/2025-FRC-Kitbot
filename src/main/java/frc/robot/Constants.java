package frc.robot;

public final class Constants {
    public final class OIConstants {
        public static int XBOXPort = 0;
    }

    public final class ShooterConstants {
        public static int shooterCAN = 5;
        public static double shooterSpeed = 4.0;
    }

    public final class DriveConstants {
        public static int leftMainCAN = 4;
        public static int leftFollowCAN = 2;
        public static int rightMainCAN = 3;
        public static int rigthFollowCAN = 1;

        public static double driveSpeed = -5.0;

        public static double turnSpeed = 1.0;
    }

    public final class AlgaeConstants {
        public static int kWristMotorCanId = 6;
        public static int kIndexMotorCanId = 7;

        public static double kIndexMotorSpeed = 4.0;
        public static double kP = 0;
        public static double kI = 0;
        public static double kD = 0;
    }
}
