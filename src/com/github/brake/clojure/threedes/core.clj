(ns com.github.brake.clojure.threedes.core
  (:gen-class)
  (:require [com.github.brake.clojure.threedes.args :as args-parser])
  (:import [javax.xml.bind DatatypeConverter]))

(defn print-result
  [result]
  (println "Result: [" result "]"))

(defn decrypt-data
  [cipher data]
  (.doFinal cipher data))

(defn process-decryption
  [& args]
  (when-let [[cipher data :as all] (apply args-parser/parse-args args)]
    (some-> (decrypt-data cipher data)
            (DatatypeConverter/printHexBinary))))

(defn -main
  [& args]
  (some-> (apply process-decryption args)
          print-result))
