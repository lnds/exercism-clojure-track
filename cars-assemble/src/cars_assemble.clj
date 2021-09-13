(ns cars-assemble)

(def cars-per-hour 221.0)

(defn production-rate
  "Returns the assembly line's production rate per hour,
   taking into account its success rate"
  [speed]
  (case speed
    (1 2 3 4) (* speed cars-per-hour)
    (5 6 7 8) (* speed cars-per-hour 0.9)
    9 (* speed cars-per-hour 0.8)
    10 (* speed cars-per-hour 0.77) 
    0.0))

(defn working-items
  "Calculates how many working cars are produced per minute"
  [speed]
  (quot (int (production-rate speed)) 60))
