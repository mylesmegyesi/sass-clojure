(defproject sass "3.2.6"
  :description "Clojure bindings for the Ruby Sass compiler"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [io.bit3/jsass "5.5.3"]]
  :profiles {:dev {:dependencies [[speclj "3.3.2"]]
                   :resource-paths ["test_fixtures"]
                   :main speclj.main}}
  :plugins [[speclj "3.3.2"]]
  :test-paths ["spec"])
