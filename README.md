Sass
==============

Clojure bindings for the Ruby Sass compiler

## WARNING

This should only be used as a development dependency. This library depends on JRuby, which means you would too.

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
(render-file-path "test.scss" :load-paths ["src/stylesheets"])
```

Rendering a string

``` clojure
; using default options
(render-string "$blue: #3bbfce; .content-navigation { border-color: $blue; }")
; ".content-navigation{border-color:#3bbfce}"

; with options
(render-string "$blue: #3bbfce; .content-navigation { border-color: $blue; }" :style :compressed :syntax :scss)
```
