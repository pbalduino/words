(ns words.core)

(defn strip  [^String text ^String pattern]
    (.replaceAll text pattern ""))

(defn remove-line-breaks [^String text]
  (strip text "\n"))

(defn remove-spaces [^String text]
  (strip text " "))

(defn remove-tags [^String text]
  (-> text
      (strip "<title>.*</title>")
      (strip "<head>.*</head>")
      (strip "<!.*>")
      (strip "<\\w*>")
      (strip "</\\w*>")
      (strip "<.*>")))

(defn count-characters [^String text]
  (let [stripped (-> text
                     remove-tags
                     remove-spaces
                     remove-line-breaks)]
    (println stripped)
    (.length stripped)))

(defn -main [& args]
  (if (seq? args)
      (-> args
          first
          slurp
          count-characters
          println)
      (println "Informe o nome do arquivo HTML"))
  (println)
)