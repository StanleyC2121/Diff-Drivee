package robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import robot.drive.Drive;

public class robot {
   
    private final CommandXboxController driver = new CommandXboxController(Ports.OI.DRIVER);
    
  
    private final Drive drive = new Drive();

   
    private void configureBindings() {
        drive.setDefaultCommand(drive.drive(driver::getLeftY, driver::getRightY));
    }

}