(defproject sass "3.2.6"
  :description "The Sass compiler"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [org.jruby/jruby-complete "1.6.7.2"]
                 [chee "1.1.2"]]
  :profiles {:dev {:dependencies [[speclj "2.5.0"]]}}
  :plugins [[speclj "2.5.0"]]
  :test-paths ["spec"]
  :resource-paths ["resources"])
