package eu.knux.passmanager.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import eu.knux.passmanager.Objects.Password;

public class PanelPasswd extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private final JButton btnNewButton = new JButton("Generate");

	/**
	 * Create the panel.
	 */
	public PanelPasswd(Password p, boolean b) {
		setLayout(new MigLayout("", "[][grow][][]", "[][][grow][]"));
		
		JLabel lblUsername = new JLabel("Username:");
		add(lblUsername, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JButton btnCopy = new JButton("Copy");
		add(btnCopy, "cell 2 0,alignx center,aligny center");
		
		JLabel lblPassword = new JLabel("Password:");
		add(lblPassword, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JButton btnCopy_1 = new JButton("Copy");
		add(btnCopy_1, "cell 2 1,alignx center,aligny center");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(btnNewButton, "cell 3 1,alignx center,aligny center");
		
		JLabel lblComment = new JLabel("Comment:");
		add(lblComment, "cell 0 2");
		
		JTextArea textArea = new JTextArea();
		add(textArea, "cell 1 2,grow");
		
		JButton btnEdit = new JButton("Edit");
		add(btnEdit, "cell 3 3,alignx center,aligny center");

		textField.setText(p.getUsername());
		textField_1.setText(p.getPass());
		textArea.setText(p.getComment());
		
	}

}
