;; Not quite as succing as the Haskell fibs = 1:1:zipWith (+) fibs (tail fibs)
;; I dare say it carries the intent better, though, and one can use this general idea
;; to generate any sequence defined by a recurrent relation.
(def fibs
  (->> [1 1]
       (iterate (fn [[a b]]
                  [b (+ a b)]))
       (map first)))


(defn solve [n]
  (->> fibs
       (filter even?)
       (take-while #(< % n))
       (reduce +)))

(solve 4000000)
