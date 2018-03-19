package com.team3418.frc2018.auto.actions;

import com.team3418.frc2018.subsystems.MrCush;
import com.team3418.frc2018.subsystems.Ramp;
import com.team3418.frc2018.subsystems.Shooter;

public class SpoolOnAction implements Action{

	private Shooter mShooter;
	private Ramp mRamp;
	
	private boolean finished = false;
	
	public SpoolOnAction() {
		mShooter = Shooter.getInstance();
		mRamp = Ramp.getInstance();
	}
	
	@Override
	public void start() {
		if (mRamp.getRampStateInt() == 0) {
			mShooter.shoot();
		}
		else if (mRamp.getRampStateInt() == 1) {
			mShooter.slowshoot();
		}
		finished = true;
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean isFinished() {
		if (finished) {
			System.out.println("Spool On Action Completed");
			return true;
		}
		return false;
	}

	@Override
	public void done() {
		
	}

}
