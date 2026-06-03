// Package declaration for the SlideLoader class
package Recursion;

// Import java packages
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//import and convert text file into 2d array
public class SlideLoader
{
    // store the dimensions of the slide
    private int rows;
    private int cols;

    //initialize the dimension of the slide
    public SlideLoader(int rows, int cols)
    {

        this.rows = rows;
        this.cols = cols;
    }


    //load specific file from directory and convert to 2d array
    public int[][] loadSlide(String filename)
    {

        // Initialize the 2D array to store the slide data
        int[][] slide = new int[rows][cols];

        try
        {
            // Create a Scanner to read from the specified file
            Scanner fileInput = new Scanner(new File(filename));

            // go through each row of the slide
            for (int i = 0; i < rows; i++) {

                // Check if there is another line to read in the file and read it
                if (fileInput.hasNext()) {

                    String line = fileInput.next();

                    //go through each column of the slide
                    for (int j = 0; j < cols; j++) {

                        //store each value color value
                        String singleDigit = line.substring(j, j + 1);

                        // Convert the digit character to an integer and store it in the slide array
                        slide[i][j] = Integer.parseInt(singleDigit);

                    }

                }

            }

            // Close the scanner
            fileInput.close();

            // Return the populated slide array
            return slide;

        }

        catch (FileNotFoundException e)
        {

            // Handle the case where the specified file does not exist
            System.out.println("File not found: " + filename);
            return null; // Return null to show loading failed

        }

        catch (NumberFormatException e)
        {

            // Handle the case where the file contains non-digit characters that cannot be converted
            System.out.println("File contains invalid characters");
            return null; // Return null to show loading failed

        }

    }

}
