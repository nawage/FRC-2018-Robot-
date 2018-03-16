package com.team3418.frc2018.auto.actions;

import com.team3418.frc2018.subsystems.Ramp;

public class RampUpAction implements Action{

	Ramp mRamp;
	
	public RampUpAction() {
		mRamp = Ramp.getInstance();
	}
	
	@Override
	public void start() {
		mRamp.high();
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
