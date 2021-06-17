(ns run-length-encoding)

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text] 
  (->> plain-text
       (partition-by identity)
       (mapcat #(if (= 1 (count %)) [(first %)] [(count %) (first %)]))
       (apply str)))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text] 
  (->> cipher-text
      (re-seq #"[\d]*?[\D]")
      (mapcat #(if (= 1 (count %)) % (repeat (Integer/parseInt (apply str (drop-last %))) (last %))))
      (apply str)))
