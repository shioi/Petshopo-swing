package petshop;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class LoginForm extends JFrame implements ActionListener{
	JTextField username;	
	JPasswordField password;
	
	LoginForm(){
		super();
		getContentPane().setBackground(new Color(153, 102, 102));
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(1366,768);
	     
	       Font ft = new Font("Serif",Font.PLAIN,26);
	       getContentPane().setLayout(null);
	       JLabel loginLabel = new JLabel("LOGIN");
	       loginLabel.setBackground(new Color(255, 204, 255));
	       loginLabel.setForeground(new Color(153, 0, 0));
	       loginLabel.setBounds(937, 124, 260, 89);
	       loginLabel.setFont(new Font("Yu Mincho Demibold", Font.BOLD, 55));
	       getContentPane().add(loginLabel);
	       
	       //main inputs fields
	       JPanel loginFields = new JPanel();
	       loginFields.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(153, 0, 51)));
	       loginFields.setBackground(new Color(255, 255, 255));
	       loginFields.setBounds(762, 221, 555, 271);
	       //loginFields.setLayout(new GridBagLayout());
	       GridBagConstraints gbc = new GridBagConstraints();  
	       gbc.gridx=0;
	       gbc.gridy = 0;
	       loginFields.setLayout(null);
	       JLabel lblUsername = new JLabel("Username:");
	       lblUsername.setForeground(new Color(153, 0, 0));
	       lblUsername.setFont(new Font("Yu Mincho Demibold", Font.BOLD, 21));
	       lblUsername.setBounds(40, 45, 109, 44);
	       loginFields.add(lblUsername);
	       gbc.gridx=1;
	       username = new JTextField(30);
	       username.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 0, 51)));
	       username.setBackground(new Color(255, 204, 204));
	       username.setBounds(189, 43, 309, 44);
	       loginFields.add(username);
	       gbc.gridx=0;
	       gbc.gridy = 1;
	       JLabel lblPassword = new JLabel("Password:");
	       lblPassword.setForeground(new Color(153, 0, 0));
	       lblPassword.setFont(new Font("Yu Mincho Demibold", Font.BOLD, 21));
	       lblPassword.setBounds(40, 134, 109, 44);
	       loginFields.add(lblPassword);
	       gbc.gridx=1;
	       password = new JPasswordField(30);
	       password.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 0, 51)));
	       password.setBackground(new Color(255, 204, 204));
	       password.setBounds(189, 132, 309, 44);
	       loginFields.add(password);
	       getContentPane().add(loginFields);
	       
	       JPanel buttons = new JPanel();
	       buttons.setBackground(new Color(153, 0, 0));
	       buttons.setBounds(189, 204, 309, 33);
	       loginFields.add(buttons);
	       
	       //login button
	       JButton login= new JButton("Login");
	       login.setForeground(new Color(153, 0, 0));
	       login.setBackground(new Color(255, 204, 204));
	       login.addActionListener(this);
	       buttons.add(login);
	       
	       //registration button
	       JButton register = new JButton("New user? sign up");
	       register.setForeground(new Color(153, 0, 0));
	       register.setBackground(new Color(255, 204, 204));
	       register.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   setVisible(false);
	    		   new RegistrationForm();
	    	   }
	       });
	       buttons.add(register);
	       String pth;
	       String os = getOperatingSystem();
	       if(os.equals("Linux")) {
	    	   pth = "/Images/";
	       } else {
	    	   pth = "C:\\Users\\NIDHI\\eclipse-workspace\\petshop\\src\\Images\\";
	       }
	       JLabel lblNewLabel = new JLabel("");
	       lblNewLabel.setIcon(new ImageIcon(LoginForm.class.getResource(pth+"loginBackground.png")));
	       lblNewLabel.setBounds(0, 0, 705, 729);
	       getContentPane().add(lblNewLabel);
	       
	       JLabel label = new JLabel("");
	       label.setIcon(new ImageIcon(LoginForm.class.getResource(pth+"loginBackground2.jpg")));
	       label.setBounds(702, 0, 648, 729);
	       getContentPane().add(label);
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
public static String getOperatingSystem() {
	    String os = System.getProperty("os.name");
	    return os;
	}
	

}

