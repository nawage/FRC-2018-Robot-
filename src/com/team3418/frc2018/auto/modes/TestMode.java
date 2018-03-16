package com.team3418.frc2018.auto.modes;

import com.team3418.frc2018.auto.AutoModeBase;
import com.team3418.frc2018.auto.AutoModeEndedException;
import com.team3418.frc2018.auto.actions.DriveStraightActionDistance;
import com.team3418.frc2018.auto.actions.DriveStraightActionTime;
import com.team3418.frc2018.auto.actions.TurnActionPID;
import com.team3418.frc2018.auto.actions.WaitAction;


public class TestMode extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {
		/*
		runAction(new CameraAlign());
		runAction(new DriveStraightActionTime(2, false));
		runAction(new ExtendGearAction());
		runAction(new WaitAction(1));
		runAction(new DriveStraightActionTime(2, true));
		runAction(new RetractGearAction());
		*/
		
		//Forward Mode - temp
		runAction(new DriveStraightActionDistance(-93));
		
	}
}
