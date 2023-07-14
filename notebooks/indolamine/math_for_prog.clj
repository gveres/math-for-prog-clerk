;; # Welcome to Math For Programmers
;; # Chapter 2.

{:nextjournal.clerk/visibility {:code :hide :result :hide}}
^{:nextjournal.clerk/toc false}
(ns indolamine.math-for-prog
  (:require [mentat.clerk-utils.show :refer [show-sci]]
            [nextjournal.clerk :as clerk]))

(clerk/eval-cljs
 '(require '[reagent.core :as reagent])
 '(require '[mafs.core :as mafs]))

(def mafs-point-and-poly-viewer
  {:transform-fn clerk/mark-presented
   :render-fn
   '(fn [{:keys [view-box preserve-aspect-ratio points polys vectors]}]
      [:<>
       [mafs/Mafs
        {:view-box view-box
         :preserve-aspect-ratio (or preserve-aspect-ratio "contain")
         :zoom true}
        [mafs.coordinates/Cartesian {}]
        (map #(map (fn [[x y]] [mafs/Point {:x x :y y}]) %) points)
        (map (fn [v] [mafs/Vector v]) vectors)
        (map (fn [poly] [mafs/Polygon poly]) polys)]])})


(defn min-max [ps]
  [[(apply min (map first ps))
    (apply max (map first ps))]
   [(apply min (map second ps))
    (apply max (map second ps))] ])

(defn view-box-around [ps]
  (let [[x-limits y-limits] (min-max ps)]
    { :x x-limits :y y-limits }))

(def dinosaur [[6 4]  [3 1]  [1 2] [-1 5] [-2 5] [-3 4] [-4 4] [-5 3]  [-5 2]  [-2 2]  [-5 1]  [-4 0]  [-2 1]  [-1 0]  [0 -3] [-1 -4]  [1 -4]  [2 -3]  [1 -2]  [3 -1]  [5 1]])

{:nextjournal.clerk/visibility {:code :hide :result :show}}

^{::clerk/viewer mafs-point-and-poly-viewer}
{:view-box (view-box-around dinosaur)
 :points [dinosaur]}

^{::clerk/viewer mafs-point-and-poly-viewer}
{:view-box (view-box-around dinosaur)
 :points [dinosaur]
 :polys [{:points dinosaur :color "red"}]}


(def parabole-points
  (mapv (fn [x] [x (* x x)]) (range -10 11)))


^{::clerk/viewer mafs-point-and-poly-viewer}
{:view-box (view-box-around parabole-points)
 :preserve-aspect-ratio "false"
 :points [ parabole-points ]}


(defn vector-add [[x1 y1][x2 y2]]
  [(+ x1 x2) (+ y1 y2)])

(defn translate [vecs xlation]
  (map #(vector-add % xlation) vecs))

(def dino-moved
  (translate dinosaur [-3 +7]))



^{::clerk/viewer mafs-point-and-poly-viewer}
{:view-box (view-box-around (concat dinosaur dino-moved))
 :points [dinosaur dino-moved]
 :polys [{:points dinosaur :color "blue"}
         {:points dino-moved :color "red"}]}


(def hundred-dinos
  (for [x (range -5 5)
        y (range -5 5)]
    (translate dinosaur [(* 12 x) (* 10 y)])))


^{::clerk/viewer mafs-point-and-poly-viewer}
{:view-box (view-box-around (mapcat identity hundred-dinos))
 :polys (mapv (fn [x] {:points x}) hundred-dinos)}


(defn vector-scale [[x y] s]
  [(* s x) (* s y)])

(def possibilities
  (let [u [-1 1]
        v [1 1]
        uniform #(- % (rand (* 2 %)))]
    (repeatedly 500 #(vector-add
                      (vector-scale u (uniform 3))
                      (vector-scale v (uniform 1))))))

^{::clerk/viewer mafs-point-and-poly-viewer}
{:view-box (view-box-around possibilities)
 :points [ (vec possibilities) ]}

^{::clerk/viewer mafs-point-and-poly-viewer}
{:view-box (view-box-around possibilities)
 :vectors [{:tip [1 1] :color :red}
           {:tail [1 1] :tip (vector-add [1 1] [1 -3]) :color :green :style :dashed}
           {:tip (vector-add [1 1] [1 -3]) :color :blue}
           {:tip [1 -3] :color :green}
           ]}


(Math/acos 0.4)

Math/PI
