(ns meetup)

(defn leap-year? [year]
  (or
    (and (zero? (mod year 4)) (pos? (mod year 100)))
    (zero? (mod year 400))))

(defn month-days [year month]
  (if (and (= month 2) (leap-year? year))
      29
      (let [days-in-month [0 31 28 31 30 31 30 31 31 30 31 30 31]]
        (nth days-in-month month))))

; uses zeller congruence algorithm
(defn zeller [year month day]
  (let [m (+ (mod (+ month 9) 12) 3)
        y (- year (quot (- m month) 12))
        J (quot y 100) K (mod y 100) ]
    (mod (+ day (quot (* 26 (inc m)) 10) K (quot K 4) (quot J 4) (* 5 J))
         7)))

(defn day-of-week [year month day]
  (let [days [:saturday :sunday :monday :tuesday :wednesday :thursday :friday]]
    (nth days (zeller year month day))))

(defn look-for-first-wday [year month day weekday iter direction]
  (def next (direction {:up inc :down dec}))
  (defn inner-look [day iter]
      (if (= weekday (day-of-week year month day))
        (if (zero? iter) [year month day] (recur (next day) (dec iter)))
        (recur  (next day) iter)))
  (inner-look day iter))

(defn look-for-wday [year month weekday pos]
  (case pos
    :first  (look-for-first-wday year month 1 weekday 0 :up)
    :second (look-for-first-wday year month 1 weekday 1 :up)
    :third  (look-for-first-wday year month 1 weekday 2 :up)
    :fourth (look-for-first-wday year month 1 weekday 3 :up)
    :last (look-for-first-wday year month (month-days year month) weekday 0 :down)
    (look-for-first-wday year month 19 weekday 0 :down)))

(defn meetup [month year weekday ordinal] 
  (look-for-wday year month weekday ordinal))