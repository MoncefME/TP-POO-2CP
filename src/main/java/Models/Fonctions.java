package Models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Fonctions implements Serializable {

    //generate random Integer between boundes
    public static int randomInt(int min , int max){
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }

    public static HashMap<String,String> def_word=new HashMap<String,String>(){{
        put("def1","def111111111111111111111111111111111111111111111");
        put("def2","def222222222222222222222222222222222222222222222");
        put("def3","def333333333333333333333333333333333333333333333");
        put("def4","def444444444444444444444444444444444444444444444");
        put("def5","def555555555555555555555555555555555555555555555");
        put("def6","def666666666666666666666666666666666666666666666");
        put("def7","def777777777777777777777777777777777777777777777");
        put("def8","def888888888888888888888888888888888888888888888");
        put("def9","def999999999999999999999999999999999999999999999");
    }};

    //generate random int array with no rep
    public static int[] randomIntArray(){
        int[] arr = new int[100];
        Arrays.fill(arr, 0);
        arr[0]=1;
        arr[99]=9;
        for(int i = 2 ; i<=6 ; i++){
            int t=10;
            if(i==2){
                while(t>0){
                    int x = Fonctions.randomInt(1,97);
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
                    int x = Fonctions.randomInt(2,97);
                    if(arr[x]==0 && arr[x+2]!=3 ){
                        arr[x]=i;
                        t--;
                    }
                }
            }
            else {
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

    public static String[] caseBorderStyle = {
            "-fx-border-width: 5 5 0 0 ;",//Top Right Corner 0
            "-fx-border-width: 5 0 0 5 ;",//Top Left Corner  1
            "-fx-border-width: 0 5 5 0 ;",//Buttom Right Corner 2
            "-fx-border-width: 0 0 5 5 ;",//Buttom Left Corner 3
            "-fx-border-width: 5 0 0 0 ;",//UpHorizontal 4
            "-fx-border-width: 0 5 0 0 ;",//RightVertical 5
            "-fx-border-width: 0 0 5 0 ;",//DownHorizontal 6
            "-fx-border-width: 0 0 0 5 ;",//LeftVertical 7
            "-fx-border-width: 5 0 5 5 ;"//the last one 8
    };


}
