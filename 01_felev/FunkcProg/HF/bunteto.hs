module Bunteto where

--Feladat1
data Pattern = Wildcard | P Int deriving (Show,Eq)
data Date = Date Pattern Pattern Pattern Pattern deriving (Show,Eq)

--Feladat2
matchField :: Pattern -> Pattern -> Bool
matchField Wildcard _ = True
matchField _ Wildcard = True
matchField (P a) (P b) | a == b = True
               | otherwise = False

match :: Date -> Date -> Bool
match (Date x1 y1 z1 w1) (Date x2 y2 z2 w2) = matchField x1 x2 && matchField y1 y2 && matchField z1 z2 && matchField w1 w2

--Feladat3
type Job = Int -> Int
type CronTab = [(Date, Job)]

selectDueJobs :: CronTab -> Date -> Maybe [Job]
selectDueJobs [] _ = Nothing
selectDueJobs l@(s:ss) n
   | null (a l n) = Nothing
   | otherwise = Just (a l n)
   where
   a :: CronTab -> Date -> [Job]
   a [] _ = []
   a (z:zs) g
    | (match (fst(z)) g) = (snd(z)):(a zs g)
    | otherwise = a zs g


{-
    | (match (fst(x)) y) = (snd(x)):(selectDueJobs xs y)
    | otherwise = selectDueJobs xs y
-}


x  = Date (P 30) (P 12) (P 05) (P 12)
y  = Date (P 00) (P 00) (P 01) (P 01)
w1 = Date (P 30) Wildcard (P 05) (P 12)
w2 = Date Wildcard Wildcard Wildcard Wildcard

tab1 :: CronTab
tab1 = [(x,  \n -> n + 1)]
tab2 :: CronTab
tab2 = [(w1, \n -> n + 1)]
tab3 :: CronTab
tab3 = [(y,  \n -> n + 1)]
tab4 :: CronTab
tab4 = [(y,  \n -> n + 1), (x,   \n -> n * 2)]
tab5 :: CronTab
tab5 = [(w2, \n -> n + 1), (w2,  \n -> n * 2)]
tab6 :: CronTab
tab6 = [(w2, \n -> n * 2), (w2,  \n -> n + 1)]

--Feladat4
cron :: CronTab -> Date -> Int -> Int
cron [] _ m = m
cron l@(s:ss) n m 
   |isNothing(selectDueJobs l n)= m
   |otherwise = a (fromJust (selectDueJobs l n)) m
   where
   a :: [Job] -> Int -> Int
   a [] j = j
   a (g:gs) j = a gs (g j)

fromJust :: Maybe a -> a
fromJust (Just a) = a

isNothing :: Maybe a -> Bool
isNothing Nothing = True
isNothing _ = False