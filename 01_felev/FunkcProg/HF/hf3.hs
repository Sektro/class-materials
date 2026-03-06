module Homework3 where 

add :: Num a => (a, a) -> (a, a) -> (a , a)
add (x , y) (c , z) = (x * z + c * y , y * z)

mul :: Num a => (a, a) -> (a, a) -> (a , a)
mul (x , y) (c , z) = (x * c , y * z)

modDiv :: Integral a => a -> a -> (a,a)
modDiv x y = ( x `mod` y , x `div` y)

quadratic :: Floating a => a -> a -> a -> (a , a)
quadratic a b c = (  ((-b) - sqrt(b*b - 4*a*c))/(2*a) , ((-b) + sqrt(b*b - 4*a*c))/(2*a))

matches :: Eq a => (a,a) -> (a,a) -> Bool
matches (u,d) (t,c) = ((u==t) || (u==c) || (d==c) || (d==t)) 

fifty :: Integral a => [a]
fifty = [50,45..(-50)]

glue :: [a] -> [a] -> [a] -> [a] 
glue x y z = x ++ y ++ z