package com.neu.swing.views.tabs.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.neu.swing.views.menu.JMenuBarCustom;
import com.neu.swing.views.tabs.buttons.AbstractButton;

public class JStopButton extends AbstractButton implements ActionListener {

	public JStopButton(String name, JMenuBarCustom menu) {
		super(name, menu);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.stop(); // GraphPainterManager
	}

	private static final long serialVersionUID = -1890203020751834566L;
}
