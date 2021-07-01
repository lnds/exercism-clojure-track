(ns sieve)

(defn sieve [n]
  (let [limit (inc n)
       root (Math/sqrt limit)
        sieve (boolean-array limit true)]
    (loop [i 2]
      (when (< i root)
        (when (aget sieve i)
          (loop [j (* i 2)]
            (when (< j limit)
              (aset sieve j false)
              (recur (+ j i)))))
        (recur (inc i))))
   (filter #(aget sieve %) (range 2 limit))))

