(defproject threedes "0.1.0"
  :description "Tripple DES (3DES) CBC decryptor"
  :url "http://github.com/brake"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot com.github.brake.clojure.threedes.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
