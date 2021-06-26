(ns binary-search)

(defn middle [list] 
   (int (Math/floor (/ (count list) 2))))

(defn search-for [value list] 
  (case (count list) 
    0 (throw (Exception. "not found"))
    1 (if (= value (first list)) 0 (throw (Exception. "not found")))
    (let [m (middle list) pivot (nth list m)]
      (cond 
        (= pivot value) m
        (> pivot value) (search-for value (take m list))
        :else (+ m (search-for value (drop m list)))))))
