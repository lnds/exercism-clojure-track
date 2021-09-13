(ns log-levels
  (:require [clojure.string :as str]))

(defn message
  "Takes a string representing a log line
   and returns its message with whitespace trimmed."
  [s]
  (str/trim (last (str/split s #":"))))

(defn log-level
  "Takes a string representing a log line
   and returns its level in lower-case."
  [s]
  (-> s
    (str/split #":")
    first
    (str/replace #"\[" "")
    (str/replace #"\]" "")
    str/lower-case))

(defn reformat
  "Takes a string representing a log line and formats it
   with the message first and the log level in parentheses."
  [s]
  (str (message s) " (" (log-level s) ")"))
