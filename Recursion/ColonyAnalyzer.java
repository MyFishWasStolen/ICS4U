// Package declaration for the ColonyAnalyzer class
package Recursion;

public class ColonyAnalyzer
{

    //store the slide data in a 2d array
    private int[][] slide;
    // store the number of rows
    private int rows;
    // store the number of columns
    private int cols;

    //initialize Colony Analyser with the above values

    public ColonyAnalyzer(int[][] slide, int rows, int cols)
    {

        this.slide = slide;
        this.rows = rows;
        this.cols = cols;

    }


    //output the results of the search
    public void reportColonies()
    {

        // Print the header for the output table.
        System.out.println("Colour\tSize\tLocation");

        // go over each cell in the grid
        for (int r = 0; r < rows; r++)
        {

            for (int c = 0; c < cols; c++)
            {

                // Check if the current cell's color is not 0
                if (slide[r][c] != 0)
                {

                    // Store the color of the current colony.
                    int targetColor = slide[r][c];
                    // Store the starting location (1-based for reporting).
                    int startRow = r + 1;
                    int startCol = c + 1;

                    // Measure the size of the colony starting at (r, c) and erase it by setting its cells to 0.
                    int totalSize = measureAndErase(r, c, targetColor);

                    // Print the details of the detected colony.
                    System.out.println(targetColor + "\t\t" + totalSize + "\t\t" + startRow + ", " + startCol);

                }

            }

        }

    }

    //recursively measure the size of the colonies on each side of the initial cell
    private int measureAndErase(int r, int c, int color)
    {

        // check if the cell is outside the gird and if the color matches
        if (r < 0 || r >= rows || c < 0 || c >= cols || slide[r][c] != color)
        {

            return 0; // the cell doesn't count towards the calculation

        }

        // erase the current cell so it doesn't get recounted
        slide[r][c] = 0;

        // Initialize the size of the colony found from this cell to 1 (for the current cell itself).
        int size = 1;

        // Recursively explore all four directions from the first cell (up, down, left, right).
        // Add the size returned by each recursive call to the current colony's size.
        size += measureAndErase(r - 1, c, color); // Explore up
        size += measureAndErase(r + 1, c, color); // Explore down
        size += measureAndErase(r, c + 1, color); // Explore right
        size += measureAndErase(r, c - 1, color); // Explore left

        // Return the total size of the colony connected to this cell.
        return size;
    }

}
