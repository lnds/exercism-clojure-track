(ns octal)

(defn to-decimal [num] 
  (reduce 
    (fn [acum digit] (+ (* 8 acum) 
      (case digit
        \0 0
         \1 1
         \2 2
         \3 3
         \4 4
         \5 5
         \6 6
         \7 7
        (* -8 acum))))
        0 num))
