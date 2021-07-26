(ns minesweeper
  (:require [clojure.string :refer [join split-lines]]))

(def line-separator (System/getProperty "line.separator"))

(defn- empty-row [len] (repeat (+ len 2) \space))

(defn- pad-row [row] (str \space row \space)) 

(defn- expand-row [row i len minefield]
  (case row
    :north (if (zero? i) (empty-row len) (pad-row (nth minefield (dec i))))
    :south (if (= i (dec (count minefield))) (empty-row len) (pad-row (nth minefield (inc i))))
    (pad-row (nth minefield i))))

(defn- mine [j row] (if (= \* (nth row j)) 1 0))

(defn- count-mines [j this-row north-row south-row]
  (+ (mine (dec j) north-row) (mine j north-row) (mine (inc j) north-row)
     (mine (dec j) this-row)  (mine j this-row)  (mine (inc j) this-row)
     (mine (dec j) south-row) (mine j south-row) (mine (inc j) south-row)))

(defn- calc-cell [i c this-row north-row south-row]
  (if (= c \space)
    (let [total (count-mines (inc i) this-row north-row south-row)]
      (if (zero? total) " " (str total)))
    (str c)))

(defn- calc-near-mines [i row minefield]
  (let [north-row (expand-row :north i (count row) minefield)
        south-row (expand-row :south i (count row) minefield)
        this-row  (expand-row :this  i (count row) minefield)]
        (join (map-indexed #(calc-cell %1 %2 this-row north-row south-row) row))))

(defn draw [board]
  (let [minefield (->> board split-lines)]
    (->> minefield
         (map-indexed #(calc-near-mines %1 %2 minefield))
         (join line-separator))))
