(ns word-count.core)
(require ['clojure.string :as 'str])

(defn create-word-count-hash [input-seq hash]
    (let [word    (first input-seq)
          current (get-in hash [word])]
        (cond
            (nil? word)    hash
            (nil? current) (create-word-count-hash (rest input-seq) (assoc hash word 1))
            :else          (create-word-count-hash (rest input-seq) (update-in hash [word] inc)))))
            
(defn word-count [s]
   (let
        [lower-s (str/lower-case s)
         no-punct (.replaceAll lower-s "[^a-z0-9 ]" "")
         words (.split no-punct "\\W+")]
        (create-word-count-hash words {})))
