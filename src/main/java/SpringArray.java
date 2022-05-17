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

    private static int currentIndex = 0;
    private static List<Spring> springsList;

    public static Spring equivalentSpring(String springExpr) {
        springsList = new ArrayList<>();
        ArrayList<List<Integer>> indexPairs = getIndexPairs(springExpr);
        return solve(springExpr, indexPairs);
    }

    public static Spring equivalentSpring(String springExpr, Spring[] springs) {
        springsList = Arrays.stream(springs).collect(Collectors.toList());
        ArrayList<List<Integer>> indexPairs = getIndexPairs(springExpr);
        return solve(springExpr, indexPairs);
    }

    protected static ArrayList<List<Integer>> getIndexPairs(String springExpr) {
        ArrayList<List<Integer>> indexes = new ArrayList<>();

        for (int i = 0; i < springExpr.length(); i++) {
            if (OPENERS.contains(Character.toString(springExpr.charAt(i)))) {
                indexes.add(List.of(i, getClosingIndex(springExpr, i)));
            }
        }
        return indexes;
    }

    private static int getClosingIndex(String springExpr, int index) {

        String current = Character.toString(springExpr.charAt(index));
        String opener = current.equals(OPEN_BRACE) ? OPEN_BRACE : OPEN_BRACKET;
        String closer = current.equals(OPEN_BRACE) ? CLOSE_BRACE : CLOSE_BRACKET;

        Queue<String> queue = new ArrayDeque<>();
        queue.add(current);

        for (int i = index + 1; i < springExpr.length(); i++) {
            String currentChar = Character.toString(springExpr.charAt(i));

            if (currentChar.equals(closer)) {
                queue.poll();
            } else if (currentChar.equals(opener)) {
                queue.add(currentChar);
            }
            if (queue.size() == 0) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid Spring Expression");
    }

    private static Spring solve(String springExpr, ArrayList<List<Integer>> indexes) {
        // TODO
        return null;
    }
}
