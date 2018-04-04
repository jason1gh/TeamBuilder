/**
 *  Copyright information...
 */
package com.willthan;

import java.util.ArrayList;

public class TeamBuilder {
    /**
     * A teambuilder class to calculates:
     *   1. number of vertices that can access all other vertices in a directional diagram
     *   2. number of vertices that can be accessed by all other vertices in a directional diagram
     * The code uses DFS to travel all paths to mark route between vertices
     * If a row has all routes to other vertices, this means the vertex can access all other vertices.
     * If a column has all routes to other vertices, this means the vertex can be accessed by all other vertices
     * Lastly tally up such rows and columns to solve the problem.
     *
     * @author Jason
     * @since 2018
     * */
    private int positions;
    private char[][] pathArray;
    private boolean[] visited;

    /**
     * Create a new array from the input paths to represent the diagram.
     * Call dfs to mark the routes between vertices.
     * tally up the count.
     *
     * @param paths  array of String represent edge between vertices with value of "1" and "0" for each edge.
     * @return results array of int.
     */
    public int[] specialLoations(String[] paths) {
        int[] results = { -1, -1 };

        /* Sanity check the input parameter */
        if (paths == null || paths.length < 1) {
            System.out.println("Invalid paths.");
            return results;
        }
        positions = paths.length;
        /* If we have an valid input, create the array to represent the diagram for travel */
        pathArray = new char[positions][positions];

        /**
         *  Change path string to N x N path array for DFS
         *  Also make sure the input parameter is NxN
         */
        visited = new boolean[positions];
        for (int i = 0; i < positions; i++) {
            pathArray[i] = paths[i].toCharArray();
            if (pathArray[i].length != positions) {
                System.out.print("Invalid paths.");
                return results;
            }
            visited[i] = false;
        }

        /**
         *  Start travel the map to find out all route between any two point
         *  Mark the location visited so that it will not be visited again.
         *  Start from position (0,0)
         */
        dfs(0);

        /* Find the location counts.
         *  As 1 in the array means there is a direct or indirect path from
         *  location a to location b.
         *  Any row with all 1's is a location can access all locations
         *  Any column with all 1's is a location can be access by all locations
         *
         *      1 1 1 1 1 1    <- Can access all locations
         *      0 1 0 1 0 1
         *      1 1 0 1 0 0
         *      1 1 1 1 1 1    <- Can access all locations
         *      0 1 1 1 1 0
         *      1 1 0 1 0 1
         *
         *        ^   ^
         *        This two locations can be accessed by all locations
         *
         *  The example has two locations can access all locations and
         *  has two locations can be accessed by all locations
         *
         */
        results[0] = 0;
        for (char[] pa : pathArray) {
            boolean canAccessAll = true;
            for (char pac : pa) {
                if (pac == '0') {
                    canAccessAll = false;
                    break;
                }
            }
            if (canAccessAll)
                results[0]++;
        }

        results[1] = 0;
        for (int i = 0; i < positions; i++) {
            boolean canBeAccessedByAll = true;
            for (int j = 0; j < positions; j++) {
                if (pathArray[j][i] == '0') {
                    canBeAccessedByAll = false;
                    break;
                }
            }
            if (canBeAccessedByAll)
                results[1]++;
        }

        return results;
    }

    /**
     * Doing DFS travel the graph using recursively for all column in a row.
     * When there is a path between vertex i, j, it means all routes that vertex j can access
     * vertex i can should be able to access. So copy all "1" from ROW(j) to ROW(i)
     * It also means, all route can access vertex i should be able to access vertext j.
     * So copy all "1" from COL(i) to COL(j).
     * Array visited use to mark the row has been visited before so that no need to
     * revisit to avoid infinite loop.
     *
     * @param row in the array to travel.
     *
     */
    private void dfs(int row) {
        visited[row] = true;
        for (int i = 0; i < positions; i++) {
            if (row == i)
                continue;
            if (pathArray[row][i] == '1') {
                copyRow(i, row);
                copyCol(row, i);

                // printArray();
                if (!visited[i]) {
                    dfs(i);
                }
            }
        }
    }

    /**
     * Copy all "1" from one row to another row
     * @param from source row.
     * @param to   destination row.
     */
    private void copyRow(int from, int to) {
        for (int i = 0; i < positions; i++) {
            if (pathArray[from][i] == '1')
                pathArray[to][i] = '1';
        }
    }

    /**
     * Copy all "1" from one column to anther column
     * @param from source column.
     * @param to   destination column.
     */
    private void copyCol(int from, int to) {
        for (int i = 0; i < positions; i++) {
            if (pathArray[i][from] == '1')
                pathArray[i][to] = '1';
        }
    }

    /**
     * For debug only. Print out the intermedia steps.
     */
    private void printArray() {
        for (char[] par : pathArray) {
            for (char parc : par) {
                System.out.print(String.valueOf(parc) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
