
public class Main {

    public static void main(String[] args) {

        Spring spring = new Spring(2);

        int[] digits = {1, 1, 0, 0, 1, 1, 0, 1};
        Converter converter = new Converter();
        System.out.println(converter.validate(digits));
    }
}
