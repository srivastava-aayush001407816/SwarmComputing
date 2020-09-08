package com.neu.controller;

import com.neu.swing.GuiManager;

public class AppManager extends Thread {

	@Override
	public void run() {
		new GuiManager().start();
	}
}
