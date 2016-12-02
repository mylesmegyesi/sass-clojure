(defproject sass "3.2.6"
  :description "Clojure bindings for the Ruby Sass compiler"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [zweikopf "0.1.0"]
                 [chee "1.1.2"]
                 [io.bit3/jsass "5.3.0"]]
  :profiles {:dev {:dependencies [[speclj "2.5.0"]]
                   :resource-paths ["test_fixtures"]
                   :main speclj.main}}
  :plugins [[speclj "2.5.0"]]
  :test-paths ["spec"]
  :resource-paths ["gems"])
