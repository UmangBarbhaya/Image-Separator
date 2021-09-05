import java.awt.*;
import java.awt.Font;;
import java.awt.event.*; 
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.*;  
import java.util.zip.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.*;


//Done
public class USeparator
{
	public static void main(String args[])
	{
		File file = new File("bin");
	 	file.setExecutable(true,false);
		file.setReadable(true,false);
		file.setWritable(true,false);
		if (!file.exists()) 
		{
    		file.mkdir();
		}
		
		File file1 = new File("images");
	 	file1.setExecutable(true,false);
		file1.setReadable(true,false);
		file1.setWritable(true,false);
		if (!file1.exists()) 
		{
    		file1.mkdir();
		}
		
		FirstPage fo=new FirstPage();
		fo.one();	
	}
}


//Done
class FirstPage
{
	static JFrame f=new JFrame("USeparator");
	public static void one()
	{
		
		JButton b1=new JButton("Join Images");
		b1.setBackground(Color.white);
		b1.addActionListener(new ActionListener()
		{
  			public void actionPerformed(ActionEvent e)
 			 {
 			 	
    			SavePage so=new SavePage();
    			f.dispose();
    			so.savejoin();
   				 
  			 }
		});
		
		
		JButton b2=new JButton("Separate Images");
		b2.setBackground(Color.white);
		b2.addActionListener(new ActionListener()
		{
  			public void actionPerformed(ActionEvent e)
 			 {
 			 	
    			
    			SeparatePage to=new SeparatePage();
    			f.dispose();
    			to.three();
   				 
  			 }
		});
		
		
		
		
		f.add(b1,BorderLayout.NORTH);
		b1.setPreferredSize(new Dimension(400, 250));
		f.add(b2,BorderLayout.SOUTH);
		b2.setPreferredSize(new Dimension(400, 250));
		Container c = f.getContentPane();
		c.setBackground(Color.black);
		f.setSize(500,500);
		f.setResizable(false);
		f.setVisible(true);
	}
}





///Joining page
class SavePage
{
	private static JFrame f=new JFrame("USeparator");
	private static JButton b1;
	public static String s="";
	private static JTextField t;
	private static JLabel lab3 = new JLabel("File already Exists*");
	void savejoin()
	{
		JLabel lab1 = new JLabel("ENTER PROJECT NAME:");
		 b1 = new JButton("NEXT");
		b1.setBounds(400,400, 70,30);
		b1.setEnabled(false);
		b1.setBackground(Color.white);
		
		t = new JTextField(1);
		//t.setPrompt("eg:-Example.jpg");
		t.setBounds(50,150, 380,30);
		
		lab1.setBounds(50,100, 380,30);
		t.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) 
			{
				changed();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				changed();
			}
			public void insertUpdate(DocumentEvent e) 
			{
				changed();
			}
			
			public void changed()
			{
				if (t.getText().equals(""))
				{
					b1.setEnabled(false);
				}
				else 
				{
					b1.setEnabled(true);
				}
			
			}
		});
		
		b1.addActionListener(new ActionListener()
		{
			
  			public void actionPerformed(ActionEvent e)
 			 {
 			 	try
 			 	{
	 			 	s=t.getText();
	 			 	File file = new File("bin\\"+s);
	 			 	File file1 = new File("bin\\"+s+".zip");
	 			 	file.setExecutable(true,false);
	 			 	file.setReadable(true,false);
	 			 	file.setWritable(true,false);
	        		if (!file.exists()&&!file1.exists()) 
	        		{
	            		if (file.mkdir()) 
	            		{
	                		
	            		} 
	            		else 
	            		{
	                		throw new Exception();
	            		}
	        		}
	        		else
	        		{
	        			
	        			throw new Exception();
	        		}
	 			 	
	 			 	
	    			SelectImages si=new SelectImages(s);
	    			
	    			f.dispose();
	    			si.select();
	   				 
	  			 }
	  			 catch(Exception pp)
	  			 {
	  			 	    
	        			lab3.setFont(new Font("Arial", Font.PLAIN, 10));
	        			lab3.setBounds(50,200,100,50);
	        			f.add(lab3);
	        			f.validate();
                    	f.repaint();
	  			 }
	  		}
	  			
		});
		f.add(t);
		f.add(b1);
		f.add(lab1);
		
		f.setLayout(null);
		
		f.setSize(500,500);
		f.setResizable(false);
		f.setVisible(true);
	}
		
		
}

class SelectImages
{
	public static int k=1;
	public static int i=250;
	public static int j=100;
	public static String s="";
	public static JButton browse=new JButton("+");
	public static JButton finish=new JButton("FINISH");
	SelectImages(String s)
	{
		this.s=s;
		
	}
	private static JFrame f;
	void select()
	{
		browse.setBackground(Color.white);
		browse.setFont(new Font("Arial", Font.PLAIN, 40));
		finish.setBackground(Color.white);
	    
	    JLabel lab2 = new JLabel("Browse/Search Images");
	    lab2.setFont(new Font("Arial", Font.PLAIN, 30));
	    
	    f=new JFrame(s);
		
		
		
		lab2.setBounds(200,0,700,100);
		browse.setBounds(150,100,100,150);
		finish.setBounds(600,600,70,30);
		
		
		browse.addActionListener(new ActionListener()
		{
  			public void actionPerformed(ActionEvent e)
 			 {
 			 	
 			 	try
 			 	{
	 			 	JFileChooser fileopen = new JFileChooser();
				    FileFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
				    fileopen.addChoosableFileFilter(filter);
				    int ret = fileopen.showDialog(null, "Open file");
				    if (ret == JFileChooser.APPROVE_OPTION) 
				    {
				    	File file = fileopen.getSelectedFile();
				    	String path = file.getAbsolutePath();					
						
						//Saving image to directory by renaming it
						
						BufferedImage image = ImageIO.read(new File(path)); 
						String pathname="bin\\"+s+"\\"+k+".png";
						ImageIO.write(image, "jpg",new File(pathname));
						k++;
						
						//Displaying
						ImageIcon icon = new ImageIcon(((new ImageIcon(path)).getImage()).getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH));
      					JLabel cl = new JLabel("", icon,JLabel.CENTER);
      					cl.setBounds(i-100,j,100,150);
      					f.add(cl);
  						if(i<500)
		 			 	{
		    				browse.setBounds(i,j,100,150);
		    				i=i+100;
		    				
		    			}
		    			else if(i>450)
		    			{
		    				i=150;
		    				j=j+150;
		    				browse.setBounds(i,j,100,150);
		    				i=i+100;
		    			}
		    			if(j>400)
		    			{
		    				browse.setVisible(false);
		    			}
						   
						   
						
	        	 	}
        	 	}
        	 	catch(Exception e11)
        	 	{
        	 		//System.out.println(e11);
        	 	}
 			 	
 			 
   				 
  			 }
		});
		
		finish.addActionListener(new ActionListener()
		{
  			public void actionPerformed(ActionEvent e)
 			 {
 			 	
    			JoinPage jp=new JoinPage(s);
    			
    			f.dispose();
    			jp.join();
   				 
  			 }
		});
	        
	    f.add(finish);
	    f.add(browse);
		f.add(lab2);
		f.setSize(700,700);
		f.setResizable(false);
		f.setLayout(null);
		f.setVisible(true);
		
	}
		
		
}


class JoinPage implements MouseMotionListener,MouseListener 
{
	public static HashMap <Object,String> hm = new HashMap<Object,String>();
	public static String s="";
	public static JPanel ppp;
	public static JInternalFrame ifr;
	public static JInternalFrame ifr1;
	public static int um=0;
    public static int mu=0;
    public static JButton zoomin=new JButton("+");
    public static JButton zoomout=new JButton("-");
    public static JButton backky=new JButton("");
    public static int xaxis=100;
    public static int yaxis=150;
    public static ImageIcon ic;
    public static ImageIcon ic1;
    public static JLabel labelll;
    JoinPage()
	{
	
		   
          
	}
	JoinPage(String s)
	{
		this.s=s;
		   
          
	}
	private static JFrame f;
	
	void join()
	{
		zoomin.setBackground(Color.white);
	
		zoomout.setBackground(Color.white);
		backky.setBackground(Color.black);
		
		f=new JFrame(s);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
      	JMenu aboutMenu = new JMenu("About");
      	aboutMenu.setActionCommand("About");
      	
      	JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newMenuItem.setActionCommand("New");
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setActionCommand("Save");
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setActionCommand("Exit");
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		exitMenuItem.setActionCommand("About");
		
		
		aboutMenuItem.addActionListener(new ActionListener()
		{
  			public void actionPerformed(ActionEvent e)
 			 {
 			 	
 			 	JOptionPane.showMessageDialog(null, "Umang Barbhaya\nStudent, KJ Somaiya College Of Engineering.", "Created By", 1);
   				 
  			 }
		});
		newMenuItem.addActionListener(new ActionListener()
		{
  			public void actionPerformed(ActionEvent e)
 			 {
 			 	
 			 	deleteFilesFolder();
 			 	FirstPage fo=new FirstPage();
				fo.one();
				f.dispose();
    			
    			
  			 }
		});
		saveMenuItem.addActionListener(new ActionListener()
		{
  			public void actionPerformed(ActionEvent e)
 			 {
 			 	final JPanel panel = new JPanel();
     			final DefaultComboBoxModel fruitsName = new DefaultComboBoxModel();

				fruitsName.addElement("jpeg");
				fruitsName.addElement("png");
				fruitsName.addElement("bmp");
			
				
				final JComboBox fruitCombo = new JComboBox(fruitsName);    
				fruitCombo.setSelectedIndex(0);
				
				JScrollPane fruitListScrollPane = new JScrollPane(fruitCombo);    
				
				
			
				panel.add(fruitListScrollPane);          
				
			int input = JOptionPane.showOptionDialog(null, panel, "Extensions", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				
				if(input == JOptionPane.OK_OPTION)
				{
				    String data = "";
				    if (fruitCombo.getSelectedIndex() != -1) {                     
				       data = ""+fruitCombo.getItemAt(fruitCombo.getSelectedIndex());             
				    }              
				     BufferedImage bufImage = new BufferedImage(ppp.getSize().width, ppp.getSize().height,BufferedImage.TYPE_INT_RGB);
	       			ppp.paint(bufImage.createGraphics());
	       			File imageFile = new File("images\\"+s+"."+data);
	       			
	    			try{
	    				if(imageFile.exists())
	    				{
	        				imageFile.delete();
	        			}
	        			
	        			imageFile.createNewFile();
	        			ImageIO.write(bufImage, data, imageFile);
	    			}
	    			catch(Exception ex)
	    			{
	    			} 
				}
				
				   
				   
				
				 			 
  			 }
		});
		exitMenuItem.addActionListener(new ActionListener()
		{
  			public void actionPerformed(ActionEvent e)
 			 {
 			 	deleteFilesFolder();
    			f.dispose();
    		
   				 
  			 }
		});
		
		
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(exitMenuItem); 
		aboutMenu.add(aboutMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu); 
		///////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		f.addWindowListener(new java.awt.event.WindowAdapter() {
    		public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        		deleteFilesFolder();
				
			}
		});
	
	
		f.setLayout(new BorderLayout());
		
        
        //gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        
        
        ppp=new JPanel();
        ppp.addMouseListener(this);
        ppp.addMouseMotionListener(this);
        ppp.setLayout(null);
        ppp.setBackground(Color.black);
        
        f.add(ppp,BorderLayout.CENTER);
        
        
        ////Reading file and saving it into internal frame
        ifr=new JInternalFrame("Images");
        File folder = new File("bin\\"+s);
		File[] listOfFiles = folder.listFiles();
		
		ifr.setLayout(null);
		
		ImageIcon iconn = new ImageIcon("brickback,jpg");
      	//JLabel cccc = new JLabel("", iconn,JLabel.CENTER);
      	//ifr.add(cccc);
		ifr.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc1.insets = new Insets(5,5,5,5);
        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.weightx = 0.25;
        gbc1.weighty = 0.15;
        int j=0;
        int k=0;
    	for (int i = 0; i < listOfFiles.length; i++) 
    	{
      		ImageIcon icon = new ImageIcon(((new ImageIcon("bin\\"+s+"\\"+listOfFiles[i].getName())).getImage()).getScaledInstance(80, 120, java.awt.Image.SCALE_SMOOTH));
      		JLabel cl = new JLabel("", icon,JLabel.CENTER);
      		hm.put(cl,"bin\\"+s+"\\"+listOfFiles[i].getName());
      		if(j==2)
      		{
      			j=0;
      			k++;      			
      		}
      		gbc1.gridx = j;
        	gbc1.gridy = k;
      		ifr.add(cl,gbc1);
      		j++;
      		
      		
      		cl.addMouseListener(new MouseListener()
			{
				
               
				public void mouseClicked(MouseEvent e) 
				{  
					
				}  
			    public void mouseEntered(MouseEvent e) 
			    {  
			          
			    }  
			    public void mouseExited(MouseEvent e) 
			    {  
			          
			    }  
			    public void mousePressed(MouseEvent e) 
			    {  
			   		labelll = (JLabel) e.getComponent();
					
					//ic=(ImageIcon)labelll.getIcon();
			    	if(labelll.getParent()==ppp)
			    	{	
			    		
			    		xaxis=labelll.getWidth();
			    		yaxis=labelll.getHeight();
			    		
			    		
			    		zoomin.addActionListener(new ActionListener()
						{
				  			public void actionPerformed(ActionEvent e)
				 			 {
				 			 	String location=hm.get(labelll);
				    			xaxis=xaxis+10;
    							yaxis=yaxis+10;
    							ic1 = new ImageIcon((new ImageIcon(location).getImage()).getScaledInstance(xaxis, yaxis, java.awt.Image.SCALE_SMOOTH));
           	           			
           	           			labelll.setIcon(ic1);
           	           			int xpos=labelll.getX();
           	           			int ypos=labelll.getY();
           	           			labelll.setBounds(xpos,ypos,xaxis,yaxis);
							  }
						});
						
						zoomout.addActionListener(new ActionListener()
						{
				  			public void actionPerformed(ActionEvent e)
				 			 {
				 			 	if(xaxis>10&&yaxis>10)
				 			 	{
				 			 		String location=hm.get(labelll);
				 			 		xaxis=xaxis-10;
    								yaxis=yaxis-10;
    								ic1 = new ImageIcon((new ImageIcon(location).getImage()).getScaledInstance(xaxis, yaxis, java.awt.Image.SCALE_SMOOTH));
    							
           	           				labelll.setIcon(ic1);
           	           				int xpos=labelll.getX();
           	           				int ypos=labelll.getY();
           	           				labelll.setBounds(xpos,ypos,xaxis,yaxis);
           	           			}
				    			
				  			 }
						});
			    		
			          	f.repaint();
					}
					else
					{
						Random r =new Random();
						int xpos=r.nextInt(500);
						int ypos=r.nextInt(500);
						labelll.setBounds(xpos,ypos,80,120);
						ppp.add(labelll);
						f.repaint();
						
					}

			    	
			        
			    }  
			    public void mouseReleased(MouseEvent e) 
			    {  
			        
  
			    }
			    
 
			    				
			});
      		      		
    	}
        
        
        ifr.pack();
        ifr.setVisible(true);
        
        f.add(ifr, BorderLayout.EAST);
        
        
		
		ifr1=new JInternalFrame("Tools");
		
		ifr1.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc2.fill = GridBagConstraints.VERTICAL;
        gbc2.weighty=0.01;
        gbc2.gridy = 0;
		gbc2.gridx=0;
		
		ifr1.add(zoomin,gbc2);
		gbc2.gridy = 0;
		gbc2.gridx=1;
		
		ifr1.add(zoomout,gbc2);
		gbc2.gridy = 0;
		gbc2.gridx=2;
		
		ifr1.add(backky,gbc2);
		ifr1.pack();
        ifr1.setVisible(true);
		f.add(ifr1,BorderLayout.SOUTH);
		
		backky.addActionListener(new ActionListener()
		{
  			public void actionPerformed(ActionEvent e)
 			 {
 			 	
    			Color c=JColorChooser.showDialog(null,"Choose",Color.BLACK);  
    			ppp.setBackground(c);
   				backky.setBackground(c); 
  			 }
		});
	
		
		f.pack();	
		f.setJMenuBar(menuBar);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setVisible(true);
		

	}
	
    
    //delete folder 
    
    public static void deleteFilesFolder()
    {
		try
		{
			File inFolder=new File("bin\\"+s);
			File outFolder=new File("bin\\"+s+".zip");
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data  = new byte[1000];
			String files[] = inFolder.list();
			for (int i=0; i<files.length; i++)
			{
				in = new BufferedInputStream(new FileInputStream(inFolder.getPath() + "/" + files[i]), 1000);  
				out.putNextEntry(new ZipEntry(files[i])); 
				int count;
				while((count = in.read(data,0,1000)) != -1)
				{
					out.write(data, 0, count);
				}
				out.closeEntry();
				in.close();
			}
			out.flush();
			out.close();
			deleteFolder(inFolder);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
    }
    
	public static void deleteFolder(File folder) 
	{
	    File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}
	
	public void mousePressed(MouseEvent e) 
	{    
		try
		{
			
	 		int x = labelll.getX() ;
        	int y = labelll.getY() ;
        	labelll.setLocation(x, y);
        	ppp.add(labelll);
        	f.repaint();
        	
        }
        catch(Exception ea)
        {
        }
	}  
	public void mouseReleased(MouseEvent e) 
	{ 
	 	int x = e.getX() ;
        int y = e.getY() ;
        labelll.setLocation(x, y);
        ppp.add(labelll);
        f.repaint();
	}
	public void mouseDragged(MouseEvent e) 
	{
    	
    	int x = e.getX() ;
        int y = e.getY() ;
        labelll.setLocation(x, y);
        ppp.add(labelll);
        f.repaint();
    }
	public void mouseClicked(MouseEvent e) 
	{  
		
	} 
	public void mouseMoved(MouseEvent e) 
	{
    } 
	public void mouseEntered(MouseEvent e) 
	{      
	}  
	public void mouseExited(MouseEvent e) 
	{    
	}  
	
	
	  	
		
}






//Separating page
class SeparatePage
{
	static int c=0;
	static BufferedImage image;
	private static JFrame f=new JFrame("USeparator");
	 
	public static void three()
	{
		JLabel lab2 = new JLabel("ENTER MIXED IMAGE");
	    lab2.setFont(new Font("Arial", Font.PLAIN, 15));
	    lab2.setBounds(75,30,300, 100);
		JButton browse=new JButton("Browse");
		browse.setBounds(100,200,100, 40);
		browse.setBackground(Color.white);
		browse.addActionListener(new ActionListener()
		{
  			public void actionPerformed(ActionEvent e)
 			 {
 			 	try
 			 	{
	 			 	JFileChooser fileopen = new JFileChooser();
				    FileFilter filter = new FileNameExtensionFilter("images", "jpg", "png", "bmp");
				    fileopen.addChoosableFileFilter(filter);
				    int ret = fileopen.showDialog(null, "Open file");
				    if (ret == JFileChooser.APPROVE_OPTION) 
				    {
				    	File file = fileopen.getSelectedFile();
				    	String path = file.getAbsolutePath();
				    	
				    	String[] parts = file.getName().split("\\.");
				    	
				    	
				    	//UnZipping
				    	
				    	
				    	unzip(parts[0]);
				    	
				    	
				    	JoinPage jp=new JoinPage(parts[0]);
    			
    					f.dispose();
    					jp.join();
				    	 
						
	        	 	}
        	 	}
        	 	catch(Exception e11)
        	 	{
        	 		JLabel lab3 = new JLabel("*Sorry! Image data not found");
	    			lab3.setFont(new Font("Arial", Font.PLAIN, 10));
	    			lab3.setBounds(0,60,300, 200);
	    			f.add(lab3);
	    			f.repaint();
        	 	}
        	}
    
  		});
  		f.add(lab2);
  		f.add(browse);
  		f.setSize(300,300);
  		f.setResizable(false);
  		f.setLayout(null);
  		f.setVisible(true);
	}
	public static void unzip(String name)throws IOException 
	{
		 File file = new File("bin\\"+name);
	 	file.setExecutable(true,false);
		file.setReadable(true,false);
		file.setWritable(true,false);
		if (!file.exists()) 
		{
    		file.mkdir();
		}
        String fileZip = "bin\\"+name+".zip";
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while(zipEntry != null){
            String fileName = zipEntry.getName();
            File newFile = new File("bin\\" + name+"\\"+fileName);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        
        File file1 = new File("bin\\"+name+".zip");
        file1.delete();
    
	}
}
