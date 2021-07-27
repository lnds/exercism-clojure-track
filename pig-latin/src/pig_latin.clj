(ns pig-latin
       (:require [clojure.string :refer [replace split join]]))

(def vowel #"([aeiou]|xr|yt).+")
(def conso #"(s?qu|[^aeiou]+)(.+)")

(defn- translate-word [word]
  (cond 
    (re-matches vowel word) (str word "ay")
    (re-matches conso word) (replace word conso "$2$1ay")
    :else word))

(defn translate [text] 
  (->>
    (split text #" ")
    (map #(translate-word %))
    (join " ")))
