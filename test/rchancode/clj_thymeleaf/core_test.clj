(ns rchancode.clj-thymeleaf.core-test
  (:require [clojure.test :refer :all]
            [rchancode.clj-thymeleaf.core :refer :all]))


(deftest render-file-from-classpath
  (let [e (template-engine :prefix "templates/")
        rendered (render-file e "test.html" {:title "Hello"})]
    (is (= "<!DOCTYPE html>\n<html>\n    <head>\n        <title>Hello</title>\n        <meta charset=\"utf-8\" />\n    </head>\n    <body>\n        <h1>Hello</h1>\n    </body>\n</html>\n"
           rendered))
    ))


(deftest render-from-a-file-path
  (let [e (template-engine :prefix "./test/templates/" :template-resolver-type :file)
        rendered (render-file e "test.html" {:title "Hello"})]
    (is (= "<!DOCTYPE html>\n<html>\n    <head>\n        <title>Hello</title>\n        <meta charset=\"utf-8\" />\n    </head>\n    <body>\n        <h1>Hello</h1>\n    </body>\n</html>\n"
           rendered))
    )
  )

(deftest render-from-url
  (let [e (template-engine :prefix "file:./test/templates/"
                           :template-resolver-type :url
                           :suffix ".html")
        rendered (render-file e "test" {:title "Hello"})]
    (is (= "<!DOCTYPE html>\n<html>\n    <head>\n        <title>Hello</title>\n        <meta charset=\"utf-8\" />\n    </head>\n    <body>\n        <h1>Hello</h1>\n    </body>\n</html>\n"
           rendered))
    )
  )


(deftest render-complex
  (let [e (template-engine :prefix "templates/")
        rendered (render-file e "complex.html" {:page {:title {:text "Hello"}}})]
    (is (= "<!DOCTYPE html>\n<html>\n    <head>\n        <title>Hello</title>\n        <meta charset=\"utf-8\" />\n    </head>\n    <body>\n        <h1>Hello</h1>\n        <h2>This is thymeleaf</h2>\n    </body>\n</html>\n"
           rendered))
    )
  )


