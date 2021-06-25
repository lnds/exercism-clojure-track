(ns sublist)

(defn comp-list [short-list long-list result]
      (cond
            (empty? short-list) result
            (some #(= short-list %) (partition (count short-list) 1 long-list)) result
            :else :unequal))

(defn classify [list1 list2] 
      (cond 
            (= list1 list2) :equal 
            (> (count list1) (count list2)) (comp-list list2 list1 :superlist)
            :else (comp-list list1 list2 :sublist)))
            
            
