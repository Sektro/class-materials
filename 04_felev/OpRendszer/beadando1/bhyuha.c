#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <string.h>

#define MAX_NEV 50
#define MAX_VERS 500

struct Versenyzo {
    char nev[MAX_NEV];
    char vers[MAX_VERS];
    int tojas;
};

void foMenu(int *input);
void adatFelvetel();
void listaKeszites();
void adatModositas();
void adatTorles();
int hossz(char *a);
void kiirVersenyzo(struct Versenyzo *v);

int hossz(char *a) {
    char *b = a;
    while (*a++);
    return --a - b;
}

void foMenu(int *input) {
    printf("\nVálassza ki mit szeretne tenni!\nOpciók:\n");
    printf("[0] Kilépés\n");
    printf("[1] Adat felvétele\n");
    printf("[2] Adat módosítása\n");
    printf("[3] Adat törlése\n");
    printf("[4] Lista készítése\n");
    scanf("%d", input);
    switch (*input) {
        case 0:
            printf("|}-----------------------------------{|\n");
            return;
        case 1:
            adatFelvetel();
            return;
        case 2:
            adatModositas();
            return;
        case 3:
            adatTorles();
            return;
        case 4:
            listaKeszites();
            return;
        default:
            printf("Nem megfelelő érték! Kérem válasszon a felsorolt lehetőségek közül!\n");
            return;
    }
}

void adatFelvetel() {
    struct Versenyzo v;
    printf("Kérjük adja meg a nyuszi adatait:\n");
    printf("Neve: ");
    scanf(" %[^\n]", v.nev);
    printf("Vers: ");
    scanf(" %[^\n]", v.vers);
    v.tojas = 0;

    int file = open("nyuszi.dat", O_CREAT | O_RDWR | O_APPEND, S_IRUSR | S_IWUSR);
    write(file, &v, sizeof(struct Versenyzo));
    close(file);
}

void listaKeszites() {
    int file = open("nyuszi.dat", O_RDONLY, S_IRUSR | S_IWUSR);
    if (file == -1) {
        printf("Hiba a fájl megnyitásakor!\n");
        return;
    }

    struct Versenyzo v;
    printf("Jelentkezők listája:\n");
    while (read(file, &v, sizeof(struct Versenyzo)) > 0) {
        kiirVersenyzo(&v);
    }
    close(file);
}

void kiirVersenyzo(struct Versenyzo *v) {
    printf("Név: %s\n", v->nev);
    printf("Vers: %s\n", v->vers);
    printf("Tojások száma: %d\n\n", v->tojas);
}

void adatModositas() {
    int file = open("nyuszi.dat", O_RDWR, S_IRUSR | S_IWUSR);
    if (file == -1) {
        printf("Hiba a fájl megnyitásakor!\n");
        return;
    }

    struct Versenyzo v;
    char nev[MAX_NEV];
    printf("Adja meg a módosítani kívánt nyuszi nevét: ");
    scanf(" %[^\n]", nev);

    int found = 0;
    while (read(file, &v, sizeof(struct Versenyzo)) > 0) {
        if (strcmp(v.nev, nev) == 0) {
            found = 1;
            printf("Módosítani kívánt adat:\n");
            kiirVersenyzo(&v);


	    printf("[1] Vers átírása\n");
	    printf("[2] Tojás számainak módosítása\n");
	    int input = 0;
	    scanf("%i",&input);
	    switch (input) 
	    {
		case 1:
	            printf("Új vers: ");
	            scanf(" %[^\n]", v.vers);
		    break;
		case 2:
        	    printf("Új tojások száma: ");
	            scanf("%d", &v.tojas);
		    break;
		default:
		    printf("Nem megfelelő érték! Kérem válasszon a felsorolt lehetőségek közül!\n");
		    close(file);
		    return; 
	    }


            lseek(file, -sizeof(struct Versenyzo), SEEK_CUR);
            write(file, &v, sizeof(struct Versenyzo));
            printf("Módosítás sikeres!\n");
            break;
        }
    }

    if (!found) {
        printf("Nem található ilyen nevű nyuszi!\n");
    }

    close(file);
}

void adatTorles() {
    int file = open("nyuszi.dat", O_RDWR, S_IRUSR | S_IWUSR);
    if (file == -1) {
        printf("Hiba a fájl megnyitásakor!\n");
        return;
    }

    struct Versenyzo v;
    char nev[MAX_NEV];
    printf("Adja meg a törölni kívánt nyuszi nevét: ");
    scanf(" %[^\n]", nev);

    int found = 0;
    int tempFile = open("nyuszi_temp.dat", O_CREAT | O_WRONLY, S_IRUSR | S_IWUSR);
    if (tempFile == -1) {
        printf("Hiba ideiglenes fájl létrehozásakor!\n");
        close(file);
        return;
    }

    while (read(file, &v, sizeof(struct Versenyzo)) > 0) {
        if (strcmp(v.nev, nev) != 0) {
            write(tempFile, &v, sizeof(struct Versenyzo));
        } else {
            found = 1;
        }
    }

    if (found) {
        printf("Törlés sikeres!\n");
    } else {
        printf("Nem található ilyen nevű nyuszi!\n");
    }

    close(file);
    close(tempFile);
    remove("nyuszi.dat");
    rename("nyuszi_temp.dat", "nyuszi.dat");
}

int main() {
    int input = 9;
    printf("|}-----------------------------------{|\n");
    printf("Üdvözöljük a Húsvéti Locsolóversenyen!\n");
    while (input) {
        foMenu(&input);
    }
    return 0;
}

