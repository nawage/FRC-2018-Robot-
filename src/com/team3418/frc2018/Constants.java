package com.team3418.frc2018;

public class Constants {
	
	//-------------------------------//
	//-subsystem speed motor speeds-//
	//-----------------------------//
	
    //PID
    public static double kFlywheelKp = 0.1;
    public static double kFlywheelKi = 0.00035;
    public static double kFlywheelKd = 2.3;
    public static double kFlywheelKf = 0.037;
    public static int kFlywheelIZone = (int) (1023.0 / kFlywheelKp);
    public static double kFlywheelRampRate = 0;
    
	//misc. (not used)
    public static int kFlywheelAllowableError = 0;
    public static double kFlywheelOnTargetTolerance = 100.0;
    
    public static double kLowRumbleValue = 0.3;
    public static double kHighRumbleValue = 1;
    
	//-------------------------------//
	//-subsystem motor speeds-//
	//-----------------------------//
    
    //Intake Roller
    public static double kRollerIntakeSpeed = 1;
	public static double kRollerReverseSpeed = -1;
    
	//Climber
	public static double kClimberReverseSpeed = -.5;
	public static double kClimberHoldSpeed = .40;
	
	//shooter
	public static double kTargetShooterRpm = 1785;
	public static double kTargetLowShooterRpm = 785;
	
	//-------------//
	//-Autonomous-//
	//-----------//
	

	//--------------------------//
	//-static port assignments-//
	//------------------------//
	
	//Do not change anything below this line
	
	//PWM (0-9)
//	public static int kLeftFrontMotorId = 0;
//	public static int kLeftRearMotorId = 1;
//	public static int kRightFrontMotorId = 2;
//	public static int kRightRearMotorId = 3;
	
	//CAN (0-3)
	public static int kLeftFrontShooterMotorId = 5;
	public static int kLeftRearShooterMotorId = 6;
	public static int kRightFrontShooterMotorId = 3;
	public static int kRightRearShooterMotorId = 4;
	public static int kLeftFrontMotorId = 9;
	public static int kLeftRearMotorId = 10;
	public static int kRightFrontMotorId = 7;
	public static int kRightRearMotorId = 8;
	public static int kClimberId = 13;
	public static int kServoMotorId = 5;
	public static int kIntakeLeftId = 12;
	public static int kIntakeRightId = 11;
	
	//DIO (0-9)
	public static int kLeftEncoderChannelA = 0;
	public static int kLeftEncoderChannelB = 1;
	public static int kRightEncoderChannelA = 2;
	public static int kRightEncoderChannelB = 3;
	
	//SOLENOIDS (0-7)
	
	public static int kLeftShifterSolenoidId = 3; //DONT CONNECT ON ACTUAL ROBOT!!!!
	public static int kRightShifterSolenoidId = 1;
	public static int kIntakeLeftSolenoidId = 5;
	public static int kIntakeRightSolenoidId = 4;
	public static int kRampLeftSolenoidId = 2;
	public static int kClimberReleaseSolenoidId = 6;
	public static int kMrCushySolenoid = 0;
}
