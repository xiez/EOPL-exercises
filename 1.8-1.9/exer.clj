;;; 1.8

;;; Sym X Listof(Sym) -> Listof(Sym)
;;; (remove-till-first s los) returns a list with
;;; the same elements arranged in the same order
;;; as *los*, except that elements utill the first
;;; occurrence of the symbol *s* are all remvoed.
(defn remove-till-first [s los]
  (if (empty? los)
      '()
      (if (= s (first los))
          (rest los)
          (remove-till-first s (rest los)))))

;;; 1.9

;;; Sym X Listof(Sym) -> Listof(Sym)
;;; (remove-all s los) returns a list with
;;; the same elements arranged in the same order
;;; as *los*, except that elements that
;;; the symbol *s* are remvoed.
(defn remove-all [s los]
  (if (empty? los)
      '()
      (if (= s (first los))
          (remove-all s (rest los))
          (cons (first los)
                (remove-all s (rest los))))))
