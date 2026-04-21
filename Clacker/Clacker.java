//Programmer: Mathew Sinoj
//Description: Clacker game - 12 numbered buttons (1-12) are displayed. The player rolls
//             two dice and may cover the number representing the total or the two
//             individual dice values. The goal is to cover all 12 numbers in the fewest rolls.
//Date Created: April 19, 2026
//Date Revised: April 19, 2026

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Clacker

{

	public static void main(String[] args)

	{

		final Random rand = new Random();
		final boolean[] covered = new boolean[12];
		final int[] rollCount = {0}; //array used so the value can be changed inside the listener

		//Create and set up the frame
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Clacker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create a content pane with a BoxLayout and empty borders
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setBackground(Color.white);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

		//Create a 2 row by 6 column grid of number buttons labeled 1 to 12
		final JButton[] numberButtons = new JButton[12];
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
		final JLabel die1Label = new JLabel(new ImageIcon("die1.gif"));
		die1Label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		die1Label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		contentPane.add(die1Label);

		//Create a label that shows the second die image
		final JLabel die2Label = new JLabel(new ImageIcon("die1.gif"));
		die2Label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		die2Label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		contentPane.add(die2Label);

		//Create a label that displays the number of rolls taken
		final JLabel rollCountLabel = new JLabel("Rolls: 0");
		rollCountLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		rollCountLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		contentPane.add(rollCountLabel);

		//Create a status label that guides the player and displays the win message
		final JLabel statusLabel = new JLabel("Press Roll to start!");
		statusLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		contentPane.add(statusLabel);

		//Create a Roll button
		JButton rollButton = new JButton("Roll");
		rollButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		rollButton.setActionCommand("Roll");
		contentPane.add(rollButton);

		//Create a New Game button
		JButton newGameButton = new JButton("New Game");
		newGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		newGameButton.setActionCommand("NewGame");
		contentPane.add(newGameButton);

		//Add action listeners to the Roll button and New Game button
		rollButton.addActionListener(new ActionListener()
		{

			//Handle Roll button click
			//Pre: none
			//Post: Both dice have been rolled, images updated, and roll count incremented
			public void actionPerformed(ActionEvent event)
			{

				int die1Value = rand.nextInt(6) + 1;
				int die2Value = rand.nextInt(6) + 1;
				rollCount[0]++;

				die1Label.setIcon(new ImageIcon("die" + die1Value + ".gif"));
				die2Label.setIcon(new ImageIcon("die" + die2Value + ".gif"));
				rollCountLabel.setText("Rolls: " + rollCount[0]);
				statusLabel.setText("Cover " + die1Value + " + " + die2Value + " = " + (die1Value + die2Value));

			}

		});

		newGameButton.addActionListener(new ActionListener()
		{

			//Handle New Game button click
			//Pre: none
			//Post: All game state has been reset for a fresh game
			public void actionPerformed(ActionEvent event)
			{

				rollCount[0] = 0;
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

		});

		//Add action listeners to each number button
		for (int i = 0; i < 12; i++)
		{

			final int num = i + 1; //must be final to use inside the listener

			numberButtons[i].addActionListener(new ActionListener()
			{

				//Handle number button click
				//Pre: none
				//Post: The number button has been covered and win condition checked
				public void actionPerformed(ActionEvent event)
				{

					covered[num - 1] = true;
					numberButtons[num - 1].setText("");

					//check if all 12 numbers are covered
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

						statusLabel.setText("You win! Covered in " + rollCount[0] + " rolls!");

					}

				}

			});

		}

		//Add content pane to frame
		frame.setContentPane(contentPane);

		//Size and then display the frame
		frame.pack();
		frame.setVisible(true);

	}

}
