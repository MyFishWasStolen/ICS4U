package Recursion;

//import packages
import java.util.ArrayList;

public class MazeSolver {

    //set up vraibles for the class
    private char[][] maze;
    private int rows;
    private int cols;
    private boolean[][] visited;
    
    // set up dynamic array to hold the coordinates
    private ArrayList<String> pathCoordinates;

    //construct the Maze solver with the overloaded data
    public MazeSolver(char[][] inputMaze, int rows, int cols)
    {

        // constructor initializes maze structure and tracking system
        this.rows = rows;
        this.cols = cols;
        this.maze = inputMaze;
        this.visited = new boolean[rows][cols];
        this.pathCoordinates = new ArrayList<>();

    }

    //finds solution to maze
    public boolean solve()
    {

        // starts the recursive search from the starting position
        return findPath(1, 1);

    }

   //finds path using recursion
    private boolean findPath(int r, int c)
    {

        //Out of bounds check
        if (r < 0 || r >= rows || c < 0 || c >= cols)
        {

            return false;

        }

        // obstetrical detection
        if (maze[r][c] == 'X' || visited[r][c])
        {

            return false;

        }

        // exit detection
        if (maze[r][c] == '$')
        {

            pathCoordinates.add("(" + (r + 1) + "," + (c + 1) + ")");
            return true;

        }

        // store the path that was taken
        visited[r][c] = true;
        pathCoordinates.add("(" + (r + 1) + "," + (c + 1) + ")");

        // check each direction from each point
        if (findPath(r, c + 1)) return true; // Right
        if (findPath(r + 1, c)) return true; // Down
        if (findPath(r, c - 1)) return true; // Left
        if (findPath(r - 1, c)) return true; // Up

        // remove coordinates for dead end
        pathCoordinates.removeLast();
        return false;

    }

    // outputs the final solved path
    public void printPathReport()
    {



        //print no paths when no solution was found
        if (pathCoordinates.isEmpty())
        {

            System.out.println("No path is available.");

        }

        else
        {

            System.out.print("Path:\t");

            //output the coordinates
            for (int i = 0; i < pathCoordinates.size(); i++)
            {

                System.out.print(pathCoordinates.get(i) + "   ");

                // formats output into rows of 6 entries
                if ((i + 1) % 6 == 0 && i != pathCoordinates.size() - 1)
                {

                    System.out.println();
                    System.out.print("\t");

                }

            }

            System.out.println();

        }

    }

}

