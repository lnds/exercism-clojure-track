(ns interest-is-interesting)

(defn interest-rate
  "Calculate the interest rate based on the specified balance:"
  [balance]
  (cond 
    (neg? balance) -3.213
    (< balance 1000) 0.5
    (< balance 5000) 1.621
    :else 2.475
  )
  )

(defn annual-yield
  "Calculate annual yield based o interest rate"
  [balance]
  (/ (*' balance (interest-rate balance)) 100.0M))

(defn annual-balance-update
  "Calculate annual balance based on interest"
  [balance]
  (bigdec (+' balance (annual-yield balance))))

(defn amount-to-donate
  "Calculate amount to donate based on tax free percentage allowed by law"
  [balance tax-free-percentage]
  (if (neg? balance)
    0
    (int (*' 2M (/ (*' balance tax-free-percentage) 100.0M)))))