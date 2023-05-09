# lang eopl

;; 2.24
(define-datatype bintree bintree?
  (leaf-node
   (num integer?))
  (interior-node
   (key symbol?)
   (left bintree?)
   (right bintree?)))

(define bintree-to-list
  (lambda (t)
    (cases bintree t
           (leaf-node (n)
                      (list 'leaf-node n))
           (interior-node (k l r)
                          (list 'interior-node k
                                (bintree-to-list l)
                                (bintree-to-list r))))))

(bintree-to-list
 (interior-node 'a (leaf-node 3) (leaf-node 4)))

;;; 2.25
(define (max-interior t)
  (define init-val (cons '() -999))
  (define (aux-f t)
    (cases bintree t
           (leaf-node (n)
                      n)
           (interior-node (k l r)
                          (let ((val (+
                                      (aux-f l)
                                      (aux-f r))))
                            (if (>= val (cdr init-val))
                                (set! init-val (cons k val))
                                '())
                            val))))
  (aux-f t)
  init-val)


(define t1
  (interior-node 'foo (leaf-node 2) (leaf-node 3)))
(max-interior t1)

(define t2
  (interior-node 'bar (leaf-node -1) t1))
(max-interior t2)

(define t3
  (interior-node 'baz t2 (leaf-node 1)))
(max-interior t3)
