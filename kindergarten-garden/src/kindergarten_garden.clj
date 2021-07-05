(ns kindergarten-garden (:require [clojure.string :as str]))

(def flowers {
  \G :grass,
  \C :clover,
  \R :radishes,
  \V :violets,
})

(def childrens
  ["Alice" "Bob" "Charlie" "David" "Eve" "Fred" "Ginny" "Harriet" "Ileana" "Joseph" "Kincaid" "Larry"])

(defn garden 
  ([cups] (garden cups childrens))
  ([cups students] 
    (let 
       [[top bottom] (str/split cups #"\n")
        full-garden (flatten (map vector (partition 2 top) (partition 2 bottom)))
        translated-garden (map flowers full-garden)
        cups (partition-all 4 translated-garden)
        students (map #(keyword (str/lower-case %)) (sort students))]
      (zipmap students cups))))
