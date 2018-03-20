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

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
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
	    mRamp.updateSubsystem();
	}
	
	public void stopAllSubsystems(){
		
		mClimber.stop();
		mDrivetrain.stop();
		mDrivetrain.lowGear();
		mIntake.stop();
		mShooter.stop();
		mMrCush.stop();
		mRamp.stop();
	}
	
	@Override
	public void robotInit() {
		
		mHardwareMap = HardwareMap.getInstance();
		mControlBoard = ControlBoard.getInstance();
		mSmartDashboardInteractions = SmartDashboardInteractions.getInstance();
//		mMinionVision = MinionVision.getInstance();
//		mMinionVision.startVision();
		
		mClimber = Climber.getInstance();
		mDrivetrain = Drivetrain.getInstance();
		mIntake = Intake.getInstance();
		mShooter = Shooter.getInstance();
		mMrCush = MrCush.getInstance();
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
        mAutoExecuter.setAutoRoutine(mSmartDashboardInteractions.getSelectedAutonMode(0));
        mAutoExecuter.start();
                
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
        System.out.println("resetting encoders");
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
        //test
//        mMinionVision.stopVision();
        
		stopAllSubsystems();
		mDrivetrain.lowGear();
		updateAllSubsystems();
	}
	
	@Override
	public void teleopPeriodic() {
		
		//Intake Left
		if(mControlBoard.getSecondaryLeftIntakeButton()) {
			mIntake.intake();
		} else if(mControlBoard.getSecondaryLeftOutakeButton()){ 
			mIntake.reverse();
		}
		else {
			mIntake.stopIntakeMotor();
		}
		
		//Intake Right
		if(mControlBoard.getSecondaryRightIntakeButton()) {
			mIntake.intake();
		} else if(mControlBoard.getSecondaryRightOutakeButton()){ 
			mIntake.reverse();
		}
		else {
			mIntake.stopIntakeMotor();
		}
		
		//Climber
		if ((mControlBoard.getClimberForwardButton()) && (mClimber.getClimberReleaseStateInt() == 0)) {
			mClimber.forward();
		} else if (mControlBoard.getClimberHoldButton() && (mClimber.getClimberReleaseStateInt() == 0)) {
			mClimber.hold();
		} else if (mControlBoard.getClimberReverseButton() && (mClimber.getClimberReleaseStateInt() == 0)) {
			mClimber.reverse();
		} else {
			mClimber.stop();
		}
		
		//Shooter Popper
		if (mControlBoard.getSecondaryShootButton() && mControlBoard.getSecondarySpoolButton()) {
			if (mShooter.isShooterReady()) {
				mMrCush.Retract();
			}
			else {
				mMrCush.Extend();
			}
		}
		else {
			mMrCush.Extend();
		}
		
		//Shooter Spool
		if (mControlBoard.getSecondarySpoolButton()) {
			if (mRamp.getRampStateInt() == 0) {
				mShooter.shoot();
			}
			else if (mRamp.getRampStateInt() == 1) {
				mShooter.slowshoot();
			}
			
			//controller shooter/spool rumble
			if (mControlBoard.getSecondarySpoolButton()) {
				if (mShooter.isShooterReady()) {
					mControlBoard.mSecondaryDriverStick.setRumble(RumbleType.kRightRumble, 1);
					mControlBoard.mSecondaryDriverStick.setRumble(RumbleType.kLeftRumble, 1);
				}
				else {
					mControlBoard.mSecondaryDriverStick.setRumble(RumbleType.kRightRumble, 0.2);
					mControlBoard.mSecondaryDriverStick.setRumble(RumbleType.kLeftRumble, 0.2);
				}
			}
		}
		else if (mControlBoard.getSecondarySlowSpoolButton()) {
			mShooter.slowshoot();
		}
		else if (mControlBoard.getSecondaryReverseSpoolButton()) {
			mShooter.reverse();
		}
		else {
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
		
		//Ramp Aim
		if(mControlBoard.getSecondaryHighAimButton()){
			mRamp.high() ;
		}
		if(mControlBoard.getSecondaryLowAimButton()){
			mRamp.low() ;
		}
		
		//Intake Left Arms
		if(mControlBoard.getSecondaryLeftArmOpenButton()){
			mIntake.open();
		}
		if(mControlBoard.getSecondaryLeftArmCloseButton()){
			mIntake.close();
		}
		
		//Intake Right Arms
		if(mControlBoard.getSecondaryRightArmOpenButton()){
			mIntake.open();
		}
		if(mControlBoard.getSecondaryRightArmCloseButton()){
			mIntake.close();
		}
		
		//Climber Release (with double safety switch)
		if(mControlBoard.getSecondaryClimberRelease() && mControlBoard.getSwitchboardClimberRelease()){
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

