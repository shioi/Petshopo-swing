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
	       add(new JLabel("Registration"));
	       username = this.makeFields("Username");
	       email = this.makeFields("Email");
	       add(new JLabel("Password"));
	       pass = new JPasswordField(30);
	       add(pass);
	       JButton register = new JButton("REGISTER");
	       register.addActionListener(this);
	       add(register);
	       setVisible(true);
	    }
	JTextField makeFields(String name) {
	       add(new JLabel(name));
	       JTextField field = new JTextField(30);
	       add(field);
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

