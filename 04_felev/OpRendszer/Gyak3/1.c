#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
    pid_t gy1, gy2;
    gy1 = fork();
    if (gy1) {
        gy2 = fork();   // szülő
    }
    printf("szülő: %d", gy1);
    printf("gyerek: %d", gy2);
    // 1-es pidúek: zombiefolyamat (árvák, akiknek megszüntek a szüleik)
}
/*
int main() {
    fork();
    fork();
    ilyenkor a második fork a gyerekben is megtörténik (több forknál forkbomba lesz)
}
*/