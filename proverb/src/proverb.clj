(ns proverb (:require [clojure.string :refer [join]]))

(defn recite [list]
    (let [proverb 
        (->> list
             (partition 2 1)
             (map #(str "For want of a " (first %) " the " (second %) " was lost.")))]
      (if-let [elem (first list)]
        (join "\n" (concat proverb [(str "And all for the want of a " elem ".")]))
        "" ) ))
