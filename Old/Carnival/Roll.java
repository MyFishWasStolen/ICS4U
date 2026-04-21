/*
 * Roll.java from Chapter 11
 * Displays a die face that corresponds to a roll.
 * Lawrenceville Press
 * June 10, 2005
 */
 
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  
import java.util.Random;      

public class Roll implements ActionListener {
	JFrame frame;
	JPanel contentPane;
	JButton rollDie;
	JLabel dieFace;
	
    public Roll(){
        /* Create and set up the frame */
        frame = new JFrame("Roll");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Create a content pane with a BoxLayout and empty borders */
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(Color.white);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        
		/* Create a label that shows a die face */
	    dieFace = new JLabel(new ImageIcon("die3.gif"));
	    dieFace.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	    dieFace.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));	//add space below
	    contentPane.add(dieFace);
	    
	    /* Create a Roll Die button */
	    rollDie = new JButton("Roll Die");
	    rollDie.setAlignmentX(JButton.CENTER_ALIGNMENT);
	    rollDie.addActionListener(this);
	    contentPane.add(rollDie);
	    
		/* Add content pane to frame */
        frame.setContentPane(contentPane);

        /* Size and then display the frame. */
        frame.pack();
        frame.setVisible(true);
    }
    
    
	/**
	 * Handle a the button click
	 * pre: none
	 * post: A message has been displayed.
	 */
    public void actionPerformed(ActionEvent event) {
    	Random rand = new Random();
    	int newRoll;
    	
    	newRoll = rand.nextInt(6) + 1;
    	if (newRoll == 1) {
    		dieFace.setIcon(new ImageIcon("die1.gif"));
    	} else if (newRoll == 2) {
    		dieFace.setIcon(new ImageIcon("die2.gif"));
    	} else if (newRoll == 3) {
    		dieFace.setIcon(new ImageIcon("die3.gif"));
    	} else if (newRoll == 4) {
    		dieFace.setIcon(new ImageIcon("die4.gif"));
    	} else if (newRoll == 5) {
    		dieFace.setIcon(new ImageIcon("die5.gif"));
    	} else if (newRoll== 6) {
    		dieFace.setIcon(new ImageIcon("die6.gif"));
    	}
        
    }


    /**
     * Create and show the GUI.
     */
    private static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        Roll luckyPlayer = new Roll();
    }
     

    public static void main(String[] args) {
        /* Methods that create and show a GUI should be 
           run from an event-dispatching thread */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runGUI();
            }
        });
    }
}
