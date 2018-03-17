package com.team3418.frc2018;

import com.team3418.frc2018.auto.AutoModeBase;
import com.team3418.frc2018.auto.modes.LeftAllign;
import com.team3418.frc2018.auto.modes.MiddleAllign;
import com.team3418.frc2018.auto.modes.RightAllign;
import com.team3418.frc2018.auto.modes.ShootMode;
import com.team3418.frc2018.auto.modes.StandStillMode;
import com.team3418.frc2018.auto.modes.TestMode;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardInteractions {
	//test
	
	//chooser object to send to smartdashboard
	private SendableChooser<AutonOption> mAutoChooser;
	private SendableChooser<AutonSettings> mAutoSettings;
	
	//default autonomous mode in case one is not selected
    private static final AutonOption DEFAULT_MODE = AutonOption.MIDDLE_ALLIGN;
    private static final AutonSettings DEFAULT_MODE1 = AutonSettings.SWITCH;
    
    //test
    //happens when the class is first created
	public void initWithDefaults() {
		//Auto Mode Chooser
		mAutoChooser = new SendableChooser<AutonOption>();
        mAutoChooser.addObject(AutonOption.LEFT_ALLIGN.name, AutonOption.LEFT_ALLIGN);
        mAutoChooser.addObject(AutonOption.MIDDLE_ALLIGN.name, AutonOption.MIDDLE_ALLIGN);
        mAutoChooser.addObject(AutonOption.RIGHT_ALLIGN.name, AutonOption.RIGHT_ALLIGN);
        mAutoChooser.addObject(AutonOption.FORWARD_LINE.name, AutonOption.FORWARD_LINE);
        mAutoChooser.addObject(AutonOption.STAND_STILL.name, AutonOption.STAND_STILL);
        mAutoChooser.addObject(AutonOption.TEST.name, AutonOption.TEST);
        mAutoChooser.addDefault("Stand_Still", AutonOption.STAND_STILL);
        
        SmartDashboard.putData("Auto Mode Chooser", mAutoChooser);
        
        //Auto Settings Chooser
        mAutoSettings = new SendableChooser<AutonSettings>();
        mAutoSettings.addObject(AutonSettings.SCALE.settingName, AutonSettings.SCALE);
        mAutoSettings.addDefault("Switch", AutonSettings.SWITCH);
        
        SmartDashboard.putData("Auto Mode Settings", mAutoSettings);
    }
	
	//compares selected auto mode to AutonOptions and returns the created mode
    public AutoModeBase getSelectedAutonMode() {
        AutonOption selectedOption = DEFAULT_MODE;
        for (AutonOption autonOption : AutonOption.values()) {
            if (autonOption == mAutoChooser.getSelected()) {
                selectedOption = autonOption;
                break;
            }
        }
        return createAutoMode(selectedOption);
    }
    
    public int getSelectedAutonSettings() {
        int selectedSetting = 0;
        if (mAutoSettings.getSelected() == AutonSettings.SWITCH) {
        	selectedSetting = 0;
        }
        else if (mAutoSettings.getSelected() == AutonSettings.SCALE) {
        	selectedSetting = 1;
        }
        
        return selectedSetting;
    }
    
    
    //enum to hold all possible auto modes
   private enum AutonOption {
	    LEFT_ALLIGN("left robot allignment", new LeftAllign()), //
	    MIDDLE_ALLIGN("middle robot allignment", new MiddleAllign()), //
	    RIGHT_ALLIGN("right robot allignment", new RightAllign()), //
        FORWARD_LINE("forward line", new ShootMode()), //
        STAND_STILL("stand still", new StandStillMode()),//
    	TEST("test (do not use at comp)", new TestMode()); //
        
        public final String name;
        public final AutoModeBase autoMode;

        AutonOption(String name, AutoModeBase autoMode) {
            this.name = name;
            this.autoMode = autoMode;
        }
    }
    
    private enum AutonSettings {
	    SCALE("Scale", 0),
	    SWITCH("Switch", 1);
	    
	    public final String settingName;
    	public final int settingValue;
    	
    	AutonSettings(String settingName, int settingValue) {
    		this.settingName = settingName;
    		this.settingValue = settingValue;
    	}
    }
    
    //method to create auto mode based on chosen mode
    private AutoModeBase createAutoMode(AutonOption autonOption) {
        switch (autonOption) {
        case LEFT_ALLIGN:
            return autonOption.autoMode;
        case MIDDLE_ALLIGN:
            return autonOption.autoMode;
        case RIGHT_ALLIGN:
            return autonOption.autoMode;
        case FORWARD_LINE:
        	return autonOption.autoMode;
        case TEST:
            return autonOption.autoMode;
        case STAND_STILL:
        default:
            System.out.println("ERROR: unexpected auto setting: " + autonOption);
            return new StandStillMode();
        }
    }
}
