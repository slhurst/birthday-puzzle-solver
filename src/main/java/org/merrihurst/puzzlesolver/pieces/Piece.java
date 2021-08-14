package org.merrihurst.puzzlesolver.pieces;

import org.merrihurst.puzzlesolver.Cell;

import java.util.Objects;
import java.util.Set;

public class Piece {
    private final String name;
    private final PieceType type;
    private final Set<Cell> cells;

    public Piece(String name, PieceType type, Set<Cell> cells) {
        this.name = name;
        this.type = type;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public PieceType getType() {
        return type;
    }

    public Set<Cell> getCells() {
        return cells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(cells, piece.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", cells=" + cells +
                '}';
    }
}
