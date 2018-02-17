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
		//MrCushySolenoid = new Solenoid(Constants.kMrCushySolenoid);
		System.out.println("Mr Cushy has been initialized :D");
	}

	private Solenoid MrCushySolenoid;
	
	public enum MrCushState {
      	EXTENDED, RETRACTED
    }
  	
  	private MrCushState MrCushState;
  	
  	public MrCushState getClimberState() {
  		return MrCushState;
  	}
	

	
	@Override
	public void updateSubsystem() {
		
		switch(MrCushState){
		case EXTENDED:
			setExtend();
			break;
		case RETRACTED:
			setRetract();
			break;
		default:
			MrCushState = MrCushState.EXTENDED;
			break;
		}
		
		outputToSmartDashboard();
	}
	
	public void Extend() {
		MrCushState = MrCushState.EXTENDED;
	}
	
	public void Retract() {
		MrCushState = MrCushState.RETRACTED;
	}
	
	private void setExtend() {
		MrCushySolenoid.set(true);
	}
	
	private void setRetract() {
		MrCushySolenoid.set(false);
	}

	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putString("MrCushyState", MrCushState.toString());
	}

	@Override
	public void stop() {
		Retract();
	}
}
