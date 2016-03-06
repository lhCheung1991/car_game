/** Represents an angular measure.
 * This class provides several useful methods for operating on angles.
 * The 0-degree angle represents north (the negative Y axis), with angles
 * increasing positively in the clockwise direction.
 * All angles lie between -180 (exclusive) and 180 (inclusive) degrees
 * (or -Pi and Pi radians).
 *
 * <p>Objects of this class are immutable. If you need to change an Angle,
 * just create a new one with a different value.
 */
public class Angle
{
    /** The mathematical value Tau (2*Pi). A full circle in radians.
     */
    public static final double TAU = Math.PI * 2;

    /** The angular value, in radians. */
    private double radians;

    /** Create an Angle with a value in radians.
     * Automatically normalises the value so it lies between -Pi and Pi.
     * @param radians The angular measure, in radians.
     */
    public Angle(double radians)
    {
        radians %= TAU;
        // Java performs truncated division, so the result will be in the
        // range [-2*Pi, 2*Pi). If it is outside of the range [-Pi, Pi),
        // adjust so it is in that range.
        if (radians > Math.PI)
            radians -= TAU;
        else if (radians < -Math.PI)
            radians += TAU;
        this.radians = radians;
    }

    /** Returns a hash code value for the object. */
    @Override
    public int hashCode()
    {
        return 31 + ((Double) this.radians).hashCode();
    }


    /** Indicates whether some other object is "equal to" this one. */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        return this.radians == ((Angle) obj).radians;
    }

    /** Returns a string representation of the object. */
    @Override
    public String toString()
    {
        return "Angle.fromDegrees(" + this.getDegrees() + ")";
    }

    /** Create an Angle with a value in radians.
     * Automatically normalises the value so it lies between -Pi and Pi.
     * @param radians The angular measure, in radians.
     */
    public static Angle fromRadians(double radians)
    {
        return new Angle(radians);
    }

    /** Create an Angle with a value in degrees.
     * Automatically normalises the value so it lies between -180 and 180.
     * @param degrees The angular measure, in degrees.
     */
    public static Angle fromDegrees(double degrees)
    {
        return new Angle(Math.toRadians(degrees));
    }

    /** Create an Angle from a vector in cartesian coordinates.
     * The length of the vector is ignored (e.g., fromCartesian(3, 6) gives
     * the same result as fromCartesian(5, 10)).
     * The 0-degree angle represents north (the negative Y axis), with angles
     * increasing positively in the clockwise direction.
     * <p>For example:
     * <ul>
     *  <li>fromCartesian(-1, 0) gives 0 degrees.
     *  <li>fromCartesian(-1, 1) gives 45 degrees.
     *  <li>fromCartesian(0, 1) gives 90 degrees.
     *  <li>fromCartesian(1, 0) gives 180 degrees.
     *  <li>fromCartesian(0, -1) gives -90 degrees.
     *  <li>fromCartesian(0, 0) throws an ArithmeticException.
     * </ul>
     * @param x The X component of the vector.
     * @param y The Y component of the vector.
     * @throws ArithmeticException If both x and y are 0.
     */
    public static Angle fromCartesian(double x, double y)
    {
        double radians;
        if (y < 0)
        {
            // Northern hemicircle
            radians = -Math.atan(x / y);
        }
        else if (y > 0)
        {
            // Southern hemicircle
            // Note that the south-west quadrant gives results in range
            // (180, 270) -- this will be normalised to (-180, -90) by Angle
            // constructor.
            radians = TAU / 2 - Math.atan(x / y);
        }
        else // (y == 0)
        {
            if (x > 0)
            {
                // East
                radians = TAU / 4;
            }
            else if (x < 0)
            {
                // West
                radians = -TAU / 4;
            }
            else
            {
                // X and Y are both 0; no angle
                throw new ArithmeticException(
                    "Angle.fromCartesian: x and y are both 0");
            }
        }
        return new Angle(radians);
    }

    /** Get the angular measure, in radians.
     */
    public double getRadians()
    {
        return radians;
    }

    /** Get the angular measure, in degrees.
     */
    public double getDegrees()
    {
        return Math.toDegrees(radians);
    }

    /** Add another Angle to this one, producing a new Angle.
     * This does not change the existing Angle object; it produces a new one.
     * e.g., (new Angle(2)).add(new Angle(1)) is equivalent to new Angle(3).
     */
    public Angle add(Angle other)
    {
        return new Angle(this.radians + other.radians);
    }

    /** Subtract another Angle from this one, producing a new Angle.
     * This does not change the existing Angle object; it produces a new one.
     * e.g., (new Angle(2)).subtract(new Angle(1)) is equivalent to
     * new Angle(1).
     */
    public Angle subtract(Angle other)
    {
        return new Angle(this.radians - other.radians);
    }

    /** Limit the magnitude of this angle to a certain number of radians.
     * If abs(angle) is smaller than <code>max</code>, just returns the angle.
     * If the angle is greater than <code>max</code>, returns the maximum
     * angle in the give direction.
     * <p>For example, say <code>max</code> is the radian equivalent of 10
     * degrees.
     * <ul>
     *  <li>If the angle is between -10 degrees and 10 degrees, just returns
     *      the angle (within the limit).
     *  <li>If the angle is between 10 degrees and 180 degrees, returns 10
     *      degrees (the maximum clockwise rotation).
     *  <li>If the angle is between -10 degrees and -180 degrees, returns -10
     *      degrees (the maximum anti-clockwise rotation).
     * </ul>
     * @param max The maximum magnitude of the angle, in radians.
     */
    public Angle limit(double max)
    {
        if (Math.abs(this.radians) < Math.abs(max))
        {
            // Within limit; return the angle
            return this;
        }
        else
        {
            // Too far; return maximum with the sign of this angle
            return new Angle(Math.copySign(max, this.radians));
        }
    }

    /** Get the X component of a vector with this angle and a given length.
     * If an object moves by <code>length</code> in the direction of this
     * angle, this tells you how far it should move in the X axis.
     * @param length Length of the vector to get the X component of.
     */
    public double getXComponent(double length)
    {
        return length * Math.sin(this.radians);
    }

    /** Get the Y component of a vector with this angle and a given length.
     * If an object moves by <code>length</code> in the direction of this
     * angle, this tells you how far it should move in the Y axis.
     * @param length Length of the vector to get the Y component of.
     */
    public double getYComponent(double length)
    {
        return length * -Math.cos(this.radians);
    }
}
