(ns sass.core
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :refer [split]]
            [clojure.walk :refer [postwalk]]
            [chee.string :refer [snake-case]]
            [chee.coerce :refer [->string ->keyword]]
            [zweikopf.core :refer :all]))

(defn- resource-path [path]
  (.getPath (resource path)))

(defn- init-sass []
  (ruby-require (resource-path "sass-3.2.6/lib/sass.rb")))

(init-ruby-context)
(init-sass)

(defn- underscore-key [k]
  (->keyword (snake-case (->string k))))

(defn- underscore-keys [m]
  (let [f (fn [[k v]] [(underscore-key k) v])]
    (postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))

(defn- new-sass-engine [template-string options]
  (call-ruby "Sass::Engine" :new (rubyize template-string) (rubyize options)))

(defn- new-sass-engine-for-file [file-path options]
  (call-ruby "Sass::Engine" :for_file (rubyize file-path) (rubyize options)))

(defn render-file-path [file-path & {:as options}]
  (clojurize (call-ruby (new-sass-engine-for-file file-path (underscore-keys (or options {}))) :render)))

(defn render-string [string & {:as options}]
  (clojurize (call-ruby (new-sass-engine string (underscore-keys (or options {}))) :render)))

(defn render-resource-path [path & options]
  (apply render-file-path (resource-path path) options))

