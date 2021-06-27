(ns gigasecond)

(def GIGASECOND 1000000000)

(defn from [year month day]
    (let [from (java.time.LocalDateTime/of year month day 0 0)
          result (.plus from GIGASECOND java.time.temporal.ChronoUnit/SECONDS)]
        [(.getYear result) (.getMonthValue result) (.getDayOfMonth result)]))
