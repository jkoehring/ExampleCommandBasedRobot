package org.usfirst.frc.team6352.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6352.robot.Robot;

/**
 * A command for moving the gear lift.
 */
public class MoveGearLift extends Command
{
	private String speedKey = null;
	private String timeoutKey = null;
	
	private double speed;
	private double timeout;
	
	private MoveGearLift()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.gearLift);
	}
	
	/**
	 * Construct an instance that gets parameters from the SmartDashboard.
	 * 
	 * In the command based robot framework command objects are created by the OI
	 * constructor which is called by Robot.robotInit(). Thus, getting the
	 * parameter values from the SmartDashboard is deferred until the command
	 * is actually executed during runtime.
	 * 
	 * @param speedKey The SmartDashboard key for getting the motor speed
	 * @param timeoutKey The SmartDasboard key for getting the command timeout
	 */
	public MoveGearLift(String speedKey, String timeoutKey)
	{
		this();
		this.speedKey = speedKey;
		this.timeoutKey = timeoutKey;
	}
	
	/**
	 * Construct an instance with set values.
	 * 
	 * @param speed The motor speed (-1 <= speed <= 1)
	 * @param timeout The command timeout (seconds)
	 */
	public MoveGearLift(double speed, double timeout)
	{
		this();
		this.speed = speed;
		this.timeout = timeout;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		if (null != speedKey)
		{
			speed = SmartDashboard.getNumber(speedKey, 0.0);
			timeout = SmartDashboard.getNumber(timeoutKey, 0.0);
		}
		
		setTimeout(timeout);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		Robot.gearLift.setSpeed(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.gearLift.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		end();
	}
}
