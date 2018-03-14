package com.team3418.frc2018.auto.actions;

import com.team3418.frc2018.subsystems.MrCush;
import com.team3418.frc2018.subsystems.Shooter;

public class SpoolShootAction implements Action{

	private MrCush mMrCush;
	private Shooter mShooter;
	
	private boolean finished = false;
	
	public SpoolShootAction() {
		mMrCush = MrCush.getInstance();
		mShooter = Shooter.getInstance();
	}
	
	@Override
	public void start() {
		mShooter.shoot();
	}

	@Override
	public void update() {
		if mShooter.isShooterReady() {
			mMrCush.Extend();
		}
	}

	@Override
	public boolean isFinished() {
		if (finished) {
			System.out.println("Spool/Shoot Action Completed");
			return true;
		}
		return false;
	}

	@Override
	public void done() {
		
	}

}
