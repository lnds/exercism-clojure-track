(ns perfect-numbers)

(defn factors-of [num] (filter #(zero? (mod num %)) (range 1 num)))

(defn sum-of-factors [num] (reduce + (factors-of num)))

(defn classify [num]
  (if (neg? num) 
    (throw (IllegalArgumentException. "negative number not allowed"))
    (let [sum-factors (sum-of-factors num)]
      (cond
        (= num sum-factors) :perfect
        (> num sum-factors) :deficient
        :else :abundant))))
