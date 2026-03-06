#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <string.h>
#include <time.h>


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
int adatModositas2(int msg[]);
void adatTorles();
void kiirVersenyzo(struct Versenyzo *v);
void locsolas();
void hirdetes();
void handler(int signum);


//beadando2
void handler(int signum) {}
void locsolas() {
	signal(SIGUSR1,handler);
	int cso[2];
	pid_t gy;
	int msg[50];
    struct Versenyzo v;

    //errorok
	if (pipe(cso) == -1) {
		perror("Hiba a pipe nyitásakor!");
		exit(EXIT_FAILURE);
	}
	gy = fork();
	if (gy == -1) {
		perror("Fork hiba!");
		exit(EXIT_FAILURE);
	}
    int file = open("nyuszi.dat", O_RDWR, S_IRUSR | S_IWUSR);
    if (file == -1) {
        printf("Hiba a fájl megnyitásakor!\n");
        return;
    }

	if (gy == 0) { // nyuszi fiúk
		close(cso[0]);
        printf("__________________________\n");
        printf("Versek felolvasása:\n\n");
        int i = 0;
        srand(time(0));
		while (read(file, &v, sizeof(struct Versenyzo)) > 0) {
            msg[i] = (rand() % 20) + 1; 
            printf("%s:\n",v.nev);
            printf("%s\n\n",v.vers);
            printf("kapott tojások száma: %i\n\n",msg[i]);
            ++i;
        }
        printf("__________________________\n");
        close(file);
		write(cso[1],msg,sizeof(msg));
		close(cso[1]);
		sleep(3);
		kill(getppid(),SIGUSR1);
        exit(EXIT_SUCCESS);
	}
	else { // főnyuszi
		close(cso[1]);
		pause();
		read(cso[0],msg,sizeof(msg));
        int maxid = 0;
        maxid = adatmodositas2(msg);
        hirdetes(maxid);
		close(cso[0]);
        close(file);
	}
	return;
}
void hirdetes(int id) {
    int file = open("nyuszi.dat", O_RDWR, S_IRUSR | S_IWUSR);
    if (file == -1) {
        printf("Hiba a fájl megnyitásakor!\n");
        return;
    }
    struct Versenyzo v;
    for (int i = 0; i < id; ++i) {lseek(file, sizeof(struct Versenyzo), SEEK_CUR);}
    read(file, &v, sizeof(struct Versenyzo));
    printf("\n~~~~~~~~~~~~~~~~~~~~~~~\n");
    printf("A győztes nyuszi: %s\n",v.nev);
    printf("~~~~~~~~~~~~~~~~~~~~~~~\n");
    close(file);
}
int adatmodositas2(int msg[]) {
    int file = open("nyuszi.dat", O_RDWR, S_IRUSR | S_IWUSR);
    if (file == -1) {
        printf("Hiba a fájl megnyitásakor!\n");
        return;
    }
    struct Versenyzo v;

    int max = 0;
    int maxid = 0;
    int i = 0;
    while (read(file, &v, sizeof(struct Versenyzo)) > 0) {
	    v.tojas = msg[i];
        lseek(file, -sizeof(struct Versenyzo), SEEK_CUR);
        write(file, &v, sizeof(struct Versenyzo));

        if (msg[i] > max) {
            max = msg[i];
            maxid = i;
        }
        ++i;
    } 
    close(file);
    return maxid;
}

void foMenu(int *input) {
    printf("\nVálassza ki mit szeretne tenni!\nOpciók:\n");
    printf("[0] Kilépés\n");
    printf("[1] Adat felvétele\n");
    printf("[2] Adat módosítása\n");
    printf("[3] Adat törlése\n");
    printf("[4] Lista készítése\n");
    printf("[5] Locsolás\n");
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
	    case 5:
	        locsolas();
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


