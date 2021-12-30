package org.merrihurst.puzzlesolver;

import org.merrihurst.puzzlesolver.pieces.Piece;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Callable;

public class Solver implements Callable<Set<Board>>, Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Solver.class);

    private final Set<Piece> pieces;
    private final Set<Cell> initiallyOccupiedCells;

    public Solver(Set<Piece> pieces, Set<Cell> initiallyOccupiedCells) {
        this.pieces = pieces;
        this.initiallyOccupiedCells = initiallyOccupiedCells;
    }

    public Set<Board> call() {
        Set<List<Piece>> permutations = new HashSet<>();
        SolverWrapper.permutations(new HashSet<>(pieces), new Stack<>(), pieces.size(), permutations);
        Set<Board> allBoards = new HashSet<>();
        for (List<Piece> permutation : permutations) {
            Board board = new Board(initiallyOccupiedCells);
            List<Piece> placedPieces = new ArrayList<>();
            for (Piece piece : permutation) {
                for (Cell candidateCell : board.getUnoccupiedCells()) {
                    if (board.attemptToPlaceInCell(piece, candidateCell)) {
                        placedPieces.add(piece);
                        logger.debug("Placed piece {} in cell {}", piece, candidateCell);
                        break;
                    }
                }
                if (!placedPieces.contains(piece)) {
                    logger.debug("Couldn't place piece {}", piece);
                    break;
                }
            }
            if (placedPieces.size() == pieces.size()) {
                allBoards.add(board);
                //TODO: does it make sense to break here? De-dupe solutions?
            }

        }
        return allBoards;
    }

    @Override
    public void run() {
        Set<List<Piece>> permutations = new HashSet<>();
        SolverWrapper.permutations(new HashSet<>(pieces), new Stack<>(), pieces.size(), permutations);
        for (List<Piece> permutation : permutations) {
            Board board = new Board(initiallyOccupiedCells);
            List<Piece> placedPieces = new ArrayList<>();
            for (Piece piece : permutation) {
                for (Cell candidateCell : board.getUnoccupiedCells()) {
                    if (board.attemptToPlaceInCell(piece, candidateCell)) {
                        placedPieces.add(piece);
                        logger.debug("Placed piece {} in cell {}", piece, candidateCell);
                        break;
                    }
                }
                if (!placedPieces.contains(piece)) {
                    logger.debug("Couldn't place piece {}", piece);
                    break;
                }
            }
            if (placedPieces.size() == pieces.size()) {
                logger.info("Solution: {}: {}", SolverWrapper.getPairFromBoard(board), board);
            }
        }

    }
}
