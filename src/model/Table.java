package model;

import java.util.Random;

public class Table {

    private Node first;
    private Node last;
    private Node r;
    private Node m;
    private int rickSeeds = 0;
    private int mortySeeds = 0;

    public void addNode(Node node){
        if(first == null){
            first = node;
            last = node;
            first.setNext(first);
            last.setPrev(last);
            last = node;
        }else{
            last.setNext(node);
            last.setPrev(last);
            node.setNext(first);
            last = node;
            first.setPrev(node);
        }
    }

    public Node search(int goal){
        return search(goal, first);
    }

    private Node search(int goal, Node actual){
        if(actual == null){
            return null;
        }else if(actual.getValue() == goal){
            return actual;
        }else{
             return search(goal, actual.getNext());
        }
    }

    public void print(int t, int colm){
        print(first, t, colm, 0);
    }

    private void print(Node actual, int t, int colm,int num){
        if(actual == null) {
            return;
        }else if(num == t){
            return;
        }
        if(num%colm == 0)
            System.out.print("\n");
        if(actual.isRick() && actual.isMorty())
            System.out.print(" [  B  ] "); //Both
        else if(actual.isRick())
            System.out.print(" [  R  ] ");//Only Morty
        else if(actual.isMorty())
            System.out.print(" [  M  ] "); //Only Rick
        else if(actual.isSeed())
                System.out.print(" [  *  ] "); //Seeds
        else{
            if(actual.getValue() < 10)
                System.out.print(" [  0" + actual.getValue() + " ] ");
            else
                System.out.print(" [  " + actual.getValue() + "  ] ");
            }
        num++;
        print(actual.getNext(), t, colm, num);
    }

    public void generateSeed(int quantity){
        int num = 0;
        int pos = getRandom(0, last.getValue());
        while(quantity!= num){
            Node s = search(pos);
            if(!s.isSeed() && !s.isMorty() && !s.isRick()){
                s.setSeed(true);
                num++;
            }
             pos = getRandom(1, last.getValue());
        }
    }

    public void generatePositions(){
        int morty = getRandom(0, last.getValue());
        int rick = getRandom(0, last.getValue());

        if(rick == morty){
            generatePositions();
        }else{
            r = search(rick);
            r.setRick(true);
            m = search(morty);
            m.setMorty(true);
        }
    }

    public int getRandom(int min, int max){
        min = 0;
        max = last.getValue();
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public int throwDice(){
        int min = 1;
        int max = 6;

        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void goForward(int dice, int turn){
        Node tmp = goForwardInt(dice, turn);
        if(tmp.isRick() && tmp.isSeed()){
            tmp.setSeed(false);
            rickSeeds += 1;
        }else if(tmp.isMorty() && tmp.isSeed()){
            tmp.setSeed(false);
            mortySeeds += 1;
        }
        if(tmp.getPortal()!=null){
            tmp = tmp.getPortal();
            if(tmp.isSeed()){
                tmp.setSeed(false);
                if(turn%2 == 0) {
                    rickSeeds+=1;
                    r = tmp;
                } else{
                    mortySeeds+=1;
                    m = tmp;
                }
            }
        }
    }

    private Node goForwardInt(int dice, int turn){ //Intern
        if(turn%2 == 0){
            r.setRick(false);
            r = r.getNext();
            if(dice==1)
                r.setRick(true);
            dice--;
        }else{
            m.setMorty(false);
            m = m.getNext();
            if(dice==1)
                m.setMorty(true);
            dice--;
        }
        if(dice>0){
            return goForwardInt(dice, turn);
        } else{
            if(turn%2 == 0)
                return r;
            else
               return m;
        }
    }

    public void goBackward(int dice, int turn){
        Node tmp = goBackwardInt(dice, turn);
        if(tmp.isRick() && tmp.isSeed()){
            tmp.setSeed(false);
            rickSeeds += 1;
        }else if(tmp.isMorty() && tmp.isSeed()){
            tmp.setSeed(false);
            mortySeeds += 1;
        }
        if(tmp.getPortal()!=null) {
            tmp = tmp.getPortal();
            if (tmp.isSeed()) {
                tmp.setSeed(false);
                if (turn % 2 == 0) {
                    rickSeeds+=1;
                    r = tmp;
                } else {
                    mortySeeds+=1;
                    m = tmp;
                }
            }
        }
    }

    public Node goBackwardInt(int dice, int turn){
        if(turn%2 == 0){
            r.setRick(false);
            r = r.getNext();
            if(dice==1)
                r.setRick(true);
            dice--;
        }else{
            m.setMorty(false);
            m = m.getNext();
            if(dice==1)
                m.setMorty(true);
            dice--;
        }
        if(dice>0){
            return goBackwardInt(dice, turn);
        } else{
            if(turn%2 == 0)
                return r;
            else
                return m;
        }
    }

    /*
    public void generatePortals(int nPortals, int colm, int rows){
        Random genRandom=new Random((((colm*rows)-1)+1)+1);
        for(int i=0;i<n*2;i++) {
            int m=0;
            do {
            }while(prtls.contains(m));
            prtls.add(m);
        }
        int j=0;
        for(int i=0;i<n*2;i+=2) {
            char ltr=(char) (65+j);
            Node a=search(prtls.get(i));
            Node b=search(prtls.get(i+1));
            a.setPrtlLtr(ltr);
            b.setPrtlLtr(ltr);
            a.setPortal(b);
            b.setPortal(a);
            j++;
        }
    }
     */
    public void printScores(){
            System.out.println("Rick's score:: " + rickSeeds);
            System.out.println("Morty's score:: " + mortySeeds);
    }
}
