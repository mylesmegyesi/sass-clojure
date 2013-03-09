(ns sass.core
  (:require [chee.string :refer [snake-case]]
            [chee.coerce :refer [->string ->keyword]]
            [sass.ruby :refer [run-ruby ->ruby]]))

(defn- underscore [k]
  (->keyword (snake-case (->string k))))

(defn- underscore-map [m]
  (reduce
    (fn [underscored [k v]]
      (assoc underscored (underscore k) v))
    {}
    m))

(defn- ->sass-engine-options [options]
  (->ruby
    (underscore-map options)))

(defn render-file-path [options file-path]
  (let [options (->sass-engine-options options)]
    (str
      (run-ruby
        (str "Sass::Engine.for_file(" (->ruby file-path) ", " options ").render")))))

(defn render-string [options string]
  (let [options (->sass-engine-options options)]
    (str
      (run-ruby
        (str "Sass::Engine.new(" (->ruby string) ", " options ").render")))))

(defn- resource-path [resource]
  (.getPath (clojure.java.io/resource resource)))

(defn render-resource-path [options path]
  (render-file-path options (resource-path path)))

(defn- init-sass []
  (let [sass-lib-path (first (clojure.string/split (.getPath (clojure.java.io/resource "gems/sass-3.2.6/lib/sass.rb")) #"/sass.rb" 2))]
    (run-ruby
      (str "$: << \"" sass-lib-path "\"")
      "require 'sass'")))

(init-sass)
