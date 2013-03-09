Sass
==============

Clojure bindings for the Ruby Sass compiler

## WARNING

This should only be used as a development dependency. This library depends on JRuby, which means you would too.

## Usage

To render a file

``` clojure
(render-file-path {} "public/test.scss")
```

To render a resource

``` clojure
(render-resource-path {} "test.scss")
```

Rendering a string

``` clojure
(render-string {:style :compressed :syntax :scss} "$blue: #3bbfce; .content-navigation { border-color: $blue; }")
; ".content-navigation{border-color:#3bbfce}"
```
