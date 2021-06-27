(ns strain)

(defn retain [pred coll]
  (persistent! 
    (reduce 
      (fn [result elem] (if (pred elem) (conj! result elem) result))
      (transient []) 
      coll)))

(defn discard [pred coll] 
  (retain (complement pred) coll))
