(ns atbash-cipher)

(defn traspose [c] c
  (if (Character/isLetter c) (char (- 122 (- (int c) 97))) c))

(defn encode [plain]
  (->>
    (clojure.string/lower-case plain)
    (filter #(or (Character/isLetter %) (Character/isDigit %)))
    (map traspose)
    (partition-all 5)
    (map (partial apply str))
    (clojure.string/join " ")))
