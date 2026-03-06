
#include <stdio.h>

//if (scanf("%c%d",&x,&y)==EOF) { break; } <----- előző órához

//globális változó
int a = 20;
int inicializalatlan_globalis;

int main ()
{
    //lokális változó a main() függvényben
    int a = 10;
    int b = 20;
    int c = 0;
    int inicializalatlan_lokalis;
}

/*
extern: más fájlból eléri a globális fájlokat
static: csak abban a fájlban látható
egybe lefordítás: pl.: gyak1, gyak2, gyak3 --> gcc gyak*
*/