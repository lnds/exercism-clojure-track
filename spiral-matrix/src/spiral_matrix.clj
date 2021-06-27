(ns spiral-matrix)

(defn- cell [n row col]
  (cond
    (= row 0) (inc col)
    (= col 0) (- (* 4 n) 3 row)
    (= row (dec n)) (- (* 3 n) 2 col) 
    (= col (dec n)) (+ n row)
    :else (+ (* 4 (dec n))     
             (cell (- n 2) (dec row) (dec col)))))

(defn spiral [n]
  (->> 
    (for [a (range n) b (range n)] [a b])
    (map #(apply (partial cell n) %))
    (partition n)))