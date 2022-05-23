public class Main {

    public static void main(String[] args) {

        // For testing

        String expr = "{[][]}";
        String expr2 = "[{}{}]";
        String expr3 = "{[{[][]}{}][{}]}";

        Spring resultSpring = SpringArray.equivalentSpring(expr);
        System.out.println("Resulting K: " + resultSpring.getK());

        Spring[] springs = { new Spring(5), new Spring(10 )};
        Spring resultSpring2 = SpringArray.equivalentSpring(expr2, springs);
        System.out.println("Resulting K: " + resultSpring2.getK());

        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        double[] input = {1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0};

        Complex[] cinput = new Complex[input.length];
        for (int i = 0; i < input.length; i++)
            cinput[i] = new Complex(input[i], 0.0);

        FT.fft(cinput);

        System.out.println("Results:");
        for (Complex c : cinput) {
            System.out.println(c);
        }
    }
}
