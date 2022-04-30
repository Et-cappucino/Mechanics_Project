import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// getters and constructors are
// automatically generated by the annotations

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Spring {

    //default value is 1
    private double k = 1;

    private void setK(double k) {
        this.k = k;
    }

    public double[] move(double t, double dt, double x0, double v0) {
        //TODO
        return null;
    }

    public double[] move(double t, double dt, double x0) {
        //TODO
        return null;
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0) {
        //TODO
        return null;
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
        //TODO
        return null;
    }

    public Spring inSeries(Spring that) {
        //TODO
        return null;
    }

    public Spring inParallel(Spring that) {
        //TODO
        return null;
    }
}
