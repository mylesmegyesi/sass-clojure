(ns sass.core-spec
  (:require [speclj.core :refer :all]
            [sass.core :refer :all]))

(defn resource-path [resource]
  (.getPath (clojure.java.io/resource resource)))

(defn base-dir [full-path sub-path]
  (first (clojure.string/split full-path (re-pattern sub-path) 2)))

(describe "sass.core"

  (it "renders a scss file with a file path"
    (should=
".content-navigation {
  border-color: #3bbfce;
  color: #2ca2af; }

.border {
  padding: 8px;
  margin: 8px;
  border-color: #3bbfce; }
"
      (render-file-path "test_fixtures/test.scss")))

  (it "renders a file with a resource path"
    (should=
".content-navigation {
  border-color: #3bbfce;
  color: #2ca2af; }

.border {
  padding: 8px;
  margin: 8px;
  border-color: #3bbfce; }
"
      (render-resource-path "test.scss")))

  (it "renders a scss file that has an import to another scss file"
    (let [scss-file "scss/test1.scss"
          file-path (resource-path scss-file)]
      (should=
".test2 {
  border-color: blue; }

.test1 {
  border-color: blue; }
"
        (render-file-path file-path :load-paths [(base-dir file-path scss-file)]))))

  (it "renders a sass file with a file path"
    (should=
".content-navigation {
  border-color: #3bbfce;
  color: #2ca2af; }

.border {
  padding: 8px;
  margin: 8px;
  border-color: #3bbfce; }
"
      (render-file-path (resource-path "test.sass"))))

  (context "options"

    (it "output style"
      (should=
".content-navigation{border-color:#3bbfce}
"
        (render-string
"$blue: #3bbfce;
.content-navigation {
  border-color: $blue; }
"
          :style :compressed :syntax :scss)))

    (it "syntax"
      (should=
".content-navigation {
  border-color: #3bbfce; }
"
        (render-string
"$blue: #3bbfce
.content-navigation
  border-color: $blue
"
          :syntax :sass)))

    (it "property syntax"
      (should=
".content-navigation {
  border-color: #3bbfce; }
"
        (render-string
"$blue: #3bbfce
.content-navigation
  border-color: $blue
"
          :syntax :sass :property-syntax :new))
      (should=
".content-navigation {
  border-color: #3bbfce; }
"
        (render-string
"$blue: #3bbfce
.content-navigation
  :border-color $blue
"
          :syntax :sass :property-syntax :old)))

    )
  )
