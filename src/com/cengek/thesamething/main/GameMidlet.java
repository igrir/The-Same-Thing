package com.cengek.thesamething.main;

import com.cengek.thesamething.manager.*;
import com.cengek.thesamething.util.RMS;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import com.nokia.mid.ui.VirtualKeyboard;

public class GameMidlet extends MIDlet {
	
	SameThingGame stg;
	RMS rms = new RMS();
	Display display;
	
	
	public GameMidlet() {
		stg = new SameThingGame(this);
		VirtualKeyboard.hideOpenKeypadCommand(true);
	}

	public RMS getRMS(){
		return rms;
	}
	
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		display = Display.getDisplay(this);
		display.setCurrent(stg);
		
		stg.start();
	}

}
