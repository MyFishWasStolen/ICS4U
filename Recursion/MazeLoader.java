package Recursion;

// Import packages
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MazeLoader
{

    //set the diemsions of the maze
    private int rows;
    private int cols;

    //construct the dimensions of the maze for the object
    public MazeLoader(int rows, int cols)
    {

        this.rows = rows;
        this.cols = cols;

    }

    //load the maze data
    public char[][] loadMaze(String filename)
    {

        // Initialize the 2D array to store the maze data
        char[][] maze = new char[rows][cols];

        try
        {

            // Create a Scanner to read from the specified file.
            Scanner fileInput = new Scanner(new File(filename));

            // go through each row of the maze
            for (int i = 0; i < rows; i++)
            {

                // Check if there is another line to read in the file
                if (fileInput.hasNextLine())
                {
                    //store the line
                    String line = fileInput.nextLine();

                    // Handle lines that are longer than the dimensions
                    if (line.length() > cols)
                    {

                        System.err.println("Error: Maze file has a line longer than expected at row " + (i + 1) + ". Expected " + cols + " characters, but found " + line.length() + " characters.");
                        fileInput.close();
                        return null; // Return null to indicate loading failure

                    }

                    // go through each character in the line
                    for (int j = 0; j < cols; j++)
                    {

                        if (j < line.length())
                        {

                            // Store each character directly into the maze array
                            maze[i][j] = line.charAt(j);

                        }

                        else
                        {

                            // If the line is shorter than 'cols', fill the rest with spaces
                            maze[i][j] = ' ';

                        }

                    }

                }

                else
                {
                    //report error when maze has fewer rows than dimensions
                    System.err.println("Error: Maze file has fewer rows than expected. Expected " + rows + ", but found " + i + " rows.");
                    fileInput.close();

                    return null; // Return null to indicate loading failure

                }

            }

            // Close the scanner
            fileInput.close();

            // Return maze array.
            return maze;

        }

        //when file is not found error handler
        catch (FileNotFoundException e)
        {

            // Handle the case where the specified file does not exist
            System.err.println("File not found: " + filename);
            return null; // Return null to indicate loading failure

        }

    }

}