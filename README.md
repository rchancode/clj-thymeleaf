# clj-thymeleaf

A Clojure wrapper for Thymeleaf templating engine (http://www.thymeleaf.org/). 

This currently uses Thymeleaf version 3.0.0.BETA01.

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
(let [^TemplateEngine engine (template-engine
                               :template-resolver-type :file ;; This can be either :file, :url or :classpath, defaults to :classpath.
                               :prefix "./test/templates/"   ;; template file name prefix
                               :suffix ".html"               ;; Template file name suffix
                               :cacheable true               ;; whether to cache templates.
                               )]
  (render-file engine "test" {:title "Hello"}))
```

`render-file` will return output as a string.

Optionally, you can also pass a `java.io.Writer` as the last argument to `render-file` function.

## License

Copyright © 2016 rchancode

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

THYMLEAF is a Copyright © of The THYMELEAF Team (http://www.thymeleaf.org/license.html)
