module Lesson12 where

data Alma = Idared Integer | Golden (Maybe Integer) deriving (Show, Eq)

fromAlma :: Alma -> Integer
fromAlma (Idared x) = x
fromAlma (Golden (Just x)) = x
fromAlma (Golden Nothing) = 0

type Author = String
type Title = String

data Book = B Author Title deriving (Show, Eq)

collection :: [Book] -> Author -> Maybe [Book]
collection [] _ = Nothing
collection l@(x:xs) a
    | Just (b l a) == Just [] = Nothing   --null (b l a) = Nothing
    | otherwise = Just (b l a)
  where
    b :: [Book] -> Author-> [Book]
    b [] _ = []
    b (y@(B a1 t):ys) a2
        | a1 == a2 = y:(b ys a2)
        | otherwise = b ys a2

--magasabbrendű megoldás:
collection' :: [Book] -> Author -> Maybe [Book]
collection' [] _ = Nothing
collection' l@(x:xs) a 
    | Just (b l a) == Just [] = Nothing   --null (b l a) = Nothing
    | otherwise = Just (b l a)
  where
    b :: [Book] -> Author-> [Book]
    b [] _ = []
    b g a2 = filter (\(B a2 t) -> a == a2) g


--datában data
data Language = Php | Java | Haskell

instance (Show Language) where
    show (Haskell) = "H"
    show (Php) = "P"
    show (Java) = "J"

data Programmer = Backend Language | Frontend Language

instance (Show Programmer) where
    show (Backend Php) = "Bck_Php"
    show (Backend Java) = "Bck_Java"
    show (Backend Haskell) = "My favorite language"
    show (Frontend Php) = "Fr_Php"
    show (Frontend Java) = "Fr_Java"
    show (Frontend Haskell) = "Not frontend language"