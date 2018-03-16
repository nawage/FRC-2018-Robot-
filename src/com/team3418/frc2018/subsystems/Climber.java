package com.team3418.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team3418.frc2018.Constants;
import com.team3418.frc2018.HardwareMap;
import com.team3418.frc2018.subsystems.Intake.IntakeArmState;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {

	static Climber mInstance = new Climber();

    public static Climber getInstance() {
        return mInstance;
    }
    
    private VictorSPX mClimberVictor;
    private Solenoid mClimberReleaseSolenoid;
        
    public Climber() {
    	mClimberVictor = new VictorSPX(Constants.kClimberId);
    	mClimberReleaseSolenoid = HardwareMap.getInstance().mClimberReleaseHardware;
    	mClimberVictor.set(ControlMode.PercentOutput,0);
    	mClimberVictor.setSensorPhase(false);
    	mClimberVictor.setInverted(false);
    }
    
  	public enum ClimberState {
      	FORWARD, REVERSE, STOP, HOLD
    }
  	
  	public enum ClimberReleaseState {
      	RELEASED, UNRELEASED
    }
  	
  	private ClimberState mClimberState;
  	private ClimberReleaseState mClimberReleaseState;
  	
  	public ClimberState getClimberState() {
  		return mClimberState;
  	}
  	
  	public ClimberReleaseState getClimberReleaseState() {
  		return mClimberReleaseState;
  	}
    
	@Override
	public void updateSubsystem() {
		
		switch(mClimberState){
		case FORWARD:
			setSpeed(1);
			break;
		case REVERSE:
			setSpeed(Constants.kClimberReverseSpeed);
			break;
		case HOLD:
			setSpeed(Constants.kClimberHoldSpeed);
			break;
		case STOP:
			setSpeed(0);
			break;
		default:
			
			break;
		}
		
		switch(mClimberReleaseState) {
		case RELEASED:
			release(false);
			break;
		case UNRELEASED:
			release(true);
			break;
		default:
			mClimberReleaseState = ClimberReleaseState.UNRELEASED;
			break;
		}
		
		outputToSmartDashboard();
		
	}
	
	public void forward(){
		mClimberState = ClimberState.FORWARD;
	}
	
	public void reverse() {
		mClimberState = ClimberState.REVERSE;
	}
	
	public void Release(){
		mClimberReleaseState = ClimberReleaseState.RELEASED;
	}
	
	@Override
	public void stop(){
		mClimberState = ClimberState.STOP;
		mClimberReleaseState = ClimberReleaseState.UNRELEASED;
	}
	
	public void hold(){
		mClimberState = ClimberState.HOLD;
	}
	
	public void setSpeed(double speed) {
		mClimberVictor.set(ControlMode.PercentOutput, speed);
	}
	
	public void release(boolean climbRelease) {
		mClimberReleaseSolenoid.set(climbRelease);
	}
	
	@Override
	public void outputToSmartDashboard() {
		//SmartDashboard.putNumber("Climber_Speed", mClimberVictor.getSpeed());
		SmartDashboard.putString("Climber_Winch_State", mClimberState.toString());
		SmartDashboard.putString("Climber_Release_State", mClimberReleaseState.toString());
	}

}
