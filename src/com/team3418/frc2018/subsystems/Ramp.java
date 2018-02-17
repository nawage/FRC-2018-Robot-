package com.team3418.frc2018.subsystems;

import com.team3418.frc2018.Constants;
import com.team3418.frc2018.HardwareMap;
import com.team3418.frc2018.subsystems.Climber.ClimberReleaseState;

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
			setRampHigh(true);
			break;
		case LOW:
			setRampHigh(false);
			break;
		default:
			mRampState = RampState.LOW;
			break;
		}
		
		outputToSmartDashboard();
	}
	
	public void setRampHigh(boolean arms) {
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
		mRampState = RampState.LOW;
	}
}