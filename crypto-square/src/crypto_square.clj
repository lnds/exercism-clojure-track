(ns crypto-square (:require [clojure.string :as s]))

(defn normalize-plaintext [plain] 
  (->> plain
    s/lower-case
    (filter #(or (Character/isLetter %) (Character/isDigit %)))
    (apply str)))

(defn square-size [plain]
  (int (Math/ceil (Math/sqrt (count plain)))))

(defn- chr-segments [plain]
  (let [normalized (normalize-plaintext plain)
        size (square-size normalized)]
      (partition size size (repeat nil) normalized)))

(defn plaintext-segments [plain]
  (->> plain
    chr-segments
    (map (partial apply str))))

(defn ciphertext [plain]
  (s/join (apply map str (chr-segments plain))))

(defn normalize-ciphertext [plain]
  (s/join " " (apply map str (chr-segments plain))))
