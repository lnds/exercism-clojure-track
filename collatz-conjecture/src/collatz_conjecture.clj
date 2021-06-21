(ns collatz-conjecture)

(defn collatz [num] ;; <- arglist goes here
  (cond 
    (neg? num) (throw (Exception. "no negative"))
    (zero? num) (throw (Exception. "no zero"))
    (== 1 num) 0
    (zero? (mod num 2)) (when-let [a (collatz (quot num 2))] (inc a))
    :else (when-let [a (collatz (inc (* 3 num)))] (inc a))))
