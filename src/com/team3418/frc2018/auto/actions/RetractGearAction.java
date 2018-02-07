package com.team3418.frc2018.auto.actions;

import com.team3418.frc2018.subsystems.MrGush;

public class RetractGearAction implements Action{

	MrGush mMrGush;
	
	
	public RetractGearAction() {
		mMrGush = MrGush.getInstance();
	}
	
	@Override
	public void start() {
		mMrGush.Retract();
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public void done() {
		
	}

}
