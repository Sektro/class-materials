#include <iostream>
#include <string>

template <typename FUNC>
void transform_string(std::string & str, FUNC t) {
    for (size_t i = 0; i < str.length(); ++i)
        str[i] = t(str[i]);
}

/* Caesar titkosítás A->B kulccsal */
class ABCaesar {
public:
    char operator() (char c) {
        if (c == 'z')
            return 'a';
        else
            return c+1;
    }
};

/* Caesar titkosítás megadható kulccsal */
class Caesar {
private:
    char kulcs;

public:
    Caesar(char kulcs) : kulcs(kulcs) {}
    char operator() (char c) {
        return (c-'a' + kulcs-'a')%('z'-'a'+1) + 'a';
    }
};

/* Round-robin titkosítás */
class RoundRobin {
private:
    char kulcs = 'b';
public:
    char operator() (char c) {
        // vezessuk vissza a Caesarra!
        Caesar caesar(kulcs);
        char eredmeny = caesar(c);
        ++kulcs;
        if (kulcs > 'z')
            kulcs = 'a';
        return eredmeny;
    }
};


int main() {
    std::string szoveg = "alma";

    ABCaesar abc;
    //~ Caesar   c('d');
    //~ RoundRobin r;
    transform_string(szoveg, abc);

    std::cout << szoveg << std::endl;
}
