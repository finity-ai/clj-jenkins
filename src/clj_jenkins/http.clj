(ns clj-jenkins.http
  (:require [aleph.http     :as aleph-http]
            [aleph.formats  :as formats]))

(defn basic-auth
  [creds]
  (str "Basic " (-> (str (:username creds) ":" (:password creds))
                    formats/base64-encode)))

(defn build-header
  [creds]
  (->> (when creds
         {"Authorization" (basic-auth creds)})
       (merge {"Accept" "application/json"})
      (hash-map :headers)))

(defn get-json
  "Execute a GET query with optional basic-auth and return json"
  [url creds]
  (->> {:method         :get
        :url            url
        :auto-transform true}
       (merge (build-header creds))
       aleph-http/http-request
       deref
       :body))
