package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class OI {

    private XboxController xbox;
    public Joystick joystick;
    public OI(){
        intialize();
    }

    private void intialize() {
//        xbox = new XboxController(RobotMap.XBOX_PORT);
        joystick = new Joystick(RobotMap.JOYSTICK_PORT);
    }

    public XboxController getXbox() {
        return xbox;
    }

}
