(ns bird-watcher)

(def last-week [0 2 5 3 7 8 4])

(def birds-per-day [2 5 0 7 4 1])

(defn today [birds]
  (last birds))

(defn inc-bird [birds]
  (conj (vec (pop birds)) (inc (last birds))))

(defn day-without-birds? [birds]
  (some? (some zero? birds)))

(defn n-days-count [birds n]
  (reduce + (take n birds)))

(defn busy-days [birds]
  (count (filter #(>= % 5) birds)))

(defn- check-odd [list last]
  (cond 
    (empty? list) true
    (= (first list) (- 1 last)) (check-odd (rest list) (first list))
    :else false))

(defn odd-week? [birds]
  (= birds [1 0 1 0 1 0 1]))


