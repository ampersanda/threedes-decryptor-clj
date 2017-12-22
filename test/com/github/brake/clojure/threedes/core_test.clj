(ns com.github.brake.clojure.threedes.core-test
  (:require [clojure.test :refer :all]
            [com.github.brake.clojure.threedes.core :as threedes-core]))

(def key-hex "C5B3174D909AF7F4FB6C374E88A28EE1223A032E086CBB74")
(def data-encrypted "284D8D512EA42CA7072D449E7ECCEC919753FC4AA941BA98920AC5E0F17EE2FA52AD554866CCA678737987802FF75B8912B5F9E4BA6B9EF274A292B63DC463724580CBF434AFD7DC78CD7D6AD28E4655547795B73C774D66B21BC979530074D09CB9EAEEA231CB8CBAB041539236137C86FA9F0752438333")
(def data-decrypted "8E2FA764881E8A2DB675695B2C80FE0382BC9355978E8DAFBAE51CB56FD086AF683C41B6DE6730D040E96C11F80DA05D636B0C36C8929125203D4618648ABB036405A7935C1F759720E63B07525AA255CDD7A0A8A050362CBF15189C44EDC84F0D1299ED907512F62C7E6BD645CA3B239148C90C00000000")

(deftest a-test
  (testing "Checking decryption..."
    (let [result (threedes-core/process-decryption key-hex data-encrypted)]
      (is (= result data-decrypted)))))



