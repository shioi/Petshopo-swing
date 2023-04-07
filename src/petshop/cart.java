package petshop;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class cart extends JFrame {
	final String username;
	int total = 0;
	Boolean isEmpty=false;
	cart(String username){
		super();
		this.username = username;
		setSize(800,800);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(53, 132, 228));
		panel.setBounds(62, 12, 693, 92);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCart = new JLabel("Cart");
		lblCart.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCart.setBounds(31, 25, 133, 24);
		panel.add(lblCart);
		
		JLabel lblAccount = new JLabel("Account: " + username);
		lblAccount.setBounds(469, 32, 201, 17);
		panel.add(lblAccount);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(72, 155, 683, 520);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnBuy = new JButton("Proceed to Buy");
		btnBuy.setBounds(542, 483, 117, 25);
		btnBuy.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   if(isEmpty) {
						JOptionPane.showInternalMessageDialog(new JPanel(),"Cart Empty!!!", "Error",JOptionPane.ERROR_MESSAGE);
	    		   } else {
	    		   setVisible(false);
	    		   new AddForm(username);
	    		   }
	    	   }
	       });


		panel_1.add(btnBuy);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 649, 459);
		scrollPane.setViewportView(createPanel());
		panel_1.add(scrollPane);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(12, 483, 117, 25);
		btnBack.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   setVisible(false);
	    		   new MainPage(username);
	    	   }
	       });


		panel_1.add(btnBack);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTotalAmount.setBounds(520, 116, 235, 27);
		getContentPane().add(lblTotalAmount);
		setVisible(true);
		
	}
	
	JPanel createPanel() {
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		//for loop starts here
		database dbms = new database();
		ArrayList<Product> prods = new ArrayList<>();
		prods = dbms.getCartProducts(username);
		if(prods.size() == 0) {
			isEmpty=true;
		}
		
		for(Product prod: prods) {
			JPanel panel_4 = new JPanel();
			panel_4.setLayout(null);
			JLabel lblName = new JLabel("Item:" + prod.name);
			lblName.setBounds(144, 22, 200, 15);
			panel_4.add(lblName);
			
			JLabel lblQuantity = new JLabel("Quantity:" + prod.total);
			lblQuantity.setBounds(144, 72, 200, 15);
			panel_4.add(lblQuantity);
			
			JLabel lblTotal = new JLabel("Total:" + prod.price*prod.total);
			lblTotal.setBounds(297, 50, 100, 15);
			panel_4.add(lblTotal);
			
			JButton btnDelete = new JButton("Delete");
			btnDelete.setBounds(413, 45, 117, 25);
			btnDelete.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   database dbms = new database();
	    		   if(dbms.deleteProdFromCart(username, prod.id)) {
					JOptionPane.showMessageDialog(panel_3,"Deleted");
					JButton button = (JButton)e.getSource();
					Container parent = button.getParent();
					Container grandparent = parent.getParent();
					grandparent.remove(parent);
					cart.this.revalidate();
					cart.this.repaint();
	    		   } 
	    	   }
	       });

			panel_4.add(btnDelete);
			panel_3.add(panel_4);
			total+=prod.price+prod.total;
		}
		
				return panel_3;
	}
}
