(defproject clj-jenkins "0.1.4"
  :description "Clojure client for Jenkins"
  :url "https://github.com/smallrivers/clj-jenkins"
  :license {:name "MIT public License"
            :url  "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [aleph "0.4.0"]
                 ; because of https://github.com/ztellman/aleph/issues/189
                 [potemkin "0.4.3"]
                 [cheshire "5.5.0"]]
  :plugins [[lein-cljfmt "0.4.1"]]
  :autodoc {:name       "clj-jenkins"
            :page-title "clojure client for jenkins"})
