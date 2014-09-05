;;; A bit hacky, but it's not production code after all

(require '[clojure.java.io :refer :all])

(defn mean [samples]
  (/ (reduce + samples) (count samples)))

(defn std-dev [samples]
  (let [n (count samples)
	mean (/ (reduce + samples) n)
	intermediate (map #(Math/pow (- %1 mean) 2) samples)]
    (Math/sqrt
     (/ (reduce + intermediate) n))))

(defn call [[sym & args]]
  (apply (resolve sym) args))

(defmacro time-millis [expr]
  `(let [before# (System/currentTimeMillis)]
    ~expr
    (- (System/currentTimeMillis) before#)))

(defn clojure-sources [dirname]
  (->> dirname
       file
       file-seq
       (map #(.getPath %))
       (filter #(.contains % ".clj"))))


(defn benchmark-single [command file]
  (load-file file)
  (dotimes [i 5] ;give the jvm some opportunity for optimizations
    (call command))
  (let [times (doall
               (for [x (range 100)]
                 (time-millis (call command))))]
    (println (format "------\nFile: %s\nTotal:%d Average:%.3f Stddev:%.3f"
                     file
                     (reduce + times)
                     (double (mean times))
                     (double (std-dev times))))))

(defn benchmark [problemID command]
  (map (partial benchmark-single command) (clojure-sources (str problemID))))
