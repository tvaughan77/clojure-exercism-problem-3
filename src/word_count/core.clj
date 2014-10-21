(ns word-count.core)
(require ['clojure.string :as 'str])

(defn foo [input-seq hash]
    (let [word    (first input-seq)
          current (get-in hash [word])]
        (cond
            (nil? word)    hash
            (nil? current) (foo (rest input-seq) (assoc hash word 1))
            :else          (foo (rest input-seq) (update-in hash [word] inc)))))
            
(defn word-count [s]
   (let
        [lower-s (str/lower-case s)
         no-punct (.replaceAll lower-s "[^a-z0-9 ]" "")
         words (.split no-punct "\\W+")]
        (foo words {})))
