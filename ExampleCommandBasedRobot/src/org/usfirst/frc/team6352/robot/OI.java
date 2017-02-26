package org.usfirst.frc.team6352.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6352.robot.commands.MoveGearLift;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	// The joystick used for driving:
	Joystick stick = new Joystick(0);
	
	// The Xbox controller used to activate various subsystems:
	XboxController gameController = new XboxController(1);
	
	// Buttons:
	
	// Give meaningful names to the game controller buttons:
	public final static int gameControllerButtonA = 1;
	public final static int gameControllerButtonB = 2;
	public final static int gameControllerButtonX = 3;
	public final static int gameControllerButtonBumperLeft = 4;
	public final static int gameControllerButtonBumperRight = 5;
	public final static int gameControllerButtonBack = 7;
	public final static int gameControllerButtonStart = 8;
	public final static int gameControllerButtonStickLeft = 9;
	public final static int gameControllerButtonStickRight = 10;
	
	Button gearLiftUpButton = new JoystickButton(gameController, gameControllerButtonA);
	Button gearLiftDownButton = new JoystickButton(gameController, gameControllerButtonB);
	
	
	// SmartDashboard keys:
	public final static String dashboardGearLiftSpeedUp = "Gear Lift Speed Up";
	public final static String dashboardGearLiftSpeedDown = "Gear Lift Speed Down";
	public final static String dashboardGearLiftTimeout = "Gear List Timeout (secs)";
	
	
	// Constructor:
	public OI()
	{
		// Bind buttons to commands:
		gearLiftUpButton.whenPressed(new MoveGearLift(dashboardGearLiftSpeedUp, dashboardGearLiftTimeout));
		gearLiftDownButton.whenPressed(new MoveGearLift(dashboardGearLiftSpeedDown, dashboardGearLiftTimeout));
		
		// Put default values on SmartDashboard:
		SmartDashboard.putNumber(dashboardGearLiftSpeedUp, 0.5);
		SmartDashboard.putNumber(dashboardGearLiftSpeedDown, -0.4);
		SmartDashboard.putNumber(dashboardGearLiftTimeout, 5.0);
	}
	
}
