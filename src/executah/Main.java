package executah;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.profesorfalken.jpowershell.PowerShell;

public class Main {
	
	// Declare main window for universal scope
    public static JFrame mainFrame = new JFrame("MC3 Executah");
	
	public static void main(String[] args) {
		
		String os = System.getProperty("os.name");    	
    	if(! os.contains("Windows")) {
    		
    		JOptionPane.showMessageDialog(null, "MC3 Executah is only compatible with Windows right now.", "Error", JOptionPane.ERROR_MESSAGE);
    		System.exit(0);
    		
    	}
        
        // Display main image on frame
        JLabel dropHereImage = new JLabel("", SwingConstants.CENTER);
        // Credit to David Lanham for this image, who's work can be found here: https://www.dlanham.com/
        dropHereImage.setIcon(new ImageIcon(Main.class.getResource("/resources/drop-box.png")));
        
        // Makes frame clickable
        dropHereImage.addMouseListener(new MouseAdapter() {
        	
          public void mouseClicked(MouseEvent me) {
              
              infoDialog();
            
          }
          
        });
        
        // Add image to frame
        mainFrame.add(dropHereImage);

        // Set window properties and display it
        mainFrame.setResizable(false);
        mainFrame.setBounds(100, 100, 300, 225);
        mainFrame.setVisible(true);
        
        // Handle initialization of file drop class and keep it running so that it won't just work once per instance    
	    new FileDrop(mainFrame, new FileDrop.Listener() {
	    	
	    	public void filesDropped(java.io.File[] files) {
	    		
	    		for(int i = 0; i < files.length; i++) {

	    			// Fetches path to executable and launches it with Powershell API
	  				PowerShell.executeSingleCommand(files[i].getAbsolutePath()).getCommandOutput();
	    			
	    		}
	    		
	          }
	    	
	    });
		
	}
	
	public static void infoDialog() {
		
        // Declare info dialog
  	    JDialog infoDialog = new JDialog();
  	    
        // Declare and set layout manager
  	    SpringLayout infoDialogLayout = new SpringLayout();
  	    infoDialog.setLayout(infoDialogLayout);
        
  	    // Declare label as an empty box to hold image
  	    JLabel infoImage = new JLabel("");
  	    // Set info icon (This image is in the public domain)
        infoImage.setIcon(new ImageIcon(Main.class.getResource("/resources/information-icon.png")));
        // Add image to dialog
        infoDialog.add(infoImage);
        
        // Declare app label
        JLabel appTitle = new JLabel("MC3-Executah");
        // Make app label red
        appTitle.setForeground(Color.RED);
        // Add app label to dialog
        infoDialog.add(appTitle);
        
        // Declare step one label
        JLabel stepOneLabel = new JLabel("Step One:");
        // Add step one label to dialog
        infoDialog.add(stepOneLabel);
        
        // Declare step one
        JLabel stepOne = new JLabel("  Drag exe to box.");
        // Add step one to dialog
        infoDialog.add(stepOne);
        
        // Declare step two label
        JLabel stepTwoLabel = new JLabel("Step Two:");
        // Add step one label to dialog
        infoDialog.add(stepTwoLabel);
        
        // Declare step two
        JLabel stepTwo = new JLabel("  Profit!");
        // Add step two to dialog
        infoDialog.add(stepTwo);
        
        // Declare links
        JLabel link = new JLabel("<HTML><u>Source Code</u></HTML>");
        // Make link label blue
        link.setForeground(Color.BLUE);
        // Add link to dialog
        infoDialog.add(link);
        
        link.addMouseListener(new java.awt.event.MouseAdapter() {
        	
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	
                linkClicked(evt);
                
            }
            
        });
        
        // Layout management
        infoDialogLayout.putConstraint(SpringLayout.NORTH, infoImage, 10, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.WEST, infoImage, 50, SpringLayout.WEST, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.NORTH, appTitle, 75, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.WEST, appTitle, 30, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.NORTH, stepOneLabel, 100, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.WEST, stepOneLabel, 20, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.NORTH, stepOne, 125, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.WEST, stepOne, 20, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.NORTH, stepTwoLabel, 150, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.WEST, stepTwoLabel, 20, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.NORTH, stepTwo, 175, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.WEST, stepTwo, 20, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.NORTH, link, 200, SpringLayout.NORTH, infoDialog);
        infoDialogLayout.putConstraint(SpringLayout.WEST, link, 32, SpringLayout.NORTH, infoDialog);
        
        // Set info window properties and display it
        infoDialog.setResizable(false);
        infoDialog.setModal(true);
        infoDialog.setBounds(200, 200, 150, 250);
        infoDialog.setLocationRelativeTo(mainFrame);
        infoDialog.setVisible(true);
		
	}
	
    private static void linkClicked(java.awt.event.MouseEvent evt) {
    	
        try {
        	
            Desktop.getDesktop().browse(new URI("https://github.com/NotAlexNoyle/MC3-Executah"));
            
        }
        catch(Exception e) {
        	
        	JOptionPane.showMessageDialog(null, "ERROR: Link could not be opened.", "Error", JOptionPane.ERROR_MESSAGE);
        	
        }
        
    }

}
