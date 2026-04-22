//Programmer: Mathew Sinoj
//Description: Clacker game with GUI
//Date Created: April 19, 2026
//Date Revised: April 22, 2026

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Clacker
{

    private Random rand = new Random(); //create random object
    private boolean[] covered = new boolean[12];  //tracks which numbers have been covered
    private int rollCount = 0;                     //tracks the number of rolls taken

    private JButton[] numberButtons = new JButton[12];  //the 12 numbered buttons
    private JLabel die1Label;                            //displays the first die image
    private JLabel die2Label;                            //displays the second die image
    private JLabel rollCountLabel;                       //displays the current roll count
    private JLabel statusLabel;                          //displays game status and win message

    //Constructor: builds and displays the game window
    //Pre: none
    //Post: the game window is visible and ready to play
    public Clacker()
    {

        JFrame frame = new JFrame("Clacker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a content pane with a vertical BoxLayout and padding
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(Color.white);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        //Create a 2 row by 6 column grid of number buttons labeled 1 to 12
        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(2, 6, 5, 5));
        numberPanel.setBackground(Color.white);
        numberPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        for (int i = 0; i < 12; i++)
        {

            numberButtons[i] = new JButton(String.valueOf(i + 1));
            numberButtons[i].setActionCommand(String.valueOf(i + 1));
            numberPanel.add(numberButtons[i]);

        }

        contentPane.add(numberPanel);

        //Create a label that shows the first die image
        die1Label = new JLabel(new ImageIcon("die1.gif"));
        die1Label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        die1Label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        contentPane.add(die1Label);

        //Create a label that shows the second die image
        die2Label = new JLabel(new ImageIcon("die1.gif"));
        die2Label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        die2Label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        contentPane.add(die2Label);

        //Create a label that displays the number of rolls taken
        rollCountLabel = new JLabel("Rolls: 0");
        rollCountLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        rollCountLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        contentPane.add(rollCountLabel);

        //Create a status label that guides the player and displays the win message
        statusLabel = new JLabel("Press Roll to start!");
        statusLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        contentPane.add(statusLabel);

        //Create a Roll button and attach its listener
        JButton rollButton = new JButton("Roll");
        rollButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        rollButton.setActionCommand("Roll");
        rollButton.addActionListener(new ActionListener()
        {

            //Handle Roll button click
            //Pre: none
            //Post: handleRoll() is called to process the roll
            public void actionPerformed(ActionEvent event)
            {

                handleRoll();

            }

        });
        contentPane.add(rollButton);

        //Create a New Game button and attach its listener
        JButton newGameButton = new JButton("New Game");
        newGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        newGameButton.setActionCommand("NewGame");
        newGameButton.addActionListener(new ActionListener()
        {

            //Handle New Game button click
            //Pre: none
            //Post: handleNewGame() is called to reset the game
            public void actionPerformed(ActionEvent event)
            {

                handleNewGame();

            }

        });
        contentPane.add(newGameButton);

        //Add action listeners to each number button
        for (int i = 0; i < 12; i++)
        {

            final int num = i + 1;  //must be final to use inside the listener

            numberButtons[i].addActionListener(new ActionListener()
            {

                //Handle number button click
                //Pre: none
                //Post: handleNumberButton() is called with the button's number
                public void actionPerformed(ActionEvent event)
                {

                    handleNumberButton(num);

                }

            });

        }

        //Add content pane to frame
        frame.setContentPane(contentPane);

        //Size and then display the frame
        frame.pack();
        frame.setVisible(true);

    }

    //Handle Roll button click
    //Pre: none
    //Post: Both dice have been rolled, images updated, and roll count incremented
    private void handleRoll()
    {

        int die1Value = rand.nextInt(6) + 1;
        int die2Value = rand.nextInt(6) + 1;
        rollCount++;

        die1Label.setIcon(new ImageIcon("die" + die1Value + ".gif"));
        die2Label.setIcon(new ImageIcon("die" + die2Value + ".gif"));
        rollCountLabel.setText("Rolls: " + rollCount);
        statusLabel.setText("Cover " + die1Value + " + " + die2Value + " = " + (die1Value + die2Value));

    }

    //Handle New Game button click
    //Pre: none
    //Post: All game state has been reset for a fresh game
    private void handleNewGame()
    {

        rollCount = 0;
        rollCountLabel.setText("Rolls: 0");
        statusLabel.setText("Press Roll to start!");
        die1Label.setIcon(new ImageIcon("die1.gif"));
        die2Label.setIcon(new ImageIcon("die1.gif"));

        for (int i = 0; i < 12; i++)
        {

            covered[i] = false;
            numberButtons[i].setText(String.valueOf(i + 1));

        }

    }

    //Handle number button click
    //Pre: none
    //Post: The number button has been covered and win condition checked
    private void handleNumberButton(int num)
    {

        covered[num - 1] = true;
        numberButtons[num - 1].setText("");

        //Check if all 12 numbers are covered
        boolean allCovered = true;

        for (int i = 0; i < 12; i++)
        {

            if (!covered[i])
            {

                allCovered = false;

            }

        }

        if (allCovered)
        {

            statusLabel.setText("You win! Covered in " + rollCount + " rolls!");

        }

    }
	public static void runGUI()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Clacker();

	}

    //Launch the game
    //Pre: none
    //Post: a new Clacker instance is created and the game window is displayed
    public static void main(String[] args)
    {

        runGUI();

    }

}
