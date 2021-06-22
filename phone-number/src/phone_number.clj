(ns phone-number)

(defn number [num-string] 
  (let 
    [num-seq (filter #(Character/isDigit %) num-string)
     first-digit (first num-seq)
     len-num (count num-seq)
     first-exchange (nth num-seq 3)]
    (cond
      (and (= 11 len-num) (= \1 first-digit)) (apply str (rest num-seq))
      (or (<= 11 len-num)  (>= 9 len-num)
          (= \1 first-digit) (= \0 first-digit)
          (= \0 first-exchange) (= \1 first-exchange)) "0000000000"
      :else (apply str num-seq))))     

(defn area-code [num-string] ;; <- arglist goes here
  (let [num (number num-string)] (->> num (take 3) (apply str))))


(defn pretty-print [num-string] ;; <- arglist goes here
  (let [num (number num-string)] (str "(" (subs num 0 3) ") " (subs num 3 6) "-" (subs num 6))))
