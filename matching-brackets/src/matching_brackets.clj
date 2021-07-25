(ns matching-brackets)

(def open-bracket {\) \(, \] \[, \} \{ })

(defn push [c stack] (conj stack c))

(defn consume [c stack] 
  (if (= (open-bracket c) (peek stack))
    (if (empty? stack) [] (pop stack))
    (conj stack \?)))

(defn match-char[c stack]
  (case c
    (\( \[ \{) (push c stack)
    (\) \] \}) (consume c stack)
    stack))

(defn valid? [str] 
  (->> str
    (reduce #(match-char %2 %1) [])
    empty?))