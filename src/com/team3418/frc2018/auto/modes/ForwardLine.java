package com.team3418.frc2018.auto.modes;

import com.team3418.frc2018.auto.AutoModeBase;
import com.team3418.frc2018.auto.AutoModeEndedException;
import com.team3418.frc2018.auto.actions.DriveStraightActionDistance;
import com.team3418.frc2018.auto.actions.DriveStraightActionTime;
import com.team3418.frc2018.auto.actions.TurnActionPID;
import com.team3418.frc2018.auto.actions.WaitAction;


public class ForwardLine extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {
		runAction(new DriveStraightActionDistance(-93));
	}
}
