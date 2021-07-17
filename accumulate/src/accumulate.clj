(ns accumulate)

(defn accumulate [f coll] 
  (if (empty? coll) 
      []
      (cons (f (first coll)) (accumulate f (rest coll)))))