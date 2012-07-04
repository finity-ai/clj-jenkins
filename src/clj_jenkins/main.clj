(ns clj-jenkins.main
  (:require [clj-jenkins.core :as jenkins]))

  (defn -main
    []
    "Main demo app for the client"
    (jenkins/with-jenkins "localhost:8080"
      (jenkins/submit-job "restmodel")
      (println "Submitted job")
      ; Wait for the job to be in-progress
      (Thread/sleep 5000)
      
      (println "Jobs : " (jenkins/list-jobs))
      (doseq [job (jenkins/list-jobs)]
        (println "Builds of job " (:name job) " are : "(jenkins/list-builds (:name job))))
        (println "Queue : " (jenkins/list-queue-items))
      (println "Builds in progress : "(jenkins/list-builds-inprogress))))