(ns phone-number)

(defn number [num-string] 
  (let 
    [num-seq (filter #(Character/isDigit %) num-string)
     first-digit (first num-seq)
     len-num (count num-seq)]
    (cond
      (and (= 11 len-num) (= \1 first-digit)) (apply str (rest num-seq))
      :else (apply str num-seq))))

(defn area-code [num-string] ;; <- arglist goes here
  (let [num (number num-string)]
    (->> 
      (if (= 9 (count num)) num (rest num))
      (take 3)
      (apply str))))


(defn pretty-print [num-string] ;; <- arglist goes here
  ;; your code goes here
)
