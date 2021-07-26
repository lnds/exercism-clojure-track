(ns isogram (:require [clojure.string :refer [lower-case]]))

(defn isogram? [text] 
    (= 1 
       (->> text 
            lower-case 
            (filter #(Character/isLetter %))
            frequencies 
            (apply max-key val)
            val)))
