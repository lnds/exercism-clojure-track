(ns flatten-array)

(defn flatten [arr] 
  (->> arr clojure.core/flatten (filter some?)))
