import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;


// getters and constructors are
// automatically generated by the annotations

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SpringArray {

    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String OPEN_BRACE = "{";
    private static final String CLOSE_BRACE = "}";
    private static final List<String> OPENERS = List.of(OPEN_BRACE, OPEN_BRACKET);
    private static final List<String> CLOSERS = List.of(CLOSE_BRACE, CLOSE_BRACKET);

    private static int currentIndex;
    private static List<Spring> springList;

    public static Spring equivalentSpring(String springExpr) {
        springList = new ArrayList<>();
        currentIndex = 0;
        ArrayList<List<Integer>> indexPairs = getIndexesInPairs(springExpr);
        return getResultingSpring(springExpr, indexPairs);
    }

    public static Spring equivalentSpring(String springExpr, Spring[] springs) {
        springList = Arrays.stream(springs).collect(Collectors.toList());
        currentIndex = 0;
        ArrayList<List<Integer>> indexPairs = getIndexesInPairs(springExpr);
        return getResultingSpring(springExpr, indexPairs);
    }

    // utility methods

    private static ArrayList<List<Integer>> getIndexesInPairs(String springExpr) {
        ArrayList<List<Integer>> indexPairs = new ArrayList<>();

        for (int i = 0; i < springExpr.length(); i++) {
            if (OPENERS.contains(Character.toString(springExpr.charAt(i)))) {
                indexPairs.add(List.of(i, findClosingCharIndex(springExpr, i)));
            }
        }
        return indexPairs;
    }

    private static int findClosingCharIndex(String springExpr, int index) {

        String current = Character.toString(springExpr.charAt(index));
        String opener = current.equals(OPEN_BRACE) ? OPEN_BRACE : OPEN_BRACKET;
        String closer = current.equals(OPEN_BRACE) ? CLOSE_BRACE : CLOSE_BRACKET;

        Queue<String> charQueue = new ArrayDeque<>();
        charQueue.add(current);

        for (int i = index + 1; i < springExpr.length(); i++) {
            String currentChar = Character.toString(springExpr.charAt(i));

            if (currentChar.equals(closer)) {
                charQueue.poll();
            } else if (currentChar.equals(opener)) {
                charQueue.add(currentChar);
            }
            if (charQueue.size() == 0) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid Spring Expression");
    }

    private static Spring getResultingSpring(String springExpr, ArrayList<List<Integer>> indexes) {
        return getResultingSpring(springExpr, indexes, 0, springExpr.length());
    }

    private static Spring getResultingSpring(String springExpr, ArrayList<List<Integer>> indexes, int start, int end) {

        if(start == end - 1) {
            if(springList.size() == 0) {
                return new Spring();
            } else {
                return springList.get(currentIndex++);
            }
        }

        ArrayList<Spring> subSprings = new ArrayList<>();
        ArrayList<List<Integer>> filteredIndexParis = indexes.stream().filter(c -> c.get(0) > start
                && c.get(1) < end).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<List<Integer>> subIndexPairs = new ArrayList<>();
        int i = 0;

        while (i < filteredIndexParis.size()) {
            List<Integer> index = filteredIndexParis.get(i);
            subIndexPairs.add(index);
                i = findIndex(filteredIndexParis, index);
            if (i == -1) break;
        }

        for (List<Integer> subIndexPair : subIndexPairs) {
            subSprings.add(getResultingSpring(springExpr, indexes, subIndexPair.get(0), subIndexPair.get(1)));
        }

        Spring spring = subSprings.get(0);
        if (Character.toString(springExpr.charAt(start)).equals(OPEN_BRACE)) {
            for (int j = 1; j < subSprings.size(); j++) {
                spring = spring.inSeries(subSprings.get(j));
            }
        } else {
            for (int j = 1; j < subSprings.size(); j++) {
                spring = spring.inParallel(subSprings.get(j));
            }
        }

        return spring;
    }

    private static int findIndex(ArrayList<List<Integer>> filteredIndexPairs,  List<Integer> index) {
        for (int i = 0; i < filteredIndexPairs.size(); i++) {
            if(filteredIndexPairs.get(i).get(0) == index.get(1) + 1) {
                return i;
            }
        }
        return -1;
    }
}
