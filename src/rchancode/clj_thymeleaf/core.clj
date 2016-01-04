(ns rchancode.clj-thymeleaf.core
  (:require [clojure.walk :refer [stringify-keys]])
  (:import org.thymeleaf.TemplateEngine
           org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
           org.thymeleaf.templateresolver.FileTemplateResolver
           org.thymeleaf.templateresolver.UrlTemplateResolver
           org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver
           org.thymeleaf.context.Context))

(defn- ^AbstractConfigurableTemplateResolver new-template-resolver [template-resolver-type]
  (case template-resolver-type
    :classpath (ClassLoaderTemplateResolver.)
    :file (FileTemplateResolver.)
    :url (UrlTemplateResolver.)))


(defn ^TemplateEngine template-engine
  "
  Instantiates a Thymeleaf TemplateEngine.
  Paramenters:
  :template-resolver-type - either :file, :url or :classpath, defaults to :classpath.
  :prefix                 - template file name prefix e.g ./test/templates/
  :suffix                 - Template file name suffix e.g .html
  :cacheable              - whether to cache templates for better performance, defaults to true.
  "
  [& {:keys [prefix suffix cacheable template-resolver-type]
      :or {prefix nil
           suffix nil
           cacheable false
           template-resolver-type :classpath}}]
  (let [^AbstractConfigurableTemplateResolver template-resolver (new-template-resolver template-resolver-type)
        ^TemplateEngine engine (TemplateEngine.)]
    (if prefix
      (.setPrefix template-resolver prefix))
    (if suffix
      (.setSuffix template-resolver suffix))
    (.setCacheable template-resolver cacheable)
    (.setTemplateResolver engine template-resolver)
    ;; (.initialize engine) ;; only required on version 2.1.4
    engine))


(defn render-file
  "Takes a context-map and renders a template as a String or to a java.io.Writer. Returns nil when output to a java.io.Writer."
  ([^TemplateEngine engine ^String template context-map]
   (render-file engine template context-map nil))
  ([^TemplateEngine engine ^String template context-map ^java.io.Writer writer]
   (if (string? template)
     (let [^Context ctx (Context.)]
       (if (map? context-map)
         (doseq [kv (stringify-keys context-map)]
           (.setVariable ctx (first kv) (second kv))))
       (if writer
         (.process engine template ctx writer)
         (.process engine template ctx))
       ))))


