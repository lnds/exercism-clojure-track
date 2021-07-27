(ns protein-translation (:require [clojure.set :refer [map-invert]]))

(def translate-codon {
  "AUG"	"Methionine",
  "UUU", "Phenylalanine",
  "UUC" "Phenylalanine",
  "UUA" "Leucine",
  "UUG" "Leucine",
  "UCU" "Serine",
  "UCC" "Serine",
  "UCA" "Serine",
  "UCG" "Serine",
  "UAU"	"Tyrosine",
  "UAC"	 "Tyrosine",
  "UGU" "Cysteine",
  "UGC"	"Cysteine",
  "UGG"	"Tryptophan",
  "UAA"	"STOP",
  "UAG" "STOP",
  "UGA"	"STOP"})

(defn translate-rna [rna] 
  (->> rna
    (partition 3)
    (map (partial apply str))
    (map translate-codon)
    (take-while #(not= % "STOP"))))
  