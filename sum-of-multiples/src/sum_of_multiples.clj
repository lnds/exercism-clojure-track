(ns sum-of-multiples)

(defn- gcd [a b]
  (if (zero? b) a (recur b (rem a b))))

(defn- lcm [a b] (quot (* a b) (gcd a b)))

; Gauss formula
(defn- sum-mults [f n]
  (let [p (quot (dec n) f)]
      (quot (* (inc p) p f) 2)))

; fast calc for 2 multiples using Gauss formula
(defn- sum-of-2-multiples [[a b] n]
  (let [s1 (sum-mults a n)
        s2 (sum-mults b n)
        s3 (sum-mults (lcm a b) n)]
      (- (+ s1 s2) s3)))

(defn- multiple? [mults m]
      (some #(zero? (rem m %)) mults))

(defn sum-of-multiples [mults n]
  (if (= 2 (count mults))
      (sum-of-2-multiples mults n)
      (->> (range n) (filter #(multiple? mults %)) (reduce +))))