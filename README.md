Sass
==============

Clojure wrapper around jsass, which is a Java wrapper around libsass.

## Installation

Add this to your project.clj

``` clojure
[sass "3.2.6"]
```

## Usage

To render a file

``` clojure
; using default options
(render-file-path "public/test.scss")

; with options
(render-file-path "public/test.scss" :property-syntax :new :style :compressed)
```

To render a resource

``` clojure
; using default options
(render-resource-path "test.scss")

; with options
(render-resource-path "test.scss" :load-paths ["src/stylesheets"])
```

Rendering a string

``` clojure
; using default options
(render-string "$blue: #3bbfce; .content-navigation { border-color: $blue; }")
; ".content-navigation{border-color:#3bbfce}"

; with options
(render-string "$blue: #3bbfce; .content-navigation { border-color: $blue; }" :style :compressed :syntax :scss)
```
