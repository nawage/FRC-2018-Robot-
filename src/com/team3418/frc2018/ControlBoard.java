package com.team3418.frc2018;

import edu.wpi.first.wpilibj.Joystick;

public class ControlBoard {
	
	private static ControlBoard mInstance = new ControlBoard();

    public static ControlBoard getInstance() {
        return mInstance;
    }
    
    //Create Joystick Object
    private final Joystick mDriverStick;
    private final Joystick mSecondaryDriverStick;
    
    //Initialize Joystick Object
    ControlBoard() {
    	mDriverStick = new Joystick(0);
    	mSecondaryDriverStick = new Joystick(1);
    }
    
    //DRIVER CONTROLLER
    
    //drive controls
    public double getDriverLeftX(){
    	return mDriverStick.getRawAxis(0);
    }
    
    public double getDriverLeftY(){
    	return -mDriverStick.getRawAxis(1);
    }
    
    public double getDriverRightX(){
    	return mDriverStick.getRawAxis(4);
    }
    
    public double getDriverRightY(){
    	return -mDriverStick.getRawAxis(5);
    }
    
    public int getDriverPov(){
    	return mDriverStick.getPOV(0);
    }
    
    //driver functional controls
    public boolean getDriverHighGearButton(){
    	return mDriverStick.getRawButton(6);
    }
    
    public boolean getDriverLowGearButton(){
    	return mDriverStick.getRawButton(5);
    }
    
    public boolean getDriverRightTrigger() {
    	return mDriverStick.getRawAxis(3) > .1;
    }
    
    public boolean getDriverLeftTrigger() {
    	return mDriverStick.getRawAxis(2) > .1;
    }
    //
    
    //SECONDARY CONTROLLER
    
    //secondary functional controls

    public boolean getClimberForwardButton(){
    	return mSecondaryDriverStick.getRawButton(3);
    }

    public boolean getClimberHoldButton(){
    	return mSecondaryDriverStick.getRawButton(8);
    }
    
    public boolean getClimberReverseButton(){
    	return mSecondaryDriverStick.getRawButton(7);
    }
    
    public boolean getSecondaryIntakeButton(){
    	return mSecondaryDriverStick.getRawAxis(1) > .2;
    }
    
    public boolean getSecondaryOutakeButton(){
    	return mSecondaryDriverStick.getRawAxis(1) < -.2;
    }
    
    public boolean getSecondarySpoolButton(){
    	return mSecondaryDriverStick.getRawButton(6);
    }
    
    public boolean getSecondaryShootButton(){
    	return mSecondaryDriverStick.getRawAxis(3) > .1;
    }
    
    public boolean getSecondaryCam1Button(){
    	return mSecondaryDriverStick.getRawButton(5);
    }
    
    public boolean getSecondaryCam2Button(){
    	return mSecondaryDriverStick.getRawAxis(2) > .1;
    }
    
    public boolean getSecondaryArmOpenButton(){
    	return mSecondaryDriverStick.getRawAxis(4) > .2;
    }
    
    public boolean getSecondaryArmCloseButton(){
    	return mSecondaryDriverStick.getRawAxis(4) < -.2;
    }
    
    public boolean getSecondaryHighAimButton(){
    	return mSecondaryDriverStick.getRawButton(4);
    }
    
    public boolean getSecondaryLowAimButton(){
    	return mSecondaryDriverStick.getRawButton(1);
    }
}
