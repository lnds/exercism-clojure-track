(ns pascals-triangle)

(defn- next-row [row]
  (flatten [1 (map +' row (rest row)) 1]))
 
(def triangle
  (iterate next-row [1]))

(defn row [n] 
  (nth triangle (dec n)))
 