package org.merrihurst.puzzlesolver;

import org.merrihurst.puzzlesolver.pieces.Piece;

import java.util.*;
import java.util.stream.Collectors;

public class Board {

    private static final Map<Cell, String> baseCells = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(new Cell(0, 0), "Jan"), new AbstractMap.SimpleEntry<>(new Cell(1, 0), "Feb"), new AbstractMap.SimpleEntry<>(new Cell(2, 0), "Mar"),
            new AbstractMap.SimpleEntry<>(new Cell(3, 0), "Apr"), new AbstractMap.SimpleEntry<>(new Cell(4, 0), "May"), new AbstractMap.SimpleEntry<>(new Cell(5, 0), "Jun"),
            new AbstractMap.SimpleEntry<>(new Cell(0, 1), "Jul"), new AbstractMap.SimpleEntry<>(new Cell(1, 1), "Aug"), new AbstractMap.SimpleEntry<>(new Cell(2, 1), "Sep"),
            new AbstractMap.SimpleEntry<>(new Cell(3, 1), "Oct"), new AbstractMap.SimpleEntry<>(new Cell(4, 1), "Nov"), new AbstractMap.SimpleEntry<>(new Cell(5, 1), "Dec"),
            new AbstractMap.SimpleEntry<>(new Cell(0, 2), "1"), new AbstractMap.SimpleEntry<>(new Cell(1, 2), "2"), new AbstractMap.SimpleEntry<>(new Cell(2, 2), "3"),
            new AbstractMap.SimpleEntry<>(new Cell(3, 2), "4"), new AbstractMap.SimpleEntry<>(new Cell(4, 2), "5"), new AbstractMap.SimpleEntry<>(new Cell(5, 2), "6"),
            new AbstractMap.SimpleEntry<>(new Cell(6, 2), "7"), new AbstractMap.SimpleEntry<>(new Cell(0, 3), "8"), new AbstractMap.SimpleEntry<>(new Cell(1, 3), "9"),
            new AbstractMap.SimpleEntry<>(new Cell(2, 3), "10"), new AbstractMap.SimpleEntry<>(new Cell(3, 3), "11"), new AbstractMap.SimpleEntry<>(new Cell(4, 3), "12"),
            new AbstractMap.SimpleEntry<>(new Cell(5, 3), "13"), new AbstractMap.SimpleEntry<>(new Cell(6, 3), "14"), new AbstractMap.SimpleEntry<>(new Cell(0, 4), "15"),
            new AbstractMap.SimpleEntry<>(new Cell(1, 4), "16"), new AbstractMap.SimpleEntry<>(new Cell(2, 4), "17"), new AbstractMap.SimpleEntry<>(new Cell(3, 4), "18"),
            new AbstractMap.SimpleEntry<>(new Cell(4, 4), "19"), new AbstractMap.SimpleEntry<>(new Cell(5, 4), "20"), new AbstractMap.SimpleEntry<>(new Cell(6, 4), "21"),
            new AbstractMap.SimpleEntry<>(new Cell(0, 5), "22"), new AbstractMap.SimpleEntry<>(new Cell(1, 5), "23"), new AbstractMap.SimpleEntry<>(new Cell(2, 5), "24"),
            new AbstractMap.SimpleEntry<>(new Cell(3, 5), "25"), new AbstractMap.SimpleEntry<>(new Cell(4, 5), "26"), new AbstractMap.SimpleEntry<>(new Cell(5, 5), "27"),
            new AbstractMap.SimpleEntry<>(new Cell(6, 5), "28"),
            new AbstractMap.SimpleEntry<>(new Cell(0, 6), "29"), new AbstractMap.SimpleEntry<>(new Cell(1, 6), "30"), new AbstractMap.SimpleEntry<>(new Cell(2, 6), "31"));

    private final Set<Cell> unoccupiedCells;
    private final Map<Cell, String> placedPieces = new HashMap<>();

    public Board() {
        unoccupiedCells = new TreeSet<>(Comparator.comparing(Cell::getY).thenComparing(Cell::getX));
        unoccupiedCells.addAll(baseCells.keySet());
    }

    public boolean attemptToPlaceInCell(Piece piece, Cell candidateCell) {
        for (Cell cell : piece.getCells()) {
            if (!unoccupiedCells.contains(cell.getCellOffsetBy(candidateCell))) {
                return false;
            }
        }
        piece.getCells().forEach(c -> unoccupiedCells.remove(c.getCellOffsetBy(candidateCell)));
        placedPieces.put(candidateCell, piece.getName());

        return true;
    }

    public Set<Cell> getUnoccupiedCells() {
        return unoccupiedCells;
    }

    public Set<String> getUnoccupiedValues() {
        return unoccupiedCells.stream().map(baseCells::get).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "Board{" +
                "placedPieces=" + placedPieces +
                "}";
    }
}
