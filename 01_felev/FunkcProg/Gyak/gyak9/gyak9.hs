module Lesson09 where


all :: (a -> Bool) -> [a]{-véges-} -> Bool
all f l = and (map (\e -> f e) l)

any :: (a -> Bool) -> [a]{-véges-} -> Bool
any f l = or (map (\e -> f e) l)

elem :: Eq a => a -> [a]{-véges-} -> Bool
elem a l = any (a ==) l

filters :: Eq a => [a] -> [a] -> [a]
filters l1 l2 = filter (`notElem` l1) l2 

zipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith f a b = map (\e -> f (fst e) (snd e)) (zip a b)
--zipWith f a b = map (\(x,y) -> f x y) (zip a b)

{-
zip = zipWith 

zip :: [a] -> [b] -> [(a, b)]
(,) :: a->b->(a,b)
zipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith :: (a->b->(a,b)) -> [a] -> [b] -> [(a,b)] ( = zip)
-}

differences :: Num a => [a] -> [a]
differences l = zipWith (-) (drop 1 l) l

fibPairs :: [(Integer, Integer)]
fibPairs = iterate (\(a,b)->(b,a+b))(0,1)

uncurry :: (a -> b -> c) -> ((a, b) -> c)
uncurry f (x,y) = f x y

decompress :: Eq a => [(Int,a)] -> [a]
decompress x = concat(map (uncurry replicate) x)

{-
(+) :: Num a => (a -> (a -> a))

inc :: Int -> Int
inc a = a + 1
inc a = 1 + a
inc a = (1 + a)
inc = (1+)
inc = (\a -> 1 + a)

add Int -> Int -> Int
add a b = a + b
add a b = (+) a b
add a   = (+) a
add     = (+)
-}

(.) :: (b -> c) -> (a -> b) -> (a -> c)
(.) f g = (\a -> f (g a) )
--(.) f g a = f (g a) 

numbersMadeOfOnes :: [Integer]
numbersMadeOfOnes = iterate ((+1) . (*10)) 1
--numbersMadeOfOnes = iterate (\x -> x*10 +1) 1

numbersMadeOfThrees :: [Integer]
numbersMadeOfThrees = iterate ((+3) . (*10)) 3
--numbersMadeOfThrees = iterate (\x -> x*10 +3) 3

{-
decompress l = concat( map (uncurry replicate) l)
decompress = concat . map (uncurry replicate)
-}

dropSpaces :: String -> String
dropSpaces = dropWhile isSpace

trim :: String{-véges-} -> String
trim = reverse . dropSpaces . reverse . dropSpaces

mapMap :: (a -> b) -> [[a]] -> [[b]]
mapMap = map . map
