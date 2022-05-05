package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    static Table list = new Table();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void firstLogIn() throws IOException {

        System.out.println("");
        System.out.println("Enter the amount of columns:: ");
        int columns = Integer.parseInt(br.readLine());

        System.out.println("Enter the amount of rows in the table:: ");
        int rows = Integer.parseInt(br.readLine());

        System.out.println("Enter the amount of seeds in the table:: ");
        int seeds = Integer.parseInt(br.readLine());
        if(seeds <= columns * rows - 2)

        System.out.println("Enter the amount of links:: ");
        int links = Integer.parseInt(br.readLine());
        if((columns * rows) >= 3){
            double max = 0.5*(columns * rows);
            if(max < links){
                System.out.println("It's not a valid number, try again:: ");
                links = Integer.parseInt(br.readLine());
            }
            System.out.println("The table has been created...\n");
            System.out.println("The user who's playing as Rick is called:: " );
            String user1 = br.readLine();
            System.out.println("The user who's playing as Morty is called:: " );
            String user2 = br.readLine();

            int cont = columns * rows;
            cont = -cont;
            createTable(columns, rows, seeds, links, cont, 0);
            br.close();
        }else{
            System.out.println("The amount of boxes is invalid, try again");
            firstLogIn();
        }
    }

    public static void createTable(int colm, int row, int seeds, int links, int cont, int tmp) throws IOException {
        int total = colm * row;
        if(cont != 0){
            list.addNode(new Node(tmp));
            cont++;
            tmp++;
            createTable(colm, row, seeds, links, cont, tmp);
        }
        else{
            list.generatePositions();
            list.generateSeed(seeds);
            System.out.println("\nThe game has started!");
            menu(total, colm, 0);
        }
    }

    public static void menu(int total, int colm, int turn) throws IOException {
        int ans = 0;
            if(turn%2 == 0)
                System.out.println("\nIt's Rick's turn");
            else
                System.out.println("\nIt's Morty's turn");
            System.out.println(
                    "What do you want to do?::\n" +
                            "1. Throw dice\n" +
                            "2. See board\n" +
                            "3. See links\n" +
                            "4. See scores\n" +
                            "5. Sign out\n");
            ans = Integer.parseInt(br.readLine());

            switch (ans) {
                case 1:
                    int diceValue = list.throwDice();
                    System.out.println("The value is:: " + diceValue);
                    System.out.println("You want to go::\n" +
                            "(1)Forward.\n" +
                            "(2)Backward.\n");
                    int rps = Integer.parseInt(br.readLine());
                    if (rps == 1) {
                         list.goForward(diceValue, turn);
                    }else if(rps == 2){
                        diceValue = -diceValue;
                        list.goBackward(diceValue, turn);
                    }
                    turn+=1;
                    break;
                case 2:
                    list.print(total, colm);
                    break;
                case 3:
                    break;
                case 4:
                    list.printScores();
                    break;
                case 5:
                    System.out.println("Closing app :)");
                    break;
            }
            if(ans == 5)
                return;
            else
                menu(total, colm, turn);
    }
}
