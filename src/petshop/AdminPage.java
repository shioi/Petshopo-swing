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
	JTextField name, type,total,price;
	 JComboBox<String> cb;
	JLabel filelabel;
	String fileurl;
	AdminPage(){
		super();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setSize(600,700);
		Font ft = new Font("Serif",Font.PLAIN,26);
	    setLayout(new BorderLayout(80,80));
		JPanel head = new JPanel();
	    JLabel petshop= new JLabel("UPLOAD ITEMS");
	    petshop.setFont(ft);
	    head.add(petshop);
	    add(head,BorderLayout.NORTH);
	    JPanel fields = makeFields();
	    add(fields);
	    
	    //fields
	    setVisible(true);
	}
	
	JPanel makeFields() {
		JPanel fields = new JPanel();
		fields.setLayout(new BoxLayout(fields,BoxLayout.Y_AXIS));

		JPanel nm = new JPanel();
		nm.add(new JLabel("Product Name"));
		name = new JTextField(40);
		nm.add(name);
		
		JPanel types = new JPanel();
		types.add(new JLabel("Types"));
	    String[] choices = { "Pet Food", "Pet Accessories"};
	    cb = new JComboBox<String>(choices);
	    types.add(cb);
	    
	    types.add(new JLabel("Quantity"));
	    total = new JTextField(5);
	    types.add(total);
	    
		JPanel pri= new JPanel();
	    types.add(new JLabel("Price"));
	    price= new JTextField(5);
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
	    		   String ty =cb.getSelectedItem().toString(); 
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
