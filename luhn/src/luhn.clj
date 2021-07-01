(ns luhn)

(defn valid? [num]
  (cond
    (some #(Character/isSpace %) num) (valid? (filter #(not (Character/isSpace %)) num))
    (some #(not (Character/isDigit %)) num) false
    (<= (count num) 1) false
    :else 
      (zero? 
        (rem 
          (->> num
           (filter #(Character/isDigit %))
           (map #(Character/digit % 10))
           reverse
           (map * (cycle [1 2]))
           (map #(if (> % 9) (- % 9) %))
           (reduce +))
         10))))

      