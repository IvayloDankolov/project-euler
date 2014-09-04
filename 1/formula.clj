(def extra-terms [0,0,0,0,3,3,8,14,14,14,23,33,33,45,45])
(def extra-count [0,1,1,1,2,2,3, 4, 4, 4, 5, 5, 5, 6, 7])

(defn solve[n]
	(let [m (quot n 15)
		  r (rem  n 15)]
		  (+ (quot (* 105 m (dec m))
		  	       2)
		     (* 45 m)
		     (extra-terms r)
		     (* m 15 (extra-count r)))))