package org.merrihurst.puzzlesolver;

import org.merrihurst.puzzlesolver.pieces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;

import static org.merrihurst.puzzlesolver.pieces.PieceType.*;

@Component
public class SolverWrapper {
    private static final Logger logger = LoggerFactory.getLogger(SolverWrapper.class);

    public void testyMcTestFace() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        Set<Set<Piece>> setsOfPieces = getSetsOfPieces();

        Map<UnorderedPair<String>, Board> results = new ConcurrentHashMap<>(1024);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Set<Board>>> futures = new ArrayList<>();

        for (Set<Piece> setOfPieces : setsOfPieces) {
//            executorService.execute(new Solver(setOfPieces));
            Future<Set<Board>> future = executorService.submit((Callable<Set<Board>>) new Solver(setOfPieces));
            futures.add(future);
        }
        for (Future<Set<Board>> future : futures) {
            for (Board solvedBoard : future.get()) {
                if (solvedBoard.getUnoccupiedValues().size() != 2) {
                    logger.error("Solver returned >2 unoccupied {}", solvedBoard.getUnoccupiedCells());
                    throw new RuntimeException("Not a real solution");
                }
                UnorderedPair<String> pair = getPairFromBoard(solvedBoard);
//                logger.info("Found a result for {}", pair);
                Board put = results.put(pair, solvedBoard);
//                if (put != null) {
//                    logger.info("Interesting, we have multiple results for {}", pair);
//                }
            }
        }
        long end = System.currentTimeMillis();
        logger.info("Took {}ms in total", end - start);

        for (Map.Entry<UnorderedPair<String>, Board> unorderedPairBoardEntry : results.entrySet()) {
            logger.info("{}: {}", unorderedPairBoardEntry.getKey(), unorderedPairBoardEntry.getValue());
        }
        executorService.shutdown();
        System.exit(0);
    }

    public static UnorderedPair<String> getPairFromBoard(Board solvedBoard) {
        Iterator<String> iterator = solvedBoard.getUnoccupiedValues().iterator();
        String first = iterator.next();
        String second = iterator.next();
        return new UnorderedPair<>(first, second);
    }

    private static Set<Set<Piece>> getSetsOfPieces() {
        EnumMap<PieceType, Set<Piece>> allPieces = PieceFactory.getAllPieces();
        Set<Set<Piece>> allSetsOfPieces = new HashSet<>();
        for (Piece notchPiece : allPieces.get(Notch)) {
            for (Piece notchedRectanglePiece : allPieces.get(NotchedRectangle)) {
                for (Piece wormPiece : allPieces.get(Worm)) {
                    for (Piece longLPiece : allPieces.get(LongL)) {
                        for (Piece squareLPiece : allPieces.get(SquareL)) {
                            for (Piece uPiece : allPieces.get(U)) {
                                for (Piece sPiece : allPieces.get(S)) {
                                    for (Piece rectanglePiece : allPieces.get(Rectangle)) {
                                        allSetsOfPieces.add(Set.of(squareLPiece, uPiece, sPiece, notchPiece, rectanglePiece, notchedRectanglePiece, wormPiece, longLPiece));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

//        for (Set<Piece> setOfPieces : allSetsOfPieces) {
//            permutations(new HashSet<>(setOfPieces), new Stack<>(), setOfPieces.size(), allPermutations);
//        }

        return allSetsOfPieces;
    }

    public static void permutations(Set<Piece> items, Stack<Piece> permutation, int size, Set<List<Piece>> masterList) {

        /* permutation stack has become equal to size that we require */
        if (permutation.size() == size) {
            /* print the permutation */
            masterList.add(new ArrayList<>(permutation));
        }

        /* items available for permutation */
        Piece[] availableItems = items.toArray(new Piece[0]);
        for (Piece i : availableItems) {
            /* add current item */
            permutation.push(i);

            /* remove item from available item set */
            items.remove(i);

            /* pass it on for next permutation */
            permutations(items, permutation, size, masterList);

            /* pop and put the removed item back */
            items.add(permutation.pop());
        }
    }

    /**
     * This class stolen from Stack Overflow: https://stackoverflow.com/a/55797156
     *
     * @param <T>
     */
    private static final class UnorderedPair<T> {
        final T first, second;

        public UnorderedPair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof UnorderedPair))
                return false;
            UnorderedPair<T> up = (UnorderedPair<T>) o;
            return (up.first == this.first && up.second == this.second) ||
                    (up.first == this.second && up.second == this.first);
        }

        @Override
        public int hashCode() {
            int hashFirst = first.hashCode();
            int hashSecond = second.hashCode();
            int maxHash = Math.max(hashFirst, hashSecond);
            int minHash = Math.min(hashFirst, hashSecond);
            return Objects.hash(minHash, maxHash);
        }

        @Override
        public String toString() {
            return "[" + first +
                    ", " + second +
                    ']';
        }
    }

    private static <E> E getRandomElement(Set<? extends E> set) {

        Random random = new Random();

        // Generate a random number using nextInt
        // method of the Random class.
        int randomNumber = random.nextInt(set.size());

        Iterator<? extends E> iterator = set.iterator();

        int currentIndex = 0;
        E randomElement = null;

        // iterate the HashSet
        while (iterator.hasNext()) {

            randomElement = iterator.next();

            // if current index is equal to random number
            if (currentIndex == randomNumber)
                return randomElement;

            // increase the current index
            currentIndex++;
        }

        return randomElement;
    }

}
