package com.team3418.frc2018.auto.modes;


import com.team3418.frc2018.auto.AutoModeBase;
import com.team3418.frc2018.auto.AutoModeEndedException;
import com.team3418.frc2018.auto.actions.DriveStraightActionDistance;
import com.team3418.frc2018.auto.actions.DriveStraightActionPID;
import com.team3418.frc2018.auto.actions.DriveStraightActionTime;
import com.team3418.frc2018.auto.actions.ShootAction;
import com.team3418.frc2018.auto.actions.SpoolOffAction;
import com.team3418.frc2018.auto.actions.SpoolOnAction;
import com.team3418.frc2018.auto.actions.TurnActionAngle;
import com.team3418.frc2018.auto.actions.TurnActionPID;
import com.team3418.frc2018.auto.actions.WaitAction;


import edu.wpi.first.wpilibj.DriverStation;

public class MiddleAllign extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {
		//FMS CODE
        String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.length() > 0) {
			if(gameData.charAt(0) == 'L') {
			//Put left auto code here
				
				runAction(new DriveStraightActionDistance(-40));
				/*runAction(new WaitAction(1));
				runAction(new TurnActionAngle(-90,.65,.35,1.5));
				runAction(new WaitAction(1));
				runAction(new DriveStraightActionDistance(-7));
				runAction(new WaitAction(1));
				runAction(new SpoolOnAction());
				runAction(new WaitAction(1));
				runAction(new ShootAction());
				runAction(new SpoolOffAction());*/
				
				
				
				
				
				//runAction(new DriveStraightActionTime(2, false, .6));
				
				//runAction(new DriveStraightActionTime(1, true));
			
				
			}
			else {
			//Put right auto code here
				runAction(new DriveStraightActionDistance(-100));
				runAction(new TurnActionAngle(50,.65,.35,1.5));
				runAction(new DriveStraightActionTime(2, false, .6));
				runAction(new WaitAction(.5));
				runAction(new DriveStraightActionTime(1, true));
		  	}
        }
		
	}
}
