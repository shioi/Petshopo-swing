package petshop;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import java.util.ArrayList;

public class AddForm extends JFrame{
	final String user;
	AddForm(String username){
		super();
		this.user = username;
		setSize(500,500);
		getContentPane().setLayout(null);
		
		JLabel lblAddress = new JLabel("Delivery Address");
		lblAddress.setForeground(new Color(192, 28, 40));
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 23));
		lblAddress.setBounds(129, 47, 259, 66);
		getContentPane().add(lblAddress);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(82, 151, 335, 188);
		getContentPane().add(editorPane);
		
		JButton btnBack = new JButton("Back to Cart");
		btnBack.setBounds(38, 405, 141, 25);
		btnBack.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   setVisible(false);
	    		   new cart(username);
	    	   }
	       });

		getContentPane().add(btnBack);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(334, 405, 117, 25);
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				database dbms = new database();
				//get the cart items 
				ArrayList<Product> itemBought = dbms.getCartProducts(username);
				//save url
				String url = editorPane.getText();
				if(dbms.buyItems(username)) {
					setVisible(false);
					new OrderSummary(username,itemBought, url);
				}
			}
		});
		getContentPane().add(btnBuy);
		setVisible(true);
	}
}
