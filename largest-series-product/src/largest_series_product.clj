(ns largest-series-product)

(defn- to-digit [d]
  (let [dc (int d)]
      (if (and (>= dc 48) (<= dc 57)) (- dc 48) (throw (Exception. "invalid")))))

(defn- product-of [s]
  (reduce * (map #(to-digit %1) s)))

(defn largest-product [n num]
  (assert (<= n (count num)))
  (if (or (empty? num) (zero? n)) 1
      (apply max (for [s (partition n 1 num)] (product-of s)))))
 
