(ns clj-jenkins.core
  (:import [com.ning.http.client AsyncHttpClient Response])
  (:require [clojure.data.json :as json])
  (:require [clj-jenkins.http :as http]))
  
  (def ^{:dynamic true} *jenkins*)
  (def ^{:dynamic true} *client*)
  
  (def build-client http/build-client)
  
  (defmacro with-jenkins
    "Evaluates body in a context in which *jenkins* is
    bound to the given jenkins hostname (ie 'localhost:8080')."
    [hostname & body]
    `(binding [*jenkins* ~hostname *client* (build-client)]
        (do ~@body)
        (.close *client*)))
  
  (defn get-json
    [url]
    "Return the JSON retrieved at the specified url using http GET"
    (http/get-json *client* url))
  
  (defn get-jenkins-infos
    "Return the information (map) of the Jenkins server"
    []
    (get-json (format "http://%s/api/json" *jenkins* )))
  
  (defn list-jobs
    []
    "List jobs configured in the jenkins build"
    (let [bjson (get-jenkins-infos)]
      (:jobs bjson)))
  
  (defn list-queue-items
    []
    "List jobs in queue to be built"
    (let [bjson (get-json (format "http://%s/queue/api/json" *jenkins*))]
      (:items bjson)))
      
  (defn list-builds-inprogress
    []
    "List builds in progress"
    (let [bjson (get-json (format "http://%s/computer/api/json?depth=1" *jenkins*))]
      (flatten
        (for [computer (:computer bjson)]
          (filter :currentExecutable (:executors computer))))))
  
  (defn list-builds
    [jobname]
    "List builds for the specified job"
    (get-json (format "http://%s/job/%s/api/json?depth=1" *jenkins* jobname )))
      
  (defn submit-job
    [jobname]
    "Tells Jenkins to execute a new build of jobname"
    (get-json (format "http://%s/job/%s/build" *jenkins* jobname)))
  