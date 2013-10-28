(ns words.core)

(defn strip [^String text ^String pattern]
    (.replaceAll text pattern ""))

(defn remove-line-breaks [^String text]
  (strip text "\n"))

(defn remove-spaces [^String text]
  (-> text 
      (strip " ")
      (strip ",")
      (strip "\\.")))

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
    ; (println stripped)
    (count stripped)))

(defn -main [& args]
  (if (seq? args)
    (let [text          (-> args
                            first
                            slurp)
          total-size    (count text)
          stripped-size (count-characters text)]
        (println (str "Tamanho total: " total-size " bytes"))
        (println (str "Tamanho real : " stripped-size " bytes"))
        (println (str "Progresso    : " (format "%,.1f" (* (/ stripped-size 20000.0) 100)) "%")))
      (println "Informe o nome do arquivo HTML"))
  (println))