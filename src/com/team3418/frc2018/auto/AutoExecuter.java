package com.team3418.frc2018.auto;


public class AutoExecuter {
	
	private AutoModeBase mAutoMode;
	public int mAutoSetting;
    private Thread m_thread = null;

    public void setAutoRoutine(AutoModeBase newAutoMode) {
        mAutoMode = newAutoMode;
    }
    
    public void setAutoSetting(int autoSetting) {
        mAutoSetting = autoSetting;
    }
    
    public void start() {
        if (m_thread == null) {
            m_thread = new Thread() {
            	
            	
                public void run() {
                    if (mAutoMode != null) {
                        mAutoMode.run();
                    }
                }
            };
            m_thread.start();
        }
    }
    // Skrrt Skrrt
    public void stop() {
        if (mAutoMode != null) {
            mAutoMode.stop();
        }
        m_thread = null;
    }
}
