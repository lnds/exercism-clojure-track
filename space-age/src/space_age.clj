(ns space-age)

(defn years_in_planet [seconds factor]
  (let [year_in_seconds (* 31557600.0 factor)]
    (/ seconds year_in_seconds)))

(defn on-mercury [seconds] (years_in_planet seconds 0.2408467))

(defn on-venus [seconds] (years_in_planet seconds 0.61519726))

(defn on-earth [seconds] (years_in_planet seconds 1.0))

(defn on-mars [seconds] (years_in_planet seconds 1.8808158))

(defn on-jupiter [seconds] (years_in_planet seconds 11.862615))

(defn on-saturn [seconds] (years_in_planet seconds 29.447498))

(defn on-neptune [seconds] (years_in_planet seconds  164.79132))

(defn on-uranus [seconds] (years_in_planet seconds 84.016846))
