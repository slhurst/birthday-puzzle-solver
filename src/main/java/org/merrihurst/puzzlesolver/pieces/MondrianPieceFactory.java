package org.merrihurst.puzzlesolver.pieces;

import org.merrihurst.puzzlesolver.Cell;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import static org.merrihurst.puzzlesolver.pieces.PieceType.*;

public class MondrianPieceFactory implements PieceFactory {
    private static final Set<Cell> blueRectangle3long = Set.of(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0),
            new Cell(1, 1), new Cell(2, 0), new Cell(2, 1));
    private static final Set<Cell> blueRectangle4long = Set.of(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0),
            new Cell(1, 1), new Cell(2, 0), new Cell(2, 1), new Cell(3, 0), new Cell(3, 1));
    private static final Set<Cell> blueRectangle5long = Set.of(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0),
            new Cell(1, 1), new Cell(2, 0), new Cell(2, 1), new Cell(3, 0), new Cell(3, 1),
            new Cell(4, 0), new Cell(4, 1));
    private static final Set<Cell> smallYellowSquare = Set.of(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0),
            new Cell(1, 1));
    private static final Set<Cell> bigYellowSquare = Set.of(new Cell(0, 0), new Cell(0, 1), new Cell(0, 2),
            new Cell(1, 0), new Cell(1, 1), new Cell(1, 2), new Cell(2, 0), new Cell(2, 1), new Cell(2, 2));
    private static final Set<Cell> redOneByFour = Set.of(new Cell(0, 0), new Cell(1, 0), new Cell(2, 0),
            new Cell(3, 0));
    private static final Set<Cell> redOneByFive = Set.of(new Cell(0, 0), new Cell(1, 0), new Cell(2, 0),
            new Cell(3, 0), new Cell(4, 0));
    private static final Set<Cell> bigWhiteRectangle = Set.of(new Cell(0, 0), new Cell(1, 0), new Cell(2, 0), new Cell(3, 0),
            new Cell(0, 1), new Cell(1, 1), new Cell(2, 1), new Cell(3, 1),
            new Cell(0, 2), new Cell(1, 2), new Cell(2, 2), new Cell(3, 2));

    //TODO: sort out these map keys
    private static final EnumMap<PieceType, Set<Cell>> typeToBaseCells = new EnumMap<>(Map.of(SquareL, blueRectangle3long, U, blueRectangle4long, LongL, blueRectangle5long, Worm, smallYellowSquare, NotchedRectangle, bigYellowSquare, Notch, redOneByFour, Rectangle, redOneByFive, S, bigWhiteRectangle));

    public final EnumMap<PieceType, Set<Piece>> getAllPieces() {
        return this.getAllPieces(typeToBaseCells);
    }

    @Override
    public Set<Cell> getInitiallyOccupiedCells() {
        // TODO: make this configurable
        return Set.of(new Cell(3,1), new Cell(2,2), new Cell());
    }
}
