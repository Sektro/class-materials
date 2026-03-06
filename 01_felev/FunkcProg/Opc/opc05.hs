module Opc05 where

type Air = Int
type Fire = Integer
type Shadow = Float
type Haskell = Double
type Open = String
type Key a b = [[(a,[b])]]
type Type = String
type Power = Integer
type Missile = (Type, Power)

mind_vision :: Ord magic => [magic] -> Bool
mind_vision [] = True
mind_vision [x] = True
mind_vision (x:y:xs)
   | y > x = mind_vision (y:xs)
   | otherwise = False

lock :: Key a b -> Open
lock [(x,xs):[y,ys]]            = "First"
lock ([_]:[(x,[xs])]:[y,ys]:[]) = "Second"
lock ([(x,y:_:[])]:[])          = "Third"

--magic_key_1, magic_key_2, magic_key_3

magic_key_1 :: (Num a, Num b) => Key a b
magic_key_1 = [[(1,[2,2]),(3,[4,4]),(5,[6,6])]]


magic_key_2 :: (Num a, Num b) => Key a b
magic_key_2 = [(0,[1])]:[(2,[3])]:[(4,[5]),(6,[7])]:[]

magic_key_3 :: (Num a, Num b) => Key a b
magic_key_3 = [[(1,[2,3])]]

mage_armor :: [Missile] -> Type -> Power
mage_armor [] _ = 0
mage_armor ((y,x):xs) n 
   | y == n = 0 + (mage_armor xs n)
   | otherwise = x + (mage_armor xs n)


backfire :: [Missile] -> [Missile]
backfire u = vegigMegy u 0
   where
   vegigMegy :: [Missile] -> Int -> [Missile]
   vegigMegy [] _ = []
   vegigMegy (x:xs) n = (elozo x (reverse(take n u)))++(vegigMegy xs (n+1))
   
   elozo :: Missile -> [Missile] -> [Missile]
   elozo (g,h) []
      | h >= 50 = [(g,h)]
      | otherwise = []
   elozo (g,h) ((y,z):ys)
      | g == y = elozo (g,h-z) []
      | otherwise = elozo (g,h) ys