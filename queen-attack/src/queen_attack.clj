(ns queen-attack (:require [clojure.string :as str]))

(defn board-string [pos] 
  (let [board (to-array-2d (repeat 8 (repeat 8 \_)))]
        (when-let [[r c] (:w pos)] (aset board r c \W))
        (when-let [[r c] (:b pos)] (aset board r c \B))
        (str (str/join "\n" (for [i (range 8)] (str/join " " (aget board i)))) "\n")))

(defn can-attack [pos]
  (let [[w-x w-y](:w pos)
        [b-x b-y] (:b pos)
        dx (Math/abs (- w-x b-x))
        dy (Math/abs (- w-y b-y))]
    (or (zero? dx) (zero? dy) (= dx dy))))
