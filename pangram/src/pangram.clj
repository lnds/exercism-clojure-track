(ns pangram 
   (:require [clojure.string :refer [lower-case]]))

(defn pangram? [text] 
  (= 26 
     (count (->> text lower-case (filter #(Character/isLetter %)) set))))
