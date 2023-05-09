;;; 2.26

;; Red-blue-tree ::= Red-blue-subtree
;; Red-blue-subtree ::=(red-node Red-blue-subtree Red-blue-subtree)
;;                  ::=(blue-node {Red-blue-tree}∗)
;;                  ::=(leaf-node Int)

(define-datatype red-blue-tree red-blue-tree?
  (leaf-node
   (num integer?))
  (blue-node
   (rb-trees (list-of red-blue-tree?)))
  (red-node
   (left red-blue-tree?)
   (right red-blue-tree?)))

(define tree
   (red-node
    (blue-node '())
    (red-node
     (leaf-node 1)
     (blue-node
      (list
       (red-node (leaf-node 1) (leaf-node 2))
       (leaf-node 3))))))


(define (mark-leaves-with-red-depth tree)
  (define (aux-f t depth)
    (cases red-blue-tree t
           (leaf-node (num)
                      (leaf-node depth))
           (red-node (left right)
                     (red-node
                      (aux-f left (+ 1 depth))
                      (aux-f right (+ 1 depth))))
           (blue-node (list-of-child)
                      (cond
                       ;; if no child, return empty blue-node
                       ((null? list-of-child) (blue-node '()))
                       ;; if one child only, recursively call aux-f
                       ((null? (cdr list-of-child))
                        (blue-node
                         (list
                          (aux-f (car list-of-child) depth))))
                       (else
                        ;; recursively call first child, and the rest children
                        (blue-node
                         (list
                          (aux-f (car list-of-child) depth)
                          (aux-f (blue-node (cdr list-of-child)) depth))))))))
  (aux-f tree 0))

(mark-leaves-with-red-depth tree)
