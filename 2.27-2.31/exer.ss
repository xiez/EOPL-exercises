;;; 2.28

;; unparse-lc-exp : LcExp → SchemeString
(define unparse-lc-exp
  (lambda (exp)
    (cases lc-exp exp
           (var-exp (var) (symbol->string var))
           (lambda-exp (bound-var body)
                       (string-append
                        "proc "
                        (symbol->string bound-var)
                        " => "
                        (unparse-lc-exp body)))
           (app-exp (rator rand)
                    (string-append
                     (unparse-lc-exp rator)
                     "("
                     (unparse-lc-exp rand)
                     ")")))))



(define exp
  '((lambda (a) (a b)) c))

(define parsed
  (parse-expression exp))

(unparse-lc-exp
 parsed)
;;; "proc a => a(b)(c)"

;;; 2.29

;;; lc-exp
(define-datatype lc-exp lc-exp?
  (var-exp
   (var symbol?))
  (lambda-exp
   (bound-vars (list-of symbol?))
   (body lc-exp?))
  (app-exp
   (rator lc-exp?) (rands (list-of lc-exp?))))


;; parse-expression : SchemeVal → LcExp
(define parse-expression
  (lambda (datum)
    (cond
     ((symbol? datum) (var-exp datum))
     ((pair? datum)
      (if (eqv? (car datum) 'lambda)
          (lambda-exp
           (cadr datum)                 ;bound-vars
           (parse-expression (caddr datum)))
          (app-exp
           (parse-expression (car datum)) ;operator
           (parse-list-expression (cdr datum)) ;operands
           )))
     (else (eopl:error "report-invalid-concrete-syntax " datum)))))

(define parse-list-expression
  (lambda (lst-of-exp)
    (if (null? lst-of-exp)
        '()
        (cons
         (parse-expression (car lst-of-exp))
         (parse-list-expression (cdr lst-of-exp))))))

(define exp
  '((lambda (a x) (a b x)) c d))

(define ast
  (parse-expression exp))

;; unparse-lc-exp : LcExp → SchemeVal
(define unparse-lc-exp
  (lambda (exp)
    (cases lc-exp exp
           (var-exp (var) var)
           (lambda-exp (bound-vars body)
                       (list 'lambda bound-vars
                             (unparse-lc-exp body)))
           (app-exp (rator rands)
                    (cons
                     (unparse-lc-exp rator)
                     (unparse-list-lc-exp rands))))))

(define unparse-list-lc-exp
  (lambda (lst-of-lc-exp)
    (if (null? lst-of-lc-exp)
        '()
        (cons
         (unparse-lc-exp (car lst-of-lc-exp))
         (unparse-list-lc-exp (cdr lst-of-lc-exp))))))

(unparse-lc-exp ast)


;;; 2.30
;;; skip

;;; 2.31

(define-datatype prefix-exp prefix-exp?
  (const-exp
   (num integer?))
  (diff-exp
   (operand1 prefix-exp?)
   (operand2 prefix-exp?)))

;; parse-exp : SchemeVal → PrefixList
(define parse-prefix-exp
  (lambda (prefix-lst)
    (let* ((exp-and-rest (aux-parse prefix-lst))
           (exp (car exp-and-rest))
           (rest (cdr exp-and-rest)))
      (if (null? rest)
          exp
          (eopl:error "expect the remaining to be null, current:" rest)))))

;; aux-parse-exp : SchemeVal → listof (PrefixList and LeftOver list elements)
(define aux-parse
  (lambda (prefix-lst)
    (cond
     ((null? prefix-lst)
      (eopl:error "emtpy list"))
     ((integer? (car prefix-lst))
      (cons
       (const-exp (car prefix-lst))
       (cdr prefix-lst)))
     ((eqv? '- (car prefix-lst))
      (let* ((rand1-with-rest (aux-parse (cdr prefix-lst)))
             (rand1 (car rand1-with-rest))
             (rest1 (cdr rand1-with-rest)))
        (if (null? rest1)
            (eopl:error "expect remaining after operand1" prefix-lst)
            (let* ((rand2-with-rest (aux-parse rest1))
                   (rand2 (car rand2-with-rest))
                   (rest2 (cdr rand2-with-rest)))
              (cons
               (diff-exp rand1 rand2)
               rest2)))))
     (else
      (eopl:error "Invalid prefix-list" prefix-lst)))))

;;; test
(parse-prefix-exp '(1))
(parse-prefix-exp '(- 3 2))
(parse-prefix-exp '(- - 3 2 1))

;; (parse-prefix-exp '(- - 3 2 1 4))
;; (parse-prefix-exp '(- - 3 2 ))
