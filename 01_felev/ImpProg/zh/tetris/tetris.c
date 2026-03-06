#include <stdio.h>
#define y 10
#define x 8

void init_game(char jatekter[y][x],char forma[2][2]);
void next_polyomino(char  forma[2][2]);
void print_game(char jatekter[y][x], char  forma[2][2]);
int update_game(char jatekter[y][x], char  forma[2][2], int oszlop);
int hanyadik;

int main()
{
    printf("\n");
    printf("Welcome to my version of Tetris!\n");
    printf("In this game you will need to place polyominos on the field to fill it up.\nBe careful though! If the playingfield overflows, you lose!");

    char ter[y][x];
    char pol[2][2];
    int column;
    //char columncheck[100];
    int eog = 0;
    int seek = 0;

    init_game(ter,pol);
    next_polyomino(pol);
    print_game(ter,pol);

    while (eog == 0)
    {
        /*
        int j = 0;
        for (int i = 0; i < 100; ++i)
        {
            columncheck[i] = ' ';
        }
        */
        printf("Column: ");
        scanf("%d",&column);
        /*
        scanf("%s",&columncheck);
        while (columncheck[j] != '1' && columncheck[j] != '2' && columncheck[j] != '3' && columncheck[j] != '4' && columncheck[j] != '5' && columncheck[j] != '6' && columncheck[j] != '7' && columncheck[j] != '8')
        {
            ++j;
        }
        column = columncheck[j];
        */
        seek = update_game(ter,pol,column);
        if (seek == (-2))
        {
            printf("Game Over!\n");
            ++eog;
        }
        if (seek == (-1))
        {
            printf("You can't place that polyomino in that column!\n");
        }
        else
        {
            next_polyomino(pol);
            print_game(ter,pol);
        }
    }
    return 0;
}

void init_game(char jatekter[y][x], char  forma[2][2])
{
    for (int i = 0; i < y; ++i)
    {
        for (int j = 0; j < 8;++j)
        {
            jatekter[i][j] = ' ';
        }
    }
    forma[0][0] = ' ';
    forma[0][1] = ' ';
    forma[1][0] = ' ';
    forma[1][1] = ' ';
}

void next_polyomino(char  forma[2][2])
{
    srand((unsigned)time(NULL));
    int r = rand() % 7;
    
    if (r == 0)
    {
        forma[0][0] = 'O';
        forma[0][1] = ' ';
        forma[1][0] = 'O';
        forma[1][1] = ' ';
    }
    else if (r == 1)
    {
        forma[0][0] = ' ';
        forma[0][1] = ' ';
        forma[1][0] = 'O';
        forma[1][1] = 'O';
    }
    else if (r == 2)
    {
        forma[0][0] = 'O';
        forma[0][1] = 'O';
        forma[1][0] = 'O';
        forma[1][1] = ' ';
    }
    else if (r == 3)
    {
        forma[0][0] = 'O';
        forma[0][1] = 'O';
        forma[1][0] = ' ';
        forma[1][1] = 'O';
    }
    else if (r == 4)
    {
        forma[0][0] = 'O';
        forma[0][1] = ' ';
        forma[1][0] = 'O';
        forma[1][1] = 'O';
    }
    else if (r == 5)
    {
        forma[0][0] = ' ';
        forma[0][1] = 'O';
        forma[1][0] = 'O';
        forma[1][1] = 'O';
    }
    else if (r == 6)
    {
        forma[0][0] = 'O';
        forma[0][1] = 'O';
        forma[1][0] = 'O';
        forma[1][1] = 'O';
    }

    hanyadik = r;
}

void print_game(char jatekter[y][x], char  forma[2][2])
{
    printf("Next polyomino:\n");
    printf("%c",forma[0][0]);
    printf("%c\n",forma[0][1]);
    printf("%c",forma[1][0]);
    printf("%c\n",forma[1][1]);
    printf("\n");
    printf("   #12345678#\n");
    for (int i = 0; i < 10; ++i)
    {
        printf("%d: #",i);
        for (int j = 0; j < 8; ++j)
        {
            printf("%c",jatekter[i][j]);
        }
        printf("#\n");
    }
    printf("   ##########\n");
}

int update_game(char jatekter[y][x], char  forma[2][2], int oszlop)
{
    if (oszlop == 8 && hanyadik != 0)
    {
        return (-1);
    }
    else
    {
        //alak
    int el = 0;
    int ml = 0;
    int ef = 0;
    int mf = 0; 

    //alaklent
    if (forma[0][0] == 'O')
    {
        el = 1;
    }
    if (forma[1][0] == 'O')
    {
        el = 2;
    }
    if (forma[0][1] == 'O')
    {
        ml = 1;
    }
    if (forma[1][1] == 'O')
    {
        ml = 2;
    }

    //alakfent
    if (forma[1][0] == 'O')
    {
        ef = 1;
    }
    if (forma[0][0] == 'O')
    {
        ef = 2;
    }
    if (forma[1][1] == 'O')
    {
        mf = 1;
    }
    if (forma[0][1] == 'O')
    {
        mf = 2;
    }

    //check lent
    int q = 0;
    int e = 0;

    while(jatekter[q][oszlop-1] == ' ')
    {
        ++q;
    }
    while (jatekter[e][oszlop] == ' ')
    {
        ++e;
    }
    
    //check fent
    //(9-q+ef) > 9 || (9-e+mf) > 9
    if (((9-q+ef) > 9 && q<e && el==2) || ((9-e+mf) > 9 && q>e && ml==2) || ((9-q+ef-1) > 9 && q<e && el==1) || ((9-e+mf-1) > 9 && q>e && ml==1) || ((9-q+ef) > 9 && ml == 0) || ((9-q+ef) > 9 && q==e) || ((9-e+mf) > 9 && q==e))
    {
        return (-2);
    }
    else
    {
        if (ml == 0)
        {
            jatekter[q-1][oszlop-1] = 'O';
            jatekter[q-2][oszlop-1] = 'O';
        }
        else if (q > e)
        {
            if (ml == 2)
            {
                jatekter[e-1][oszlop] = forma[1][1];
                jatekter[e-2][oszlop] = forma[0][1];
                jatekter[e-1][oszlop-1] = forma[1][0];
                jatekter[e-2][oszlop-1] = forma[0][0];
            }
            else
            {
                jatekter[e][oszlop-1] = forma[1][0];
                jatekter[e-1][oszlop-1] = forma[0][0];
                jatekter[e-1][oszlop] = forma[0][1];
            }
        }
        else if (e > q)
        {
            if (el == 2)
            {
                jatekter[q-1][oszlop] = forma[1][1];
                jatekter[q-2][oszlop] = forma[0][1];
                jatekter[q-1][oszlop-1] = forma[1][0];
                jatekter[q-2][oszlop-1] = forma[0][0];
            }
            else
            {
                jatekter[q-1][oszlop-1] = forma[0][0];
                jatekter[q][oszlop] = forma[1][1];
                jatekter[q-1][oszlop] = forma[0][1];
            }
        }
        else
        {
            jatekter[q-1][oszlop] = forma[1][1];
            jatekter[q-2][oszlop] = forma[0][1];
            jatekter[q-1][oszlop-1] = forma[1][0];
            jatekter[q-2][oszlop-1] = forma[0][0];
        }

        return 0;
    }
    }
}