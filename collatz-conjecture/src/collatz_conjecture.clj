(ns collatz-conjecture)

(defn collatz [num] ;; <- arglist goes here
  (cond 
    (neg? num) (throw (Exception. "no negative"))
    (zero? num) (throw (Exception. "no zero"))
    (== 1 num) 0
    (zero? (mod num 2)) (if-let [a (collatz (quot num 2))] (inc a) nil)
    :else (if-let [a (collatz (inc (* 3 num)))] (inc a) nil)))
