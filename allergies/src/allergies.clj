(ns allergies)

(def alergenes [
  :eggs 
  :peanuts 
  :shellfish 
  :strawberries 
  :tomatoes 
  :chocolate 
  :pollen
  :cats
])

(defn allergies [score]
  (if (zero? score)
    []
    (->>
      (filter #(bit-test score %) (range 0 8))
      (map #(alergenes %)))))

(defn allergic-to? [score alergene]
  (some #(= alergene %) (allergies score)))
