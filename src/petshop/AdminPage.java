package petshop;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;


public class AdminPage extends JFrame implements ActionListener{
	JTextField name, type,total,price;
	JLabel filelabel;
	String fileurl;
	AdminPage(){
		super();
		getContentPane().setBackground(new Color(145, 65, 172));
		setBackground(new Color(222, 221, 218));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setSize(600,700);
		Font ft = new Font("Serif",Font.PLAIN,26);
	    getContentPane().setLayout(new BorderLayout(80,80));
		JPanel head = new JPanel();
	    JLabel petshop= new JLabel("UPLOAD ITEMS");
	    petshop.setFont(ft);
	    head.add(petshop);
	    getContentPane().add(head,BorderLayout.NORTH);
	    JPanel fields = makeFields();
	    getContentPane().add(fields);
	    
	    //fields
	    setVisible(true);
	}
	
	JPanel makeFields() {
		JPanel fields = new JPanel();
		fields.setLayout(new BoxLayout(fields,BoxLayout.Y_AXIS));

		JPanel nm = new JPanel();
		nm.setLayout(null);
		JLabel label_3 = new JLabel("Product Name");
		label_3.setFont(new Font("Dialog", Font.BOLD, 17));
		label_3.setBounds(29, 12, 162, 50);
		nm.add(label_3);
		name = new JTextField(40);
		name.setBounds(144, 72, 444, 50);
		nm.add(name);
		
		JPanel types = new JPanel();
		types.setLayout(null);
		JLabel label = new JLabel("Types");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(36, 74, 78, 29);
		types.add(label);
	    String[] choices = { "Pet Food", "Pet Accessories"};
	    
	    JComboBox comboBox = new JComboBox(choices);
	    comboBox.setBounds(122, 72, 140, 32);
	    types.add(comboBox);
	    
	    JLabel label_1 = new JLabel("Quantity");
	    label_1.setFont(new Font("Dialog", Font.BOLD, 15));
	    label_1.setBounds(291, 75, 83, 28);
	    types.add(label_1);
	    total = new JTextField(5);
	    total.setBounds(439, 74, 59, 19);
	    types.add(total);
	    
		JPanel pri= new JPanel();
	    JLabel label_2 = new JLabel("Price");
	    label_2.setFont(new Font("Dialog", Font.BOLD, 15));
	    label_2.setBounds(291, 132, 68, 17);
	    types.add(label_2);
	    price= new JTextField(5);
	    price.setBounds(439, 131, 59, 19);
	    types.add(price);
	    
	    //Image picker button
	    JButton imagePicker = new JButton("Choose Image");
	    imagePicker.setBounds(69, 17, 134, 36);
	    imagePicker.addActionListener(this);
	    pri.setLayout(null);
	    pri.add(imagePicker);
		filelabel = new JLabel();	
		filelabel.setBounds(369, 17, 0, 0);
		pri.add(filelabel);

		
		
		fields.add(nm);
		fields.add(types);
		fields.add(pri);
		
		//submit button
		JButton submit = new JButton("Submit");
		submit.setBackground(new Color(181, 131, 90));
		submit.setBounds(241, 129, 115, 36);
		pri.add(submit);
		submit.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   String nm = name.getText();
	    		   String ty =comboBox.getSelectedItem().toString(); 
	    		   int tot = Integer.parseInt(total.getText());
	    		   int prc = Integer.parseInt(price.getText());
					File source = new File(filelabel.getText());
					int ind = filelabel.getText().lastIndexOf("/");
					String filename = filelabel.getText().substring(ind+1); 
					 File dest =new File(System.getProperty("user.dir")+"/src/Images/" + filename);
					try {
						copyFile(source,dest);
					} catch (Exception exe) {
						exe.printStackTrace();
					}
					fileurl = System.getProperty("user.dir")+"/src/Images/"+filename;
	    		   Product prod  = new Product(nm,ty,tot,prc,fileurl);
	    		   database dbms = new database();
	    		   if(dbms.addToProducts(prod)) {
						JOptionPane.showMessageDialog(fields,"Added!!");
	    		   } else 
	    			   JOptionPane.showMessageDialog(fields, "Error", "Cannot upload", JOptionPane.ERROR_MESSAGE);
	    		   }
	       });
		return fields;
		
			
}	

	public static BufferedImage scaleImage(int w, int h, BufferedImage img) throws Exception {
	    BufferedImage bi;
	    bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(img, 0, 0, w, h, null);
	    g2d.dispose();
	    return bi;
	}
	
	 private static void copyFile(File src, File dest) throws IOException {
	        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
	    chooser.showOpenDialog(null);
	    File f = chooser.getSelectedFile();
	    String filename = f.getAbsolutePath();
	  filelabel.setText(filename);
	    try {
	        ImageIcon ii=new ImageIcon(scaleImage(120, 120, ImageIO.read(new File(f.getAbsolutePath()))));//get the image from file chooser and scale it to match JLabel size
	        filelabel.setIcon(ii);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
		
	}
}
