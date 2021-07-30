(ns go-counting (:require [clojure.set :refer [union]]))

(defn- dimensions [grid]
    (if (empty? grid) [0 0] [(count (first grid)) (count grid)]))

(defn- cell [grid x y] (nth (nth grid y) x))

(defn- step [x y direction w h]
    (case direction
        :west  (if (zero? x) (step x y :north w h) [(dec x) y :west])
        :north (if (zero? y) (step x y :east  w h) [x (dec y) :north])
        :east  (if (= x (dec w))   (step x y :south w h) [(inc x) y :east])
        :south (if (= y (dec h))   [x y :halt]           [x (inc y) :south])
        [x y :halt]))

(def owner? {\W :white \B :black})

(defn- find-territory 
    ([grid w h x y]
      (let [stone (cell grid x y)]
        (if (not= \space stone)
            {:stones #{} :owner nil}
            (->> {:stones #{} :owner #{}}
                    (find-territory grid w h x y :west)
                    (find-territory grid w h x y :north)
                    (find-territory grid w h x y :east)
                    (find-territory grid w h x y :south)))))

    ([grid w h x y direction result]
      (let [stone (cell grid x y)
            stones (:stones result)
            owners (:owner result)
            [new-x new-y new-direction] (step x y direction w h)]
            (cond 
                (not= \space stone) {:stones stones :owner (conj owners (owner? stone)) }
                (= :halt new-direction) {:stones (conj stones [x y]),  :owner (conj owners (owner? stone))}
                :else (recur grid w h new-x new-y new-direction 
                        {:stones (conj stones [x y]) :owner owners} )))))

(defn- det-owner [owners]
    (cond
        (and (:white owners) (:black owners)) nil
        (:white owners) :white
        (:black owners) :black
        :else nil))            

(defn territory [grid [x y]]
    (let [[w h] (dimensions grid)]
        (assert (and (<= 0 x) (< x w)))
        (assert (and (<= 0 y) (< y h)))
        (let [stone (cell grid x y)
              terr (find-territory grid w h x y)]
              (assoc terr :owner (det-owner (:owner terr))))))

(defn- map-territory [terrs color]
  (->> terrs (filter #(= (:owner %) color)) (map :stones) (apply union)))

(defn territories [grid]
    (let [[w h] (dimensions grid)
          terrs (for [x (range w) y (range h) :when (= \space (cell grid x y))] (territory grid [x y]))
          black-territory (map-territory terrs :black)
          white-territory (map-territory terrs :white)
          null-territory  (map-territory terrs nil)]
        {:black-territory black-territory :white-territory white-territory :null-territory null-territory}))