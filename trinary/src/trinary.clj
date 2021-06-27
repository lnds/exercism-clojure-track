(ns trinary)

(defn to-decimal [num] 
  (reduce 
    (fn [acum digit] (+ (* 3 acum) 
      (case digit
        \0 0
        \1 1
        \2 2
        (* -3 acum))))
        0 num))