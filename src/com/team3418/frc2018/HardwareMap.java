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
	
	public Compressor mCompressorHardware;
	public VictorSP mIntakeLeftHardware;
	public VictorSP mIntakeRightHardware;
	public VictorSP mFeederHardware;
	public VictorSP mClimberHardware;
	public Solenoid mLeftShifterHardware;
	public Solenoid mRightShifterHardware;
	public AnalogInput mLaserHardware;
	public Solenoid mClimberReleaseHardware;
	public VictorSP mServoMotorHardware;
	
	
	public ADXRS450_Gyro mGyro;	
	
	HardwareMap() {
		
		try
		{
			mClimberHardware = new VictorSP(Constants.kClimberId);
			mLeftShifterHardware = new Solenoid(Constants.kLeftShifterSolenoidId);
	    	mRightShifterHardware = new Solenoid(Constants.kRightShifterSolenoidId);
	    	mIntakeLeftHardware = new VictorSP(Constants.kIntakeLeftId);
	    	mIntakeRightHardware = new VictorSP(Constants.kIntakeRightId);
			mCompressorHardware = new Compressor(0);
			mGyro = new ADXRS450_Gyro();
			mGyro.calibrate();
			mLaserHardware = new AnalogInput(0);
			mClimberReleaseHardware = new Solenoid(Constants.kClimberReleaseSolenoidId);
			mServoMotorHardware = new VictorSP(Constants.kServoMotorId);

		}
		catch(Exception e)
		{
			
		}
	}

}
