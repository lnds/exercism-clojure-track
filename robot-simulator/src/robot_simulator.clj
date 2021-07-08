(ns robot-simulator)

(defrecord Robot [coordinates bearing])

(defn robot [coordinates bearing] 
  (Robot. coordinates bearing))

(def turn-right {:north :east, :east :south, :south :west, :west :north})

(def turn-left {:north :west, :west :south, :south :east, :east :north})

(defn- advance [coord bearing]
    (case bearing
      :north (update-in coord [:y] inc)
      :south (update-in coord [:y] dec)
      :east  (update-in coord [:x] inc)
      :west  (update-in coord [:x] dec)
      coord))

(defn- move [robot action]
  (case action 
    \R (update-in robot [:bearing] turn-right)
    \L (update-in robot [:bearing] turn-left)
    \A (update-in robot [:coordinates] advance (:bearing robot))
    robot))

(defn simulate [movements robot] (reduce move robot movements))


