(ns prime-factors)

(defn factor-of? [f n]
      (zero? (rem n f)))

(defn factors [f n]
      (cond
        (= n 1) (lazy-seq [])
        (factor-of? f n) (lazy-seq (cons f (factors f (/ n f))))
        :else (recur (inc f) n)))

(defn of [n] 
      (factors 2 n))
