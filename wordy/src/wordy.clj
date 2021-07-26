(ns wordy (:require [clojure.string :refer [lower-case split]]))

(defn- parse-num [word]
  (try
      (Integer/parseInt word)
      (catch NumberFormatException _ :error)))

(defn- scan [text]
  (->> 
      (split (lower-case text) #" ")
      (map #(case %
            "what" :what
            "is" nil
            "plus" :plus
            "minus" :minus
            "multiplied" :multiplied
            "divided" :divided
            "by"  nil
            (parse-num %)))
      (keep identity)))

(defn- parse [[a & [op & [b & tokens]]]]
  (if (nil? op)
    a
    (case op
      :plus (recur (cons (+ a b) tokens))
      :minus (recur (cons (- a b) tokens))
      :multiplied (recur (cons (* a b) tokens))
      :divided (recur (cons (/ a b) tokens))
      (throw (IllegalArgumentException. "invalid operation")))))

(defn- parse-question [expression]
   (let [tokens (scan expression)]
      (if (not= :what (first tokens))
        (throw (IllegalArgumentException. "invalid question"))
        (parse (rest tokens)))))

(defn evaluate [expression]
  (if (not= \? (last expression))
    (throw (IllegalArgumentException. "not-a-question"))
    (parse-question (apply str (drop-last expression)))))
