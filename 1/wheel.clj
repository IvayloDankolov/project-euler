(def wheel 
	(cycle [3, 2, 1, 3, 1, 2, 3]))

;; A bit slower than the naive one because of the overhead, but very beatiful
(defn solve-pretty [n]
	(->> wheel
		 (reductions + 0)
		 (take-while #(< % n))
		 (reduce + 0)))

;; Ugly as sin (well, not really, but it IS dirty) 
(defn solve [n]
	(loop [curr 0 res 0 dists wheel]
		(if (< curr n)
			(recur (+ curr (first dists))
				   (+ res curr)
				   (rest dists))
			res)))