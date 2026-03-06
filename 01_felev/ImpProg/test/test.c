
#include <stdio.h>
#include <math.h>

int main()
{
  int szam = 0;
  int dim = 0;
  int irany = 1;
  scanf("%d",&dim);
  int matrix[dim][dim];
  int start = (dim/2);

  //filler
  for (int i = 0; i < dim; ++i)
  {
    for (int j = 0; j < dim; ++j)
    {
      matrix[i][j] = 0;
    }
  }

  //bizonyito kiiras
  for (int i = 0; i < dim; ++i)
  {
    for (int j = 0; j < dim; ++j)
    {
      printf("%d ",matrix[i][j]);
    }
    printf("\n");
  }

  //generacio
  if (dim % 2 == 0)
  {
    for (int i = 0; i < ((dim * 2)-1); ++i)
    {
      if (irany == 1)
      {
       for (int j = start+(i/4); j > start-(i/4)-1; --j)
        {
          matrix[j][start-1-(i/4)] = szam;
          ++szam;
        }
        printf("%d",irany);
        ++irany;
      }
      else if (irany == 2)
      {
       for (int j = start-(i/4)-1; j < start+(i/4); ++j)
        {
          matrix[start-1-(i/4)][j] = szam;
          ++szam;
        }
        printf("%d",irany);
        ++irany;
      }
      else if (irany == 3)
      {
       for (int j = start-(i/4)-1; j < start+(i/4)+1; ++j)
        {
          matrix[j][start+(i/4)] = szam;
          ++szam;
        }
        printf("%d",irany);
        ++irany;
      }
      else if (irany == 4)
      {
       for (int j = start+(i/4); j > start-(i/4)-2; --j)
        {
          matrix[start+1+(i/4)][j] = szam;
          ++szam;
        }
        printf("%d",irany);
        irany = 1;
      }
    }
  }

  //masodik kiiras
  printf("\n");
  for (int i = 0; i < dim; ++i)
  {
    for (int j = 0; j < dim; ++j)
    {
      if (matrix[i][j]<10)
      {printf("%d  ",matrix[i][j]);}
      else 
      {printf("%d ",matrix[i][j]);}
    }
    printf("\n");
  }
  return 0;
}

/*
#include <stdio.h>

int main()
{
  int arr[2][2] = {{1,2},{3,4}};
  printf("%d",**arr);
  printf("%d",*(*(arr)+1));
  printf("%d",**(arr+1));
  printf("%d",*(*(arr+1)+1));

  int *p = arr;
  
  printf("%d",*p);
  printf("%d",*(p+1));
  printf("%d",*(p+2));
  printf("%d",*(p+3));
}
*/
/*
#include <stdio.h>
#include <stdlib.h>

int main()
{
  int *p;
  int d = 0;
  scanf("%d",&d);
  p = malloc(sizeof(int)*d);
  for (int i = 0; i < d; ++i)
  {
    *(p+i) = 1;
  }
  for (int i = 0; i < d; ++i)
  {
    printf("%d",*(p+i));
  }
  free(p);
}
*/


/*
#include <stdio.h>
#include <math.h>

int main()
{
  int t = 9;
  t = sqrt(t);
  printf("%d",t);
  return 0;
}
*/
/*
#include <stdio.h>

int main(int argc, char *argv[])
{
    char nev[20];
    scanf("%s",nev);
    FILE *fp;
    int osszeg = 0;
    char x[20];

    fp = fopen(nev, "r");
    //fp = fopen(argv[1], "r");
    while (fscanf(fp, "%s", &x) == 1)
    {
        printf("%s\n",x);
    }
    fclose(fp);

    printf("Osszeg: %d\n", osszeg);

    return 0;
}
*/
/*
#include <stdio.h>
#include <string.h>

int main()
{
  char sex[8];
  printf("Sex? ");
  scanf("%s", sex);
  if (strcmp(sex,"yes")==0) {printf("lessgoo\n");}
  if (strcmp(sex,"no")==0) {printf("NOOOOO\n");}
}
*/
/*
#include <stdio.h>
#include <string.h>

int main() {
    int testint = 12;
    char t1 = 'a';
    char t2[] = "lma";
    char testchar[50];
    sprintf(testchar, "%d.) Kedvenc gyumolcsom az %c%s!",testint, t1, t2);
    printf("%s\n",testchar);

    return 0;
}
*/