;;; 1.15
(defn duple [n x]
  (if (= n 0)
    '()
    (cons
     x
     (duple (- n 1) x))))

;;; 1.16
(defn invert [lst]
  (defn invert-list [lst]
    (cons (first (rest lst)) (list (first lst))))
  (if (empty? lst)
    '()
    (cons
     (invert-list (car lst))
     (invert (rest lst)))))

;;; 1.17
(defn down [lst]
  (if (empty? lst)
    '()
    (cons
     (list (car lst))
     (down (rest lst)))))

;;; 1.18
(defn swapper [s1 s2 slist]
  (defn swapper-one [ele]
    (cond
      (= s1 ele) s2
      (= s2 ele) s1
      :else ele))
  (if (empty? slist)
    '()
    (cons
     (if (symbol? (first slist))
       (swapper-one (first slist))
       (swapper s1 s2 (first slist)))
     (swapper s1 s2 (rest slist))))
  )

;;; 1.19
(defn list-set [lst n x]
  (if (= n 0)
    (cons x
          (rest lst))
    (cons (first lst)
          (list-set (rest lst) (- n 1) x))))

;;; 1.20
(defn count-occurrences [s slist]
  (defn count-sym [s sym]
    (if (= s sym) 1 0))
  (if (empty? slist)
    0
    (+
     (if (symbol? (first slist))
       (count-sym s (first slist))
       (count-occurrences s (first slist)))
     (count-occurrences s (rest slist)))))

;;; 1.21
(defn product-one [sym sos]
  (if (empty? sos)
    '()
    (cons
     (list sym (first sos))
     (product-one sym (rest sos)))))
(defn product [sos1 sos2]
  (if (empty? sos1)
    '()
    (concat
     (product-one
      (first sos1) sos2)
     (product (rest sos1) sos2))))


;;; 1.22
(defn filter-in [pred lst]
  (if (empty? lst)
    '()
    (if (pred (first lst))
      (cons
       (first lst)
       (filter-in pred (rest lst)))
      (filter-in pred (rest lst)))))


;;; 1.23
(defn list-index [pred lst]
  (defn aux-list-index [pred lst idx]
    (if (empty? lst)
      false
      (if (pred (first lst))
        idx
        (aux-list-index pred (rest lst) (+ idx 1)))))
  (aux-list-index pred lst 0))

;;; 1.24
(defn my-every? [pred lst]
  (if (empty? lst)
    true
    (if (pred (first lst))
      (my-every? pred (rest lst))
      false)))

;;; 1.25
(defn exists? [pred lst]
  (if (empty? lst)
    false
    (if (pred (first lst))
      true
      (exists? pred (rest lst)))))

;;; 1.26
;;; if the first of the lst is a list, return the list
;;; if the first of the lst is a symbol, return a list containing that symbol
;;; concat the first result list with the result of the remaining lst
(defn up [lst]
  (if (empty? lst)
    '()
    (concat
     (if (list? (first lst))
       (first lst)
       (list (first lst)))
     (up (rest lst)))))

;;; 1.27
;;; if the first element is a list, flatten that element
;;; if the first element is a symbol, return a list containing that symbol
;;; concat previous result list with the result of the remaining lst
(defn my-flatten [slist]
  (if (empty? slist)
    '()
    (concat
     (if (list? (first slist))
       (my-flatten (first slist))
       (list (first slist)))
     (my-flatten (rest slist)))))

;;; 1.28
;;; if the first int of lst1 less than first int of lst2
;;; then cons the first int of lst1 with the merge result of rest of lst1 and ls2
;;; else cons the first int of lst2 with the merge result of lst1 and the rest of ls2
(defn my-merge [loi1 loi2]
  (cond
    (empty? loi1) loi2
    (empty? loi2) loi1
    :else
    (if (< (first loi1) (first loi2))
      (cons
       (first loi1)
       (my-merge (rest loi1) loi2)
       )
      (cons
       (first loi2)
       (my-merge loi1 (rest loi2))))))


;;; 1.29
;;; merge sort
;;; split the list into two lists, and sort each list, then merge two sorted list
;;; if the length of list is 1, then it's sorted
;; loi -> (loi1, loi2)
(defn split-2 [loi]
  (split-at
   (Math/ceil
    (/ (count loi) 2))
   loi)
  )
(defn my-sort [loi]
  (cond
    (empty? loi) '()
    (empty? (rest loi)) loi
    :else
    (let [splits (split-2 loi)]
      (my-merge
       (my-sort (first splits))
       (my-sort (first (rest splits)))))))

;;; 1.30
(defn merge-predicate [pred loi1 loi2]
  (cond
    (empty? loi1) loi2
    (empty? loi2) loi1
    :else
    (if (pred (first loi1) (first loi2))
      (cons
       (first loi1)
       (merge-predicate pred (rest loi1) loi2)
       )
      (cons
       (first loi2)
       (merge-predicate pred loi1 (rest loi2)))))
  )
(defn sort-predicate [pred loi]
  (cond
    (empty? loi) '()
    (empty? (rest loi)) loi
    :else
    (let [splits (split-2 loi)]
      (merge-predicate
       pred
       (sort-predicate pred (first splits))
       (sort-predicate pred (first (rest splits)))))))


;;; Definition 1.1.7 (binary tree)
;; Bintree::=Int |(Symbol Bintree Bintree)
;; Here are some examples of such trees:
;;   1
;;   2
;;   (foo 1 2)
;;   (bar 1 (foo 1 2))
;;   (baz
;;     (bar 1 (foo 1 2))
;;     (biz 4 5))
;;; 1.31
(defn leaf [val]
  val)
(defn interior-node [sym lson rson]
  (list sym lson rson))
(defn leaf? [t]
  (int? t))
(defn lson [node]
  (if (leaf? node)
    '()
    (first (rest node))))
(defn rson [node]
  (if (leaf? node)
    '()
    (first (rest (rest node)))))
(defn content-of [node]
  (if (leaf? node)
    node
    (first node)))

;;; 1.32
(defn double-tree [bintree]
  (if (leaf? bintree)
    (* 2 bintree)
    (list
     (content-of bintree)
     (double-tree (lson bintree))
     (double-tree (rson bintree)))))

;;; 1.33
(def btree
  (interior-node 'red
                 (interior-node 'bar (leaf 26)
                                (leaf 12))
                 (interior-node 'red (leaf 11) (interior-node 'quux
                                                              (leaf 117)
                                                              (leaf 14)))))

(defn mark-leaves-with-red-depth [btree]
  (defn aux-mark [btree cnt]
    (if (leaf? btree)
      cnt
      (let [next-cnt
            (if (= (content-of btree) 'red)
              (+ cnt 1)
              cnt)]
        (list
         (content-of btree)
         (aux-mark (lson btree) next-cnt)
         (aux-mark (rson btree) next-cnt)))))
  (aux-mark btree 0))

;;; 1.34
(def btree
  '(14 (7 () (12 () ())) (26 (20 (17 () ())
                                 ())
                             (31 () ()))))
(defn path [n bst]
  (defn aux-path [n bst]
    (cond
      (empty? bst) '(not-found)
      (> n (content-of bst))
      (cons 'right
            (aux-path n (rson bst)))
      (< n (content-of bst))
      (cons 'left
            (aux-path n (lson bst)))
      :else '()))
  (let [res (aux-path n bst)]
    (if (= 'not-found (last res))
      '()
      res)))

;;; 1.35
(def btree
  (interior-node 'foo
                 (interior-node 'bar (leaf 26)
                                (leaf 12))
                 (interior-node 'baz (leaf 11) (interior-node 'quux
                                                              (leaf 117)
                                                              (leaf 14)))))
;; (def btree
;;   (interior-node 'foo
;;                  (interior-node 'baz
;;                                 (leaf 11)
;;                                 (interior-node 'quux
;;                                                (leaf 117)
;;                                                (leaf 14)))
;;                  (leaf 12)))
(defn number-leaves [btree]
  (defn aux-f [btree num]
    (cond
      (leaf? btree) (leaf num)
      :else
      (let [
            lson2 (aux-f (lson btree) num)
            rson2 (aux-f (rson btree)
                         (if (leaf? lson2)
                           (+ 1 lson2)
                           (+ 1 (last lson2))))
            ]
        (interior-node
         (content-of btree)
         lson2 rson2)
        )))
  (aux-f btree 0))


;;; 1.36
(defn g [first-ele rest-eles]
  (println first-ele rest-eles)
  (cons first-ele
        (map (fn [x] (cons (+ 1 (first x)) (rest x)))
             rest-eles)))
(defn number-elements [lst]
  (if (empty? lst)
    '()
    (g
     (list 0 (first lst))
     (number-elements (rest lst)))))
