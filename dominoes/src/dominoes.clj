(ns dominoes)

(defn- combine [[a b] [c d]]
  (if (= b c) [a d] [a c]))
  
(defn- -- [x [head & tail]] ; remove first ocurrence of x from a list 
  (if (= head x) 
    tail
    (cons head (-- x tail))))
    
(defn can-chain? [stones]
  (cond
    (empty? stones) true
    (and (= 1 (count stones)) (let [[a b] (first stones)] (= a b))) true
    :else 
      (let [[a b] (first stones)
            pile (rest stones)
            matches (for [[x y] pile :when (or (= x b) (= y b))] [x y])]
            (some #(can-chain? (into [(combine [a b] %)] (-- % pile))) matches))))


 
