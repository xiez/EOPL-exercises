#lang scheme
;; 2.1
(define N 1000)

(define (zero)
  '())

(define (is-zero? n)
  (null? n))

(define (successor n)
  (cond
   ((is-zero? n) (list 1))
   ((equal? (car n) (- N 1))
      (cons
       0
       (successor (cdr n))))
   (else
    (cons
     (+ 1 (car n))
     (cdr n)))))

(define (predecessor n)
  (cond
   ((equal? n (list 1)) (zero))
   ((= (car n) 0)
    (cons
     (- N 1)
     (predecessor (cdr n))))
   (else
    (cons
     (- (car n) 1)
     (cdr n)))))


(define (plus x y)
  (if (is-zero? y)
      x
      (plus (successor x) (predecessor y))))

(define ONE (successor (zero)))

(define (mult x y)
  (if (or (is-zero? x)
          (is-zero? y))
      (zero)
      (if (equal? y ONE)
          x
          (plus x
           (mult x (predecessor y))))))


(define (fact n)
  (if (is-zero? n)
      ONE
      (mult
       n
       (fact (predecessor n)))))
