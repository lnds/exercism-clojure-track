(ns binary)

(defn to-decimal [num] 
  (reduce + 
    (map-indexed 
      #(* (bit-shift-left 1 %1) (Character/digit %2 2)) 
          (reverse (filter #(Character/isDigit %) num)))))
