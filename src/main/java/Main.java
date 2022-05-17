import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Spring spring = new Spring(2);

        int[] digits = {1, 1, 0, 0, 1, 1, 0, 1};
        Converter converter = new Converter();
        System.out.println(converter.validate(digits));

        // For testing

        String expr = "{[{}{}][]}";
        String expr2 = "{[{[][]}{}][{}]}";

        ArrayList<List<Integer>> indexPairs = SpringArray.getIndexPairs(expr);
        System.out.println("Expression: " + expr);
        System.out.println("Size: " + expr.length());
        System.out.println(indexPairs);

        System.out.println("------------------------------------------------------------------------------");

        ArrayList<List<Integer>> indexPairs2 = SpringArray.getIndexPairs(expr2);
        System.out.println("Expression: " + expr2);
        System.out.println("Size: " + expr2.length());
        System.out.println(indexPairs2);
    }
}
