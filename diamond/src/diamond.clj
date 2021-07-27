(ns diamond)

(defn- mirror [data]
  (concat data (rest (reverse data))))        

(defn- format-char[width [i c]]
  (let [fill (vec (repeat width \space))]
    (->> (assoc fill i (char c))
         reverse
         mirror
         (apply str))))

(defn diamond [letter] 
  (let [ini (int \A)
        end (inc (int letter))
        width (- end ini)]
    (->>
      (mirror (map-indexed vector (range ini end)))
      (map #(format-char width %)))))
