module Homework08 where 
import Data.List
{-
compress :: Eq a => [a] -> [(a,Int)]
compress [] = []
compress l@(x:xs) = (((group l)!!0)!!0,length((group l)!!0)):(compress (drop (length((group l)!!0)) l)) where
--    n = sum [1|y<-[1..(length l)], ]
-}
compress :: Eq a => [a] -> [(a,Int)]
compress l = map cnt (group l) where
    cnt xs = (head xs, length xs)

{-
decompress :: Eq a => [(a,Int)] -> [a]
decompress [] = []
decompress l@((x,y):xs) = [x|z<-[1..y]] ++ decompress xs
-}

decompress :: Eq a => [(a,Int)] -> [a]
decompress l = concat (map f l) where
    f (a,b) = replicate b a

{-
mapping :: [(Char,Char)]
mapping = [('0','3'),('1','4'),('2','5'),('3','6'),('4','7'),('5','8'),('6','9'),('7','A'),('8','B'),('9','C'),('A','D'),('B','E'),('C','F'),('D','G'),('E','H'),('F','I'),('G','J'),('H','K'),('I','L'),('J','M'),('K','N'),('L','O'),('M','P'),('N','Q'),('O','R'),('P','S'),('Q','T'),('R','U'),('S','V'),('T','W'),('U','X'),('V','Y'),('W','Z'),('X','a'),('Y','b'),('Z','c'),('a','d'),('b','e'),('c','f'),('d','g'),('e','h'),('f','i'),('g','j'),('h','k'),('i','l'),('j','m'),('k','n'),('l','o'),('m','p'),('n','q'),('o','r'),('p','s'),('q','t'),('r','u'),('s','v'),('t','w'),('u','x'),('v','y'),('w','z'),('x','0'),('y','1'),('z','2')]
-}

mapping :: [(Char,Char)]
mapping = zip l k where
    l = ['0'..'9'] ++ ['A'..'Z'] ++ ['a'..'z']
    k = drop 3 l ++ ['0'..'2']

{-
encodeCaesar :: String -> String
encodeCaesar [] = []
encodeCaesar (x:xs) = snd(mapping!!n):(encodeCaesar xs) where
    n = last [y|y<-[0..(length(mapping)-1)], x == fst(mapping!!y)]
-}

encodeCaesar :: String -> String
encodeCaesar l@(x:xs) = map search l where
    search k = maybe k snd (find (\(x, _)->x == k) mapping)

{-
decodeCaesar :: String -> String
decodeCaesar [] = []
decodeCaesar (x:xs) = fst(mapping!!n):(decodeCaesar xs) where
    n = last [y|y<-[0..(length(mapping)-1)], x == snd(mapping!!y)]
-}

decodeCaesar :: String -> String
decodeCaesar l@(x:xs) = map search l where
    search k = maybe k fst (find (\(_, x)->x == k) mapping)


apsOnLists :: [a -> b] -> [[a]] -> [[b]]
apsOnLists f l = zipWith (\fs ls -> map fs ls) f l
