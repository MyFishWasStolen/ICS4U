import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoAlbum implements ActionListener
{
	JFrame frame;
	JPanel panel;
	JLabel label;
	JButton nextButton;
	JButton backButton;

	int currentIndex=0;

	List<String> image = new ArrayList<>();

	public PhotoAlbum()
	{

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		loadImageFromFolder("photoAlbum");

		label = new JLabel();
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

		if (image.isEmpty())
		{

			label.setText("No images found. Please add images to the folder 'photoAlbum'.");

		}
		else
		{

			updateImage();

		}
		panel.add(label, BorderLayout.CENTER);


		JPanel buttonPanel = new JPanel();

		backButton = new JButton("Back");
		backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		backButton.setActionCommand("back");
		backButton.addActionListener(this);
		buttonPanel.add(backButton);

		nextButton = new JButton("Next");
		nextButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		nextButton.setActionCommand("next");
		nextButton.addActionListener(this);
		buttonPanel.add(nextButton);


		panel.add(buttonPanel, BorderLayout.SOUTH);


		frame.setContentPane(panel);

		frame.setSize(800,600);
		frame.setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent event)
	{

		String eventName = event.getActionCommand();

		if(image.isEmpty()) return;

		if(eventName.equals("next"))
		{
			currentIndex = (currentIndex + 1) % image.size();
		}
		else if (eventName.equals("back"))
		{

			currentIndex = (currentIndex - 1 + image.size()) % image.size();

		}

		updateImage();//update image



	}

	private void loadImageFromFolder (String path)
	{
		File file = new File(path);
		if (!file.exists())
		{
			file.mkdir();
		}

		File[] files = file.listFiles();
		if (files != null)
		{
			for (File f : files)
			{
				String name = f.getName().toLowerCase();
				if(name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png"))
				{
					image.add(f.getAbsolutePath());
				}
			}

		}


	}


	private void updateImage()
	{
		ImageIcon icon = new ImageIcon(image.get(currentIndex));

		Image image = icon.getImage().getScaledInstance(frame.getWidth()-100, frame.getHeight()-150, Image.SCALE_SMOOTH);

		label.setIcon(new ImageIcon(image));


	}

	private static void runGUI()
	{

		JFrame.setDefaultLookAndFeelDecorated(true);
		new PhotoAlbum();


	}
	public static void main(String[] args)
	{
		runGUI();


	}
}