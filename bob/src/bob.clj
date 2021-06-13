(ns bob)

(require '[clojure.string :as str])

(defn ask? [s] (str/ends-with? s "?"))

(defn yell? [s]
  (let [letters (char-array s)
        a (count (filter #(Character/isLetter %) s))
        b (count (filter #(Character/isUpperCase %) s))]
        (and (> a 0) (> b 0) (= a b))))


(defn response-for [s] ;; <- arglist goes here
  (let  [s (str/trim s)
         ask (ask? s)
         yell (yell? s)]
    (cond
      (and ask yell) "Calm down, I know what I'm doing!"
      ask "Sure."
      yell "Whoa, chill out!"
      (str/blank? s) "Fine. Be that way!"
      :else "Whatever.")))

