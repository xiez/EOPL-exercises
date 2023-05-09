;; 2.21
# lang eopl

;;; Env-exp ::= (empty-env)
;;          ::=(extend-env Identifier Scheme-value Env-exp)
(define-datatype env env?
  (empty-env)
  (extend-env
   (var symbol?)
   (val always?)
   (env env?)))

(define has-binding?
  (lambda (search-var an-env)
    (cases env an-env
           (empty-env () #f)
           (extend-env (var val an-env)
                       (or
                        (eq? var search-var)
                        (has-binding? search-var an-env))))))

;;; 2.22
;;; S = (empty-stack) | (push-stack val S)
(define-datatype stack stack?
  (empty-stack)
  (push-stack
   (val number?)
   (s stack?)))

(define empty-stack?
  (lambda (a-stack)
    (cases stack a-stack
           (empty-stack () #t)
           (else #f))))

(define pop
  (lambda (a-stack)
    (cases stack a-stack
           (empty-stack () (eopl:error 'pop "can not pop an empty stack"))
           (push-stack (val s) s))))

(define top
  (lambda (a-stack)
    (cases stack a-stack
           (empty-stack () (eopl:error 'pop "can not top an empty stack"))
           (push-stack (val s) val))))

;;; 2.23
(define (identifier? var)
  (and
   (symbol? var)
   (not (eq? var 'lambda))))

(define-datatype lc-exp lc-exp?
  (var-exp
   (var identifier?))
  (lambda-exp
   (bound-var identifier?)
   (body lc-exp?))
  (app-exp
   (rator lc-exp?)
   (rand lc-exp?)))

(lc-exp? (var-exp 's))
(lc-exp? (var-exp 'lambda))
(lc-exp? (app-exp (var-exp 'inc1) (var-exp 'a)))
