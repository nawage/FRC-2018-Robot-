package com.team3418.frc2018;

import com.team3418.frc2018.auto.AutoExecuter;
import com.team3418.frc2018.plugins.MinionVision;
import com.team3418.frc2018.subsystems.Climber;
import com.team3418.frc2018.subsystems.Drivetrain;
import com.team3418.frc2018.subsystems.Intake;
//import com.team3418.frc2018.subsystems.Laser;
import com.team3418.frc2018.subsystems.MrCush;
import com.team3418.frc2018.subsystems.Ramp;
import com.team3418.frc2018.subsystems.Shooter;
import com.team3418.frc2018.subsystems.Intake.IntakeArmState;

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
	//Laser mLaser;
	Ramp mRamp;
	
	AutoExecuter mAutoExecuter = null;
	
	public void updateAllSubsystems(){
		mClimber.updateSubsystem();
		mDrivetrain.updateSubsystem();
		mIntake.updateSubsystem();
		mShooter.updateSubsystem();
		mMrCush.updateSubsystem();
	    //mLaser.updateSubsystem();
	    mRamp.updateSubsystem();
	}
	
	public void stopAllSubsystems(){
		
		mClimber.stop();
		mDrivetrain.stop();
		mDrivetrain.lowGear();
		mIntake.stop();
		mShooter.stop();
		mMrCush.stop();
		//mLaser.stop();
		mRamp.stop();
	}
	
	@Override
	public void robotInit() {
		
		mHardwareMap = HardwareMap.getInstance();
		mControlBoard = ControlBoard.getInstance();
		mSmartDashboardInteractions = new SmartDashboardInteractions();
//		mMinionVision = MinionVision.getInstance();
//		mMinionVision.startVision();
		
		mClimber = Climber.getInstance();
		mDrivetrain = Drivetrain.getInstance();
		mIntake = Intake.getInstance();
		mShooter = Shooter.getInstance();
		mMrCush = MrCush.getInstance();
		//mLaser = Laser.getInstance();
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
			mIntake.stopIntakeMotor();
		}
		
		//Climber
		if (mControlBoard.getClimberForwardButton()) {
			mClimber.forward();
		} else if (mControlBoard.getClimberHoldButton()) {
			mClimber.hold();
		} else if (mControlBoard.getClimberReverseButton()) {
			mClimber.reverse();
		} else {
			mClimber.stop();
		}
		
		//Shooter Popper
		if (mControlBoard.getSecondaryShootButton()) {
			mMrCush.Retract();
		} else {
			mMrCush.Extend();
		}
		// Skrrt Skrrt
		//Shooter Spool
		if (mControlBoard.getSecondarySpoolButton()) {
			mShooter.shoot();
		} else {
			mShooter.stop();
		}

		//Drive train 
		if(mControlBoard.getDriverHighGearButton()) {
			mDrivetrain.highGear();
		}
		if(mControlBoard.getDriverLowGearButton()) {
			mDrivetrain.lowGear();
		}
		mDrivetrain.setTankDriveSpeed(mControlBoard.getDriverLeftY(), mControlBoard.getDriverRightY());
		
		//Laser
		//mLaser.updateSubsystem();
		
		//Ramp Aim
		if(mControlBoard.getSecondaryHighAimButton()){
			mRamp.high() ;
		}
		if(mControlBoard.getSecondaryLowAimButton()){
			mRamp.low() ;
		}
		
		//Intake Arms
		if(mControlBoard.getSecondaryArmOpenButton()){
			mIntake.open();
		}
		if(mControlBoard.getSecondaryArmCloseButton()){
			mIntake.close();
		}
		
		
		//Cameras
		if(mControlBoard.getSecondaryCam1Button()){
			 
		}
		if(mControlBoard.getSecondaryCam2Button()){
			 
		}
		
		//Climber Release (with double safety switch)
		if(mControlBoard.getSecondaryArmCloseButton() && mControlBoard.getSwitchboardClimberRelease()){
			mClimber.Release();
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

