module Homework4 where 

myDate :: [(Int,Int)]
myDate = [ (1,y) |  y <- [1..31]] ++ [ (2,y) |  y <- [1..28]] ++ [ (3,y) |  y <- [1..31]] ++ [ (4,y) |  y <- [1..30]] ++ [ (5,y) |  y <- [1..31]] ++ [ (6,y) |  y <- [1..30]] ++ [ (7,y) |  y <- [1..31]] ++ [ (8,y) |  y <- [1..31]] ++ [ (9,y) |  y <- [1..30]] ++ [ (10,y) |  y <- [1..31]] ++ [ (11,y) |  y <- [1..30]] ++ [ (12,y) |  y <- [1..31]]

--[(m,d) | m<-[1..12], d<-[1..31], (m `elem` [4, 6, 9, 11]) <= (d <= 30), (m == 2) <= (d <= 28)] :: [(Int,Int)]
--[ (h,n) | h<-[1..12], n<-[1..31], elem h [1,3,5,7,8,10,12] || (elem h [4,6,9,11]) && (n<31) || (n < 29)]

numCharPair :: [(Int,Char)]
numCharPair = zip [ y | y <- [1..length ([ x | x <- ['a'..'z']])]] [ x | x <- ['a'..'z']] 

-- zip [1..] ['a'..'z'] :: [(Int,Char)]

mySequence1 :: [Int]
mySequence1 = [ i | i <- [1..], x <- [1..i], x /= i + 1]

--mySequence1 = [ x | x <- [1..], y <- [1..x]]

{-
mySequence2 :: [Int]
mySequence2 = [[1..y] ++ [y, (y-1)..2] | y <- [2..] ]
-}
{- 
mySequence2 :: [Int]
mySequence2 = concat [ [x,z]| y <- [2..], x <- [1..(y-1)], z <- [y,(y-1)..2]]
-}

{-
mySequence2 :: [Int]
mySequence2 = concat [ [x,y] | c <- [1..5], x <- [ z | z <- [1..c] , z /= c], y <- [ d | d <- [c,(c-1)..2], x == c ] ] 
-}

-- mySequence2 :: [Int]
-- mySequence2 = [ x | c <- [1..5], x <- [ z | z <- [1..c], (z == c) <= (z <= 0)] ] 
-- mySequence2 :: [Int]
-- mySequence2 = [ y | c <- [1..5], y <- [ d | d <- [c,(c-1)..1], (d == 1) <= (d <= 0)] ] 

-- [ x | c <- [1..5], x <- [ z | z <- [1..c], (z == c) <= (z <= 0)] ] 
-- [ y | c <- [1..5], y <- [ d | d <- [c,(c-1)..1], (d == 1) <= (d <= 0)] ] 


--Nem végtelenes példa (eredmény: ürestömb):
--mySequence2 :: [Int]
--mySequence2 = concat [ [x]++[c] | x <- [ z | z <- [1..5],(z==5)<=(z<=0)], c <- [ d | d <- [5..2],(x/=5)<=(d<=0)] ]
--vagy mySequence2 = concat [ [x,c] | x <- [ z | z <- [1..5],(z==5)<=(z<=0)], c <- [ d | d <- [5..2],(x/=5)<=(d<=0)] ]

--mySequence2 :: [Int]
--mySequence2 = concat [ [x]++[c] | y <- [1..], x <- [ z | z <- [1..y],(z==y)<=(z<=0)], c <- [ d | d <- [y,(y-1)..2],(x/=y)<=(d<=0)] ]
--Itt nem tudom hogy konvertáljam át működőképessé, ha az x, vagy a c ürestömb, akkor egyiket sem írja ki, pedig concat [] [1,2,3] és [] ++ [1,2,3] parancsok működnek

mySequence2 :: [Int]
mySequence2 = concat [take x [1..x] ++ reverse [2..x-1] | x <- [2..]]
--Ezt segítséggel csináltam, még ködös nekem

stars :: String
stars = unwords [ (replicate x '*') | x <- [1..] ]

swap :: (a, b) -> (b, a)
swap (x,y) = (y,x)

mirrorX :: Num a => (a, a) -> (a, a)
mirrorX (x,y) = (x, negate y)

mirrorP :: Num a => (a, a) -> (a, a) -> (a, a)
mirrorP (x,y) (c,d) = (x+(x-c), y+(y-d))

distance :: Floating t => (t, t) -> (t, t) -> t
distance (x,y) (c,d) = sqrt((x-c)^2+(y-d)^2)

