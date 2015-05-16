/* File: EvaluateDivisors.java - May 2015 */
import java.lang.Math;

/**
 * Usage: EvaluateDivisors A B K
 *
 * Given integers 1 &lt; A &lt; B and K positive and odd, the number of integers
 * between A and B (inclusive) with exactly K divisors is printed.
 *
 * @author Andrew Ashton
 */
public class EvaluateDivisors {

    /**
     * Takes A, B, and K as command line arguments, handles any possible errors,
     * and then prints the solution.
     *
     * @param args A, B, and K
     */
    public static void main(String[] args) {
        try {
            long a = Long.parseLong(args[0]);
            long b = Long.parseLong(args[1]);
            int k = Integer.parseInt(args[2]);
            
            if (a <= 1 || b <= a) {
                error("Error: must have 1 < A < B");
            }
            if (k <= 0 || k % 2 == 0) {
                error("Error: K must be a positive odd number");
            }
            System.out.println(solution(a, b, k));
        } catch (IndexOutOfBoundsException e) {
            error("Usage: EvaluateDivisors A B K");
        } catch (NumberFormatException e) {
            error("Error: arguments must be integers");
        }
    }

    /**
     * Returns the solution given by A, B, and K.
     *
     * @param a A
     * @param b B
     * @param k K
     *
     * @return the solution
     */
    private static int solution(long a, long b, int k) {
        // If K is prime there is a more efficient algorithm.
        if (prime(k)) {
            return primeSolution(a, b, k);
        }
        int result = 0;

        /* Integers with an odd number of divisors are perfect squares, since
           every divisor n of c corresponds to a pair (n, c/n) unless n = c/n,
           that is, c = n^2. */
        for (long n = (long) Math.sqrt(a); n*n <= b; n++) {
            // 1, n, and n^2 are divisors of n^2.
            int divisors = 3;

            /* Count other divisors in pairs (m, n^2/m). If m > n then n^2/m < n
               and so the pair has been counted. Therefore stop once m < n. */
            for (long m = 2; m < n && divisors <= k; m++) {
                if (n*n % m == 0) {
                    divisors += 2;
                }
            }
            if (divisors == k) {
                result++;
            }
        }
        return result;
    }
    
    /**
     * Returns the solution provided K is prime.
     *
     * @param a A
     * @param b B
     * @param k K
     *
     * @return the solution
     */
    private static int primeSolution(long a, long b, int k) {
        int result = 0;
        
        /* If K is prime and n has K divisors then n = p^(K - 1) for some prime
           p (see README.txt for details). */
        int n = 2;
        while (Math.pow(n, k - 1) < a) {
            n++;
        }
        while (Math.pow(n, k - 1) <= b) {
            if (prime(n++)) {
                result++;
            }
        }
        return result;
    }
    
    /**
     * Returns true if the given positive integer is prime.
     *
     * @param n a positive integer
     *
     * @return true if n is prime
     */
    private static boolean prime(int n) {
        /* If m > sqrt(n) divides n then some integer less than m divides
           n. Therefore the loops stops when m > sqrt(n). */
        for (int m = 2; m <= Math.sqrt(n); m++) {
            if (n % m == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints the given message to stderr and then exits reporting failure.
     *
     * @param message message to print to stderr
     */
    private static void error(String message) {
        System.err.println(message);
        System.exit(1);
    }

}
