(ns isbn-verifier)

(defn transform-to-num [data]
  (map #(if (or (= \X %) (= \x %)) 10 (Character/digit % 10)) data))

(defn check-sum [data] 
  (loop [i 0 x (first data) tail (rest data) sum 0]
      (if (or (= 10 i) (and (= x 10) (< i 9))) 
        sum
        (recur (inc i) (first tail) (rest tail) (+ (* (- 10 i) x) sum)))))

(defn check-isbn [data] 
  (zero? (mod (check-sum data) 11)))

(defn isbn? [isbn] ;; <- arglist goes here
  (let [data (seq (filter #(or (Character/isDigit %) (= \X %) (= \x %)) isbn))]
    (if (= (count data) 10)
      (check-isbn (transform-to-num data))
      false)))
