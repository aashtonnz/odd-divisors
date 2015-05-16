# odd-divisors
*A Java application for finding the number of integers with a given odd number of divisors.*

### The Problem

Given integers 1 < A < B and K positive and odd, how many integers between A and B (inclusive) have exactly K divisors?

### The Algorithm

We first check if K is prime. K is prime if it is not divisible by any integer 1 < n < K. We only need to ascend through integers n <= sqrt(K), otherwise K must have a divisor less than n, which would have already been found. If K is prime there is a more efficient algorithm as detailed in the next section.

Suppose c is an integer and n is a divisor of c, that is, c/n is some integer. Then c / (c/n) = n means c/n is also a divisor of c. Therefore a divisor n of c corresponds to a pair (n, c/n) unless n = c/n. Therefore if c has an odd number of divisors we must have n = c/n for some n, that is, c = n^2. Therefore an integer with an odd number of divisors must be a perfect square. Hence only perfect squares between A and B (inclusive) need to be considered.

Let c = n^2 be a perfect square such that A <= c <= B. Since 1, c, and n divide c, c has at least three divisors. We first count divisors m of c such that 1 < m < n. Each divisor will correspond to a pair (m, c/m) since m != n, hence each divisor m will increment the count by two.

Now consider any divisor m > n. Then c/m = n^2/m < n, and so the pair (m, c/m) has already been counted. Hence divisors m >= n need not be considered. We can also stop counting if the number of divisors exceeds K.

If the count of divisors of c equals K then the solution is incremented by one. Once all perfect squares between A and B (inclusive) have been considered, the solution is returned.

### If K is Prime

Consider any prime number K and suppose c > 1 has K divisors. Then, according to *http://en.wikipedia.org/wiki/Divisor* under *Further Notions and Facts*, K = (v1 + 1)(v2 + 1)(v3 + 1) ... (vn + 1), where c = p1^v1 * p2^
v2 * p3^v3 * ... * pn^vn for primes p1, p2, p3, ..., pn. However, K is prime so we must have K = vi + 1, that is, vi = K - 1, for some 1 <= i <= n, and vj = 0 for j != i. Therefore c = pi^(K - 1) for some i.

Given the above, if K is prime we only need to count the number of primes p such that A <= p^(K - 1) <= B. Therefore we step through all integers n such that A <= n^(K - 1) <= B and if n is prime the solution is incremented.
