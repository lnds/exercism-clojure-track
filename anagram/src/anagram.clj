(ns anagram)

(require '[clojure.string :as str])

(defn is-anagram [s a]
  (let [ss (str/lower-case s) aa (str/lower-case a)]
    (and 
      (= (sort ss) (sort aa))
      (not= ss aa))))
  
(defn anagrams-for [word prospect-list] 
  (filter #(is-anagram word %)  prospect-list))
