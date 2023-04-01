package petshop;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;

public class OrderSummary extends JFrame {
	private static final long serialVersionUID = 1L;
	ArrayList<Product> orderItems;
	String address;
	private JTable table;
	OrderSummary(String username,ArrayList<Product> items, String add){
		super();
		getContentPane().setBackground(new Color(181, 131, 90));
		orderItems = items;
		this.address = add;
		
		//GUI
		setSize(800,800);
		getContentPane().setLayout(null);
		
		JLabel lblOrderSummary = new JLabel("Order Summary");
		lblOrderSummary.setBackground(new Color(255, 163, 72));
		lblOrderSummary.setFont(new Font("Dialog", Font.BOLD, 19));
		lblOrderSummary.setBounds(48, 12, 199, 58);
		getContentPane().add(lblOrderSummary);
		
		//create table content
		String content [][] = new String[orderItems.size()][4];
		int i=0;
		int total=0;
		for(Product prod: orderItems) {
			content[i][0] = Integer.toString(i+1);
			content[i][1] = prod.name;
			content[i][2] = Integer.toString(prod.getTotal());
			content[i++][3] = Integer.toString(prod.getPrice());
			total+=prod.getPrice() * prod.getTotal();
		}
		
		String []columnNames = {"no","Item","Quantity","Price"};
		
		table = new JTable(content,columnNames);
		table.setBounds(83, 82, 652, 324);
		getContentPane().add(table);
		
		JLabel lblTotal = new JLabel("Total: " + total);
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTotal.setBackground(new Color(145, 65, 172));
		lblTotal.setBounds(504, 464, 177, 43);
		getContentPane().add(lblTotal);
	
		
		
		JLabel textPane = new JLabel(address);
		textPane.setBackground(new Color(255, 255, 255));
		textPane.setBounds(112, 539, 596, 174);
		getContentPane().add(textPane);
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.setBounds(635, 722, 117, 25);
		btnNewButton.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   OrderSummary.this.setVisible(false);
	    		   new MainPage(username);
	    	   }
	       });
		getContentPane().add(btnNewButton);
		
		JLabel lblDelivaryAddress = new JLabel("Delivary Address");
		lblDelivaryAddress.setBounds(112, 512, 135, 43);
		getContentPane().add(lblDelivaryAddress);
		setVisible(true);
		
	}
}
