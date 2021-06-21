(ns hamming)

(defn distance [strand1 strand2] ; <- arglist goes here
  (when (= (count strand1) (count strand2)) 
      (->> (map vector strand1 strand2)
           (filter (partial apply not= ))
           count)))