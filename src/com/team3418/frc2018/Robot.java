package com.team3418.frc2018;

import com.team3418.frc2018.auto.AutoExecuter;
import com.team3418.frc2018.plugins.MinionVision;
import com.team3418.frc2018.subsystems.Climber;
import com.team3418.frc2018.subsystems.Drivetrain;
import com.team3418.frc2018.subsystems.Intake;
import com.team3418.frc2018.subsystems.Laser;
import com.team3418.frc2018.subsystems.MrCush;
import com.team3418.frc2018.subsystems.Ramp;
import com.team3418.frc2018.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	//Initialize main parts of the robot
	HardwareMap mHardwareMap;
	ControlBoard mControlBoard;
	SmartDashboardInteractions mSmartDashboardInteractions;
	MinionVision mMinionVision;
	
	//initialize subsystems
	Climber mClimber;
	Drivetrain mDrivetrain;
	Intake mIntake;
	Shooter mShooter;
	MrCush mMrCush;
	Laser mLaser;
	Ramp mRamp;
	
	AutoExecuter mAutoExecuter = null;
	
	public void updateAllSubsystems(){
		mClimber.updateSubsystem();
		mDrivetrain.updateSubsystem();
		mIntake.updateSubsystem();
		mShooter.updateSubsystem();
		mMrCush.updateSubsystem();
	    mLaser.updateSubsystem();
	    mRamp.updateSubsystem();
	}
	
	public void stopAllSubsystems(){
		
		mClimber.stop();
		mDrivetrain.stop();
		mDrivetrain.lowGear();
		mIntake.stop();
		mShooter.stop();
		mMrCush.stop();
		mLaser.stop();
		mRamp.stop();
	}
	
	@Override
	public void robotInit() {
		
		mHardwareMap = HardwareMap.getInstance();
		mControlBoard = ControlBoard.getInstance();
		mSmartDashboardInteractions = new SmartDashboardInteractions();
		mMinionVision = MinionVision.getInstance();
		mMinionVision.startVision();
		
		mClimber = Climber.getInstance();
		mDrivetrain = Drivetrain.getInstance();
		mIntake = Intake.getInstance();
		mShooter = Shooter.getInstance();
		mMrCush = MrCush.getInstance();
		mLaser = Laser.getInstance();
		mRamp = Ramp.getInstance();
		
		mSmartDashboardInteractions.initWithDefaults();
		
		stopAllSubsystems();
	}
	
	@Override
	public void autonomousInit() {
		if (mAutoExecuter != null) {
            mAutoExecuter.stop();
        }
        mAutoExecuter = null;
//        
        mAutoExecuter = new AutoExecuter();
        mAutoExecuter.setAutoRoutine(mSmartDashboardInteractions.getSelectedAutonMode());
        mAutoExecuter.start();
		
        /*FMS CODE
        String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.length() > 0) {
			if(gameData.charAt(0) == 'L') {
			//Put left auto code here
				
			}
			else {
			//Put right auto code here
				
		  	}
        }
		*/
                
		stopAllSubsystems();
		updateAllSubsystems();
		
	}
	
	@Override
	public void autonomousPeriodic() {
		
		updateAllSubsystems();
	}

	@Override
	public void disabledInit(){
		
		if (mAutoExecuter != null) {
            mAutoExecuter.stop();
        }
        mAutoExecuter = null;
        
		//mMinionVision.startVision();
        
        //mMinionVision.stopVision();
        mDrivetrain.resetEncoders();
        mDrivetrain.highGear();
        
		
		stopAllSubsystems();
		updateAllSubsystems();
	}
	
	@Override
	public void disabledPeriodic() {
		
	}
	
	@Override
	public void teleopInit(){
		
		if (mAutoExecuter != null) {
            mAutoExecuter.stop();
        }
        mAutoExecuter = null;
        
//        mMinionVision.stopVision();
        
		stopAllSubsystems();
		//mHardwareMap.mGyro.reset();
		mDrivetrain.lowGear();
		updateAllSubsystems();
	}
	
	@Override
	public void teleopPeriodic() {
		//Intakes
		if(mControlBoard.getSecondaryIntakeButton()) {
			mIntake.intake();
		} else if(mControlBoard.getSecondaryOutakeButton()){ 
			mIntake.reverse();
		}
		else {
			mIntake.stop();
		}
		
		
		
		
		
		
		//---------------------------------------------------------------//
		
		
		if (mControlBoard.getClimberForwardButton()) {
			mClimber.forward();
		} else if (mControlBoard.getClimberHoldButton()) {
			mClimber.hold();
		} else if (mControlBoard.getClimberReverseButton()) {
			mClimber.reverse();
		} else {
			mClimber.stop();
		}
		
		

		//-----------------------------------------------------------------
		
		//shooter
		if (mControlBoard.getSecondaryShootButton()) {
			mMrCush.Retract();
		} else {
			mMrCush.Extend();
		}
		//Spool
		if (mControlBoard.getSecondarySpoolButton()) {
			mShooter.shoot();
		} else {
			mShooter.stop();
		}
		//---------------------------------------------------------------

		//  Drive train 
		if(mControlBoard.getDriverHighGearButton()) {
			mDrivetrain.highGear();
		}
		if(mControlBoard.getDriverLowGearButton()) {
			mDrivetrain.lowGear();
		}
		
		mDrivetrain.setTankDriveSpeed(mControlBoard.getDriverLeftY(), mControlBoard.getDriverRightY());
	/*	
		if (mControlBoard.getDriverPov() > -1){
			switch(mControlBoard.getDriverPov()){
			//--------------------------------------------------
			case 0: //forwards
				mDrivetrain.setArcadeDriveSpeed(.5, 0);
				break;
			case 180:// back
				mDrivetrain.setArcadeDriveSpeed(-.5, 0);
				break;
			//--------------------------------------------------
			case 45: // forwards / right
				mDrivetrain.setArcadeDriveSpeed(.5, .25);
				break;
			case 315: // forwards / left
				mDrivetrain.setArcadeDriveSpeed(.5, -.25);
				break;
			//--------------------------------------------------
			case 90:// right
				mDrivetrain.setArcadeDriveSpeed(0, .5);
				break;
			case 270: //left
				mDrivetrain.setArcadeDriveSpeed(0, -.5);
				break;
			//--------------------------------------------------
			case 135:// back / right
				mDrivetrain.setArcadeDriveSpeed(-.5, -.25);
				break;
			case 225:// back / left
				mDrivetrain.setArcadeDriveSpeed(-.5, .25);
				break;
			//--------------------------------------------------
			}
		}*/
		//---------------------------------------------------
		
		//Laser
		mLaser.updateSubsystem();
		
		//ramp
		if(mControlBoard.getSecondaryHighAimButton()){
			mRamp.setRampHigh(true) ;
		}
		if(mControlBoard.getSecondaryHighAimButton()){
			mRamp.setRampHigh(false) ;
		}
		
		//arms
		if(mControlBoard.getSecondaryArmOpenButton()){
			mIntake.setArmsOpen(true); 
		}
		if(mControlBoard.getSecondaryArmCloseButton()){
			mIntake.setArmsOpen(false); 
		}
		//camera
		if(mControlBoard.getSecondaryCam1Button()){
			 
		}
		if(mControlBoard.getSecondaryCam2Button()){
			 
		}
		
		
		
		
		updateAllSubsystems();
		
	}
	
	@Override
	public void testInit( ){
		stopAllSubsystems();
		updateAllSubsystems();
	}
	
	@Override
	public void testPeriodic() {
		
	}
}

