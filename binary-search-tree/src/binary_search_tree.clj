(ns binary-search-tree)

(defn value [node] (:value node))

(defn singleton [v] {:value v :left nil :right nil})

(defn insert [v tree] 
  (if (nil? tree) 
    (singleton v)
    (if (> v (value tree)) 
      (assoc tree :right (insert v (:right tree)))
      (assoc tree :left  (insert v (:left tree))))))

(defn left [node] (:left node))

(defn right [node] (:right node))

(defn to-list [tree] 
  (when (not-empty tree) 
    (concat (to-list (left tree)) [(value tree)] (to-list (right tree)))))

(defn from-list [[head & tail]] 
  (reduce #(insert %2 %1) (singleton head) tail))
