package petshop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;


public class RegistrationForm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JTextField username, email;
	JPasswordField pass;
	private JTextField textField;

	RegistrationForm(){
		super();
		System.out.println(getOperatingSystem());
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setSize(1366,768);
	       username = this.makeFields("Username");
//	       email = this.makeFields("Email");
	       JLabel label = new JLabel("Password");
	       label.setForeground(new Color(255, 204, 204));
	       label.setFont(new Font("Yu Mincho Demibold", Font.BOLD, 18));
	       label.setBounds(374, 320, 115, 40);
	       getContentPane().add(label);
	       pass = new JPasswordField(30);
	       pass.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 204, 204)));
	       pass.setBounds(516, 320, 257, 40);
	       getContentPane().add(pass);
	       JButton register = new JButton("REGISTER");
	       register.setBackground(new Color(255, 204, 204));
	       register.setForeground(new Color(153, 0, 51));
	       register.setFont(new Font("Yu Mincho Demibold", Font.PLAIN, 18));
	       register.setBounds(522, 387, 170, 51);
	       register.addActionListener(this);
	       getContentPane().add(register);
	       
	       textField = new JTextField(30);
	       textField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 204, 204)));
	       textField.setBounds(516, 245, 256, 43);
	       getContentPane().add(textField);
	       
	       JPanel panel = new JPanel();
	       panel.setBackground(new Color(153, 0, 51));
	       panel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
	       panel.setBounds(327, 109, 562, 359);
	       getContentPane().add(panel);
	       panel.setLayout(null);
	       
	       JLabel lblNewLabel_1 = new JLabel("Email");
	       lblNewLabel_1.setBounds(52, 141, 98, 28);
	       panel.add(lblNewLabel_1);
	       lblNewLabel_1.setForeground(new Color(255, 204, 204));
	       lblNewLabel_1.setFont(new Font("Yu Mincho Demibold", Font.BOLD, 18));
	       
	       String os = getOperatingSystem();
	       String pth="";
	       if(os.equals("Linux")) {
	    	   pth = "/Images/registrationBackground.jpg";
	       } else {
	    	   pth  = "C:\\Users\\NIDHI\\eclipse-workspace\\petshop\\src\\Images\\registrationBackground.jpg";
	       }
	       JLabel lblNewLabel = new JLabel("");
	       lblNewLabel.setIcon(new ImageIcon(RegistrationForm.class.getResource(pth)));
	       lblNewLabel.setBounds(0, 0, 1350, 729);
	       getContentPane().add(lblNewLabel);
	       setVisible(true);
	    }
	JTextField makeFields(String name) {
	       getContentPane().setLayout(null);
	       JLabel lblRegisterHere = new JLabel("Register Here!");
	       lblRegisterHere.setForeground(new Color(153, 0, 51));
	       lblRegisterHere.setFont(new Font("Yu Mincho Demibold", Font.PLAIN, 45));
	       lblRegisterHere.setBounds(459, 43, 303, 62);
	       getContentPane().add(lblRegisterHere);
	       JLabel label_1 = new JLabel(name);
	       label_1.setForeground(new Color(255, 204, 204));
	       label_1.setFont(new Font("Yu Mincho Demibold", Font.BOLD, 18));
	       label_1.setBounds(372, 174, 117, 40);
	       getContentPane().add(label_1);
	       JTextField field = new JTextField(30);
	       field.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 204, 204)));
	       field.setBounds(516, 174, 256, 43);
	       getContentPane().add(field);
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
	public static String getOperatingSystem() {
	    String os = System.getProperty("os.name");
	    // System.out.println("Using System Property: " + os);
	    return os;
	}
	
	
}