factor = lambda n: 1 if n == 1 else n * factor(n-1)
print(factor(3))
is_odd = lambda n: True if n % 2 != 0 else False
print(is_odd(3))

def reciprocal(n):
    try:
        n = 1 / n
    except ZeroDivisionError:
        print("Division failed")
        n = None
    else:
        print("Fine")
    finally:
        print("Goodbye")
    return n
print(reciprocal(2))
print(reciprocal(0))

class A:
    def greet(self):
        print("Hello from A")
class B(A):
    def greet(self):
        print("Hello from B")
class C(B):
    def greet(self):
        super().greet() # Az aktuális osztály szuperosztályára mutat
        #super(B, self).greet() # Explicit módon az A osztály greet metódusát hívja meg
print("Hello from C")
c = C()
c.greet()


class Dog:
    kennel = 0
    def __init__(self,breed):
        self.breed = breed
        Dog.kennel += 1
    def __str__(self):
        return self.breed + " says: Woof!"
class SheepDog(Dog):
    def __str__(self):
        return super().__str__() + " Don't run away, Little Lamb!"
class GuardDog(Dog):
    def __str__(self):
        return super().__str__() + " Stay where you are, Mister Intruder!"
Rocky = SheepDog("Collie")
Luna = GuardDog ("Dobermann")
Lassie = SheepDog("Rough Collie")
print(Rocky)
print(Luna)
print(issubclass(SheepDog, Dog), issubclass(SheepDog, GuardDog))
print(isinstance(Rocky, GuardDog ), isinstance(Luna, GuardDog))
print(Luna is Luna, Rocky is Luna)
print(Rocky.kennel)

class Book:
    def __init__(self, title=None, author=None, pages=None):
        if not title or not author or not pages:
            raise ValueError("Minden tulajdonság megadása kötelező!")
        if not isinstance(pages, int) or pages <= 0:
            raise ValueError("Az oldalszámnak pozitív egész számnak kell lennie!")
        self.title = title
        self.author = author
        self.pages = pages
    def __lt__(self, other): #less than
        if not isinstance(other, Book):
            raise Exception("Összehasonlíthatatlan adatok!")
        return self.pages < other.pages
    def __eq__(self, other): #equal
        if not isinstance(other, Book):
            raise Exception("Összehasonlíthatatlan adatok!")
        return self.pages == other.pages
    def __gt__(self, other): #greater than
        if not isinstance(other, Book):
            raise Exception("Összehasonlíthatatlan adatok!")
        return self.pages > other.pages
    def __str__(self):
        return f"{self.title} - {self.author} ({self.pages} oldal)"

book1 = Book("Harry Potter", "J.K. Rowling", 300)
book2 = Book("The Lord of the Rings", "J.R.R. Tolkien", 500)
book3 = ("The Hobbit", "J.R.R. Tolkien", 250)
print(book1)
print(book1 < book2)
print(book1 == book2)
print(book2 > book3)

class Library:
    overall_books = [] #osztályváltozó
    def __init__(self):
        self.books = []
    def add_book(self, book): ###
    def __len__(self): ###
    def __str__(self): ###