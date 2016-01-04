# Use cases for clj-thymeleaf

## HTML Templating for Luminus Web Framework

You can use Thymeleaf for HTML templating in Luminus web framework (http://luminusweb.net).

To enable Thymeleaf, add `[rchancode/clj-thymeleaf "0.1.0-SNAPSHOT"]` to your project `:dependencies`.

Edit `layout.clj`, add `[rchancode.clj-thymeleaf.core :as tf]` and `[environ.core :refer [env]]` to `:require`.

Then add:
```clojure
(def thymeleaf-template-prefix "templates/")

(defonce thymeleaf-engine (tf/template-engine
                           :prefix thymeleaf-template-prefix
                           :cacheable (not (env :dev))))

(defn thymeleaf
  [template & [params]]
  (content-type
    (ok
     (tf/render-file
      thymeleaf-engine
      template
      (assoc params
        :page template
        :csrf-token *anti-forgery-token*
        :servlet-context *app-context*)))
    "text/html; charset=utf-8"))

```

To render the template, use:

```clojure
(layout/thymeleaf "about.html" {:a {:b "Hello World" :xs ["1" "2"]}})
```



