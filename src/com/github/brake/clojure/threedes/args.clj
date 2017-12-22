(ns com.github.brake.clojure.threedes.args
  (:import [javax.xml.bind DatatypeConverter]
           [javax.crypto Cipher]
           [javax.crypto.spec DESedeKeySpec SecretKeySpec IvParameterSpec]))

(def triple-des-key-length-hex (* 2 DESedeKeySpec/DES_EDE_KEY_LEN))
(def algo-spec "DESede/CBC/NoPadding")
(def algo "DESede")
(def base-cipher (Cipher/getInstance algo-spec))
(def key-parameter-name "<3DES-key-hex>")
(def data-parameter-name "<data-to-decrypt-hex>")
(def usage-text ["", "Usage:", (str "\t<program> " key-parameter-name " " data-parameter-name), ""])
(def des-block-size 8)
(def iv (-> des-block-size
            (byte-array (byte 0))
            (IvParameterSpec.)))

(defn valid-3des-key? 
  [s] 
  (= (count s) triple-des-key-length-hex))

(defn hex-to-bytes
  [hex-str]
  (try
    (DatatypeConverter/parseHexBinary hex-str)
    (catch IllegalArgumentException _ nil)))

(defn make-cipher
  [hex-key]
  (when (valid-3des-key? hex-key)
    (doto base-cipher
      (.init Cipher/DECRYPT_MODE
             (-> hex-key
                 hex-to-bytes
                 (SecretKeySpec. algo))
             iv))))

(defn usage
  []
  (doseq [text usage-text]
    (println text)))

(defn report-parameter-error
  [parameter-name error-text]
  (println "Error in parameter" parameter-name ":" error-text))

(defn report-invalid-args 
  [pkey pdata error-text]
  (let [result (transient [])]
    (when (nil? pkey)
      (conj! result [key-parameter-name error-text]))
    (when (nil? pdata)
      (conj! result [data-parameter-name error-text]))
    
    (mapv #(apply report-parameter-error %) (persistent! result)))
  (usage))

(defn parse-args
  [& args]
  (let [[key-hex data-hex :as all] args]
    (if (or (nil? all) (some nil? [key-hex data-hex]))
      (report-invalid-args key-hex data-hex "Missing required parameter")
      (let [cipher (make-cipher key-hex)
            data (hex-to-bytes data-hex)]
        (if (some nil? [cipher data])
          (report-invalid-args cipher data "Error while parsing parameter")
          [cipher data])))))

