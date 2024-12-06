package robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drive.Drive;
import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private final Drive drive;
    private final DoubleSupplier leftSpeed;
    private final DoubleSupplier rightSpeed;

    public DriveCommand(Drive drive, DoubleSupplier leftSpeed, DoubleSupplier rightSpeed) {
        this.drive = drive;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        // Initialization code if needed (e.g., reset encoders, etc.)
    }

    @Override
    public void execute() {
        // Drive the robot with the joystick values
        drive.drive(leftSpeed.getAsDouble(), rightSpeed.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the motors when the command ends
        drive.drive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false; // Run indefinitely until manually interrupted
    }
}
