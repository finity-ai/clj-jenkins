(ns clj-jenkins.core
  (:require [aleph.http :as aleph-http]))
  
(def ^{:dynamic true} *jenkins*)

(def ^{:dynamic true} *creds*)

(defmacro with-jenkins
  "Evaluates body in a context in which *jenkins* is
  bound to the given jenkins hostname (ie 'localhost:8080')."
  [hostname creds & body]
  `(binding [*jenkins* ~hostname
             *creds*   ~creds]
     (do ~@body)))

(defn get-json
  "Execute a GET query with optional basic-auth and return json"
  [url & [opts]]
  (let [{:keys [username password]} *creds*]
    (-> url
        (aleph-http/get (merge {:basic-auth [username password]
                                :as         :json}
                               opts))
        deref
        :body)))

(defn get-jenkins-infos
  "Return the information of the Jenkins server"
  []
  (get-json (format "http://%s/api/json" *jenkins*)))

(defn list-jobs
  "List jobs configured in the jenkins build"
  []
  (let [bjson (get-jenkins-infos)]
    (:jobs bjson)))

(defn list-queue-items
  "List jobs in queue to be built"
  []
  (let [bjson (get-json (format "http://%s/queue/api/json" *jenkins*))]
    (:items bjson)))

(defn list-builds-inprogress
  "List builds in progress"
  []
  (let [bjson (get-json (format "http://%s/computer/api/json?depth=1" *jenkins*))]
    (flatten
      (for [computer (:computer bjson)]
        (filter :currentExecutable (:executors computer))))))

(defn list-builds
  "List builds for the specified job"
  [job-name]
  (get-json (format "http://%s/job/%s/api/json?depth=1" *jenkins* job-name)))

(defn- with-download-url
  [build]
  (->> build :artifacts (map #(assoc % :download-url (str (:url build) "artifact/" (:relativePath %))))))

(defn list-artifacts
  "List artifacts of given optional build number or the latest successful build"
  [job-name & {:keys [build-number]}]
  (let [response (->> (format "http://%s/job/%s/api/json?depth=2" *jenkins* job-name) get-json)
        build (if build-number
                (->> response :builds (filter #(= build-number (:number %))) first)
                (:lastSuccessfulBuild response))]
    (with-download-url build)))

(defn list-module-artifacts
  "List module artifacts of the latest successful build"
  [job-name]
  (let [[module] (-> (format "http://%s/job/%s/api/json?depth=2" *jenkins* job-name) get-json :modules)
        build (:lastSuccessfulBuild module)]
    (with-download-url build)))

(defn submit-job
  "schedule a new build of job-name"
  [job-name]
  (get-json (format "http://%s/job/%s/build" *jenkins* job-name)))
