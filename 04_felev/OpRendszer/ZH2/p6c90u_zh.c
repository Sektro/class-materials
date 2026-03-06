#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <signal.h>
#include <fcntl.h>
#include <time.h>
#include <mqueue.h>
#include <sys/msg.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/wait.h>
#define pt(fstr, ...) {printf("[%s] "fstr"\n", whoami ?: "N/A", ##__VA_ARGS__);}
char* whoami = 0;
void dummy_handler(int x){}
void do_not_kill_me(int signum){signal(signum, dummy_handler);}
void szemafor_muvelet(int semid, int sem_op){
 struct sembuf muvelet = {.sem_num = 0, .sem_op = sem_op, .sem_flg = 0};
 semop(semid, &muvelet, 1);
}



struct msg{
    long type;
    char buf[150];
};



int main(int argc, char* argv[])
{
    do_not_kill_me(SIGUSR1);

    int szolgalat_vevo[2];
    if (pipe(szolgalat_vevo)){
        printf("pipe fail\n");
        return;
    }

    int vevo_szolgalat[2];
    if (pipe(vevo_szolgalat)){
        printf("pipe fail\n");
        return;
    }

    key_t key = ftok(argv[0],37);
    if(key == -1){
        perror("ftok error: ");
        return 1;
    }

    int msgQ = msgget(key, 0600 | IPC_CREAT);

    int sem = semget(key, 1, S_IRUSR | S_IWUSR | IPC_CREAT);

    int shm = shmget(key, 100, S_IRUSR | S_IWUSR | IPC_CREAT);

    char* shared = shmat(shm,NULL,0);
    semctl(sem, 0, SETVAL, 0);


    

    pid_t gy1 = fork();
    if(gy1)
    {
        whoami = "Ügyfélszolgálat";
        close(vevo_szolgalat[1]);
        close(szolgalat_vevo[0]);

        char buf[100];
        read(vevo_szolgalat[0], buf, sizeof(buf));
        pt("Vevőnk: %s",buf);


        struct msg to_send = (struct msg) {.type = 5};
        sprintf(to_send.buf, argv[1]);
        msgsnd(msgQ, &to_send, sizeof(to_send) - sizeof(long), 0);

        
        char* shared = shmat(shm,NULL,0);
        szemafor_muvelet(sem, -1);
        pt("Vevőnk döntése : %s", shared);
        shmdt(shared);
        
        sleep(1);
        pt("kilepes");
    }else
    {
        whoami = "vevő";
        kill(getpid(), SIGUSR1);
        close(vevo_szolgalat[0]);
        close(szolgalat_vevo[1]);

        char *msg = "Melyik fűrész akciós?";
        write(vevo_szolgalat[1], msg, strlen(msg)+1);

        struct msg to_receive;
        msgrcv(msgQ, &to_receive, sizeof(to_receive) - sizeof(long), 5, 0);
        pt("Kaptam ajánlatot: %s", to_receive.buf);

        
        
        
        int tetszik =0;
        
        if((rand() % 2) == 0)
        {
            tetszik = 1;
        }
        else
        {
            tetszik = 0;
        }


        char* shared = shmat(shm,NULL,0);

        

        if(tetszik == 0)
        {
            sprintf(shared,"Sajnos most nem sikerült ennyit összelocsolni egy fűrészért."); 
        }
        else
        {
            char buff[100] = "Kérem a ";
            strcat(buff, to_receive.buf);
            sprintf(shared, buff);

        }
        szemafor_muvelet(sem, 1);

        shmdt(shared);
        
        
        pt("kilepes");
    }

 return 0;
}