package com.team3418.frc2018.subsystems;

import com.team3418.frc2018.Constants;
import com.team3418.frc2018.HardwareMap;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem
{
	static Intake mInstance = new Intake();

    public static Intake getInstance() {
    	System.out.println("Before MINStance");
    	return mInstance;
    	
    }
    
	private VictorSP mIntakeLeftTalon;
	private VictorSP mIntakeRightTalon;
	private Solenoid mIntakeArmLeftSolenoid;
	private Solenoid mIntakeArmRightSolenoid;
    
	public Intake() {
		mIntakeLeftTalon=HardwareMap.getInstance().mIntakeLeftHardware;
		
	
		//mIntakeLeftTalon.reverseOutput(false);
		mIntakeRightTalon = HardwareMap.getInstance().mIntakeRightHardware;
		//mIntakeRightTalon.reverseOutput(true);
		
		mIntakeArmLeftSolenoid = new Solenoid(Constants.kIntakeLeftSolenoidId);
		
		mIntakeArmRightSolenoid = new Solenoid(Constants.kIntakeRightSolenoidId);
		
		System.out.println("Intake Done Initializing.");
	}
    
    public enum IntakeRollerState {
    	INTAKE,
    	REVERSE,
    	STOP
    }
    
    public enum IntakeArmState {
    	OPEN,
    	CLOSED
    }
	
	private IntakeRollerState mIntakeRollerState;
	private IntakeArmState mIntakeArmState;

	public IntakeRollerState getIntakeRollerState() {
		return mIntakeRollerState;
	}
	
	public IntakeArmState getIntakeArmState() {
		return mIntakeArmState;
	}
	
	@Override
	public void updateSubsystem()
	{
		switch(mIntakeRollerState) {
		case INTAKE:
			setRollerSpeed(Constants.kRollerIntakeSpeed);
			break;
		case REVERSE:
			setRollerSpeed(Constants.kRollerReverseSpeed);
			break;
		case STOP:
			setRollerSpeed(0);
			break;
		default:
			mIntakeRollerState = IntakeRollerState.STOP;
			break;
		}
		
		switch(mIntakeArmState) {
		case OPEN:
			setArmsOpen(true);
			System.out.println("Opening Intake Arms");
			break;
		case CLOSED:
			setArmsOpen(false);
			System.out.println("Closing Intake Arms");
			break;
		default:
			mIntakeArmState = IntakeArmState.CLOSED;
			System.out.println("Default");
			break;
		}
		
		outputToSmartDashboard();
	}	
	
	
	
	public void intake(){
		mIntakeRollerState = IntakeRollerState.INTAKE;
	}
	
	public void reverse(){
		mIntakeRollerState = IntakeRollerState.REVERSE;
	}
	
	public void close(){
		mIntakeArmState = IntakeArmState.CLOSED;
	}
	
	public void open(){
		mIntakeArmState = IntakeArmState.OPEN;
	}
	
	@Override
	public void stop(){
		mIntakeRollerState = IntakeRollerState.STOP;
		mIntakeArmState = IntakeArmState.CLOSED;
		System.out.println("Stopping Intake");
	}
	public void stopIntakeMotor()
	{
		mIntakeRollerState = IntakeRollerState.STOP;
	}

	public void setRollerSpeed(double speed) {
		mIntakeLeftTalon.set(speed);
		mIntakeRightTalon.set(-1*speed);
	}
	
	public void setArmsOpen(boolean arms) {
		mIntakeArmLeftSolenoid.set(arms);
		mIntakeArmRightSolenoid.set(arms);
	}

	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("Left_Intake_Speed", mIntakeLeftTalon.getSpeed());
		SmartDashboard.putString("Intake_Roller_State", mIntakeRollerState.toString());
		SmartDashboard.putString("Intake_Arm_State", mIntakeArmState.toString());
		//SmartDashboard.putString("Sensor_Value", toString());
	}
}