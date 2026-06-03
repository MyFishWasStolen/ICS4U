//Programmer: Mathew Sinoj
//Description: Maze solver
//Date Created: May 2, 2026
//Date Revised: May 21, 2026
package Recursion;

//solves the maze
public class MazeApp
{

    // set the size of the maze
    private static final int MAZE_ROWS = 5; // Example: 5 rows
    private static final int MAZE_COLS = 5; // Example: 5 columns

    //set the file name
    private static final String MAZE_FILENAME = "maze.txt";


    public static void main(String[] args)
    {

        //initialize the maze loader
        MazeLoader loader = new MazeLoader(MAZE_ROWS, MAZE_COLS);

        //load the maze from the file
        char[][] mazeData = loader.loadMaze(MAZE_FILENAME);

        // Check if the maze data was loaded successfully
        if (mazeData == null)
        {

            System.out.println("Error loading Maze file");
            return; // Exit if loading failed.

        }

        // Print the loaded maze.
        System.out.println("Loaded Maze (" + MAZE_ROWS + "x" + MAZE_COLS + "):");
        printMaze(mazeData);
        System.out.println("--------------------");

        //initialize the maze solver
        MazeSolver solver = new MazeSolver(mazeData, MAZE_ROWS, MAZE_COLS);

        // Attempt to solve the maze
        boolean solved = solver.solve();

        //out the result of solving
        if (solved)
        {

            System.out.println("Maze solved successfully!");

        }

        else
        {

            System.out.println("No path found for the maze.");

        }

        // Print the path coordinates
        solver.printPathReport();

    }

   //print maze data
    private static void printMaze(char[][] maze)
    {
        //return if not Maze data
        if (maze == null)
        {

            System.out.println("Maze data is null.");
            return;

        }

        //go their each character
        for (int i = 0; i < maze.length; i++)
        {

            for (int j = 0; j < maze[i].length; j++)
            {

                System.out.print(maze[i][j] + " ");

            }
            System.out.println();

        }

    }

}
