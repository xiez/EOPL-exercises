;; constructor

;; () -> S
(defn empty-stack []
  (list 'empty-stack))

;; Val x S -> S
(defn push [v s]
  (list 'push v s))

;; S -> S
(defn pop [s]
  (if (empty-stack? s)
    (throw (Exception. (format "Error: stack is empty")))
    (first (rest (rest s)))))


;; observers

;; S -> bool
(defn empty-stack? [s]
  (= 'empty-stack (first s)))

;; S -> Val
(defn top [s]
  (if (empty-stack? s)
    (throw (Exception. (format "Error: stack is empty")))
    (first (rest s))))


