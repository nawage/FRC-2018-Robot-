package com.team3418.frc2018.auto.modes;

import com.team3418.frc2018.auto.AutoModeBase;
import com.team3418.frc2018.auto.AutoModeEndedException;
import com.team3418.frc2018.auto.actions.DriveStraightActionDistance;
import com.team3418.frc2018.auto.actions.DriveStraightActionTime;
import com.team3418.frc2018.auto.actions.TurnActionAngle;
import com.team3418.frc2018.auto.actions.WaitAction;

import edu.wpi.first.wpilibj.DriverStation;

public class RightAllign extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {
		//FMS CODE
        String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.length() > 0) {
			//LEFT SWTICH ---
			if(gameData.charAt(0) == 'L') {
			//Put left auto code here
				//LEFT SWITCH - LEFT SCALE
				if(gameData.charAt(1) == 'L') {
					
				}
				//LEFT SWITCH - RIGHT SCALE
				else {
					
					
				}
			}
			//RIGHT SWITCH ---
			else {
			//Put right auto code here
				//RIGHT SWITCH - LEFT SCALE
				if(gameData.charAt(1) == 'L') {
					
				}
				//RIGHT SWITCH - RIGHT SCALE
				else {
					
				}
		  	}
        }
	}
}
