(ns clojure02011.core)
;(import '(java.lang.StatefulInteger))

(def five (atom 5))
(def six  (atom 6))
(defn intbr [& args]
  (let [a @five]                         ;@ daje cistu vrednost
    (println "prvo" a (= @five @six))
    (reset! five 6)
    (println "drugo" @five (= @five @six)))   
  )
;(intbr)
#_(prvo 5 false
drugo 6 true)

;(import 'java.lang.Integer)
(def fivei 5.1)
(def sixi 6.1)
(defn intbri [& args]
  (let [a fivei]                        
    (println "prvo" (.intValue a) (= fivei sixi))
    (def fivei 6)
    (println "drugo" fivei (= fivei (.intValue sixi))))   
  )
;(intbri)
#_{prvo 5 false
drugo 6 true}

(import 'java.awt.Point)
(def pt (Point. 5 10))
(defn tacka [& args]
  (println (.x pt))
  (set! (.x pt) -42)
  (println (.x pt)))
;(tacka)
#_(5
-42)

(def h {[1 2] 3})
(defn map1 [& args]
  (println h)
  (println (h [1 2]))
  (conj (first (keys h)) 8 9 [5 6] 'a 'b)
  )
;(map1)
#_({[1 2] 3}
3
[1 2 8 9 [5 6] a b])

(defn call-twice [f x]          ;funkcija koja ozvrsava doneti kod
  (f x)
  (f x)
  (f x)
  (f x))
;(call-twice println 123)
#_(123
123
123
123)

(defn maxfunc 
  [args]
  (println (clojure.string/lower-case "ClojUre"))
  (apply max args))
;(maxfunc [1 35 46 8 7 563 1024 5 69 7])
#_(clojure
1024)

(defn map12 [& args]
  (println (map clojure.string/lower-case ["Java" "Clojure" "Pyton" "Ruby" "KONJI"]))
  (println (map * [1 3 5 7] [10 20 30 40])))
;(map12)
#_((java clojure pyton ruby konji)
(10 60 150 280))

(defn assoc1 [& args]
  (println (assoc [1 2 3] 0 66 1 55))         ;prvi(rbr 0 i vrednoscu 1) elelment menja sa brojem 66 a drugi(rbr 1 i vrenoscu 2 menja sa 55)
  (println (assoc [1 2 3] 3 44))              ; sa obzirom da ne postoji treci(rbr 4) element ali ima tri elementa dodaje ovaj elemant na kraj mape
  (println (assoc {:key1 "old value1" :key2 "value2"} :key1 "new value1" :key3 "value3"))   ;pravi novi niz sa novim elementima
  (println (assoc [] 0 11 1 22 2 33)))      ;ovde moze da se dodaje jer krecemo od nultog(prvog) calna koji je za jedan veci od nista ..
;(assoc1)
;([66 55 3]
;[1 2 3 44]
;{:key3 value3, :key2 value2, :key1 new value1}
;[11 22 33]

(defn merge1 [& args]
  (println(merge {:a 1 :b 2 :c 3} {:b 9 :d 4}))
  (println(conj  {:a 1 :b 2 :c 3} {:b 9 :d 4}))
  (println(merge [1 2 3] 2 4))
  (println(conj  [1 2 3] 2 4))
  )
;(merge1)
#_({:d 4, :c 3, :b 9, :a 1}
{:d 4, :c 3, :b 9, :a 1}
[1 2 3 2 4]
[1 2 3 2 4])

(defn reduce1 [& args]
  (println (reduce + [1 2 3 4 5]))
  (println (reduce + 1 [2 3])))
;(reduce1)
#_(15
6)

(defn reduce2 [& args]
  (reduce
    (fn [m v]
      (assoc m v (* v v)))
    {}
    [1 2 3 5]))
;(reduce2)
;{5 25, 3 9, 2 4, 1 1}

(defn reduce22 [& args]
  (reduce
    #(assoc % %2 (* %2 %2))
    {}
    [1 2 3 5]))
;(reduce22)
;{5 25, 3 9, 2 4, 1 1}

(defn reduce3 [& args]                   ;Fibonacijev niz
  (reduce
    (fn [a b] (conj a (+' (last a) (last (butlast a)))))  
    [0 1]  
    (range 98)))
;(reduce3)
#_([0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765 10946 17711 28657 46368 75025 121393 196418 317811 514229 832040 1346269 2178309 3524578 5702887 9227465 14930352 24157817 39088169 63245986 102334155 165580141 267914296 433494437 701408733 1134903170 1836311903 2971215073 4807526976 7778742049 12586269025 20365011074 32951280099 53316291173 86267571272 139583862445 225851433717 365435296162 591286729879 956722026041 1548008755920 2504730781961 4052739537881 6557470319842 10610209857723 17167680177565 27777890035288 44945570212853 72723460248141 117669030460994 190392490709135 308061521170129 498454011879264 806515533049393 1304969544928657 2111485077978050 3416454622906707 5527939700884757 8944394323791464 14472334024676221 23416728348467685 37889062373143906 61305790721611591 99194853094755497 160500643816367088 259695496911122585 420196140727489673 679891637638612258 1100087778366101931 1779979416004714189 2880067194370816120 4660046610375530309 7540113804746346429 12200160415121876738N 19740274219868223167N 31940434634990099905N 51680708854858323072N 83621143489848422977N 135301852344706746049N 218922995834555169026N])

;(apply hash-map [:a 5 :b 6])
(def x111 [2 -2 10 15])
(defn apply1 [& args]
  (println (apply * 0.5 3 x111)))  ;0.5*3*2*(-2)*10*15
;(apply1)
;-900

(def only-strings (partial filter string?))
;(only-strings ["a" 5 "b" 6])
;("a" "b")

;(#(filter string? %) ["a" 5 "b" 6])
;(#(filter % ["a" 5 "b" 6]) string?)
;(#(filter % ["a" 5 "b" 6]) number?)

(defn partial1 [& args]
  (println(#(map * % %2 %3) [1 2 3] [4 5 6] [7 8 9]))
  (println(#(map * % %2) [1 2 3] [4 5 6]))
  (println(#(apply map * %&) [1 2 3] [4 5 6] [7 8 9]))
  (println(#(apply map * %&) [1 2 3] [4 5 6]))
  (println((partial map *) [1 2 3] [4 5 6] [7 8 9]))           ;partial komanda vrsi raccunske operacije nad prvim clanovima pa zatim drugim ...
  (println((partial map *) [1 2 3] [4 5 6]))
  )
;(partial1)
#_((28 80 162)
(4 10 18)
(28 80 162)
(4 10 18)
(28 80 162)
(4 10 18))

(defn negated-sum-str1
  [& numbers]
  (str (- (apply + numbers))))
;(negated-sum-str1 10 12 3.4 1)
;"-26.4"

(def negated-sum-str2 (comp str - +))
;(negated-sum-str2 10 12 3.4 1)
;"-26.4"

(require '[clojure.string :as str])       ;DEFINICIJA DA JE PROMENJIVA STRING promenjiva str je clojure string
(def camel->keyword (comp keyword                                    ;kompozicija funkcija za keyword (rec)
                          ;[s]                         ;moze da se definise i ulazna promenjiva ali ako jr nema upotrebljava se %
                          str/join                                   ;pridruzi(spoj) dobijene reci
                          (partial interpose \-)                         ;sa znakom - izmedju dobijenih delova
                          (partial map str/lower-case)                   ;velika slova prepravi na mala
                          #(str/split % #"(?<=[a-z])(?=[A-Z])")))        ; razdvaja unetu rec '%' po velikim slovima i pravi VECTOR
;(camel->keyword "PeraCamelCaseKlapNebojsaKK")
;:pera-camel-case-klap-nebojsa-kk

(defn camel1->keyword
  [s]
  (->> (str/split s #"(?<=[a-z])(?=[A-Z])")))                     ;razdvaja unetu rec 's' po velikim slovima i pravi VECTOR
;(camel1->keyword "PeraCamelCaseOOKlapNebojsaKK")
;["Pera" "Camel" "Case" "OOKlap" "Nebojsa" "KK"]

(defn camel2->keyword
  [s]
  (->> (str/split s #"(?<=[a-z])(?=[A-Z])")
    (map str/lower-case)
    (interpose \-)
    str/join
    keyword))

;(camel2->keyword "PeraCamelCaseOOKlapNebojsaKK")
;:pera-camel-case-ooklap-nebojsa-kk

(def camel-pairs->map (comp                         ;kompozicija 
                        (partial apply hash-map)    ;pravi map
                        (partial map-indexed        ;ucitava map u parovima i-indeks(rbr), x-vrednost i-tog clana ulaza (prvi clan ima indeks 0, drugi 1, ..., n-ti n-1)
                                 (fn [i x]                  
                                   ;(println i x)
                                   (if (odd? i)               ;proverava da li je i neparan
                                     x                        ;ako jeste dodaje vrednost
                                     (camel->keyword x))))))  ;ako nije poziva funkciju camel i vtsi prepravke na stringu

;(camel-pairs->map ["CamelCase" 5 "lowerCamelCase" 3 "5" 2])  ;ulaz svakog drugog polja mora da bude string jer funkcija calel radi iskljucivo sa stringovima
;{:camel-case 5, :lower-camel-case 3, :5 2}

(defn adder
  [n]
  (fn [x] (+ n x)))
;((adder 5) 18)
;23

(defn doubler
  [f]                   ;znak koji se upotrebljava u funkciji            
  (fn [& args]          ;brojevi nad kojima se vrsi operacija [f]
    (* 2 (apply f args))))
;((doubler +) 1 2 3 4 5 6)
;42        dobija se kao (1+2+3+4+5+6)*2
;((doubler -) 1 2 3 4 5 6)
;-38       dobija se kao (1-2-3-4-5-6)*2

(defn print-logger
  [writer]
  #(binding [*out* writer]
     (println %)))

(def writer (java.io.StringWriter.))
(def retained-logger (print-logger writer))
(retained-logger "hello Klap")