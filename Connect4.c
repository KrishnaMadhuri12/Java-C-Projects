/* Include libraries*/
#include<time.h>
#include<stdlib.h>
#include<stdio.h>
#include<pthread.h>

/*Marco definitions*/
#define NROWS 6
#define NCOLS 7

/* Variables*/
int a[6][7];
int pos_x=0, pos_y=0, i, j;
int flag=0;

/*Mutex objects*/

pthread_mutex_t one_mutex=PTHREAD_MUTEX_INITIALIZER;

pthread_cond_t cond_player=PTHREAD_COND_INITIALIZER;

pthread_cond_t cond_ref=PTHREAD_COND_INITIALIZER;

/*Methods*/

void *player_function();

void *referee_function();

void drop(int k);

int check(); 

void display();



//Player function: Each player waits for signal from referee and drops the ball accordingly

void *player_function()

{
int comp;
for(comp=1;comp<=42;comp++)
	{
	pthread_mutex_lock(&one_mutex); //lock thread
 	 if(flag==0)
		{	 
	 pthread_cond_wait(&cond_ref,&one_mutex); //conditional wait on the player thread

	 	} // making the player wait until it receives a play signal from the referee
	
	 drop(rand()%7); //using rand function to generate a random value to drop the ball
	 display(); //display the board after every drop to the user in console
     flag=0; //release the flag 
    
     pthread_cond_signal(&cond_ref); 
     pthread_mutex_unlock(&one_mutex); //release thread
     usleep(1000000 + rand()%2000000);
	}

}
//referee function: checks for winner. if winner is found then calls to end game else signals players to continue playing

void *referee_function()
{
  int x,comp; //local variables

  printf("\n in the referee function");

  for(comp=0;comp<=42;comp++)
    {
     pthread_mutex_lock(&one_mutex);
      if(flag!=0)
      {
		pthread_cond_wait(&cond_ref,&one_mutex);
	   }
	   
      printf("\nGot the signal from player to check");

      x=check(); //call function "check" to find game winner status. 
      if(x==1)
		{
		  printf("\nGame Ends");
		  exit(0);
		}
	 flag=1;
      pthread_cond_signal(&cond_ref);

      pthread_mutex_unlock(&one_mutex);

      usleep(1000000 + rand()%2000000);

	}

}

//display board
void display()
{

  for(i=0;i<NROWS;i++)
    {
      for(j=0;j<NCOLS;j++)
		{
		 printf("!%d",a[i][j]);
		 }
	  printf("\n");
    }

}


/*Main method*/

int  main()
{
  printf("Setting board to 0's\n");
  printf("\n");

  for(i=0;i<NROWS;i++)

    {

      for(j=0;j<NCOLS;j++)

	{a[i][j]=0;

	}

    }

  printf("\n");

  display();

 

  time_t t;

  srand(time(&t));

  pthread_t referee, player1, player2;
  pthread_create(&player1,NULL,player_function,NULL);

  pthread_create(&player2, NULL,player_function,NULL);

  pthread_create(&referee,NULL,referee_function,NULL);



  //joining the threads

  pthread_join(player1, NULL);

  pthread_join(player2,NULL);

  pthread_join(referee,NULL);



  exit(0);

}
/*Drop function: accepts int parameter */

void  drop(int k)
{
  printf("\nDroping the ball  in the col %d", k);
  j=k;

  int val=pthread_self(); //thread id

  printf("\n %d thread id\n", val);

  if(a[0][j]==0)
  {

    printf("Column is not filled\n");  

	for(i=5;i>=0&&a[0][j]==0;i--)
    {
      if(a[i][j]==0)
		{               
	 	 a[i][j]=val;
	  	 pos_x=i;
	  	 pos_y=j;
	  	 break;
		}
    }

  }

  else
  {
  printf("\ncolumn is filled");

  printf("\n getting the new column number by calling the drop function again");

  drop(rand()%7);

  }

}
/*Check function: returns 1 when winner is found, 0 to continue playing*/
int  check()

{
  int count_vp=0, count_dif=0,count_dib=0;

  //checking for a[pos_x][pos_y]!=0 before checking for the winner
  if(a[pos_x][pos_y]!=0)
  {
  for(i=pos_x;i<NROWS&&count_vp!=4;i++)
    {
      if(a[i][pos_y]==a[pos_x][pos_y])
		{
			count_vp=count_vp+1;
		}
	  else
		{
     	  break;
		}
     }
 if(count_vp==4)
   {
     printf("\n vertically matched winner is %d found at %d %d",a[pos_x][pos_y], pos_x, pos_y);
     return 1;
   }
printf("\nVertically didnt match");

  // forward diagonal check up and down

  for(i=pos_x,j=pos_y; i>=0 &&j>=0&&count_dif!=4;i--,j--)
    {
	  if(a[i][j]==a[pos_x][pos_y])
		{
		count_dif=count_dif+1;
		}
      else
		break;
    }

  for(i=pos_x+1,j=pos_y+1; i< NROWS&& j< NCOLS&&count_dif!=4; i++,j++)
    {
      if(a[i][j]==a[pos_x][pos_y])
        {
        count_dif=count_dif+1;
        }
      else
		break;
    }
  if(count_dif==4)
    {
	  printf("\nWinner is %d forward diagonal check", a[pos_x][pos_y]);
      return 1;
    }

  //backward diagonal check up and down

  for(i=pos_x,j=pos_y; i< NROWS && j>=0&&count_dib!=4; i++,j--)
    {
		if(a[i][j]==a[pos_x][pos_y])
		{ 
		count_dib=count_dib+1;
		}
       else
		{
		break;
		}
    }

  for(i=pos_x-1,j<pos_y+1; i<=0&&j< NCOLS&&count_dib!=4; i--,j++)

    {
      if(a[i][j]==a[pos_x][pos_y])
      {
      	count_dib=count_dib+1;
      }
      else
      {
		break;
	  }
    }

  if(count_dib==4)
    {
      printf("\nWinner is %d  back ward Diagonal Match at a[%d][%d]", a[pos_x][pos_y],pos_x,pos_y);
      return 1;
    }

  printf("\checking is done and could not find the winner");

  return 0;
}

  else
  { 
  	printf("\n Start the game"); 
    return 0;

  }

}

