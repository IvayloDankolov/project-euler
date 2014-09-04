(defn sum-upto [n]
	(quot (* n (inc n)) 2))

(defn solve [m]
	(let [n (dec m)]
		(+ (* 3 (sum-upto (quot n 3)))
		   (* 5 (sum-upto (quot n 5)))
		   (- (* 15 (sum-upto (quot n 15)))))))