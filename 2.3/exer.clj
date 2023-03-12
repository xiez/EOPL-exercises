;;;

(defn zero []
  '(diff (one) (one)))

(defn is-zero? [n]
  (=
   n
   '(diff (one) (one))))

(defn successor [n]
  ;; successor of n equals n - (-1)
  (list
   'diff
   n
   '(diff (diff (one) (one)) (one))))

(defn predecessor [n]
  ;; predecessor of n equals n - 1
  (list
   'diff
   n
   '(one)))

;;;

(defn diff-tree-plus [n1 n2]
  ;; n1 + n2 equals n1 - (-n2)
  (list
   'diff
   n1
   '(diff (zero) n2)))
