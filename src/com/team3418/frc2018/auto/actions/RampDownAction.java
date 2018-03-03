package com.team3418.frc2018.auto.actions;

import com.team3418.frc2018.subsystems.Ramp;

public class RampDownAction implements Action{

	Ramp mRamp;
	
	public RampDownAction() {
		mRamp = Ramp.getInstance();
	}
	
	@Override
	public void start() {
		mRamp.low();
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
