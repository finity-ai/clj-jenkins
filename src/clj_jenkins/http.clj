(ns clj-jenkins.http
  (:import [com.ning.http.client AsyncHttpClient Response])
  (:require [clojure.data.json :as json]))
  
  (defn build-client[] (AsyncHttpClient.))
  
  (defn get-resp
    [req]
    (let [resp (-> req .execute .get)
          headers (-> resp .getHeaders)
          hkeys (into [] (-> headers .keySet))
          mappedh (map #(vector % (-> headers (.getFirstValue %))) hkeys)
          headers (into {} mappedh)]
      [headers (.getResponseBody resp) resp]))

  (defn extract-body-json
    [resp]
    "Extract and parse the json body of the server response"
    (let [body (second resp)]
      (try
        (-> body json/read-json)
      (catch Exception e
        nil))))
        
  (defn get-json
    "Execute a GET request on specified url and return the map repr of the response body"
    [client url]
    (let [req  (.prepareGet client url)]
      (extract-body-json (get-resp req))))
      