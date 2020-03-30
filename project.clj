(defproject clj-jenkins "0.1.9-SNAPSHOT"
  :description "Clojure client for Jenkins"
  :url "https://github.com/finity-ai/clj-jenkins"
  :license {:name "MIT public License"
            :url  "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/tools.logging "1.0.0"]
                 [aleph "0.4.6"]
                 [cheshire "5.10.0"]]
  :plugins [[lein-cljfmt "0.6.7"]]
  :autodoc {:name       "clj-jenkins"
            :page-title "clojure client for jenkins"})
