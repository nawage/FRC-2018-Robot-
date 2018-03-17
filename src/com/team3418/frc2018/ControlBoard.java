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
    private final Joystick mSwitchboard;
    
    //Initialize Joystick Object
    ControlBoard() {
    	mDriverStick = new Joystick(0);
    	mSecondaryDriverStick = new Joystick(1);
    	mSwitchboard = new Joystick(2);
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
    
    public boolean getPovUp(){
    	return mSecondaryDriverStick.getPOV(0)==0 || mSecondaryDriverStick.getPOV(0)==45||mSecondaryDriverStick.getPOV(0)==315;
    	
    }
    public boolean getPovRight(){
    	return mSecondaryDriverStick.getPOV(0)==90 || mSecondaryDriverStick.getPOV(0)==45||mSecondaryDriverStick.getPOV(0)==135;
    	
    }
    public boolean getPovDown(){
    	return mSecondaryDriverStick.getPOV(0)==180 || mSecondaryDriverStick.getPOV(0)==135||mSecondaryDriverStick.getPOV(0)==225;
    	
    }
    public boolean getPovLeft(){
    	return mSecondaryDriverStick.getPOV(0)==270 || mSecondaryDriverStick.getPOV(0)==315||mSecondaryDriverStick.getPOV(0)==225;
    	
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
    
    public boolean getSecondaryLeftIntakeButton(){
    	return mSecondaryDriverStick.getRawAxis(1) > .2;
    }
    
    public boolean getSecondaryRightIntakeButton(){
    	return mSecondaryDriverStick.getRawAxis(5) > .2;
    }
    
    public boolean getSecondaryLeftOutakeButton(){
    	return mSecondaryDriverStick.getRawAxis(1) < -.2;
    }
    
    public boolean getSecondaryRightOutakeButton(){
    	return mSecondaryDriverStick.getRawAxis(5) < -.2;
    }
    
    public boolean getSecondarySpoolButton(){
    	return mSecondaryDriverStick.getRawButton(6);
    }
    
    public boolean getSecondaryShootButton(){
    	return mSecondaryDriverStick.getRawAxis(3) > .1;
    }
    
    public boolean getSecondaryReverseSpoolButton(){
    	return mSecondaryDriverStick.getRawButton(5);
    }
    
    public boolean getSecondaryCam2Button(){
    	return mSecondaryDriverStick.getRawAxis(2) > .1;
    }
    
    public boolean getSecondaryLeftArmOpenButton(){
    	return mSecondaryDriverStick.getRawAxis(0) < -.8;
    }
    
    public boolean getSecondaryRightArmOpenButton(){
    	return mSecondaryDriverStick.getRawAxis(4) > .8;
    }
    
    public boolean getSecondaryLeftArmCloseButton(){
    	return mSecondaryDriverStick.getRawAxis(0) > .8;
    }
    
    public boolean getSecondaryRightArmCloseButton(){
    	return mSecondaryDriverStick.getRawAxis(4) < -.8;
    }
    
    public boolean getSecondaryHighAimButton(){
    	return mSecondaryDriverStick.getRawButton(4);
    }
    
    public boolean getSecondaryLowAimButton(){
    	return mSecondaryDriverStick.getRawButton(1);
    }
    
    public boolean getSwitchboardClimberRelease(){
    	return mSwitchboard.getRawButton(1);
    }
    
    public boolean getSecondaryClimberRelease(){
    	return mSecondaryDriverStick.getRawButton(2);
    }
}
