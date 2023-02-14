Exercise 1.14 [ ] Given the assumption 0 ≤ n < length(v), prove that partial- vector-sum is correct.


if n = 0, f(n) = v(0)
if n > 0, f(n) = v(n) + f(n - 1)


Indcution start: n = 0, (partial-vector-sum 0) is v(0)
Induction hypothesis: The claim is true for k > 0
Induction step: for k+1, (partial-vector-sum (k+1)) is v(0) + v(1) + .. + v(k) + v(k+1) which is (partial-vector-sum k) + v(k+1)

