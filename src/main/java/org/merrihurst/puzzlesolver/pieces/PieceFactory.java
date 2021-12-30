package org.merrihurst.puzzlesolver.pieces;

import org.apache.catalina.util.ParameterMap;
import org.merrihurst.puzzlesolver.Cell;

import java.util.*;
import java.util.stream.Collectors;



public interface PieceFactory {
    default EnumMap<PieceType, Set<Piece>> getAllPieces(EnumMap<PieceType, Set<Cell>> typeToBaseCells) {
        EnumMap<PieceType, Set<Piece>> allPiecesByType = new EnumMap<>(PieceType.class);

        for (Map.Entry<PieceType, Set<Cell>> e : typeToBaseCells.entrySet()) {
            PieceType type = e.getKey();
            Piece piece = new Piece(e.getKey().name(), e.getKey(), e.getValue());
            Set<Piece> allPieces = new HashSet<>();
            allPieces.add(piece);
//            allPieces.add(reflectInX(piece));
//            allPieces.add(reflectInY(piece));
//            allPieces.add(rotate90(piece));
//            allPieces.add(reflectInX(rotate90(piece)));
//            allPieces.add(reflectInY(rotate90(piece)));
//            allPieces.add(rotate180(piece));
//            allPieces.add(reflectInX(rotate180(piece)));
//            allPieces.add(reflectInY(rotate180(piece)));
//            allPieces.add(rotate270(piece));
//            allPieces.add(reflectInX(rotate270(piece)));
//            allPieces.add(reflectInY(rotate270(piece)));
            allPiecesByType.put(type, allPieces);
        }
        return allPiecesByType;
    }

    //TODO: something here doesn't work properly with Notch piece
    private static Piece rotate90(Piece piece) {
        return new Piece(piece.getName()+"rotate90", piece.getType(), rotate90(piece.getCells()));
    }

    private static Piece rotate180(Piece piece) {
        return new Piece(piece.getName()+"rotate180", piece.getType(), rotate90(rotate90(piece.getCells())));
    }

    private static Piece rotate270(Piece piece) {
        return new Piece(piece.getName()+"rotate270", piece.getType(), rotate90(rotate90(rotate90(piece.getCells()))));
    }

    private static Set<Cell> rotate90(Set<Cell> cells) {
        return normalize(cells.stream().map(c -> new Cell(c.getY(), -c.getX())).collect(Collectors.toSet()));
    }

    private static Piece reflectInX(Piece piece) {
        return new Piece(piece.getName()+"reflectInX", piece.getType(), reflectInX(piece.getCells()));
    }

    private static Set<Cell> reflectInX(Set<Cell> cells) {
        return normalize(cells.stream().map(c -> new Cell(c.getX(), -c.getY())).collect(Collectors.toSet()));
    }

    private static Piece reflectInY(Piece piece) {
        return new Piece(piece.getName()+"reflectInY", piece.getType(), reflectInY(piece.getCells()));
    }

    private static Set<Cell> reflectInY(Set<Cell> cells) {
        return normalize(cells.stream().map(c -> new Cell(-c.getX(), c.getY())).collect(Collectors.toSet()));
    }

    private static Set<Cell> normalize(Set<Cell> cells) {
        int minX = cells.stream().min(Comparator.comparing(Cell::getX)).get().getX();
        int minY = cells.stream().min(Comparator.comparing(Cell::getY)).get().getY();
        return cells.stream().map(c -> new Cell(applyOffset(c.getX(), minX), applyOffset(c.getY(), minY))).collect(Collectors.toSet());
    }

    private static int applyOffset(int original, int offset) {
        return original + Math.abs(Math.min(offset, 0));
    }

    EnumMap<PieceType, Set<Piece>> getAllPieces();
    Set<Cell> getInitiallyOccupiedCells();
}
