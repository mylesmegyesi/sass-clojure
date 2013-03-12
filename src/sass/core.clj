(ns sass.core
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :refer [split]]
            [clojure.walk :refer [postwalk]]
            [chee.string :refer [snake-case]]
            [chee.coerce :refer [->string ->keyword]]
            [zweikopf.core :refer :all]))

(defn- underscore-key [k]
  (->keyword (snake-case (->string k))))

(defn- underscore-keys [m]
  (let [f (fn [[k v]] [(underscore-key k) v])]
    (postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))

(defn- new-sass-engine [template-string options]
  (call-ruby "Sass::Engine" :new (rubyize template-string) (rubyize options)))

(defn- new-sass-engine-for-file [file-path options]
  (call-ruby "Sass::Engine" :for_file (rubyize file-path) (rubyize options)))

(defn render-file-path [file-path options]
  (clojurize (call-ruby (new-sass-engine-for-file file-path (underscore-keys options)) render)))

(defn render-string [string options]
  (clojurize (call-ruby (new-sass-engine string (underscore-keys options)) render)))

(defn- resource-path [path]
  (.getPath (resource path)))

(defn render-resource-path [path options]
  (render-file-path (resource-path path) options))

(defn- append-to-load-path! [path]
  (ruby-eval (str "$: << \"" path "\"")))

(defn- init-sass []
  (ruby-require (.getPath (resource "sass-3.2.6/lib/sass.rb"))))

(init-ruby-context)
(init-sass)
