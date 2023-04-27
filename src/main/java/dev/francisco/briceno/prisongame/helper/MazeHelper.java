package dev.francisco.briceno.prisongame.helper;


public class MazeHelper {

    /**
     Checks whether the player can escape from the prison.
     @param prisonMap an array of strings representing the prison map.
     @return true if the player can escape, false otherwise.
     */
    public static boolean canEscape(String[] prisonMap) {
        int numRows = prisonMap.length;
        int numCols = prisonMap[0].length();
        boolean[][] visitedCells = new boolean[numRows][numCols];

        int startX = -1;
        int startY = -1;

        // Look for the initial position of player
        for (int row = 0; row < numRows && startX < 0; row++) {
            for (int col = 0; col < numCols && startY < 0; col++) {
                if (prisonMap[row].charAt(col) == 'P') {
                    startX = row;
                    startY = col;
                }
            }
        }

        if (startX < 0 || startY < 0) {
            // if initial position was not found, then there is not escaping
            return false;
        }

        // Buscar un camino para escapar
        return searchForEscape(prisonMap, startX, startY, visitedCells);
    }

    /**
     Searches for a path to escape from the current position in the prison.
     @param prisonMap an array of strings representing the prison map.
     @param currentX the current position on the X axis.
     @param currentY the current position on the Y axis.
     @param visitedCells a boolean array indicating the visited cells.
     @return true if a path to escape was found, false otherwise.
     */
    private static boolean searchForEscape(String[] prisonMap, int currentX, int currentY, boolean[][] visitedCells) {
        int numRows = prisonMap.length;
        int numCols = prisonMap[0].length();

        if (currentX < 0 || currentX >= numRows || currentY < 0 || currentY >= numCols
                || "|><^v".contains(Character.toString(prisonMap[currentX].charAt(currentY))) || visitedCells[currentX][currentY]) {
            return false;
        }

        if (prisonMap[currentX].charAt(currentY) == 'S') {
            return true;
        }

        visitedCells[currentX][currentY] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direction : directions) {
            int nextX = currentX + direction[0];
            int nextY = currentY + direction[1];

            if (direction[0] == -1 && isGuardVisible(prisonMap, currentX - 1, currentY, 1, 0)) continue;
            if (direction[0] == 1 && isGuardVisible(prisonMap, currentX + 1, currentY, -1, 0)) continue;
            if (direction[1] == -1 && isGuardVisible(prisonMap, currentX, currentY - 1, 0, 1)) continue;
            if (direction[1] == 1 && isGuardVisible(prisonMap, currentX, currentY + 1, 0, -1)) continue;

            if (searchForEscape(prisonMap, nextX, nextY, visitedCells)) {
                return true;
            }
        }

        return false;
    }

    /**

     Verifies if a guard can see the player from its position in the prison.
     @param prison an array of strings representing the prison map.
     @param guardRow the row position of the guard.
     @param guardCol the column position of the guard.
     @param rowIncrement the row increment direction to look for the player.
     @param colIncrement the column increment direction to look for the player.
     @return true if the guard can see the player, false otherwise.
     */
    private static boolean isGuardVisible(String[] prison, int guardRow, int guardCol, int rowIncrement, int colIncrement) {
        int numRows = prison.length;
        int numCols = prison[0].length();

        int currentRow = guardRow;
        int currentCol = guardCol;

        while (currentRow >= 0 && currentRow < numRows && currentCol >= 0 && currentCol < numCols) {
            char currentCell = prison[currentRow].charAt(currentCol);

            if (currentCell == '|') {
                return false;
            }

            if (currentCell == '>' && rowIncrement == 0 && colIncrement == 1) {
                return true;
            }

            if (currentCell == '<' && rowIncrement == 0 && colIncrement == -1) {
                return true;
            }

            if (currentCell == '^' && rowIncrement == -1 && colIncrement == 0) {
                return true;
            }

            if (currentCell == 'v' && rowIncrement == 1 && colIncrement == 0) {
                return true;
            }

            currentRow += rowIncrement;
            currentCol += colIncrement;
        }

        return false;
        }
    }
