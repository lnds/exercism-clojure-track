(ns say)

(def digits ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", 
  "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",])

(def tens ["twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety", ])

(declare number)

(defn sufix_number [n m sufix]
  (let [a (quot n m) b (mod n m)]
    (if (zero? b) 
        (str (number a) " " sufix)
        (str (number a) " " sufix " " (number b)))))

(defn number [num] 
  (cond
    (neg? num) (throw (IllegalArgumentException. "must be positive number"))
    (< num 20) (digits num)
    (some #(= num %) [20 30 40 50 60 70 80 90]) (tens (quot (- num 20) 10))
    (< num 100) (str (number (* (quot num 10) 10)) "-" (number (mod num 10)))
    (== num 100) "one hundred"
    (< num 1000) (sufix_number num 100 "hundred")
    (< num 1000000) (sufix_number num 1000 "thousand")
    (< num 1000000000) (sufix_number num 1000000 "million")
    (< num 1000000000000) (sufix_number num 1000000000 "billion")
    :else (throw (IllegalArgumentException. "not supported"))))
