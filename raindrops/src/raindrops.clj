(ns raindrops)

(defn- factor-of? [f n]
  (zero? (rem n f)))

(defn convert [num] 
  (cond-> nil
      (factor-of? 3 num) (str "Pling")
      (factor-of? 5 num) (str "Plang")
      (factor-of? 7 num) (str "Plong")
      :else (or (str num))))