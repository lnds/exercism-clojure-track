(ns grains)

(defn square [s]
     (reduce *' (repeat (dec s) 2)))

(defn total []  
    (reduce +' (map square (range 1 65))))
