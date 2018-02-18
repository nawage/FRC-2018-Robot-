package com.team3418.frc2018.subsystems;

import com.team3418.frc2018.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MrCush extends Subsystem {
	
	static MrCush mInstance = new MrCush();

    public static MrCush getInstance() {
        return mInstance;
    }
    
	public MrCush() {
		MrCushySolenoid = new Solenoid(Constants.kMrCushySolenoid);
		System.out.println("Mr Cushy has been initialized :D");
	}

	private Solenoid MrCushySolenoid;
	
	public enum MrCushState {
      	EXTENDED, RETRACTED
    }
  	
  	private MrCushState mMrCushState;
  	
  	public MrCushState getClimberState() {
  		return mMrCushState;
  	}
	

	
	@Override
	public void updateSubsystem() {
		
		switch(mMrCushState){
		case EXTENDED:
			setExtend();
			break;
		case RETRACTED:
			setRetract();
			break;
		default:
			mMrCushState = MrCushState.EXTENDED;
			break;
		}
		
		outputToSmartDashboard();
	}
	
	public void Extend() {
		mMrCushState = MrCushState.EXTENDED;
	}
	
	public void Retract() {
		mMrCushState = MrCushState.RETRACTED;
	}
	
	private void setExtend() {
		MrCushySolenoid.set(true);
	}
	
	private void setRetract() {
		MrCushySolenoid.set(false);
	}

	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putString("MrCushyState", mMrCushState.toString());
	}

	@Override
	public void stop() {
		Extend();
	}
}
