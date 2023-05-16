#lang scheme


;; S-list ::=({S-exp}∗)
;; S-exp  ::=Symbol | S-list

(define-datatype s-list s-list?
  (empty-s-list)
  (non-empty-s-list
   (s-exps (list-of s-exp?))))

(define-datatype s-exp s-exp?
  (symbol-s-exp
   (sym symbol?))
  (s-list-s-exp
   (slist s-list?)))


(s-list-s-exp
 (non-empty-s-list
  (list
   (symbol-s-exp 'foo)
   (s-list-s-exp
    (empty-s-list))
   )))

;;; lc-exp
(define-datatype lc-exp lc-exp?
  (var-exp
   (var symbol?))
  (lambda-exp
   (bound-var symbol?)
   (body lc-exp?))
  (app-exp
   (rator lc-exp?) (rand lc-exp?)))

;; parse-expression : SchemeVal → LcExp
(define parse-expression
  (lambda (datum)
    (cond
     ((symbol? datum) (var-exp datum))
     ((pair? datum)
      (if (eqv? (car datum) 'lambda)
          (lambda-exp
           (car (cadr datum))
           (parse-expression (caddr datum)))
          (app-exp
           (parse-expression (car datum))
           (parse-expression (cadr datum)))))
     (else (eopl:error "report-invalid-concrete-syntax " datum)))))

(define exp
  '((lambda (a) (a b)) c))

(define parsed
  (parse-expression exp))

;; unparse-lc-exp : LcExp → SchemeVal
(define unparse-lc-exp
  (lambda (exp)
    (cases lc-exp exp
           (var-exp (var) var)
           (lambda-exp (bound-var body)
                       (list 'lambda (list bound-var)
                             (unparse-lc-exp body)))
           (app-exp (rator rand)
                    (list
                     (unparse-lc-exp rator) (unparse-lc-exp rand))))))

(unparse-lc-exp
 parsed)
