(ns grade-school)

(defn grade [school grade]  
    (get school grade []))

(defn add [school name grade] 
    (update-in school [grade] (comp vec conj) name))

(defn sorted [school] 
   (->> school
        (map (fn [[k v]] {k (vec (sort v))}))
        (into (sorted-map))))
 