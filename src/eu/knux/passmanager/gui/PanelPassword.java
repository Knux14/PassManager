package eu.knux.passmanager.gui;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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

		GroupLayout gl = new GroupLayout(this);

		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);

		setLayout(gl);
		gl.setVerticalGroup(gl.createSequentialGroup()
								.addGroup(gl.createParallelGroup()
										 	.addComponent(username)
										 	.addComponent(usernameField)
										 )
							    .addGroup(gl.createParallelGroup()
							    		 	.addComponent(password)
							    		 	.addComponent(passwordField)
							    		 )
							    .addGroup(gl.createParallelGroup()
							    		 	.addComponent(comment)
							    		 	.addComponent(commentArea)
							    		 )
							 );
		gl.setHorizontalGroup(gl.createSequentialGroup()
	            .addGroup(gl.createParallelGroup()
	            		.addComponent(component))
	            .addGroup(gl.createParallelGroup()
	                .addComponent(textField)
	                .addGroup(gl.createSequentialGroup()
	                    .addGroup(gl.createParallelGroup()
	                        .addComponent(caseCheckBox)
	                        .addComponent(wholeCheckBox))
	                    .addGroup(gl.createParallelGroup()
	                        .addComponent(wrapCheckBox)
	                        .addComponent(backCheckBox))))
	            .addGroup(gl.createParallelGroup()
	                .addComponent(findButton)
	                .addComponent(cancelButton))
	        );

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
