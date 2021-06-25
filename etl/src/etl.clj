(ns etl)

(require '[clojure.string :as str])

(defn zip-freqs [source]
    (for [[freq words] source word words]
          [(str/lower-case word) freq]))

(defn transform [source] 
  (->> source
    zip-freqs
    flatten
    (apply hash-map)))
