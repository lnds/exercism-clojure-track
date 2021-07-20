(ns all-your-base)

(defn convert [base-1 num base-2]
  (cond 
    (empty? num) nil
    (or (<= base-1 1) (<= base-2 1)) nil
    (not-every? #(and (>= %1 0) (< %1 base-1)) num) nil
    :else (loop [n (reduce #(+ (* base-1 %1) %2) 0 num)
                 r (transient (if (zero? n) [0] []))]
            (if (zero? n) 
              (reverse (persistent! r))
              (recur (quot n base-2) (conj! r (rem n base-2)))))))
