package petshop;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginForm extends JFrame implements ActionListener{
	JTextField username;	
	JPasswordField password;
	
	LoginForm(){
		super();
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(500,500);
	     setLayout(new BorderLayout());
	     
	       Font ft = new Font("Serif",Font.PLAIN,26);
	       JLabel loginLabel = new JLabel("LOGIN");
	       loginLabel.setFont(ft);
	       add(loginLabel, BorderLayout.NORTH);
	       
	       //main inputs fields
	       JPanel loginFields = new JPanel();
	       loginFields.setLayout(new GridBagLayout());
	       GridBagConstraints gbc = new GridBagConstraints();  
	       gbc.gridx=0;
	       gbc.gridy = 0;
	       loginFields.add(new JLabel("Username"),gbc);
	       gbc.gridx=1;
	       username = new JTextField(30);
	       loginFields.add(username, gbc);
	       gbc.gridx=0;
	       gbc.gridy = 1;
	       loginFields.add(new JLabel("Password"),gbc);
	       gbc.gridx=1;
	       password = new JPasswordField(30);
	       loginFields.add(password,gbc);
	       add(loginFields,BorderLayout.CENTER);
	       
	       JPanel buttons = new JPanel();
	       
	       //login button
	       JButton login= new JButton("Login");
	       login.addActionListener(this);
	       buttons.add(login);
	       
	       //registration button
	       JButton register = new JButton("New user? sign up");
	       register.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   setVisible(false);
	    		   new RegistrationForm();
	    	   }
	       });
	       buttons.add(register);
	       add(buttons,BorderLayout.SOUTH);
	       setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		database db = new database();
		String pass = new String(password.getPassword());
		if(username.getText().equals("admin") && pass.equals("admin")) {
			JOptionPane.showMessageDialog(this,"Welcome Admin");
			setVisible(false);
			new AdminPage();
		} else {
		Boolean result = db.login(username.getText(), password.getPassword());
		if(result) {
			JOptionPane.showMessageDialog(this,"Welcome");
			setVisible(false);
			new MainPage(username.getText());

		} else {
			JOptionPane.showMessageDialog(this,"cannot login! Check your password or username","Error",JOptionPane.ERROR_MESSAGE);
		}
		}
	
	}
	
	
}
