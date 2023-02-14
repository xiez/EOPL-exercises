(defn list-length [lst]
  (if (empty? lst)
    0
    (+ 1
       (list-length (rest lst)))))

(println
 (list-length (list 1 2 3 4))
 )

;;; --------------------

(defn list-ref [lst n]
  (if (empty? lst)
    (throw (Exception. "Error: empty list."))
    (if (= n 0)
      (first lst)
      (list-ref (rest lst)
               (- n 1)))))

(println
 (list-ref (list 1 2 3 4) 3)
 )


;;; --------------------
(defn remove-first [s los]
  (if (empty? los)
    '()
    (if (= s (first los))
      (rest los)
      (cons
       (first los)
       (remove-first s (rest los))))))

;;; --------------------
;; occurs-free? : Sym × LcExp → Bool
;; usage: returns #t if the symbol var occurs free
;; in exp, otherwise returns #f.
(defn occurs-free? [var exp]
  (cond (symbol? exp) (= var exp)
        (list? exp)
        (if (= 'lambda (first exp))
          (and
           (not (= var (first (first (rest exp)))))
           (occurs-free? var (rest (rest exp))))
          (or
           (occurs-free? var (first exp))
           (occurs-free? var (first (rest exp)))))
        :else
        (throw (Exception. (format "Error: invalid exp %s" exp)))))

;;; --------------------
;;; why clojure need forward declare ??
;;; https://stackoverflow.com/questions/33143936/clojure-why-a-function-should-be-declare-if-it-is-called-before-definition-in
(declare subst-in-s-exp)
(defn subst [new old slist]
  (if (empty? slist)
    '()
    (cons
     (subst-in-s-exp
      new old (first slist))
     (subst new old (rest slist)))))

(defn subst-in-s-exp [new old sexp]
  (if (symbol? sexp)
    (if (= old sexp) new sexp)
    (subst new old sexp)))


(println
 (subst 'a 'b '((b c) (b () d)))
 )

;;; --------------------
(defn number-elements [lst]
  (defn number-elements-from [lst n]
    (if (empty? lst)
      '()
      (cons
       (list n (first lst))
       (number-elements-from (rest lst) (+ n 1)))))
  (number-elements-from lst 0))

(println
 (number-elements (list 1 2 3 4))
 )

