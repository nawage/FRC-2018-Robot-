package com.team3418.frc2018;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

public class HardwareMap {
	
	private static HardwareMap mInstance = new HardwareMap();
	
	public static HardwareMap getInstance(){
		return mInstance;
	}
	
	public Compressor mCompressor;
	public VictorSP mIntakeHardware;
	public VictorSP mFeederHardware;
	public VictorSP mClimberHardware;
	public Solenoid mLeftShifterHardware;
	public Solenoid mRightShifterHardware;
	public AnalogInput mLaserHardware;
	public Solenoid mClimberReleaseHardware;
	
	
	
	public ADXRS450_Gyro mGyro;	
	
	HardwareMap() {
		
		try
		{
//			mClimberHardware = new VictorSP(Constants.kClimberId);
			mLeftShifterHardware = new Solenoid(Constants.kLeftShifterSolenoidId);
	    	mRightShifterHardware = new Solenoid(Constants.kRightShifterSolenoidId);
//	    	mIntakeHardware = new VictorSP(Constants.kIntakeRollerId);
//	    	mFeederHardware = new VictorSP(Constants.kFeederId);
			mCompressor = new Compressor(0);
//			mGyro = new ADXRS450_Gyro();
//			mGyro.calibrate();
			mLaserHardware = new AnalogInput(0);
			mClimberReleaseHardware = new Solenoid(Constants.kClimberReleaseSolenoidId);
			

		}
		catch(Exception e)
		{
			
		}
	}

}
