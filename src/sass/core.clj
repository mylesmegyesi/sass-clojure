(ns sass.core
  (:require [clojure.java.io :refer [file resource]]))

(defn- build-jsass-options [options]
  (let [jsass-options (io.bit3.jsass.Options.)
        include-paths (.getIncludePaths jsass-options)]

    (doseq [load-path (:load-paths options)]
      (.add include-paths (file load-path)))

    (.setIsIndentedSyntaxSrc jsass-options (= :sass (:syntax options)))

    (case (:style options)
      :nested
      (.setOutputStyle jsass-options io.bit3.jsass.OutputStyle/NESTED)
      :compressed
      (.setOutputStyle jsass-options io.bit3.jsass.OutputStyle/COMPRESSED)
      :expanded
      (.setOutputStyle jsass-options io.bit3.jsass.OutputStyle/EXPANDED)
      :compact
      (.setOutputStyle jsass-options io.bit3.jsass.OutputStyle/COMPACT)
      nil)

    jsass-options))

(defn render-file-path [file-path & {:as options}]
  (let [jsass-options (build-jsass-options options)
        compiler (io.bit3.jsass.Compiler.)
        file-uri (.toURI (file file-path))
        output (.compileFile compiler file-uri nil jsass-options)]
    (.getCss output)))

(defn render-string [string & {:as options}]
  (let [jsass-options (build-jsass-options options)
        compiler (io.bit3.jsass.Compiler.)
        output (.compileString compiler string jsass-options)]
    (.getCss output)))

(defn render-resource-path [path & options]
  (let [jsass-options (build-jsass-options options)
        resource-uri (.toURI (resource path))
        compiler (io.bit3.jsass.Compiler.)
        output (.compileFile compiler resource-uri nil jsass-options)]
    (.getCss output)))

