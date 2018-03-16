package com.team3418.frc2018.auto.modes;

import com.team3418.frc2018.auto.AutoModeBase;
import com.team3418.frc2018.auto.AutoModeEndedException;
import com.team3418.frc2018.auto.actions.ShootAction;
import com.team3418.frc2018.auto.actions.SpoolOffAction;
import com.team3418.frc2018.auto.actions.SpoolOnAction;
import com.team3418.frc2018.auto.actions.WaitAction;

public class ShootMode extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {
		runAction(new SpoolOnAction());
		runAction(new WaitAction(10));
		runAction(new SpoolOffAction());
	}

}
