# clj-thymeleaf

A Clojure wrapper for Thymeleaf templating engine (http://www.thymeleaf.org/).

## Installation

With Leiningen, run:

```
lein install
```

Add the following to your `:dependencies`:

```clojure
[rchancode/clj-thymeleaf "0.1.0-SNAPSHOT"]
```

## Usage

```clojure
(use rchancode.clj-thymeleaf.core)
```

Or in the `ns` declaration:

```clojure
(:require [rchancode.clj-thymeleaf :refer :all])
```


```Clojure
(let [engine (template-engine
               :template-resolver-type :file ;; This can be either :file, :url or :classpath, defaults to :classpath.
               :prefix "./test/templates/"   ;; template file name prefix
               :suffix ".html"               ;; Template file name suffix
               :cacheable true               ;; whether to cache templates.
               )]
  (render-file engine "test" {:title "Hello"}))
```

By default, the `render-file` function produces a string. You can also redirect the output to a `java.io.Writer` by passing it
as the last argument to the function.

## License

Copyright © 2016 rchancode

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

THYMELEAF is a Copyright © of The THYMELEAF Team (http://www.thymeleaf.org)
