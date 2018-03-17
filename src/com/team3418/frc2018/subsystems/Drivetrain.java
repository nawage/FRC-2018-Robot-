package com.team3418.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team3418.frc2018.Constants;
import com.team3418.frc2018.HardwareMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {
	
	static Drivetrain mInstance = new Drivetrain();

    public static Drivetrain getInstance() {
        return mInstance;
    }
    
    
   
    Solenoid mLeftSolenoid;
    Solenoid mRightSolenoid;
    AnalogInput mLaserInput;
    
    
    public Encoder mLeftEncoder;
    public Encoder mRightEncoder;
    
    VictorSPX mRightFrontDrive;
    VictorSPX mRightRearDrive;
    VictorSPX mLeftFrontDrive;
    VictorSPX mLeftRearDrive;
	
    public Drivetrain(){
    	
		final double ticksPerRev = 1024;
		final double pi = 3.14;
		final double radius = 2;
		final double calculated = (2*pi)/ticksPerRev*radius;
//    	hi
		
    	mLeftSolenoid = HardwareMap.getInstance().mLeftShifterHardware;
    	mRightSolenoid = HardwareMap.getInstance().mRightShifterHardware;
    	//mLaserInput = HardwareMap.getInstance().mLaserHardware;
    	
    	
    	mLeftFrontDrive = new VictorSPX(Constants.kLeftFrontMotorId);
    	mLeftFrontDrive.set(ControlMode.PercentOutput,0);
    	mLeftFrontDrive.setSensorPhase(false);
    	mLeftFrontDrive.setInverted(false);
    	
    	mRightFrontDrive = new     VictorSPX(Constants.kRightFrontMotorId);
    	mRightFrontDrive.set(ControlMode.PercentOutput,0);
    	mRightFrontDrive.setSensorPhase(false);
    	mRightFrontDrive.setInverted(true);
    	
    	mLeftRearDrive = new     VictorSPX(Constants.kLeftRearMotorId);
    	mLeftRearDrive.set(ControlMode.PercentOutput,0);
    	mLeftRearDrive.setSensorPhase(false);
    	mLeftRearDrive.setInverted(false);
    	
    	mRightRearDrive = new     VictorSPX(Constants.kRightRearMotorId);
    	mRightRearDrive.set(ControlMode.PercentOutput,0);
    	mRightRearDrive.setSensorPhase(false);
    	mRightRearDrive.setInverted(true);
    	
    	
    	
    	mLeftEncoder = new Encoder(1, 2);
		mLeftEncoder.setDistancePerPulse(calculated);
		mLeftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		mLeftEncoder.setReverseDirection(true);
		mRightEncoder = new Encoder(3, 4);
		mRightEncoder.setDistancePerPulse(calculated);
		mRightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		mRightEncoder.setReverseDirection(false);
		}
    
    private DriveGear mDriveGear;
    
    private double mLeftSpeed;
    private double mRightSpeed;
    private double mMoveSpeed;
    private double mRotateSpeed;
    
    public enum DriveGear {
    	LOW, HIGH
    }
    
    public DriveGear getDriveGear(){
    	return mDriveGear;
    }
    
    public void resetEncoders(){
    	mLeftEncoder.reset();
    	mRightEncoder.reset();
    }
    
    public double getDistance(){
    	return (mLeftEncoder.getDistance() + mRightEncoder.getDistance()) / 2.0;
    }
	
    public void setTankDriveSpeed(double left, double right){
    	//System.out.println("Left speed = " + left + " right speed = " + right);
    	mLeftSpeed = -left;
    	mRightSpeed = right;
    	mLeftFrontDrive.set(ControlMode.PercentOutput,left);
    	mLeftRearDrive.set(ControlMode.PercentOutput,left);
    	mRightFrontDrive.set(ControlMode.PercentOutput,right);
    	mRightRearDrive.set(ControlMode.PercentOutput,right);
    }
    
//    public void setArcadeDriveSpeed(double move, double rotate){
//    	mRotateSpeed = rotate;
//    	mMoveSpeed = move;
//    	mDrive.arcadeDrive(move, rotate);
//    }
    
    
    
    
    @Override
    public void stop(){
    	mLeftFrontDrive.set(ControlMode.PercentOutput,0);
    	mLeftRearDrive.set(ControlMode.PercentOutput,0);
    	mRightFrontDrive.set(ControlMode.PercentOutput,0);
    	mRightRearDrive.set(ControlMode.PercentOutput,0);
    }
    
    private void setLowGear() {
    	
    	mLeftSolenoid.set(false);
    	mRightSolenoid.set(false);
    }
	
    private void setHighGear() {
    	mLeftSolenoid.set(true);
    	mRightSolenoid.set(true);
    }
	
	@Override
	public void updateSubsystem() {
		System.out.println(mRightEncoder.getDistance() + " Right");
		System.out.println(mLeftEncoder.getDistance() + " Left");
		switch(mDriveGear){
		case HIGH:
			setHighGear();
			break;
		case LOW:
			setLowGear();
			break;
		default:
			highGear();
			break;
		}
		
		outputToSmartDashboard();
		
	}
	
	public void highGear(){
		mDriveGear = DriveGear.HIGH;
	}
	
	public void lowGear(){
		mDriveGear = DriveGear.LOW;
	}

	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("DriveTrain_LeftMotorSpeeds", mLeftSpeed);
		SmartDashboard.putNumber("DriveTrain_RightMotorSpeeds", mRightSpeed);
		SmartDashboard.putNumber("DriveTrain_MoveValue", mMoveSpeed);
		SmartDashboard.putNumber("DriveTrain_RotateValue", mRotateSpeed);
		SmartDashboard.putNumber("Right_Drivetrain_Encoder_Distance", mRightEncoder.getDistance());
		SmartDashboard.putString("Drive_Gear", mDriveGear.toString());
		//SmartDashboard.putString("Laser Distance", mLaserInput.toString());
	}
}
