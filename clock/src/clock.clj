(ns clock)

(defn clock->string [clock] 
  (format "%02d:%02d" (quot clock 60) (mod clock 60)))

(defn clock [hours minutes]
    (mod (+ (* 60 hours) minutes) 1440))

(defn add-time [clock time] 
  (clock/clock 0 (+ clock time)))
