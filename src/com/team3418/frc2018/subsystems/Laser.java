package com.team3418.frc2018.subsystems;
import  com.team3418.frc2018.HardwareMap;
//import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Laser extends Subsystem  
{
	public double LaserDistance;
	public double LaserVolts;
	
	static Laser mInstance = new Laser();
	
	public static Laser getInstance() {
		return mInstance;
	}
	
	private VictorSP mServoMotor;
	//private AnalogInput mLaserAnalog;
	
	public ServoState getServoState() {
  		return mServoState;
  	}
	
	public Laser() {
		//mLaserAnalog = HardwareMap.getInstance().mLaserHardware;
		//System.out.println("Laser Initialized");
		
		mServoMotor = HardwareMap.getInstance().mServoMotorHardware;
		System.out.println("Laser Servo Motor Initialized");
		
		LaserDistance = 0;
		LaserVolts = 0;
	}
	
	public enum ServoState {
      	MANUAL, SCAN, OFF
    }
	
	private ServoState mServoState;
	   
	public void updateSubsystem() {

		//LaserVolts = mLaserAnalog.getVoltage() ;
		//LaserDistance = (LaserVolts+39) / 0.1696 + 10;
		// Math! ((volts + 39) / 0.1696 + 10);
		
		switch(mServoState) {
		case MANUAL:
			setSpeed(1);
			break;
		case SCAN:
			setSpeed(1);
			break;
		case OFF:
			setSpeed(0);
			break;
		default:
			mServoState = ServoState.MANUAL;
			break;
		}
		
		outputToSmartDashboard();
	}
	
	public void stop(){
		mServoState = ServoState.OFF;
	}
	
	public void setSpeed(double speed) {
		mServoMotor.set(speed);
	}
	
	public void outputToSmartDashboard()
	
	{
		SmartDashboard.putNumber("Laser Distance", LaserDistance);
		SmartDashboard.putString("Servo State", mServoState.toString());
		
	}
}
