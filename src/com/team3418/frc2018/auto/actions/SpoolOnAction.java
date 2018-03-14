package com.team3418.frc2018.auto.actions;

import com.team3418.frc2018.subsystems.MrCush;
import com.team3418.frc2018.subsystems.Shooter;

public class SpoolOnAction implements Action{

	private Shooter mShooter;
	
	private boolean finished = false;
	
	public SpoolOnAction() {
		mShooter = Shooter.getInstance();
	}
	
	@Override
	public void start() {
		mShooter.shoot();
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
