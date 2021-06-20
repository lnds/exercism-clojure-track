(ns beer-song)

(defn verse
  "Returns the nth verse of the song."
  [num]
  (cond 
    (zero? num) "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    (= 1 num) "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    (= 2 num) "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    (< num 99) (str num " bottles of beer on the wall, " num " bottles of beer.\nTake one down and pass it around, " (dec num) " bottles of beer on the wall.\n")
    :default ""))


(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start] (sing start 0))
  ([start end]
    (->> (range end (inc start)) 
         reverse 
         (map #(verse %)) 
         (clojure.string/join "\n"))))