(ns difference-of-squares)

(defn sum-of-squares [n] 
  (let [n2 (* n n) n3 (* n n2)]
        (+ (/ n3 3) (/ n2 2) (/ n 6))))

(defn square-of-sum [n] 
  (let [sum (/ (* n (inc n)) 2)] (* sum sum)))

(defn difference [n] 
  (- (square-of-sum n) (sum-of-squares n)))
