(ns elyses-destructured-enchantments)

(defn first-card
  "Returns the first card from deck."
  [deck] 
  (let [[first-card & rest] deck] first-card))

(defn second-card
  "Returns the second card from deck."
  [deck]
  (let [[first-card second-card & rest] deck] second-card))

(defn swap-top-two-cards
  "Returns the deck with first two items reversed."
  [deck]
  (let [[first-card second-card & remaining] deck] 
    (into [] (concat [second-card first-card] remaining))))

(defn discard-top-card
  "Returns a vector containing the first card and
   a vector of the remaining cards in the deck."
  [deck]
  (let [[first-card & remaining] deck]
    [first-card remaining] ) )

(def face-cards
  ["jack" "queen" "king"])

(defn insert-face-cards
  "Returns the deck with face cards between its head and tail."
  [deck]
  (let [[first-card & remaining] deck]
    (if (nil? first-card)
      face-cards
      (into []  (concat [first-card] face-cards remaining)))))