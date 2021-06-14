(ns rna-transcription)

(def strands {\G \C, \C \G, \T \A, \A \U})

(defn to-rna [dna] 
  (->> 
    (seq dna)
    (map #(let [found (strands %)] (if (some? found) found (assert false))))
    (apply str)))
