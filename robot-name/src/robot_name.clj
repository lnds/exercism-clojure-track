(ns robot-name)

(defn rand-letters [] (take 3 (shuffle (seq  "ABCDEFGHIJKLMNOPQRSTUVWXYZ"))))

(defn gen-name [] [(rand-letters) (rand-int 1000)])

(defn robot [] (atom (gen-name)))

(defn robot-name [robot] 
    (let [[letters num] @robot]
        (format "%s%03d" (apply str letters) num)))

(defn reset-name [robot] 
  (reset! robot (gen-name)))
