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
	private JTable table;
	
	LoginForm(){
		super();
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(1000,500);
	     getContentPane().setLayout(new BorderLayout());
	     
	       Font ft = new Font("Serif",Font.PLAIN,26);
	       
	       //main inputs fields
	       JPanel loginFields = new JPanel();
	       loginFields.setLayout(null);
	       JLabel label = new JLabel("Username");
	       label.setBounds(63, 114, 115, 46);
	       label.setFont(new Font("Dialog", Font.BOLD, 20));
	       loginFields.add(label);
	       username = new JTextField(30);
	       username.setBounds(217, 125, 226, 36);
	       loginFields.add(username);
	       JLabel label_1 = new JLabel("Password");
	       label_1.setBounds(62, 222, 116, 24);
	       label_1.setFont(new Font("Dialog", Font.BOLD, 20));
	       loginFields.add(label_1);
	       password = new JPasswordField(30);
	       password.setBounds(217, 219, 226, 36);
	       loginFields.add(password);
	       getContentPane().add(loginFields,BorderLayout.CENTER);
	       JLabel loginLabel = new JLabel("LOGIN");
	       loginLabel.setBounds(181, 12, 158, 46);
	       loginFields.add(loginLabel);
	       loginLabel.setFont(ft);
	       
	       //login button
	       JButton login= new JButton("Login");
	       login.setBounds(132, 319, 73, 25);
	       loginFields.add(login);
	       
	       //registration button
	       JButton register = new JButton("New user? sign up");
	       register.setBounds(254, 319, 164, 25);
	       loginFields.add(register);
	       
	       table = new JTable();
	       table.setBounds(706, 232, 226, -156);
	       loginFields.add(table);
	       
	       JLabel lblNewLabel = new JLabel("New label");
	       lblNewLabel.setBounds(145, 324, 6, 93);
	       loginFields.add(lblNewLabel);
	       
	       JLabel lblNewLabel_1 = new JLabel("");
	       lblNewLabel_1.setIcon(new ImageIcon(LoginForm.class.getResource("/Images/catchain.jpg")));
	       lblNewLabel_1.setBounds(535, 12, 453, 407);
	       loginFields.add(lblNewLabel_1);
	       register.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   setVisible(false);
	    		   new RegistrationForm();
	    	   }
	       });
	       login.addActionListener(this);
	       
	       JPanel buttons = new JPanel();
	       getContentPane().add(buttons,BorderLayout.SOUTH);
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
