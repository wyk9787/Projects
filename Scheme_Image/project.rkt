#lang racket
(require gigls/unsafe)

;;;Default;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;; Procedure:
;;;   background-dark
;;; Parameters:
;;;   width, a positive integer
;;;   height, a positive integer
;;;   N, a non-negative integer
;;; Purpose:
;;;   To produce a dark-colored width*height image
;;;   Color is decided by N
;;; Produces:
;;;   result, a image 
(define background-dark
  (lambda (width height N)
    (random-seed N)
    (let ([n (random 7)])
      (image-compute
       (lambda (col row)
         (cond
           [(= n 0)
            (if (< col (* (/ width height) row))
                (irgb 186 205 225)
                (irgb 225 186 205))]
           [(= n 1)
            (if (> col (* (/ width height) row))
                (irgb 186 205 225)
                (irgb 225 186 205))]
           [(= n 2)
            (irgb 208
                  (remainder (* col 32) 225) (remainder (* row 64) 225)
                  )]
           [(= n 3)
            (irgb 225
                  (remainder (* col 12) 186) (remainder (* row 12) 205)
                  )]
           [(= n 4)
            (if (< col (* (/ width height) row))
                (irgb 166 160 161)
                (irgb 160 16 165))]
           [(= n 5)
            (if (< col (* (/ width height) row))
                (irgb 171 114 87)
                (irgb 87 144 171))]
           [(= n 6)
            (if (< col (* (/ width height) row))
                (irgb 90 71 85)
                (irgb 71 90 76))]))
       width height))))

;;; Procedure:
;;;   background-light
;;; Parameters:
;;;   width, a positive integer
;;;   height, a positive integer
;;;   N, a non-negative integer
;;; Purpose:
;;;   To produce a light-colored width*height image
;;;   Color is decided by N
;;; Produces:
;;;   result, a image 
(define background-light
  (lambda (width height N)
    (random-seed N)
    (let ([n (random 6)])
      (image-compute
       (lambda (col row)
         (cond
           [(= n 0)
            (if (> col (* (/ width height) row))
                (irgb 208 200 201)
                (irgb 208 248 201))]
           [(= n 1)
            (irgb 208
                  (* 250 (+ 1 (sin (* pi 0.0025 col))))
                  (* 201 (+ 1 (sin (* pi 0.0020 row)))))]
           [(= n 2)
            (irgb 239
                  (* 240 (+ 1 (sin (* pi 0.0025 col))))
                  (* 218 (+ 1 (sin (* pi 0.0020 row)))))]
           [(= n 3)
            (if (> col (* (/ width height) row))
                (irgb 244 200 164)
                (irgb 244 240 164))]
           [(= n 4)
            (if (< col (* (/ width height) row))
                (irgb 253 209 189)
                (irgb 209 253 189))]
           [(= n 5)
            (irgb 209
                  (remainder (* col 2) 189) (remainder (* row 2) 253)
                  )]))
       width height))))

(define color-of-flower-dark
  (list (irgb 209 189 253)
        (irgb 189 253 209)
        (irgb 248 217 1)
        (irgb 228 4 1)
        (irgb 102 106 227)
        (irgb 116 189 184)))

(define color-of-flower-light
  (list (irgb 255 228 167)
        (irgb 255 202 202)
        (irgb 213 213 255)
        (irgb 221 255 221)
        (irgb 233 202 255)
        (irgb 255 233 202)))

;;; Procedure:
;;;   append-lists
;;; Parameters:
;;;   lst, a list
;;;   num, a positive integer
;;;   lst-so-far, a list
;;; Purpose:
;;;   To produce a longer list for the use of "for-each"
;;; Produces:
;;;   result, a list
(define append-lists
  (lambda (lst num lst-so-far)
    (if (= num 0)
        lst-so-far
        (append-lists lst (- num 1) (append lst lst-so-far)))))


;;;FirstFlower;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Procedure:
;;;   action
;;; Parameters:
;;;   turtle, a turtle
;;;   length, a positive integer
;;; Purpose:
;;;   have turtle do a certain move to draw a square
;;; Produces:
;;;   [side effects]
(define action 
  (lambda (turtle length)
    (turtle-forward! turtle length)
    (turtle-turn! turtle 90)))

;;; Procedure:
;;;   draw-first-flower
;;; Parameters:
;;;   turtle, a turtle
;;;   length, a positive integer
;;; Purpose:
;;;   repeat action 4 times to produce a kind of flower
;;; Produces:
;;;   [side effects]
(define draw-first-flower
  (lambda (turtle length) 
    (repeat 4 action turtle length)))

;;; Procedure:
;;;   turtle-spiral1!
;;; Parameters:
;;;   turtle, a turtle
;;;   distance, a positive number
;;;   num, a positive integer
;;;   N, a non-negative integer
;;;   color, a rgb-encoded color
;;; Purpose:
;;;   use turtle to draw a sprial with first kind of flower along the drawing
;;; Produces:
;;;   [side effects]
(define turtle-spiral1!
  (lambda (turtle distance num N color)
    (random-seed N)
    (turtle-forward! turtle distance)
    (turtle-set-color! turtle color)
    (turtle-turn! turtle (+ 45 (random 30)))
    (draw-first-flower turtle num))) 

;;; Procedure:
;;;   flower1
;;; Parameters:
;;;   canvas, a image
;;;   turtle, a turtle
;;;   num, a positive integer
;;;   x, a positive number
;;;   y, a positive number
;;;   N, a non-negative integer
;;;   light-or-dark, a number either 0 or 1
;;; Purpose:
;;;   repeat turtle-spiral1 and make it a bigger flower
;;; Produces:
;;;   [side effects]
(define flower1
  (lambda (canvas turtle num x y N light-or-dark)
    ;;; (x, y) is where to start
    ;;; num is how big this flower needs to be (how many squares are there)
    ;;; N is the big N for the main procedure; also the random seed
    (turtle-teleport! turtle x y)
    (turtle-set-brush! turtle "2. Hardness 050" 0.2)
    (if (= light-or-dark 0)
        (for-each (lambda (distance num color) (turtle-spiral1! turtle distance num N color))
                  (map (l-s + 10) (iota num)) ;;; list for distance
                  (iota num) ;;; list for num
                  (append-lists color-of-flower-dark (/ num 6) null))
        (for-each (lambda (distance num color) (turtle-spiral1! turtle distance num N color))
                  (map (l-s + 10) (iota num)) ;;; list for distance
                  (iota num) ;;; list for num
                  (append-lists color-of-flower-light (/ num 6) null))))) ;;; list for color

;;;SecondFlower;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Procedure:
;;;   turtle-spiral2!
;;; Parameters:
;;;   canvas, a image
;;;   turtle, a turtle
;;;   distance, a positive number
;;;   N, a non-negative integer
;;;   light-or-dark, a number either 0 or 1
;;; Purpose:
;;;   use turtle to draw a sprial with either first, third or fifth kind of flower along the drawing
;;; Produces:
;;;   [side effects]
(define turtle-spiral2!
  (lambda (canvas turtle distance N light-or-dark)
    (let ([ran (random 3)])
      (turtle-up! turtle)
      (turtle-forward! turtle distance)
      (cond [(= ran 0)
             (flower1 canvas (turtle-new canvas) 12 (turtle-col turtle) (turtle-row turtle) N light-or-dark)]
            [(= ran 1)
             (flower3 canvas (turtle-new canvas) 12 (turtle-col turtle) (turtle-row turtle) N light-or-dark)]
            [(= ran 2)
             (flower5 canvas (turtle-new canvas) 12 (turtle-col turtle) (turtle-row turtle) N light-or-dark)])
      )))

;;; Procedure:
;;;   flower2
;;; Parameters:
;;;   canvas, a image
;;;   turtle, a turtle
;;;   num, a positive integer
;;;   x, a positive number
;;;   y, a positive number
;;;   N, a non-negative integer
;;;   light-or-dark, a number either 0 or 1
;;; Purpose:
;;;   make six turtle-spiral2! aound the center of the drawing 
;;; Produces:
;;;   [side effects]
(define flower2
  (lambda (canvas turtle num x y N light-or-dark)
    (let* ([new-num (/ num 12)]
           [turtles (map turtle-clone (make-list 6 turtle))])
      (for-each (section turtle-teleport! <> x y) turtles)
      (for-each (section turtle-set-brush! <> "2. Hardness 050" 0.5) turtles)
      (for-each turtle-turn! turtles (make-list 6 -90))
      (for-each turtle-turn! turtles (map (l-s * 60) (iota 6)))
      (for-each (lambda (turtle distance) (turtle-spiral2! canvas turtle distance N light-or-dark))
                turtles
                (make-list 6 (* 10 new-num))))))  

;;;;;Third Flower;;;;;;;;;;;;;;;;;;;;;
;;; Procedure:
;;;   flower3
;;; Parameters:
;;;   canvas, a image
;;;   turtle, a turtle
;;;   num, a positive integer
;;;   x, a positive number
;;;   y, a positive number
;;;   N, a non-negative integer
;;;   light-or-dark, a number either 0 or 1
;;; Purpose:
;;;   make 12 spirals from the center of the image by using turtle-spiral1! 
;;; Produces:
;;;   [side effects]
(define flower3
  (lambda (canvas turtle num x y N light-or-dark)
    (let ([turtles (map turtle-clone (make-list num turtle))])
      (for-each (section turtle-teleport! <> x y) turtles)
      (for-each (section turtle-set-brush! <> "2. Hardness 050" 0.2) turtles)
      (for-each turtle-turn! turtles (map (l-s * 30) (iota num)))
      (if (= light-or-dark 0)
          (for-each (lambda (turtle distance num color) (turtle-spiral1! turtle distance num N color))
                    turtles
                    (map (l-s + 10) (iota num)) ;;; list for distance
                    (iota num) ;;; list for num
                    (append-lists color-of-flower-dark (/ num 6) null))
          (for-each (lambda (turtle distance num color) (turtle-spiral1! turtle distance num N color))
                    turtles
                    (map (l-s + 10) (iota num)) ;;; list for distance
                    (iota num) ;;; list for num
                    (append-lists color-of-flower-light (/ num 6) null))))))

;;;;;Fourth Flower;;;;;;;;;;;;;;;;;;;;;;
;;; Procedure:
;;;   draw-fourth-flower
;;; Parameters:
;;;   canvas, a images
;;;   left, a positive number
;;;   top, a positive number
;;;   width, a positive number
;;;   height, a positive number
;;;   num, a positive integer
;;;   color, a rgb-encoded color
;;; Purpose:
;;;   make a circle defined by left, top, width, height
;;; Produces:
;;;   [side effects]
(define draw-fourth-flower
  (lambda (canvas left top width height num color)
    (image-select-ellipse! canvas REPLACE 
                           left top width height)
    (context-set-fgcolor! color)
    (context-set-brush! "2. Hardness 100" 1.3)
    (image-stroke-selection! canvas)))

;;; Procedure:
;;;   flower4
;;; Parameters:
;;;   canvas, a image
;;;   turtle, a turtle
;;;   num, a positive integer
;;;   x, a positive number
;;;   y, a positive number
;;;   N, a non-negative integer
;;;   light-or-dark, a number either 0 or 1
;;; Purpose:
;;;   draw circles along the way of drawing each spirals from the center to make the fourth flower
;;; Produces:
;;;   [side effects]
(define flower4
  (lambda (canvas turtle num x y N light-or-dark)
    (let ([turtle-spiral4!
           (lambda (canvas turtle distance num N color)
             (turtle-up! turtle)
             (turtle-forward! turtle distance)
             (turtle-turn! turtle 45)
             (draw-fourth-flower canvas 
                                 (turtle-col turtle) 
                                 (+ (turtle-row turtle) (/ 2 num))
                                 (* 2 num)
                                 (* 2 num)
                                 num color))]
          [turtles (map turtle-clone (make-list num turtle))])
      (for-each (section turtle-teleport! <> x y) turtles)
      (for-each (section turtle-set-brush! <> "2. Hardness 050" 0.5) turtles)
      (for-each turtle-turn! turtles (make-list num -90))
      (for-each turtle-turn! turtles (map (l-s * 60) (iota num)))
      (if (= light-or-dark 0)
          (for-each (lambda (turtle distance num color) (turtle-spiral4! canvas turtle distance num N color))
                    turtles
                    (map (l-s + 15) (iota num)) ;;; list for distance
                    (make-list num 1) ;;; list for num
                    (append-lists color-of-flower-dark (/ num 6) null))
          (for-each (lambda (turtle distance num color) (turtle-spiral4! canvas turtle distance num N color))
                    turtles
                    (map (l-s + 15) (iota num)) ;;; list for distance
                    (make-list num 1) ;;; list for num
                    (append-lists color-of-flower-light (/ num 6) null)))
      (image-select-nothing! canvas)
      (context-update-displays!))))

;;;Fifth Flower;;;;;;;;;;;;;;;

;;; Procedure:
;;;   draw-fifth-flower
;;; Parameters:
;;;   turtle, a turtle
;;;   length, a postive integer
;;; Purpose:
;;;   draw a star
;;; Produces:
;;;   [side effects]
(define draw-fifth-flower
  (lambda (turtle length) 
    (let ([action1 
           (lambda (turtle length)
             (turtle-forward! turtle length)
             (turtle-turn! turtle -72)
             (turtle-forward! turtle length)
             (turtle-turn! turtle 144))])
      (repeat 5 action1 turtle length))))

;;; Procedure:
;;;   flower5
;;; Parameters:
;;;   canvas, a image
;;;   turtle, a turtle
;;;   num, a positive integer
;;;   x, a positive number
;;;   y, a positive number
;;;   N, a non-negative integer
;;;   light-or-dark, a number either 0 or 1
;;; Purpose:
;;;   drawing stars along the way of drawing spiral to make the fifth flower
;;; Produces:
;;;   [side effects]
(define flower5
  (lambda (canvas turtle num x y N light-or-dark)
    (let ([turtle-spiral5!
           (lambda (turtle distance N color)
             (random-seed N)
             (turtle-forward! turtle distance)
             (turtle-set-color! turtle color)
             (turtle-turn! turtle (+ 45 (random 30)))
             (draw-fifth-flower turtle 3.5))])
      (turtle-teleport! turtle x y)
      (turtle-set-brush! turtle "2. Hardness 050" 0.3)
      (if (= light-or-dark 0)
          (for-each (lambda (distance color) (turtle-spiral5! turtle distance N color))
                    (map (l-s + 10) (iota num)) ;;; list for distance
                    (append-lists color-of-flower-dark (/ num 6) null))
          (for-each (lambda (distance color) (turtle-spiral5! turtle distance N color))
                    (map (l-s + 10) (iota num)) ;;; list for distance
                    (append-lists color-of-flower-light (/ num 6) null)))))) ;;; list for color


;;;Main procedure;;;;;;;;
;;; Procedure:
;;;   image-series
;;; Parameters:
;;;   N, an integer 
;;;   width, a positive integer
;;;   height, a positive integer
;;; Purpose:
;;;   make an image with interesting drawings and distinctive variations   
;;; Produces:
;;;   an image when with the width width and height height when called a specific number. Produce 1000 different images in total.
;;; Precondition:
;;;   N is limited to 0 to 999
;;;   width and height must be higher than 50
;;; Postcondition:
;;;   If the random draws a dark background, the color of flowers is light;
;;;   If the random draws a light background, the color of flowers is dark;
;;;   There are three parts. The first part is a star sign backbone composed of GIMP circle.;
;;;   The second part is a turtle drawing of a series of stars radiating from the center of the star sign backbone.
;;;   The third part is several continuous turtle drawings of squares spread out. 

(define image-series
  (lambda (N width height)
    (let ([bounds1
           (lambda (width height)
             (let* ([bound-width (max 8 (floor (/ width 14)))]
                    [bound-height (max 8 (floor (/ width 14)))]
                    [bound (min bound-width bound-height)]
                    [low-bound (floor (min (/ bound 4) (- bound 3)))]
                    [high-bound (floor (max (/ bound 4) (- bound 3)))])
               (+ low-bound (random (- high-bound low-bound)))))]
          [bounds2
           (lambda (width height)
             (let* ([bound-width (max 8 (floor (/ width 14)))]
                    [bound-height (max 8 (floor (/ width 14)))]
                    [bound (min bound-width bound-height)]
                    [low-bound (floor (min (/ bound 4) (- bound 3)))]
                    [high-bound (floor (max (/ bound 4) (- bound 3)))])
               (+ low-bound (random (- high-bound low-bound)))))])
      (random-seed N)
      (let* ([light-or-dark (random 2)])
        (if (= light-or-dark 0)  
            (let* ([canvas (background-light width height N)]
                   [tony (turtle-new canvas)]
                   [ran (* 6 (bounds1 width height))]
                   [ran2 (* 12 (bounds2 width height))])
              (image-show canvas)
              (cond [(or (<= ran 48) (<= ran2 48))
                     (flower4 canvas tony 6 (/ width 2) (/ height 2) N light-or-dark)]
                    [else 
                     (flower4 canvas tony ran  (/ width 2) (/ height 2) N light-or-dark)
                     (flower5 canvas tony ran2 (/ width 2) (- (/ height 2) 10) N light-or-dark)
                     (flower2 canvas tony ran2 (/ width 2) (/ height 2) N light-or-dark)]))
            (let* ([canvas (background-dark width height N)]
                   [tony (turtle-new canvas)]
                   [ran (* 6 (bounds1 width height))]
                   [ran2 (* 12 (bounds2 width height))])
              (image-show canvas)
              (cond [(or (<= ran 48) (<= ran2 48))
                     (flower4 canvas tony 6 (/ width 2) (/ height 2) N light-or-dark)]
                    [else 
                     (flower4 canvas tony ran  (/ width 2) (/ height 2) N light-or-dark)
                     (flower5 canvas tony ran2 (/ width 2) (- (/ height 2) 10) N light-or-dark)
                     (flower2 canvas tony ran2 (/ width 2) (/ height 2) N light-or-dark)])))))))




