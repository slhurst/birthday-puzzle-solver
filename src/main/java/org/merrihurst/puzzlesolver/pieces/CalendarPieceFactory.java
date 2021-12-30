package org.merrihurst.puzzlesolver.pieces;

import org.merrihurst.puzzlesolver.Cell;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import static org.merrihurst.puzzlesolver.pieces.PieceType.*;

public class CalendarPieceFactory implements PieceFactory {
    private static final Set<Cell> squareLCells = Set.of(new Cell(0, 0), new Cell(0, 1), new Cell(0, 2),
            new Cell(1, 2), new Cell(2, 2));
    private static final Set<Cell> uCells = Set.of(new Cell(0, 0), new Cell(0, 1), new Cell(1, 1),
            new Cell(2, 1), new Cell(2, 0));
    private static final Set<Cell> longLCells = Set.of(new Cell(0, 0), new Cell(1, 0), new Cell(2, 0),
            new Cell(3, 0), new Cell(0, 1));
    private static final Set<Cell> wormCells = Set.of(new Cell(0, 0), new Cell(0, 1), new Cell(0, 2),
            new Cell(1, 2), new Cell(1, 3));
    private static final Set<Cell> notchedRectangleCells = Set.of(new Cell(0, 0), new Cell(1, 0), new Cell(0, 1),
            new Cell(1, 1), new Cell(1, 2));
    private static final Set<Cell> notchCells = Set.of(new Cell(0, 0), new Cell(-1, 1), new Cell(0, 1),
            new Cell(0, 2), new Cell(0, 3));
    private static final Set<Cell> rectangleCells = Set.of(new Cell(0,0), new Cell(1,0), new Cell(2,0),
            new Cell(0,1), new Cell(1,1), new Cell(2,1));
    private static final Set<Cell> sCells = Set.of(new Cell(0, 0), new Cell(1, 0), new Cell(1, 1),
            new Cell(1, 2), new Cell(2, 2));

    private static final EnumMap<PieceType, Set<Cell>> typeToBaseCells = new EnumMap<>(Map.of(SquareL, squareLCells, U, uCells, LongL, longLCells, Worm, wormCells, NotchedRectangle, notchedRectangleCells, Notch, notchCells, Rectangle, rectangleCells, S, sCells));

    public final EnumMap<PieceType, Set<Piece>> getAllPieces() {
        return this.getAllPieces(typeToBaseCells);
    }

    @Override
    public Set<Cell> getInitiallyOccupiedCells() {
        return Set.of();
    }
}
