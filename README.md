# 3DES CBC decryptor 
## Mainly for encrypted GSM 03.48 messages (excluding unencrypted header)

[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://opensource.org/licenses/MIT)

Decrypts ciphered (with 3DES CBC) part of GSM 03.48 message.

## Installation (from source)

First, install [Leiningen](https://leiningen.org/#install)

Then,
```
$ git clone https://github.com/brake/threedes-decryptor-clj.git
$ cd threedes-decryptor-clj
$ lein uberjar
```
Finally you're ready to use `./target/uberjar/threedes-<version>-standalone.jar`

## Usage

$ java -jar threedes-<version>-standalone.jar `<3DES-key-hex>` `<Data-hex>`
