(ns pov (:require [clojure.set :as s]))

(def root first)

(def children rest)

(defn- find-in-tree [node tree parent]
  (cond
    (empty? tree) nil
    (= node (root tree)) parent
    :else (first (keep #(find-in-tree node % tree) (children tree)))))

                  
(defn- remove-node [node tree]
  (cond
    (empty? tree) nil
    (= node (root tree)) nil
    :else 
      (vec (cons (root tree) (keep #(remove-node node %) (children tree))))))

(defn of [node tree]
  (if (= (root tree) node) 
    [node]
    (let [parent (find-in-tree node tree tree)]
      (when parent
        (let [nodes (children parent)]
           (conj (first (filter #(= node (root %1)) nodes)) 
              (vec 
                (concat   
                  (remove-node node parent)
                  (let [new-tree (of (root parent) (remove-node node tree))]
                      (vec (s/difference (set new-tree) (set parent))))))))))))
  

(defn- find-node-path [node tree path]
  (cond
    (empty? tree) nil
    (= node (root tree)) path
    :else (first (keep #(find-node-path node % (conj path (root %))) (children tree)))))

(defn path-from-to [node-1 node-2 tree] 
  (find-node-path node-2 (of node-1 tree) [node-1]))

