package com.team3418.frc2018.auto.actions;

import com.team3418.frc2018.subsystems.MrCush;
import com.team3418.frc2018.subsystems.Shooter;

public class ShootAction implements Action{

	private MrCush mMrCush;
	
	private boolean finished = false;
	
	public ShootAction() {
		mMrCush = MrCush.getInstance();
	}
	
	@Override
	public void start() {
		mMrCush.Retract();
		finished = true;
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean isFinished() {
		if (finished) {
			System.out.println("MrCushy Shoot Action Completed");
			return true;
		}
		return false;
	}

	@Override
	public void done() {
		
	}

}
