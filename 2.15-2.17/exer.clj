
;; 2.15

;;; constructors
(defn var-exp [var]
  var)

(defn lambda-exp [var exp]
  (list 'lambda var exp))

(defn app-exp [exp1 exp2]
  (list exp1 exp2))

;;; predicates
(defn var-exp? [exp]
  (symbol? exp))

(defn lambda-exp? [exp]
  (and (list? exp)
       (= 'lambda (first exp))))

(defn app-exp? [exp]
  (list? exp))

;;; extractors
(defn var-exp->var [exp]
  exp)

(defn lambda-exp->bound-var [exp]
  (first (first (rest exp))))

(defn lambda-exp->body [exp]
  (first (rest (rest exp))))

(defn app-exp->rator [exp]
  (first exp))

(defn app-exp->rand [exp]
  (first (rest exp)))


;;; 2.16
;;; skip

;;; 2.17
(defn app-exp [exp1 exp2]
  (list exp2 exp1))

(defn app-exp->rator [exp]
  (first (rest exp)))

(defn app-exp->rand [exp]
  (first exp))

;;; test
(defn occurs-free? [search-var exp]
  (cond
    (var-exp? exp) (= search-var (var-exp->var exp))
    (lambda-exp? exp)
    (and
     (not (= search-var (lambda-exp->bound-var exp)))
     (occurs-free? search-var (lambda-exp->body exp)))
    :else
    (or
     (occurs-free? search-var (app-exp->rator exp))
     (occurs-free? search-var (app-exp->rand exp)))))

(assert (true? (occurs-free? 'x 'x)))
(assert (false? (occurs-free? 'x 'y)))
(assert (false? (occurs-free? 'x '(lambda (x) (x y)))))
(assert (true? (occurs-free? 'x '(lambda (y) (x y)))))
(assert (true? (occurs-free? 'x '((lambda (x) x) (x y)))))
(assert (true? (occurs-free? 'x '(lambda (y) (lambda (z) (x (y z)))))))
