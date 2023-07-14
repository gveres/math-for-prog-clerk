(ns indolamine.custom)

;; ## Custom ClojureScript
;;
;; This namespace contains custom ClojureScript definitions used by the
;; {{raw/name}} project.

;; With the default configuration in `indolamine.sci-extensions`, any function
;; or var you add here will be available inside your SCI viewers as
;; `custom/<fname>` or as the fully-qualified `indolamine.custom/<fname>`.

(defn square
  "Returns the square of `x`."
  [x]
  (* x x))
