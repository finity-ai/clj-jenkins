(defproject clj-jenkins "0.1.10"
  :description "Clojure client for Jenkins"
  :url "https://github.com/finity-ai/clj-jenkins"
  :license {:name "MIT public License"
            :url  "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/tools.logging "1.2.4"]
                 [aleph "0.5.0"]
                 [cheshire "5.11.0"]]
  :plugins [[lein-cljfmt "0.9.0"]]
  :autodoc {:name       "clj-jenkins"
            :page-title "clojure client for jenkins"})
