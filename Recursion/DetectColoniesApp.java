package Recursion;
//Programmer: Mathew Sinoj
//Description: Detect Colonies for size color and location
//Date Created: May 2, 2026
//Date Revised: May 21, 2026

public class DetectColoniesApp
{

    // Define the dimensions of the slide
    private static final int SLIDE_ROWS = 6;
    private static final int SLIDE_COLS = 8;

    // Define the filename for the slide data
    private static final String SLIDE_FILENAME = "slide.txt";

    //main method to run program
    public static void main(String[] args)
    {
        // Load the slide data
        SlideLoader loader = new SlideLoader(SLIDE_ROWS, SLIDE_COLS);

        // Load the slide data from the file
        int[][] slideData = loader.loadSlide(SLIDE_FILENAME);

        // Check if the slide data was loaded successfully
        if (slideData == null)
        {
            System.out.println("Application terminated due to slide loading error.");
            return; // Exit if loading failed.

        }

        // Analyze and report colonies
        ColonyAnalyzer analyzer = new ColonyAnalyzer(slideData, SLIDE_ROWS, SLIDE_COLS);

        // Call the reportColonies method to find and print all colonies
        analyzer.reportColonies();

    }

}