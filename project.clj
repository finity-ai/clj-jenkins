(defproject clj-jenkins "0.1.8"
  :description "Clojure client for Jenkins"
  :url "https://github.com/smallrivers/clj-jenkins"
  :license {:name "MIT public License"
            :url  "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.4.0"]
                 [aleph "0.4.3"]
                 [cheshire "5.8.0"]]
  :plugins [[lein-cljfmt "0.5.6"]]
  :autodoc {:name       "clj-jenkins"
            :page-title "clojure client for jenkins"})
