package com.neu.swing.views.tabs.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.neu.swing.views.menu.JMenuBarCustom;
import com.neu.swing.views.tabs.AlgorithmForm;
import com.neu.swing.views.tabs.buttons.AbstractButton;

import javax.swing.JOptionPane;

public class JStartButton extends AbstractButton implements ActionListener {
	AlgorithmForm form;

	public JStartButton(String name, JMenuBarCustom menu) {
		super(name, menu);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = form.updateDataFromForm();
		if (s != null) {
			JOptionPane.showMessageDialog(this, s, "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (manager.start(this, second) == true) {// GraphPainterManager
			this.setEnabled(false);
			second.setEnabled(true);
			menu.changeEnabledFileMenu(false);
		}
	}

	public void setAlgorithmForm(AlgorithmForm form) {
		this.form = form;
	}

	private static final long serialVersionUID = 7877702719672731779L;
}
