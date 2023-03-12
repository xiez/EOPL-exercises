;; 2.5, 2.8, 2.9, 2.10
;;; Env = () | (cons (Var Val) Env)
;;; Var = String

(defn empty-env []
  '())

(defn empty-env? [env]
  (empty? env))

;; extend-env: Var x Val x Env -> Env
(defn extend-env [var val env]
  (cons
   (list var val)
   env))

(defn extend-env* [vars vals env]
  (if (empty? vars)
    '()
    (let [var (first vars)
          val (first vals)]
      (cons
       (list var val)
       (extend-env* (rest vars) (rest vals) env)))))



(defn has-binding? [env s]
  (if (seq? env)
    (if (empty-env? env)
      false
      (let [binding (first env)]
        (if (= (first binding) s)
          true
          (has-binding?
           (rest env s)))))
    (println (format "Error: Bad environment %s." env))))

;; apply-env: Env x Var -> Val
(defn apply-env [env search-var]
  (if (seq? env)
    (if (empty? env)
      (println (format "Error: No binding for %s." search-var))
      (let [binding (first env)]
        (if (= (first binding) search-var)
          (first (rest binding))
          (apply-env
           (rest env)
           search-var))))
    (println (format "Error: Bad environment %s." env))))

;; 2.6
;;; Env = () | (cons {Var->Val} Env)
;;; Var = String

(defn empty-env []
  '())
(defn extend-env [var val env]
  (cons
   {var val}
   env))
(defn apply-env [env search-var]
  (if (seq? env)
    (if (empty? env)
      (println (format "Error: No binding for %s." search-var))
      (let [binding (first env)]
        (if (contains? binding search-var)
          (get binding search-var)
          (apply-env
           (rest env)
           search-var))))
    (println (format "Error: Bad environment %s." env)))
  )

;;; 2.7
;;; skip

;;; 2.11
(defn extend-env* [vars vals env]
  (cons
   (list vars vals)
   env))
