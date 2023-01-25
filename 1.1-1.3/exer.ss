;;; 1.1 ;;;;;;;;;;;;;;;;;;;;
;; Write inductive definitions of the following sets. Write each defini- tion in all three styles (top-down, bottom-up, and rules of inference). Using your rules, show the derivation of some sample elements of each set.

;; 1. {3n+2 | n ∈ N}
;;; {2, 5, 8, ...}
;;; top-down: A nature number n is in S and only if 1) n = 2, or 2) n-3 ∈ S.
;;; bottom-up: Let S to be the smallest set satisfying two properties: 1) 2 ∈ S, and 2) if n ∈ S, then n+3 ∈ S.
;;; rules of inference: 2 ∈ S, if n ∈ S, then n+3 ∈ S.
;;; e.g. 1!=2, then 1 not in S; 2==2, then 2 in S; 5=2+3 ans 2 in S, then 5 in S.


;; 2. {2n+3m+1|n,m∈N}
;;; {1, 3, 4, 6, 5, 7, 8, 9,10,11}
;;; top-down: A nature number n is in S and only if 1) n = 1, or 2) n-2 ∈ S, or 3) n-3 ∈ S.
;;; bottom-up: Let S to be the smallest set satisfying two properties: 1) 1 ∈ S, and 2) if n ∈ S, then n+2 ∈ S, and 3) if n ∈ S, then n+3 ∈ S.
;;; rules of inference: 1 ∈ S, if n ∈ S, then n+2 ∈ S, and if n ∈ S, then n+3 ∈ S.
;;; e.g. 1==1, then 1 in S. 2!=1,and 2!=2+2,and 2 != 2+3, then 2 not in S.

;; 3. {(n,2n+1)|n∈N}
;;; {(0,1), (1,3), (2,5), ...}
;;; top-down: (m,n) is in S and only if 1) m=0, n=1, or 2) (m-1,n-2) ∈ S
;;; bottom-up: Let S to be the smallest set satisfying two properties: 1) (0,1) ∈ S, and 2) if (m,n) ∈ S, then (m+1, n+2) ∈ S.
;;; rules of inference: (0,1) ∈ S, if (m,n) ∈ S, then (m+1, n+2) ∈ S.
;;; e.g. (1,1) not in S since (0,-1) not in S.

;; 4. {(n, n2) | n ∈ N} Do not mention squaring in your rules. As a hint, remember the equation(n+1)2 =n2 +2n+1.
;;; {(0,0), (1,1), (2,4), (3,9), ...}
;;; top-down: (m,n) is in S and only if 1) m=0, n=0, or 2) (m-1,n-2m+1) ∈ S.
;;; bottom-up: S to be the smallest set contained in N and satisfying two properties: 1) (0,0) ∈ S, and 2) if (m,n) ∈ S, then (m+1, n+2m+1) ∈ S.
;;; rules of inference: (0,0) ∈ S, if (m,n) ∈ S, then (m+1, n+2m+1) ∈ S.
;;; e.g. (1,1) in S, since (0,0) in S.

;;; 1.2 ;;;;;;;;;;;;;;;;;;;;

;;; 1. {(0,1), (1,8), (2,15),(3,22), ...} -> {(n, 7n+1) | n ∈ S}
;;; 2. {(0,1), (1,2), (2,4), (3,8), ...} -> {(n, 2 ^ n) | n ∈ S}
;;; 3. {(0,0,1), (1,1,1), (2,1,2), ()} ...}  -> {(n, fib(n), fib(n+1) | n ∈ S}
;;; 4. {(n, 2n+1, n ^ 2) | n ∈ S}

;;; 1.3 ;;;;;;;;;;;;;;;;;;;;
;;; 0 ∈ T, 1 ∈ T, if n ∈ T, then n + 3 ∈ T
