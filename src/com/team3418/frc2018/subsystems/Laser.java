package com.team3418.frc2018.subsystems;
import  com.team3418.frc2018.HardwareMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Laser extends Subsystem  
{
	public double LaserDistance;
	public double LaserVolts;
	
	public Laser()
	{
		LaserDistance = 0;
		LaserVolts = 0;
	}
	   
	public void updateSubsystem()
	{

		LaserVolts = HardwareMap.getInstance().mLaser.getVoltage() ;
		LaserDistance = (LaserVolts+39) / 0.1696 + 10;
		// Math! ((volts + 39) / 0.1696 + 10);
		
	}
	public void outputToSmartDashboard()
	
	{
		SmartDashboard.putNumber("Laser Distance", LaserDistance);
		
	}
	
	public void stop()
	
	{ 
		
	}
}
