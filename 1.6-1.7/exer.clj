;; 1.6
;;; the error message will never be displayed if the list is empty or n out of bounds
;;; in scheme there will be error: car: contract violation expected: pair?
;;; in clojure returns nil

;;; scheme
(define nth-element
  (lambda (lst n)
          (if (zero? n)
            (car lst)
            (if (null? lst)
              (error "empty list")
              (nth-element (cdr lst) (- n 1))))))

;;; clojure
(defn list-ref [lst n]
  (if (= n 0)
    (first lst)
    (if (empty? lst)
      (throw (Exception. "Error: empty list."))
      (list-ref (rest lst)
                (- n 1)))))

;; 1.7
;;; use inner auxiliary function and catch the free variables
(defn list-ref [lst n]
  (defn list-ref-aux [lst2 n2]
    (if (empty? lst2)
      (throw (Exception.
              (format "Error: list %s does not have %s elements." lst (+ 1 n))))
      (if (= n2 0)
        (first lst2)
        (list-ref-aux (rest lst2)
                      (- n2 1)))))
  (list-ref-aux lst n))

