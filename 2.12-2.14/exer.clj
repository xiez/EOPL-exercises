;; 2.12

;;; an empty stack can only be pushed, pop/top will throw error
(defn empty-stack []
  (fn [m]
    (cond (= m 'empty?) true
          :else (throw (Exception. (format "Error: stack is empty"))))))

(defn push [v s]
  (fn [m]
    (cond (= m 'empty?) false
          (= m 'pop) s
          (= m 'top) v)))

(defn pop [s]
  (s 'pop))

(defn top [s]
  (s 'top))

(defn empty-stack? [s]
  (s 'empty?))

(def s (empty-stack))
(empty-stack? s)

(let [s (push 2 (push 1 s))]
  (println "first top should be 2:" (top s))
  (let [s (pop s)]
    (println "second top should be 1:" (top s))
    (let [s (pop s)]
      (if (empty-stack? s)
        (do
          (println "stack is empty" s)
          (top s))
        (println "stack is not empty")))))

;; 2.13

;;; Extend the procedural representation to implement empty-env? by representing the environment by a list of two procedures: one that returns the value associated with a variable, as before, and one that returns whether or not the environment is empty.
(defn empty-env []
  (list
   (fn [search-var]
     (throw (Exception. (format "No binding found for %s" search-var))))
   (fn [] true)))


(defn extend-env [saved-var saved-val saved-env]
  (list
   (fn [search-var]
     (if (= search-var saved-var)
       saved-val
       (apply-env saved-env search-var)))
   (fn [] false)))


(defn apply-env [env search-var]
  ((first env) search-var))


(defn empty-env? [env]
  ((first (rest env))))

;; 2.14
(defn empty-env []
  (list
   (fn [search-var]
     (throw (Exception. (format "No binding found for %s" search-var))))
   (fn [] true)
   (fn [search-var] false)))

(defn has-binding? [env search-var]
  ((first (rest (rest env)))
   search-var))

(defn extend-env [saved-var saved-val saved-env]
  (list
   (fn [search-var]
     (if (= search-var saved-var)
       saved-val
       (apply-env saved-env search-var)))
   (fn [] false)
   (fn [search-var]
     (if (= search-var saved-var)
       true
       (has-binding? saved-env search-var)))))

(defn apply-env [env search-var]
  ((first env) search-var))

(defn empty-env? [env]
  ((first (rest env))))


;;; test
(def e (empty-env))
(has-binding? e 'a)
(let [e (extend-env 'a 42 e)]
  (println
   "has binding? a" (has-binding? e 'a))
  (println
   "apply a in env is"
   (apply-env e 'a))
  (println
   "has binding? b" (has-binding? e 'b))
  (println
   "apply b in env is"
   (apply-env e 'b)))
