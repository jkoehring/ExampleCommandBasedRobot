package org.usfirst.frc.team6352.robot.subsystems;

import org.usfirst.frc.team6352.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The subsystem for the gear lifter.
 */
public class GearLift extends Subsystem
{
	SpeedController motor = new Victor(RobotMap.gearLiftChannel);
	
	public GearLift()
	{
		motor = RobotMap.isCompetitionRobot
			? new Spark(RobotMap.gearLiftChannel)
			: new Victor(RobotMap.gearLiftChannel);
	}
			
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void setSpeed(double speed)
	{
		motor.set(speed);
	}
	
	public void stop()
	{
		motor.set(0);
	}
}
