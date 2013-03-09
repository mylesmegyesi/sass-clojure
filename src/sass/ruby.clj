(ns sass.ruby
  (:require [clojure.string :refer [join]])
  (:import [org.jruby.javasupport JavaEmbedUtils]))

(def jruby-runtime (JavaEmbedUtils/initialize (list)))
(def jruby-evaluator (JavaEmbedUtils/newRuntimeAdapter))

(defn run-ruby [& ruby-exprs]
  (.eval jruby-evaluator jruby-runtime (join "\n" ruby-exprs)))

(defprotocol AsRuby
  (->ruby [this]))

(extend-protocol AsRuby
  clojure.lang.Keyword
  (->ruby [this] (str this))

  clojure.lang.IPersistentCollection
  (->ruby [this]
    (str "[" (join ", " (map ->ruby this)) "]"))

  clojure.lang.IPersistentMap
  (->ruby [this]
    (str "{" (join ", " (map
                          (fn [[k v]]
                            (str (->ruby k) " => " (->ruby v)))
                          this)) "}"))

  java.lang.String
  (->ruby [this]
    (str "\"" this "\""))

  java.lang.Boolean
  (->ruby [this] (str this))

  nil
  (->ruby [this] "nil")

  )
