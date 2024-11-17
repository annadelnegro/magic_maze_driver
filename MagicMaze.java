
/** Anna Del Negro
 *  COP3503 Computer Science 2
 *  Programming Assignment 2
 *  Spring 2024
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagicMaze {
    /* 2d array to store maze read from file */
    private char[][] maze;

    /* visited slots in maze aray */
    private boolean[][] visited;

    /* portals */
    private Map<Character, List<int[]>> magicalSquares;
    private int rs, cls; /* row2s & col2s */

    public MagicMaze(String maze_file, int rs, int cls) {
        this.rs = rs;
        this.cls = cls;
        this.maze = new char[rs][cls];
        this.visited = new boolean[rs][cls];
        this.magicalSquares = new HashMap<>();

        /* read file and store in buffer reader */
        try (BufferedReader buffer_reader = new BufferedReader(new FileReader(maze_file))) {

            int row2 = 0;
            String line_reader;

            /* read while file is not null & we get to end */
            while ((line_reader = buffer_reader.readLine()) != null && row2 < rs) {
                /* iterating over each character in the current line of the maze */
                for (int col2 = 0; col2 < line_reader.length() && col2 < cls; col2++) {
                    maze[row2][col2] = line_reader.charAt(col2); /* passing character in maze */
                    /* check if character of maze is digit */
                    if (Character.isDigit(maze[row2][col2])) {
                        magicalSquares.computeIfAbsent(maze[row2][col2], k -> new ArrayList<>())
                                .add(new int[] { row2, col2 });
                    }
                    /*
                     * ^^ if digit => use magicalSquares to store position
                     * else => store arrayList (ints)
                     */
                }
                row2++;
            }
            /* error handling */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* initialize at bottom left corner of magic maze per instructions */
    public boolean solveMagicMaze() {
        return solution_maze(rs - 1, 0);
    }

    /* row2 & col2 = currently being examinated in method */
    private boolean solution_maze(int row2, int col2) {
        /*
         * checking if positions are invalid, if:
         * 1. negative indexes
         * 2. curr outside lower bound
         * 3. if "@" (wall)
         * 4. if it has already been visited
         */
        if (row2 < 0 || col2 < 0 || row2 >= rs || col2 >= cls || maze[row2][col2] == '@' || visited[row2][col2]) {
            return false;
        }
        /* checking if we're at the exit of the maze, in which case we return true */
        if (maze[row2][col2] == 'X') {
            return true;
        }
        /* setting visited array to true */
        visited[row2][col2] = true;

        /* checking if current square is a portal for teleport */
        if (Character.isDigit(maze[row2][col2])) {
            /*
             * if curr square is teleport -> refer to magic squares array for (r,c) pair to
             * teleport to
             */
            List<int[]> positions = magicalSquares.get(maze[row2][col2]);

            /* iterating through every pair of points (for-each loop) */
            for (int[] pos : positions) {
                /*
                 * ensuring not teleporting back to same position we're in
                 * & also avoids revisiting squares already explored
                 */
                if ((pos[0] != row2 || pos[1] != col2) && !visited[pos[0]][pos[1]]) {
                    /* if we're good = recursively solves & finds path to exit */
                    if (solution_maze(pos[0], pos[1]))
                        return true;
                }
            }
        }

        /*
         * checks for recursive calls (up, down, left, right)
         * if any of these true, we return boolean true
         */
        if (solution_maze(row2 - 1, col2) || solution_maze(row2 + 1, col2)
                || solution_maze(row2, col2 - 1) || solution_maze(row2, col2 + 1)) {
            return true;
        }

        /*
         * if no solutions are found, we backtrack & then return false indicating last
         * recursive call generated no solutions
         */
        visited[row2][col2] = false;
        return false;
    }

}
