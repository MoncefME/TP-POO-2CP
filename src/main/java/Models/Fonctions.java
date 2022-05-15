package Models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public final class Fonctions implements Serializable {

    /*****************************************/
    /************Classe Utilitaire************/
    /*****************************************/

    //generate random Integer between boundes
    public static int randomInt(int min , int max){
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }
    public static boolean existInArr(int x , ArrayList<Integer> arr){
        boolean answer = false;
        for(int i = 0 ; i <arr.size() ; i++ ){
            if(x == arr.get(i)){
                answer = true;

            }
        }
        return answer;
    }
    public static int randomIntUnique(int min , int max , ArrayList<Integer> arr){
        boolean stop = false;
        int random_int = randomInt(min,max);
        while (!stop){
            random_int = randomInt(min,max);
            if(existInArr(random_int,arr)){
               stop = false;
            }else {
                stop = true;
            }
        }
        return random_int;
    }

    //generate random int array with no rep
    public static int[] randomIntArray(){
        int[] arr = new int[100];
        Arrays.fill(arr, 0);
        arr[0]=1;
        arr[99]=9;
        for(int i = 2 ; i<=6 ; i++){
            int t=5;
            if(i==2){
                while(t>0){
                    int x = Fonctions.randomInt(1,96);
                    if(arr[x]==0 && arr[x+2]!=3){
                        arr[x]=i;
                        t--;
                    }
                }
            } else if (i == 3) {
                while(t>0){
                    int x = Fonctions.randomInt(2,97);
                    if(arr[x]==0 && arr[x-2]!=2 ){
                        arr[x]=i;
                        t--;
                    }
                }
            }
            else if (i==5) {
                while(t>0){
                    int x = Fonctions.randomInt(2,96);
                    if(arr[x]==0 && arr[x+2]!=3 ){
                        arr[x]=i;
                        t--;
                    }
                }
            }else if (i==6){
                while(t>0){
                    int x = Fonctions.randomInt(2,94);
                    if(arr[x]==0 && arr[x-2]!=2 ){
                        arr[x]=i;
                        t--;
                    }
                }
            }
            else  {
                while(t>0){
                    int x = Fonctions.randomInt(2,97);
                    if(arr[x]==0 && arr[x-2]!=2){
                        arr[x]=i;
                        t--;
                    }
                }
            }
        }
        return arr;
    }

    public static int[][] spiralPattern = {
            {0  , 1  , 2  , 3  , 4  , 5  , 6  , 7  , 8  , 9 },
            {35 , 36 , 37 , 38 , 39 , 40 , 41 , 42 , 43 , 10},
            {34 , 63 , 64 , 65 , 66 , 67 , 68 , 69 , 44 , 11},
            {33 , 62 , 83 , 84 , 85 , 86 , 87 , 70 , 45 , 12},
            {32 , 61 , 82 , 95 , 96 , 97 , 88 , 71 , 46 , 13},
            {31 , 60 , 81 , 94 , 99 , 98 , 89 , 72 , 47 , 14},
            {30 , 59 , 80 , 93 , 92 , 91 , 90 , 73 , 48 , 15},
            {29 , 58 , 79 , 78 , 77 , 76 , 75 , 74 , 49 , 16},
            {28 , 57 , 56 , 55 , 54 , 53 , 52 , 51 , 50 , 17},
            {27 , 26 , 25 , 24 , 23 , 22 , 21 , 20 , 19 , 18},
    };

    public static int[][] spiralBorderPattern = {{4 , 4 , 4 , 4 , 4 , 4 , 4 , 4 , 4 , 0},
            {1 , 4 , 4 , 4 , 4 , 4 , 4 , 4 , 0 , 5},
            {7 , 1 , 4 , 4 , 4 , 4 , 4 , 0 , 5 , 5},
            {7 , 7 , 1 , 4 , 4 , 4 , 0 , 5 , 5 , 5},
            {7 , 7 , 7 , 1 , 4 , 0 , 5 , 5 , 5 , 5},
            {7 , 7 , 7 , 7 , 8 , 2 , 5 , 5 , 5 , 5},
            {7 , 7 , 7 , 3 , 6 , 6 , 2 , 5 , 5 , 5},
            {7 , 7 , 3 , 6 , 6 , 6 , 6 , 2 , 5 , 5},
            {7 , 3 , 6 , 6 , 6 , 6 , 6 , 6 , 2 , 5},
            {3 , 6 , 6 , 6 , 6 , 6 , 6 , 6 , 6 , 2},};

    public static String[] caseBorderStyle = {"-fx-border-width: 5 5 0 0 ;",
            "-fx-border-width: 5 0 0 5 ;",//Top Left Corner  1
            "-fx-border-width: 0 5 5 0 ;",//Buttom Right Corner 2
            "-fx-border-width: 0 0 5 5 ;",//Buttom Left Corner 3
            "-fx-border-width: 5 0 0 0 ;",//UpHorizontal 4
            "-fx-border-width: 0 5 0 0 ;",//RightVertical 5
            "-fx-border-width: 0 0 5 0 ;",//DownHorizontal 6
            "-fx-border-width: 0 0 0 5 ;",//LeftVertical 7
            "-fx-border-width: 5 0 5 5 ;"};

    public static String[] wordList = {"year#a period of 365 days",
            "week#a period of seven days",
            "today#the present day",
            "tomorrow#the day after today",
            "yesterday#the day before today",
            "calendar#a chart that shows the days, weeks, and months of a year",
            "hour#a period of time, equal to sixty minutes",
            "minute#a period of 60 seconds",
            "clock#a device used for telling the time",
            "can#to be able to do something",
            "reception#a social gathering often for the purpose of extending a formal welcome",
            "model#use something as an example to follow or imitate",
            "rung#horizontal support on a ladder for a person's foot",
            "dent#It is used to join two ideas that are opposites.",
            "use#to deploy something for the purpose it was made",
            "house#a building for human habitation, home",
            "learn#find out information",
            "small#of a amount that is less than normal",
            "care#maintenance, nursing",
            "final#last, ultimate , not to be changed",
            "garden#a piece of ground for growing flowers etc…",
            "hospital#an institution for providing medical treatment",
            "although# It is used to show two opposite statements.",
            "student# a person that is studying at a school or college.",
            "soul#the spiritual part of a human being or animal",
            "family#parents and children living together in a household",
            "arbitrary#seemingly chosen or designated without reason or purpose",
            "banal#. boring, cliché",
            "capacity#the total amount a container can hold",
            "facilitate#to make something easier or simpler",
            "oscillate#to swing back and forth between two points, poles, or positions",
            "quotidian#relating to the everyday or mundane",
            "subjective#open to personal interpretation; not based in objective fact",
            "sufficient#enough for a given purpose",
            "accept#Recieve, agree to , approve ",
            "pillow#A rectangular cloth bag filled with soft material, such as feathers or artificial materials, used for resting your head on in bed ",
            "game#A form of competitive activity or sport played according to rules. ",};
}
