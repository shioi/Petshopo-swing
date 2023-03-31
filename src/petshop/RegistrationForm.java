package petshop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class RegistrationForm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JTextField username, email;
	JPasswordField pass;

	RegistrationForm(){
		super();
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setSize(500,500);
	       setLayout(new GridBagLayout());
	       GridBagConstraints gbc = new GridBagConstraints();  
	       
	       gbc.gridx = 0;
	       gbc.gridy = 0;
	       gbc.fill = GridBagConstraints.HORIZONTAL;
	       add(new JLabel("Registration"),gbc);
	       username = this.makeFields("Username", gbc,1);
	       email = this.makeFields("Email", gbc,2);
	       gbc.gridx = 0;
	       gbc.gridy = 3;
	       add(new JLabel("Password"),gbc);
	       gbc.gridx = 1;
	       gbc.gridy = 3;
	       pass = new JPasswordField(30);
	       add(pass,gbc);
	       gbc.gridheight = 0;
	       gbc.gridy=4;
	       JButton register = new JButton("REGISTER");
	       register.addActionListener(this);
	       add(register,gbc);
	       setVisible(true);
	    }
	JTextField makeFields(String name, GridBagConstraints gbc,int y) {
		   gbc.gridx = 0;
	       gbc.gridy = y;
	       add(new JLabel(name),gbc);
	       gbc.gridx = 1;
	       gbc.gridy = y;
	       JTextField field = new JTextField(30);
	       add(field,gbc);
	       return field;

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		database db = new database();
		Boolean result = db.registration(username.getText(), email.getText(), pass.getPassword());
		if(result) {
			this.setVisible(false);
			new LoginForm();
		}
			
	}
}

