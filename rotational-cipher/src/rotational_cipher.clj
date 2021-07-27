(ns rotational-cipher)

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn rot-left [n]
  (->> alphabet
       cycle
       (drop n)
       (take 26)))

(defn rot-right [n]
  (rot-left (- 26 (rem n 26))))

(defn rotate [input key] 
  (let [cipher (if (neg? key) (rot-right (Math/abs key)) (rot-left key))
       collate (zipmap alphabet cipher)]
       (->> input
            (map #(cond
                  (not (Character/isLetter %)) %
                  (Character/isLowerCase %) (collate %)
                  :else (Character/toUpperCase (collate (Character/toLowerCase %)))))
            (apply str))))
