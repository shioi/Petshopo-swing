package petshop;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class MainPage extends JFrame{
	private static final long serialVersionUID = 1L;
	private String currentuser;
	MainPage(String username){
		super();

		this.currentuser = username;
		Font ft = new Font("Serif",Font.PLAIN,26);
	    getContentPane().setLayout(new BorderLayout(5,5));
		JPanel head = new JPanel();
	    head.setLayout(null);
	    JLabel petshop = new JLabel("PET SHOP");
	    petshop.setBounds(419, 31, 125, 36);
	    petshop.setFont(ft);
		head.add(petshop);
		JButton cart = new JButton("Cart");
		cart.setBounds(924, 16, 64, 25);
		head.add(cart);
		head.setPreferredSize(new Dimension(350, 100));
		head.setBackground(Color.blue);

		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel food= createPanel("Pet Food");
	    JScrollPane scrolledpanel1 = new JScrollPane(food);
		JPanel acc= createPanel("Pet Accessories");
		acc.setLayout(new WrapLayout());
	    JScrollPane scrolledpanel2 = new JScrollPane(acc);
		tabbedPane.add("Pet Food",scrolledpanel1);
		tabbedPane.add("Pet Accessories",scrolledpanel2);
			
	    //adding to the current frame
	    getContentPane().add(head,BorderLayout.NORTH);
	    getContentPane().add(tabbedPane);
		setSize(1000,800);
		setVisible(true);
	}
	
	JPanel createPanel(String title) {
			//main items fetch from the Database 
		Border blackline = blackline = BorderFactory.createLineBorder(Color.black);
		JPanel items = new JPanel();
		database dbms = new database();
		ArrayList<Product> products = dbms.getProducts(title);
		items.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
		items.setBackground(new Color(122,157,161));
		items.setLayout(new FlowLayout(FlowLayout.LEFT,40,40));
		if(products.size() == 0) {
			items.add(new JLabel("No products"));
		} else {
			for(Product product: products) {
				JPanel pan= new JPanel();
				pan.setBorder(blackline);
				JLabel pic = new JLabel();
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(product.getUrl()).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
				pic.setIcon(imageIcon);
				pan.add(pic);
				pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
				pan.add(new JLabel(product.name));
				pan.add(new JLabel("Price: "+Integer.toString(product.price)));
				String quantity;
				if(product.total == 0) {
					quantity = "sold out";
				} else {
					quantity = "Quantity:"+Integer.toString(+product.total);
				}
				 pan.add(new JLabel(quantity));
								if(product.total >0) {
				 SpinnerModel value =  
				             new SpinnerNumberModel(1, //initial value  
				                1, //minimum value  
				                product.total, //maximum value  
				                1); //step  
				JSpinner spinner = new JSpinner(value);   

					pan.add(spinner);
				JButton click = new JButton("Add to cart");
				click.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		  // System.out.println("items added");
	    		   if(dbms.addToCart(currentuser, product.id,Integer.valueOf(spinner.getValue().toString()))) {
					JOptionPane.showInternalMessageDialog(pan,"Added to card");
	    		   } else {
						JOptionPane.showInternalMessageDialog(pan,"Error! cannot add to card", "Error",JOptionPane.ERROR_MESSAGE);
	    		   }
	    	   }
	       });

					pan.add(click);
				}

				items.add(pan);
					
			}
		}
		
		return items;
	}
	

}
