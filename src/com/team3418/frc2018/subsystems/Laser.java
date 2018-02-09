package com.team3418.frc2018.subsystems;
import  com.team3418.frc2018.HardwareMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Laser extends Subsystem  
{
	public double LaserDistance;
	public double LaserVolts;
	
	static Laser mInstance = new Laser();
	
	public static Laser getInstance() {
		return mInstance;
	}
	
	private AnalogInput mLaserAnalog;
	
	public Laser() {
		mLaserAnalog = HardwareMap.getInstance().mLaser;
		System.out.println("Laser Initialized");
		
		LaserDistance = 0;
		LaserVolts = 0;
	}
	   
	public void updateSubsystem()
	{

		LaserVolts = HardwareMap.getInstance().mLaser.getVoltage() ;
		LaserDistance = (LaserVolts+39) / 0.1696 + 10;
		// Math! ((volts + 39) / 0.1696 + 10);
		
		outputToSmartDashboard();
		
	}
	public void outputToSmartDashboard()
	
	{
		SmartDashboard.putNumber("Laser Distance", LaserDistance);
		
	}
	
	public void stop()
	
	{ 
		
	}
}
