package robot.drive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.Ports;
import java.util.List;
import edu.wpi.first.wpilibj.CANSparkMax.IdleMode;

public class drive extends SubsystemBase {
    private final CANSparkMax leftLeader = new CANSparkMax(Ports.Drive.LEFT_LEADER, MotorType.kBrushless);
    private final CANSparkMax leftFollower = new CANSparkMax(Ports.Drive.LEFT_FOLLOWER, MotorType.kBrushless);
    private final CANSparkMax rightLeader = new CANSparkMax(Ports.Drive.RIGHT_LEADER, MotorType.kBrushless);
    private final CANSparkMax rightFollower = new CANSparkMax(Ports.Drive.RIGHT_FOLLOWER, MotorType.kBrushless);
    
    private final RelativeEncoder leftEncoder = leftLeader.getEncoder();
    private final RelativeEncoder rightEncoder = rightLeader.getEncoder();
    private final AnalogGyro gyro = new AnalogGyro(Ports.Drive.GYRO_CHANNEL);

    public drive() {
        for (CANSparkMax spark : List.of(leftLeader, leftFollower, rightLeader, rightFollower)) {
            spark.restoreFactoryDefaults();
            spark.setIdleMode(IdleMode.kBrake);
        }

        rightFollower.follow(rightLeader);
        leftFollower.follow(leftLeader);

        leftLeader.setInverted(true);

        // Set encoder conversion factors
        leftEncoder.setPositionConversionFactor(DriveConstants.POSITION_FACTOR);
        rightEncoder.setPositionConversionFactor(DriveConstants.POSITION_FACTOR);

        leftEncoder.setVelocityConversionFactor(DriveConstants.VELOCITY_FACTOR);
        rightEncoder.setVelocityConversionFactor(DriveConstants.VELOCITY_FACTOR);

        // Reset encoders
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);

        // Reset Gyro
        gyro.reset();
    }

    public void drive(double leftSpeed, double rightSpeed) {
        leftLeader.set(leftSpeed);
        rightLeader.set(rightSpeed);
    }

    public Command drive(DoubleSupplier vLeft, DoubleSupplier vRight) {
        return run(() -> drive(vLeft.getAsDouble(), vRight.getAsDouble()));
    }
}