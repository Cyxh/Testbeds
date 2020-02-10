package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.motor.Filter;
import frc.robot.subsystems.DriveTrain;

import java.util.function.DoubleSupplier;


public class DriveTrainCommand extends CommandBase {

    public static final double MAX_SPEED = 1.0;

    private final DriveTrain driveTrain;
    private DoubleSupplier leftInput, rightInput;
    private Filter leftFilter, rightFilter;

    private boolean filterEnabled;

    public DriveTrainCommand(DoubleSupplier leftInput, DoubleSupplier rightInput, DriveTrain driveTrain) {
        // enable filtering by default
        this(leftInput, rightInput, driveTrain, true);
    }

    public DriveTrainCommand(DoubleSupplier leftInput, DoubleSupplier rightInput, DriveTrain driveTrain, boolean filterEnabled) {
        this.driveTrain = driveTrain;
        this.leftInput = leftInput; // adapted from the `DriveTrain` example // todo explain double supplier
        this.rightInput = rightInput;
        this.filterEnabled = filterEnabled;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        leftFilter = new Filter(0.1); // todo adjust sensitivity
        rightFilter = new Filter(0.1); // todo adjust sensitivity
    }

    @Override
    public void execute() {
        double maxSpeed = MAX_SPEED; // todo actually adjust max speed based on other circumstances
        if (filterEnabled) {
            leftFilter.update(leftInput.getAsDouble());
            rightFilter.update(rightInput.getAsDouble());
            driveTrain.set(rightFilter.getValue(), leftFilter.getValue());
        } else {
            driveTrain.set(leftInput.getAsDouble(), rightInput.getAsDouble());
        }
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        this.driveTrain.stop();
    }


}