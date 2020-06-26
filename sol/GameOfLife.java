/*******************************************************************************
 * Copyright 2020 Michael S. Yao
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

public class GameOfLife {
    /* Matrix to keep track of the state of the board. */
    private int[][] boardState;

    /* Integer to keep track of the dimension n of the nxn board. */
    private int dim;
	
    public GameOfLife(int size, String[] initial) {
        this.boardState = new int[size][size];

        int counter = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.boardState[i][j] = Integer.parseInt(initial[counter]);
                counter++;
            }
        }

        this.dim = size;
    }

    /* Function to count the number of alive neighbors of boardStat[i][j]. */
    private int countNeighbors(int i, int j) {
        int res = 0;

        /* Handle the corners of the board. */
        if (i == 0 && j == 0) {
            res += (this.boardState[0][1] + this.boardState[1][0] + 
                    this.boardState[1][1]);
        }
        else if (i == 0 && j == this.dim - 1) {
            res += (this.boardState[0][j-1] + this.boardState[1][j] + 
                    this.boardState[1][j-1]);
        }
        else if (i == this.dim - 1 && j == 0) {
            res += (this.boardState[i-1][0] + this.boardState[i][1] + 
                    this.boardState[i-1][1]);
        }
        else if (i == this.dim - 1 && j == this.dim - 1) {
            res += (this.boardState[i-1][j] + this.boardState[i][j-1] + 
                    this.boardState[i-1][j-1]);
        }
        /* Handle the edges of the board. */
        else if (i == 0) {
            res += (this.boardState[0][j-1] + this.boardState[1][j-1] + 
                    this.boardState[1][j] + this.boardState[1][j+1] + 
                    this.boardState[0][j+1]);
        }
        else if (i == this.dim - 1) {
            res += (this.boardState[i][j-1] + this.boardState[i-1][j-1] + 
                    this.boardState[i-1][j] + this.boardState[i-1][j+1] + 
                    this.boardState[i][j+1]);
        }
        else if (j == 0 ) {
            res += (this.boardState[i-1][0] + this.boardState[i-1][1] + 
                    this.boardState[i][1] + this.boardState[i+1][1] + 
                    this.boardState[i+1][0]);
        }
        else if (j == this.dim - 1) {
            res += (this.boardState[i-1][j] + this.boardState[i-1][j-1] + 
                    this.boardState[i][j-1] + this.boardState[i+1][j-1] + 
                    this.boardState[i+1][j]);
        }
		/* Handle all of the other cases, meaning the middle squares. */
        else {
            res += (this.boardState[i-1][j-1] + this.boardState[i-1][j] + 
                    this.boardState[i-1][j+1] + this.boardState[i][j-1] + 
                    this.boardState[i][j+1] + this.boardState[i+1][j-1] + 
                    this.boardState[i+1][j] + this.boardState[i+1][j+1]);
        }

        return res;
    }

    /* Iterate over a round according to the rules of the Game of Life. */
    public void round() {
        int[][] newBoardState = new int[this.dim][this.dim];

        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                /* Handle the case where the cell is alive. */
                if (this.boardState[i][j] == 1) {
                    /* 
                     * Any live cell with 0 or 1 live neighbors becomes dead,
                     * because of under-population.
                     */
                    if (this.countNeighbors(i, j) <= 1) {
                        newBoardState[i][j] = 0;
                    }
                    /* 
                     * Any live cell with more than 3 live neighbors becomes
                     * dead, because of over-population.
                     */
                    else if (this.countNeighbors(i, j) > 3) {
                        newBoardState[i][j] = 0;
                        }
                    /* 
                     * Any live cell with 2 or 3 live neighbors stays alive,
                     * because its neighborhood is just right.
                     */
                    else {
                        newBoardState[i][j] = 1;
                    }
                }
                /* Handle the case where the cell is dead. */
                else {
                    /* 
                     * Any dead cell with exactly 3 live neighbors becomes
                     * alive, by reproduction.
                     */
                    if (this.countNeighbors(i, j) == 3) {
                        newBoardState[i][j] = 1;
                    }
                    /* Otherwise, the cell just remains dead. */
                    else {
                        newBoardState[i][j] = 0;
                    }
                }
            }
        }

        this.boardState = newBoardState;
    }

    /* Print out the current state of the board in HTML format. */
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                if (this.boardState[i][j] == 1) {
                    res.append("*");
                }
                else {
                    res.append(".");
                }
            }

            res.append("<br>");
        }

        return res.toString();
    }
}