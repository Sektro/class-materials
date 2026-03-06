module Magewar where

showState a = show a
showMage a = show a
eqMage a b =  a == b
showUnit a = show a
showOneVOne a = show a 

type Health = Integer
type Name = String
type Spell = (Integer -> Integer)
type Army = [Unit]
type EnemyArmy = Army
type Amount = Integer

--Feladat1
data State a = Dead  | Alive a deriving Eq

instance Show a => (Show (State a)) where
    show (Alive  a) = show a
    show Dead = "Dead"


data Entity = Golem Health | HaskellElemental Health deriving (Eq, Show)

data Mage = Master Name Health Spell 

--
papi = let 
    tunderpor enemyHP
        | enemyHP < 8 = 0
        | even enemyHP = div (enemyHP * 3) 4
        | otherwise = enemyHP - 3
    in Master "Papi" 126 tunderpor
java = Master "Java" 100 (\x ->  x - (mod x 9))
traktor = Master "Traktor" 20 (\x -> div (x + 10) ((mod x 4) + 1))
jani = Master "Jani" 100 (\x -> x - div x 4)
skver = Master "Skver" 100 (\x -> div (x+4) 2)

instance Show (Mage) where
    show (Master n h s) | h >= 5 = n
                        | otherwise = "Wounded " ++ n
instance Eq (Mage) where
   (Master n1 h1 s1) == (Master n2 h2 s2) = (n1==n2)&&(h1==h2)

data Unit = M (State Mage)| E (State Entity) deriving Eq

instance Show (Unit) where
    show (M (Alive (Master n h s))) = show (Master n h s)
    show (M Dead) = "Dead"
    show (E (Alive (Golem h))) = show (Golem h)
    show (E (Alive (HaskellElemental h))) = show (HaskellElemental h)
    show (E Dead) = "Dead"

--Feladat2
formationFix :: Army -> Army
formationFix l = a(l) ++ b(l)
  where
    a :: Army -> Army
    a [] = []
    a ((M (Alive (Master n h s))):xs) = (M (Alive (Master n h s))):(a xs)
    a ((E (Alive x)):xs) = (E (Alive x)):(a xs)
    a ((E Dead):xs) = (a xs)
    a ((M Dead):xs) = (a xs)
    b :: Army -> Army
    b [] = []
    b ((M (Alive (Master n h s))):xs) = (b xs)
    b ((E (Alive x)):xs) = (b xs)
    b ((E Dead):xs) = (E Dead):(b xs)
    b ((M Dead):xs) = (M Dead):(b xs)

--Feladat3
over :: Army -> Bool
over l = a(l) == []
  where
    a :: Army -> Army
    a [] = []
    a ((M (Alive (Master n h s))):xs) = (M (Alive (Master n h s))):(a xs)
    a ((E (Alive x)):xs) = (E (Alive x)):(a xs)
    a ((E Dead):xs) = (a xs)
    a ((M Dead):xs) = (a xs)

--
potionMaster = 
  let plx x
        | x > 85  = x - plx (div x 2)
        | x == 60 = 31
        | x >= 51 = 1 + mod x 30
        | otherwise = x - 7 
  in Master "PotionMaster" 170 plx



--Feladat4

fight :: EnemyArmy -> Army -> Army
fight [] (E (Alive (HaskellElemental y)):ys) 
  | y <= 0 = (E Dead):(fight [] ys)
  | otherwise = (E (Alive (HaskellElemental y))):(fight [] ys)
fight [] (E (Alive (Golem y)):ys) 
  | y <= 0 = (E Dead):(fight [] ys)
  | otherwise = (E (Alive (Golem y))):(fight [] ys)
fight [] ((M (Alive (Master n h s))):ys) 
  | h <= 0 = (M Dead):(fight [] ys)
  | otherwise = (M (Alive (Master n h s))):(fight [] ys)

fight ((E Dead):xs) (E (Alive (HaskellElemental y)):ys) 
  | y <= 0 = (E Dead):(fight xs ys)
  | otherwise = (E (Alive (HaskellElemental y))):(fight xs ys)
fight ((E Dead):xs) (E (Alive (Golem y)):ys) 
  | y <= 0 = (E Dead):(fight xs ys)
  | otherwise = (E (Alive (Golem y))):(fight xs ys)
fight ((E Dead):xs) ((M (Alive (Master n h s))):ys) 
  | h <= 0 = (M Dead):(fight xs ys)
  | otherwise = (M (Alive (Master n h s))):(fight xs ys)

fight ((M Dead):xs) (E (Alive (HaskellElemental y)):ys) 
  | y <= 0 = (E Dead):(fight xs ys)
  | otherwise = (E (Alive (HaskellElemental y))):(fight xs ys)
fight ((M Dead):xs) (E (Alive (Golem y)):ys) 
  | y <= 0 = (E Dead):(fight xs ys)
  | otherwise = (E (Alive (Golem y))):(fight xs ys)
fight ((M Dead):xs) ((M (Alive (Master n h s))):ys) 
  | h <= 0 = (M Dead):(fight xs ys)
  | otherwise = (M (Alive (Master n h s))):(fight xs ys)

fight (x:xs) ((E Dead):ys) = (E Dead):(fight xs ys) 
fight [] ((E Dead):ys) = (E Dead):(fight [] ys) 
fight (x:xs) ((M Dead):ys) = (M Dead):(fight xs ys) 
fight [] ((M Dead):ys) = (M Dead):(fight [] ys)
fight _ [] = []
fight (E (Alive (HaskellElemental x)):xs) (E (Alive (HaskellElemental y)):ys) 
  | (y-3) <= 0 = (E Dead):(fight xs ys)
  | otherwise = (E (Alive (HaskellElemental (y-3)))):(fight xs ys)
fight (E (Alive (HaskellElemental x)):xs) (E (Alive (Golem y)):ys)
  | (y-3) <= 0 = (E Dead):(fight xs ys)
  | otherwise  = (E (Alive (Golem (y-3)))):(fight xs ys)
fight (E (Alive (HaskellElemental x)):xs) (M (Alive (Master n h s)):ys) 
  | (h-3) <= 0 = (M Dead):(fight xs ys)
  | otherwise = (M (Alive (Master n (h-3) s))):(fight xs ys)
fight (E (Alive (Golem x)):xs) (E (Alive (HaskellElemental y)):ys)
  | (y-1) <= 0 = (E Dead):(fight xs ys)
  | otherwise = (E (Alive (HaskellElemental (y-1)))):(fight xs ys)
fight (E (Alive (Golem x)):xs) (E (Alive (Golem y)):ys) 
  | (y-1) <= 0 = (E Dead):(fight xs ys)
  | otherwise  = (E (Alive (Golem (y-1)))):(fight xs ys)
fight (E (Alive (Golem x)):xs) ((M (Alive (Master n h s))):ys) 
  | (h-1) <= 0 = (M Dead):(fight xs ys)
  | otherwise = (M (Alive (Master n (h-1) s))):(fight xs ys)
fight ((M (Alive (Master n h s))):xs) (E (Alive (HaskellElemental y)):ys) 
  | (s y) <= 0 = (E Dead):(fight xs (varazsSeged s ys))
  | otherwise = (E (Alive (HaskellElemental (s y)))):(fight xs (varazsSeged s ys))
fight ((M (Alive (Master n h s))):xs) (E (Alive (Golem y)):ys)
  | (s y) <= 0 = (E Dead):(fight xs (varazsSeged s ys))
  | otherwise  = (E (Alive (Golem (s y)))):(fight xs (varazsSeged s ys))
fight ((M (Alive (Master n1 h1 s1))):xs) ((M (Alive (Master n2 h2 s2))):ys) 
  | (s1 h2) <= 0 = (M Dead):(fight xs (varazsSeged s1 ys))
  | otherwise = (M (Alive (Master n2 (s1 h2) s2))):(fight xs (varazsSeged s1 ys))

varazsSeged :: Spell -> Army -> Army
varazsSeged _ [] = []
varazsSeged ss (E (Alive (HaskellElemental a)):as) = (E (Alive (HaskellElemental (ss a)))):(varazsSeged ss as)
varazsSeged ss (E (Alive (Golem a)):as) = (E (Alive (Golem (ss a)))):(varazsSeged ss as)
varazsSeged ss1 ((M (Alive (Master nn hh ss2))):as) = (M (Alive (Master nn (ss1 hh) ss2))):(varazsSeged ss1 as)

--Feladat5
haskellBlast :: Army -> Army
haskellBlast [] = []
haskellBlast l@(x:y:z:w:q:xs) =  reverse(drop ((findTarget (countDamage l))+5) (reverse l)) ++ reverse (blastArmy(take 5 (drop (findTarget (countDamage l)) (reverse l)))) ++ reverse (take (findTarget (countDamage l)) (reverse l))
haskellBlast l@(x:y:z:w:xs) = blastArmy l
haskellBlast l@(x:y:z:xs) = blastArmy l
haskellBlast l@(x:y:xs) = blastArmy l
haskellBlast l@(x:xs) = blastArmy l

extractHealth :: Unit -> Health
extractHealth (E (Alive (HaskellElemental y))) = y
extractHealth (E (Alive (Golem y))) = y
extractHealth (M (Alive (Master n h s))) = h
extractHealth (M Dead) = 0
extractHealth (E Dead) = 0

blast :: Health -> Health
blast h
   | h > 4 = h - 5
   | otherwise = 0

countDamage :: Army -> [Health]
countDamage l@(x:y:z:w:q:xs) = ((extractHealth x)-(blast (extractHealth x)) + (extractHealth y)-(blast (extractHealth y)) + (extractHealth z)-(blast (extractHealth z)) + (extractHealth w)-(blast (extractHealth w)) + (extractHealth q)-(blast (extractHealth q))):(countDamage (y:z:w:q:xs))
countDamage (x:y:z:w:xs) = []

findTarget :: [Health] -> Int
findTarget l@(x:xs) = a l (maximum l)
   where
   a :: [Health] -> Health -> Int
   a (y:ys) hp
      | y == hp = sum [1|t<-ys]
      | otherwise = a ys hp

blastArmy :: Army -> Army
blastArmy [] = []
blastArmy ((E (Alive (HaskellElemental y))):xs)
   | blast y == 0 = (E Dead):(blastArmy xs) 
   | otherwise =  (E (Alive (HaskellElemental (blast y)))):(blastArmy xs)
blastArmy ((E (Alive (Golem y))):xs)
   | blast y == 0 = (E Dead):(blastArmy xs) 
   | otherwise  = (E (Alive (Golem (blast y)))):(blastArmy xs)
blastArmy ((M (Alive (Master n h s))):xs) 
   | blast h == 0 = (M Dead):(blastArmy xs) 
   | otherwise = (M (Alive (Master n (blast h) s))):(blastArmy xs)
blastArmy ((M Dead):xs) = (M Dead):(blastArmy xs)
blastArmy ((E Dead):xs) = (E Dead):(blastArmy xs)


--Feladat6
multiHeal :: Health -> Army -> Army
multiHeal h l 
   | h <= 0 = l
   | otherwise = multiHealSeged h (take 1000 l)

multiHealSeged :: Health -> Army -> Army
multiHealSeged _ [] = []
multiHealSeged hl l@(y:ys)
   | hl <= 0 = l
   | sum(aliveAndWell l) == 0 = l
   | otherwise = heal (((fromIntegral hl)-(((fromIntegral hl) `div` (sum(aliveAndWell l)))*(sum (aliveAndWell l)))) `mod` (sum (aliveAndWell l))) ((iterate (\p -> heal (fromIntegral hl) p) l)!!((fromIntegral hl) `div` (sum (aliveAndWell l)))) 
   where
   heal :: Int -> Army -> Army
   heal h [] = []
   heal h ((E (Alive (HaskellElemental x))):xs)
      | h == 0 = ((E (Alive (HaskellElemental x))):xs)
      | otherwise = (E (Alive (HaskellElemental (x+1)))):(heal (h-1) xs)
   heal h ((E (Alive (Golem x))):xs)
      | h == 0 = ((E (Alive (Golem x))):xs)
      | otherwise = (E (Alive (Golem (x+1)))):(heal (h-1) xs)
   heal h ((M (Alive (Master n x s))):xs)
      | h == 0 = ((M (Alive (Master n x s))):xs)
      | otherwise = (M (Alive (Master n (x+1) s))):(heal (h-1) xs)
   heal h (E Dead:xs) = (E Dead):(heal h xs)
   heal h (M Dead:xs) = (M Dead):(heal h xs)

aliveAndWell :: Army -> [Int]
aliveAndWell [] = []
aliveAndWell ((E (Alive (HaskellElemental z))):zs) = 1:(aliveAndWell zs)
aliveAndWell ((E (Alive (Golem z))):zs) = 1:(aliveAndWell zs)
aliveAndWell ((M (Alive (Master n1 z s1))):zs) = 1:(aliveAndWell zs)
aliveAndWell (E Dead:zs) = 0:(aliveAndWell zs)
aliveAndWell (M Dead:zs) = 0:(aliveAndWell zs)

notAliveNotWell :: Army -> [Int]
notAliveNotWell [] = []
notAliveNotWell ((E (Alive (HaskellElemental z))):zs) = 0:(notAliveNotWell zs)
notAliveNotWell ((E (Alive (Golem z))):zs) = 0:(notAliveNotWell zs)
notAliveNotWell ((M (Alive (Master n1 z s1))):zs) = 0:(notAliveNotWell zs)
notAliveNotWell (E Dead:zs) = 1:(notAliveNotWell zs)
notAliveNotWell (M Dead:zs) = 1:(notAliveNotWell zs)
{-
   heal h (x:xs) 
      | h == 0 = (x:xs)
      | otherwise = (x+1):(heal (h-1) xs)
-}

--Opcionalis

battle :: Army -> EnemyArmy -> Maybe Army -- vagy EnemyArmy lesz az eredmény.
battle [] [] = Nothing
battle lf le 
   | over lf = Just le
   | over le = Just lf
   | otherwise = Just (formationFix (multiHeal 20 (haskellBlast lf)))


teszt :: [Int] -> Int ->[Int]
teszt l e =  (iterate (\p -> a p e) l)!!3
   where
   a :: [Int] -> Int->[Int]
   a [] _ = []
   a (x:xs) k= (x+k):(a xs k)
