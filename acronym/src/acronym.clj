(ns acronym (:require [clojure.string :refer [upper-case capitalize split]]))

(defn- sep-word [word]
  (->>
    (cons
      (first word)
      (->> word
        (drop-while #(Character/isUpperCase %))
        (filter #(Character/isUpperCase %))))
    (apply str)
    upper-case))

(defn acronym [text] 
  (apply str (->> 
    (split text #"[ -]")
    (map sep-word))))
