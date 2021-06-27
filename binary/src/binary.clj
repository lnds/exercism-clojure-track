(ns binary)

(defn to-decimal [num] 
  (reduce 
    (fn [acum digit] (+ (* 2 acum) (if (= digit \1) 1 0))) 0 num)) 
