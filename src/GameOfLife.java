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
	
    /* 
     * Size is the dimensions of the nxn board. initial is an array
     * that represents the initial state of the board.
     */
    public GameOfLife(int size, String[] initial) {
        // TODO
    }

    /* Function to count the number of alive neighbors of boardStat[i][j]. */
    private int countNeighbors(int i, int j) {
        // TODO

        return -1;
    }

    /* Iterate over a round according to the rules of the Game of Life. */
    public void round() {
        // TODO	

        return;
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