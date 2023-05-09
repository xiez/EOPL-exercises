(defn empty-env []
  (fn [search-var]
    (throw (Exception. (format "No binding found for %s" search-var)))))

(defn extend-env [saved-var saved-val saved-env]
  (fn [search-var]
    (if (= search-var saved-var)
      saved-val
      (apply-env saved-env search-var))))

(defn apply-env [env search-var]
  (env search-var))

(def e (empty-env))
(let [e (extend-env 'a 42 e)]
  (println
   "apply a in env is"
     (apply-env e 'a))
  (println
   "apply b in env is"
     (apply-env e 'b)))

;; --------------------
