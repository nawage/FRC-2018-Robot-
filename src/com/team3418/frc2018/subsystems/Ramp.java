package com.team3418.frc2018.subsystems;

import com.team3418.frc2018.Constants;
import com.team3418.frc2018.HardwareMap;
import com.team3418.frc2018.subsystems.Climber.ClimberReleaseState;
import com.team3418.frc2018.subsystems.Drivetrain.DriveGear;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ramp extends Subsystem
{
	static Ramp mInstance = new Ramp();

    public static Ramp getInstance() {
        return mInstance;
    }
    
	private Solenoid mRampLeftSolenoid;
	private Solenoid mRampRightSolenoid;
    
	public Ramp() {
		mRampLeftSolenoid = new Solenoid(Constants.kRampLeftSolenoidId);
		mRampRightSolenoid = new Solenoid(Constants.kRampRightSolenoidId);
		
		System.out.println("Ramp Done Initializing.");
	}
    
    public enum RampState {
    	HIGH,
    	LOW
    }
	
	private RampState mRampState;
	
	public RampState getRampState() {
		return mRampState;
	}
	
	@Override
	public void updateSubsystem()
	{
		switch(mRampState) {
		case HIGH:
			setRampLow(false);
			break;
		case LOW:
			setRampLow(true);
			break;
		default:
			mRampState = RampState.HIGH;
			break;
		}
		
		outputToSmartDashboard();
	}
	
	public void high(){
		mRampState = RampState.HIGH;
	}
	
	public void low(){
		mRampState = RampState.LOW;
	}
	
	public void setRampLow(boolean arms) {
		mRampLeftSolenoid.set(arms);
		mRampRightSolenoid.set(arms);
	}

	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putString("Ramp_Position_State", mRampState.toString());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		mRampState = RampState.HIGH;
	}
}