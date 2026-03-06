module Lesson3 where

{- Tuple / rendezett pár / rendezett n-es
Függvények:
 - 
-}

t1 :: (Int, Int)
t1 = (1,2)

t2 :: (Int, Char)
t2 = (1, 'a')

t3 :: (Int, Char, Bool)
t3 = (1,'A',True)

myFst :: (Num a, Num b) => (a,b) -> a
myFst (x,y) = x
--rendezett pár -> első tag

mySnd :: (a,b) -> b
mySnd x = snd x
--rendezett pár -> második tag

t4 :: (Int -> Int, Int -> Bool)
t4 = ((*2), even)

t5 :: a -> b -> (a,b,a)
t5 a b = (a,b,a)

type MyString = [Char]

11 :: Mystring
11 = "almafa"

12 :: Num a => a -> b -> [(a,b,a)]
12 x y = [(x,y,x),(x,y,x+x),(x+3,y,x-2)]