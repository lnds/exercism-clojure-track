(ns change)

(defn- min-coins [cached-amounts amount coins]
    (->> coins
      (filter #(<= % amount))
      (map #(conj (cached-amounts (- amount %) []) %))
      (apply min-key count)
      (assoc cached-amounts amount)))

(def memo-coins (memoize min-coins))

(defn issue [sum coins]
  (cond
    (zero? sum) []
    (or (neg? sum) (every? #(< sum %) coins)) (throw (IllegalArgumentException. "cannot change"))
    :else    
      ((reduce #(memo-coins %1 %2 (sort coins)) {} (range 1 (inc sum)))) sum)