(ns poker (:require [clojure.string :refer [split]]))

(def A 14)

(defn card [rep]
   { :value (+ 2 (.indexOf ["2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A"] (apply str (drop-last rep))))
     :color (last rep) })

(defn hand [hand-str]
  (sort-by :value (map card (split hand-str #" "))))

(defn group-hand [hand]
  (sort (map count (map val (group-by :value hand)))))

(defn straight? [hand]
  (->> hand
    (map :value)
    (partition 2 1)
    (every? #(or (= (first %) (dec (second %))) (and (= 5 (first %)) (= A (second %)))))))

(defn flush? [hand]
  (apply = (map :color hand)))

(defn classify [hand]
  (let [grouped (group-hand hand)]
    (cond
      (= [1 4] grouped) :four-of-a-kind
      (= [2 3] grouped) :full-house
      (= [1 1 3] grouped) :three-of-a-kind
      (= [1 2 2] grouped) :two-pair
      (= [1 1 1 2] grouped) :one-pair
      (and (flush? hand) (straight? hand)) :straight-flush
      (straight? hand) :straight
      (flush? hand) :flush
      :else :high-card)))

(def rank 
  [:high-card, :one-pair, :two-pair, :three-of-a-kind, :straight, :flush, :full-house, :four-of-a-kind, :straight-flush])

(defn extract-high-card-value [hand] (apply max (map :value hand)))

(defn extract-grouped-value[hand group-size]
  (apply max (map first (filter #(= group-size (count (val %))) (group-by :value hand)))))

(defn extract-min-grouped-value[hand group-size]
  (apply min (map first (filter #(= group-size (count (val %))) (group-by :value hand)))))  

(defn value-of [hand cl]
    (case cl
      :high-card (extract-grouped-value hand 1)
      :one-pair (extract-grouped-value hand 2)
      :two-pair (extract-grouped-value hand 2)
      :three-of-a-kind (extract-grouped-value hand 3)
      :flush (extract-grouped-value hand 1)
      :straight (let [value (extract-grouped-value hand 1)] (if (= A value) 1 value))
      :full-house (extract-grouped-value hand 3)
      :four-of-a-kind (extract-grouped-value hand 4)
      :straight-flush (extract-high-card-value hand)))

(defn untie [hand cl value]
  (case cl
    :full-house (extract-grouped-value hand 2)
    :four-of-a-kind (extract-grouped-value hand 1)
    :two-pair (extract-min-grouped-value hand 2)
    :three-of-a-kind (extract-grouped-value hand 1)
    value))

(defn untie2 [hand cl value]
  (case cl
    :high-card (vec (reverse (map :value (drop-last hand))))
    :two-pair (extract-grouped-value hand 1)
    value))

(defn map-hands [hands]
  (->> hands
    (map 
      #(let [h (hand %) cl (classify h) value (value-of h cl)] 
        (hash-map :hand %  :rank (.indexOf rank cl) :value value
                  :untie (untie h cl value) :untie2 (untie2 h cl value) )))))

(defn tie[h1 h2]
  (and 
    (= (:rank h1) (:rank h2)) 
    (= (:value h1) (:value h2)) 
    (= (:untie h1) (:untie h2))
    (= (:untie2 h1) (:untie2 h2))))

(defn best-hands [hands] 
  (cond 
    (empty? hands) []
    (= 1 (count hands)) hands
    :else 
      (let [sorted (reverse (sort-by (juxt :rank :value :untie :untie2)  (map-hands hands)))
            winner (first sorted)]
            (map :hand (take-while #(tie winner %) sorted)))))
