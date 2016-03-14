/**
     * Checks nearby cells for if reproduction conditions are met.
     * @param neighborC the amount of same type neighbors required
     * @param emptyC the amount of empty cells required
     * @param neighbors the amount of same type neighbors required
     * @param food the amount of same type neighbors required
     * @return boolean true if conditions are met and false if not
     */
    protected boolean checkNeighbors(int neighborC, int emptyC, int foods,
            Inhabitant type, Edible edible) {
        final int three = 3;
        int neighbors = 0;
        int empty = 0;
        int food = 0;
        final Cell[][] cells = cell.getAdjacentCells();
        
        for (int row = 0; row < three; row++) {
            for (int col = 0; col < three; col++) {
                if (cells[row][col] != null) {
                    if (cells[row][col].getInhabitant() == null) {
                        empty++;
                    } else if (cells[row][col].getInhabitant() 
                            instanceof type) {
                        neighbors++;
                    } else if (cells[row][col].getInhabitant() 
                            instanceof Plant) {
                        neighbors++;
                    }
                }
            }
        }
        if (neighbors >= neighborC && empty >= emptyC) {
            return true;
        } else {
            return false;
        }        
    }