package eu.knux.passmanager.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import eu.knux.passmanager.Objects.Password;

public class PanelPassword extends JPanel {

	Password pass;

	JLabel username, password, comment;
	JTextField usernameField, passwordField;
	JTextArea commentArea;

	JButton copyUser, copyPassword, edit, genPass;

	boolean editMode = false;

	public PanelPassword(Password p, boolean editMode) {
		this.pass = p;
		this.editMode = editMode;
		
		username 	  = new JLabel("Username: ");
		password 	  = new JLabel("Password: ");
		comment  	  = new JLabel("Comment:");
		usernameField = new JTextField(p.getUsername());
		passwordField = new JTextField(p.getPass());
		commentArea   = new JTextArea(p.getComment());
		copyUser 	  = new JButton("Copy");
		copyPassword  = new JButton("Copy");
		edit 		  = new JButton("Edit");
		genPass 	  = new JButton("Generate password");

		setLayout(new MigLayout());

		add(username);
		add(usernameField);
		add(copyUser, "wrap");
		add(password);
		add(passwordField);
		add(copyPassword);
		add(genPass, "wrap");
		add(comment);
		add(commentArea, "wrap");
		
		updateMode();
	}

	private void updateMode() {
		usernameField.setEditable(editMode);
		passwordField.setEditable(editMode);
		commentArea.setEditable(editMode);
		edit.setText(editMode ? "Save" : "Edit");
		genPass.setVisible(editMode);
		copyUser.setVisible(editMode);
		copyPassword.setVisible(editMode);
	}

}
