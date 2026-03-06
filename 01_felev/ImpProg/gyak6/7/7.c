#include <stdio.h>

int main()
{
    FILE *fp;
    /*
    
    r - Megnyitja olvasásra

    w - Megnyitja írásra. Ha nem létezik, újat hoz létre.

    a - Megnyitja írásra hozzáadás módban. Ha nem létezik, újat hoz létre.

    r+ - Megnyitja olvasásra és írásra.

    w+ Megnyitja olvasásra és írásra. Kiüríti a fájlt ha létezik, egyébként újat hoz létre.

    a+ - Megnyitja olvasásra és írásra. Létrehoz egy új fájlt ha nem létezik. 

    
    Összesre: Olvasás az elejétől kezdődik, de írás csak a végére lehetséges.

    */

    fp = fopen("test.txt", "w+");
    fprintf(fp, "Ez itt fprintf...\n"); //tudjuk formázni a kiírást
    fputs("Ez itt fputs...\n", fp);
    fclose(fp);

    return 0;
}