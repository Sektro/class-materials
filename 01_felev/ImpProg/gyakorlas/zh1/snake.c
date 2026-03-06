#include <stdio.h>
#include <stdbool.h>
#define c 10
#define z 20

int update_snake(char asdf[c][z], int kigyoup[9][2], char mozdulat);
void init_field(char t[c][z],int malma);
void init_snake(int kigyoin[9][2]);
void print_game(char asdf[c][z], int kigyoin[9][2]);

int main()
{
    char palya[c][z];
    int alma = 10;
    int kigyo[9][2];
    char move[100];
    _Bool eog = false;

    printf("\n");
    init_field(palya,alma);
    init_snake(kigyo);
    print_game(palya,kigyo);


    while (!eog)
    {
        int q = 0;
        int watch = 0;
        int begyujtve = 0;

        for (int i = 0; i < 100; ++i) move[i] = ' ';
        printf("\n");

        scanf("%s", move);
        while (q < 1 || !eog)
        {
            watch = update_snake(palya,kigyo,move[q]);
            if (watch == (-1) || watch == (-2)) eog = true;
            if (watch == 1)
            {
                ++begyujtve;
                --alma;
            }
            ++q;
        }

        print_game(palya,kigyo);
        printf("Begyujtott almak szama: %d\n", begyujtve);
        if (watch == (-1)) printf("Jatek vege! Beleutkoztel a falba!");
        else if (watch == (-2)) printf("Jatek vege! Beleutkoztel magadba!");
        else if (alma == 0)
        {
            printf("Jatek vege! Megszerezted az osszes almat!");
            eog = true;
        }


    }
    

    return 0;
}


int update_snake(char asdf[c][z], int kigyoup[9][2], char mozdulat)
{
    int y = kigyoup[0][0];
    int x = kigyoup[0][1];
    _Bool mozog = false;
    _Bool magaba = false;

    if (mozdulat == 'w')
    {
        mozog = true;
        --y;
    }
    else if (mozdulat == 's')
    {
        mozog = true;
        ++y;
    }
    else if (mozdulat == 'a')
    {
        mozog = true;
        --x;
    }
    else if (mozdulat == 'd')
    {
        mozog = true;
        ++x;
    }
    else return 0;

    if (mozog)
    {
        for (int i = 1; i < 9; ++i)
        {
            if (y == kigyoup[i][0] && x ==  kigyoup[i][1])
            {
                magaba = true;
            }
        }
        if (x > 19 || x < 0 || y > 9 || y < 0) return (-1);
        else if (magaba) return (-2);
        else if (asdf[y][x] == 'a') 
        {
            asdf[kigyoup[8][0]][kigyoup[8][1]] = ' ';
            asdf[y][x] = ' ';
            for (int i = 8; i > 0; --i)
            {
                kigyoup[i][0] = kigyoup[i-1][0];
                kigyoup[i][1] = kigyoup[i-1][1];
            }
            kigyoup[0][0] = y;
            kigyoup[0][1] = x;
            return (1);
        }
        else 
        {
            asdf[kigyoup[8][0]][kigyoup[8][1]] = ' ';
            for (int i = 8; i > 0; --i)
            {
                kigyoup[i][0] = kigyoup[i-1][0];
                kigyoup[i][1] = kigyoup[i-1][1];
            }
            kigyoup[0][0] = y;
            kigyoup[0][1] = x;
            
            return 0;
        }
    }
}

/*
void init_field(char asdf[c][z], int malma)
{
    for (int i = 0; i < c; ++i)
    {
        for (int j = 0; j < z; ++j)
        {
            asdf[i][j] = ' ';
        }
    }
    _Bool van;
    int rx;
    int ry;
    for (int i = 0; i < malma; ++i)
    {
        van = false;
        do
        {
            srand((unsigned)time(NULL));
            rx = rand() % c;
            ry = rand() % z;
            if (asdf[rx][ry] == 'a')
            {
                van = true;
            }
            else
            {
                van = false;
            }
        }while (van);
        asdf[rx][ry] = 'a';
    }
}
*/

void init_field(char t[c][z],int malma){
    srand(time(NULL));
    for(int i =0; i<c;i++)
    {
        for(int j =0; j<z;j++)
        {
            t[i][j]=' ';
        }
    }
    while(0<malma)
    {
        int i,j;
        i=(rand()%((c-1)-0+1)+0);
        j=(rand()%((z-1)-0+1)+0);
        if(t[i][j]!='a'){
            t[i][j]='a';
            malma--;
        }
    }



}

void init_snake(int kigyoin[9][2])
{
    for (int i = 0; i < 9; ++i)
    {
        kigyoin[i][0] = 5;
        kigyoin[i][1] = 15 - i;
    }
}

void print_game(char asdf[c][z], int kigyoin[9][2])
{
    asdf[kigyoin[0][0]][kigyoin[0][1]] = '8';
    for (int i = 1; i < 9; ++i)
    {
        asdf[kigyoin[i][0]][kigyoin[i][1]] = '0';
    }

    printf("######################\n");
    for (int i = 0; i < c; ++i)
    {
        printf("#");
        for (int j = 0; j < z; ++j)
        {
            printf("%c",asdf[i][j]);
        }
        printf("#");
        printf("\n");
    }
    printf("######################\n");
}