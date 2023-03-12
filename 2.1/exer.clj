;; 2.1
(def N 16)

(defn zero []
  '())

(defn is-zero? [n]
  (empty? n))

(defn successor [n]
  (cond
    (is-zero? n) (list 1)
    (= (first n) (- N 1))
    (cons
     0
     (successor (rest n)))
    :else (cons
           (+ 1 (first n))
           (rest n))))

(defn predecessor [n]
  (cond
    (= n (list 1)) (zero)
    (= (first n) 0)
    (cons
     (- N 1)
     (predecessor (rest n)))
    :else
    (cons
     (- (first n) 1)
     (rest n))))


(defn plus [x y]
  (defn plus-aux [x y val]
    (if (is-zero? y)
      val
      (plus-aux x (predecessor y) (successor val))))
  (plus-aux x y x))

(defn mult [x y]
  (defn aux-mult [x y val]
    (if (is-zero? y)
      val
      (aux-mult
       x
       (predecessor y)
       (plus x val))))
  (if (or (is-zero? x)
          (is-zero? y))
    (zero)
    (aux-mult x y (zero))))

(def ONE (successor (zero)))

(defn fact [n]
  (defn aux-fact [n val]
    (if (is-zero? n)
      val
      (aux-fact (predecessor n)
                (mult n val))))
  (aux-fact n ONE))

