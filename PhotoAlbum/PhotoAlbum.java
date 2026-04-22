import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple Swing-based photo album viewer.
 * Loads images from a local folder and lets the user cycle through them
 * using Back and Next buttons.
 */
public class PhotoAlbum implements ActionListener
{
    JFrame frame;
    JPanel panel;
    JLabel label;         // Displays the current image
    JButton nextButton;
    JButton backButton;

    int currentIndex = 0; // Index of the currently displayed image

    List<String> image = new ArrayList<>(); // File paths of all loaded images

    public PhotoAlbum()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with padding around the edges
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Scan the photoAlbum folder and populate the image list
        loadImageFromFolder("photoAlbum");

        // Label used to render the image (or an error message if none found)
        label = new JLabel();
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        if (image.isEmpty())
        {
            // Inform the user if no supported images were found
            label.setText("No images found. Please add images to the folder 'photoAlbum'.");
        }
        else
        {
            // Show the first image immediately
            updateImage();
        }
        panel.add(label, BorderLayout.CENTER);

        // Button panel sits at the bottom of the window
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
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    /**
     * Handles Back and Next button clicks.
     * Wraps around at both ends of the image list using modular arithmetic.
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        String eventName = event.getActionCommand();

        // Nothing to navigate if the folder was empty
        if (image.isEmpty()) return;

        if (eventName.equals("next"))
        {
            // Advance forward; wrap to 0 after the last image
            currentIndex = (currentIndex + 1) % image.size();
        }
        else if (eventName.equals("back"))
        {
            // Step backward; wrap to the last image from index 0
            currentIndex = (currentIndex - 1 + image.size()) % image.size();
        }

        updateImage(); // Refresh the label with the new image
    }

    /**
     * Scans the given folder path for JPG and PNG images and stores their
     * absolute paths in the image list. Creates the folder if it doesn't exist.
     *
     * @param path relative or absolute path to the image folder
     */
    private void loadImageFromFolder(String path)
    {
        File file = new File(path);

        // Create the directory if it is missing so the app doesn't crash
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

                // Only include common image formats
                if (name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png"))
                {
                    image.add(f.getAbsolutePath());
                }
            }
        }
    }

    /**
     * Loads the image at currentIndex, scales it to fit the window,
     * and sets it on the label.
     */
    private void updateImage()
    {
        ImageIcon icon = new ImageIcon(image.get(currentIndex));

        // Scale the image to fill most of the window while keeping it smooth
        Image scaledImage = icon.getImage().getScaledInstance(
            frame.getWidth() - 100,
            frame.getHeight() - 150,
            Image.SCALE_SMOOTH
        );

        label.setIcon(new ImageIcon(scaledImage));
    }

    /**
     * Applies the system look-and-feel decoration and launches the album.
     * Called on the Event Dispatch Thread via SwingUtilities.
     */
    private static void runGUI()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new PhotoAlbum();
    }

    public static void main(String[] args)
    {
        // Swing UIs must be created on the Event Dispatch Thread
        SwingUtilities.invokeLater(PhotoAlbum::runGUI);
    }
}
