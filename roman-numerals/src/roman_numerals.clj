(ns roman-numerals)

(defn unit [part base mid limit]
  (case part
      (1 2 3) (apply str (repeat part base))
      4 (str base mid)
      (5 6 7 8) (str mid (apply str (repeat (- part 5) base)))
      9 (str base limit)
      ""))

(defn part [num digit] (quot (mod num (* 10 digit)) digit))

(defn numerals [num] 
  (str 
    (unit (part num 1000) "M" "" "")
    (unit (part num 100)  "C" "D" "M")
    (unit (part num 10)   "X" "L" "C")
    (unit (part num 1)    "I" "V" "X")))
