
package org.usfirst.frc.team6352.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.UsbCameraInfo;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6352.robot.commands.DriveAutonomous;
import org.usfirst.frc.team6352.robot.subsystems.GearLift;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	// The following are for configuring the USB cameras.
	// Network bandwidth from the robot to the driver station is limited by the FMS.
	// When using multiple USB cameras, the image size and frame rate may
	// need to be adjusted to stay within the allowed bandwidth.
	// Information about bandwidth limitations can be found at
	// http://wpilib.screenstepslive.com/s/4485/m/24193/l/291972-fms-whitepaper.
	private final int usbCameraImageWidth = 640;
	private final int usbCameraImageHeight = 480;
	private final int usbCameraFrameRate = 10;
	private UsbCamera usbCameras[];
	
	// The gear lift subsystem:
	public static final GearLift gearLift = new GearLift();
	
	// The one instance of the OI:
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		oi = new OI();
		
		chooser.addDefault("Default Auto", new DriveAutonomous());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		// Do not delete the following line!
		CameraServer.getInstance();
		initializeUsbCameras();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit()
	{

	}

	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit()
	{
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		LiveWindow.run();
	}
	
	
	/**
	 * Starts up USB cameras, starting capture on each one.
	 */
	private void initializeUsbCameras()
	{
		UsbCameraInfo infos[] = UsbCamera.enumerateUsbCameras();
		usbCameras = new UsbCamera[infos.length];
		for (int i = 0; i < usbCameras.length; i++)
		{
			usbCameras[i] = new UsbCamera("USB" + i, infos[i].path);
			usbCameras[i].setResolution(usbCameraImageWidth, usbCameraImageHeight);
			usbCameras[i].setFPS(usbCameraFrameRate);
			System.out.println("Created USB camera " + i + ": " + usbCameras[i].getPath());
			CameraServer.getInstance().startAutomaticCapture(usbCameras[i]);
		}
	}

}
