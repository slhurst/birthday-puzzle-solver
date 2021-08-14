package org.merrihurst.puzzlesolver;

import java.util.Set;

public class Solution {
    private final Set<String> visibleSquares;
    private final Board completedBoard;

    public Solution(Set<String> visibleSquares, Board completedBoard) {
        this.visibleSquares = visibleSquares;
        this.completedBoard = completedBoard;
    }
}
