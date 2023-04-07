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


public class AdminPage extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JTextField name, type,total,price;

	JLabel filelabel;
	String fileurl;
	AdminPage(){
		super();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setSize(1129,700);
		Font ft = new Font("Serif",Font.PLAIN,26);
		getContentPane().setLayout(null);
		JPanel head = new JPanel();
		head.setBounds(0, 0, 1113, 44);
	    head.setLayout(null);
	    JLabel petshop= new JLabel("UPLOAD ITEMS");
	    petshop.setBounds(488, 5, 187, 34);
	    petshop.setFont(ft);
	    head.add(petshop);
	    getContentPane().add(head);
	    JPanel fields = makeFields();
	    getContentPane().add(fields);
	    
	    //fields
	    setVisible(true);
	}
	
	JPanel makeFields() {
		JPanel fields = new JPanel();
		fields.setBounds(0, 124, 1113, 537);
		fields.setLayout(new BoxLayout(fields,BoxLayout.Y_AXIS));

		JPanel nm = new JPanel();
		nm.setLayout(null);
		JLabel label_3 = new JLabel("Product Name");
		label_3.setBounds(357, 8, 67, 14);
		nm.add(label_3);
		name = new JTextField(40);
		name.setBounds(429, 5, 326, 20);
		nm.add(name);
		
		JPanel types = new JPanel();
		types.setLayout(null);
		JLabel label = new JLabel("Types");
		label.setBounds(146, 63, 46, 14);
		types.add(label);
	    String[] choices = { "Pet Food", "Pet Accessories"};
	    
	    JComboBox comboBox = new JComboBox(choices);
	    comboBox.setBounds(159, 5, 101, 20);
	    types.add(comboBox);
		// JComboBox<String> cb = new JComboBox<String>(choices);
	    //types.add(cb);
	    
	    JLabel label_1 = new JLabel("Quantity");
	    label_1.setBounds(276, 8, 42, 14);
	    types.add(label_1);
	    total = new JTextField(5);
	    total.setBounds(323, 5, 46, 20);
	    types.add(total);
	    
		JPanel pri= new JPanel();
	    JLabel label_2 = new JLabel("Price");
	    label_2.setBounds(374, 8, 23, 14);
	    types.add(label_2);
	    price= new JTextField(5);
	    price.setBounds(402, 5, 46, 20);
	    types.add(price);
	    
	    //Image picker button
	    JButton imagePicker = new JButton("Choose Image");
	    imagePicker.addActionListener(this);
	    pri.add(imagePicker);
		filelabel = new JLabel();	
		pri.add(filelabel);
		
		//submit button
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   String nm = name.getText();
	    		   String ty =comboBox.getSelectedItem().toString(); 
	    		   int tot = Integer.parseInt(total.getText());
	    		   int prc = Integer.parseInt(price.getText());
					File source = new File(filelabel.getText());
					int ind = filelabel.getText().lastIndexOf("\\");
					String filename = filelabel.getText().substring(ind+1); 
					 File dest =new File(System.getProperty("user.dir")+"\\src\\Images\\" + filename);
					try {
						copyFile(source,dest);
					} catch (Exception exe) {
						exe.printStackTrace();
					}
					//fileurl = System.getProperty("user.dir")+"\\src\\Images\\"+filename;
					fileurl = filename;
					System.out.println(fileurl);
	    		   Product prod  = new Product(nm,ty,tot,prc,fileurl);
	    		   database dbms = new database();
	    		   if(dbms.addToProducts(prod)) {
						JOptionPane.showMessageDialog(fields,"Added!!");
	    		   } else 
	    			   JOptionPane.showMessageDialog(fields, "Error", "Cannot upload", JOptionPane.ERROR_MESSAGE);
	    		   }
	       });

		
		
		fields.add(nm);
		fields.add(types);
		fields.add(pri);
		fields.add(submit);
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

