
;; 1.11
;;; sexp is a smaller substructure, either (first slist) or (rest slist)
;;; and will eventually ends with a symbol.

;; 1.12

(defn subst [new old slist]
  (if (empty? slist)
    '()
    (if (symbol? (first slist))
      (cons
       (if (= old (first slist))
         new
         (first slist))
       (subst new old (rest slist)))
      (cons
       (subst new old (first slist))
       (subst new old (rest slist))))))


;; 1.13
(defn subst2 [new old slist]
  (defn subst-in-s-exp [sexp]
    (if (symbol? sexp)
      (if (= old sexp) new sexp)
      (subst2 new old sexp)))
  (map subst-in-s-exp slist))


