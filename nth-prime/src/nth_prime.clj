(ns nth-prime)

(defn primes-seq [n]
      (let [root (-> n Math/sqrt int)
            sieve (boolean-array n true)]
           (loop [i 2]
                 (when (< i root)
                       (when (aget sieve i)
                             (loop [j (* i 2)]
                                   (when (< j n)
                                         (aset sieve j false)
                                         (recur (+ j i)))))
                       (recur (inc i))))
           (filter #(aget sieve %) (range 2 n))))

(defn- calc-limit [n]
      (let [log (Math/log n)
            loglog (Math/log log)
            logsum (+ log loglog)]
           (-> n (* logsum) int (+ 3))))

(defn nth-prime [n] 
  (cond 
    (zero? n) (throw (IllegalArgumentException. "no-zero"))
    (= n 1) 2
    (= n 2) 3
    :else (last (take n (primes-seq (calc-limit n))))))