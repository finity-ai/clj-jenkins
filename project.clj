(defproject clj-jenkins "0.1.0-SNAPSHOT"
  :description "Clojure client for Jenkins"
  :dependencies [ [org.clojure/clojure         "1.3.0"]
                  [com.ning/async-http-client  "1.6.2"]
                  [org.clojure/data.json       "0.1.1"]
                  [org.slf4j/slf4j-api         "1.6.1"]
                  [org.slf4j/slf4j-log4j12     "1.6.1"]
                  [log4j                       "1.2.16"]]
                  
  :dev-dependencies [[lein-zi "0.1.1"]] ; pom generation

  :autodoc {:name       "clj-jenkins"
            :page-title "clojure client for jenkins"}

  :main clj-jenkins.main)
