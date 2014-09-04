(defn solve [n]
	(->> n
		 range
		 (filter #(or (= 0 (rem % 3)) (= 0 (rem % 5))))
		 (reduce + 0)))

(solve 1000)