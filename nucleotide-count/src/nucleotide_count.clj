(ns nucleotide-count)

(defn count-of-nucleotide-in-strand [nucleotide strand] 
  (assert (contains? #{\A \T \C \G} nucleotide))
  (count (filter #(= nucleotide %) (seq strand))))

(defn nucleotide-counts [strand] 
  {\A (count-of-nucleotide-in-strand \A strand)
   \T (count-of-nucleotide-in-strand \T strand)
   \C (count-of-nucleotide-in-strand \C strand)
   \G (count-of-nucleotide-in-strand \G strand)})
