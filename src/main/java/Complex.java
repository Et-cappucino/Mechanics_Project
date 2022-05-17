import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Complex {

    private double re;
    private double im;

    public Complex add(Complex b) {
        return new Complex(this.re + b.re, this.im + b.im);
    }

    public Complex sub(Complex b) {
        return new Complex(this.re - b.re, this.im - b.im);
    }

    public Complex mult(Complex b) {
        return new Complex(this.re * b.re - this.im * b.im,
                this.re * b.im + this.im * b.re);
    }

    @Override
    public String toString() {
        return String.format("(%f, %f i)", re, im);
    }
}
