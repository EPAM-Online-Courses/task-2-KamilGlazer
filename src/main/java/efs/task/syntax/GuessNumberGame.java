package efs.task.syntax;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {

    private int M;
    private double L;
    private int number;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {

        try{
            this.M = Integer.parseInt(argument);
        }catch(NumberFormatException e){
            System.out.println("'"+argument+"'"+ " to "+ UsefulConstants.WRONG_ARGUMENT + " - to nie liczba");
            throw new IllegalArgumentException();
        }

        if(M < 1 || M > 400) {
            System.out.println(argument + " to " + UsefulConstants.WRONG_ARGUMENT + " - jest spoza zakresu <1,"+UsefulConstants.MAX_UPPER_BOUND+">");
            throw new IllegalArgumentException();
        }

        Random random = new Random();
        this.number = random.nextInt(this.M)+1;
        this.L = Math.floor(Math.log((double)this.M)/Math.log(2)) + 1;
    }

    public void play() {

        int trial = 0;
        String[] tab = new String[(int)this.L];
        int guess;

        Arrays.fill(tab,".");


        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1,"+ M +">");
        while (true){

            Scanner scanner = new Scanner(System.in);

            if(trial >= (int)this.L){
                System.out.println(UsefulConstants.UNFORTUNATELY+ ", wyczerpałeś limit prób ("+(int)this.L+") do odgadnięcia liczby "+this.number);
                break;
            }

            tab[trial] = "*";

            System.out.print("Twoje próby: [");
            for(int i=0;i< tab.length;i++){
                System.out.print(tab[i]);
            }
            System.out.println("]");

            System.out.println(UsefulConstants.GIVE_ME+" liczbę : ");

            try{
                guess = scanner.nextInt();
                if(guess > number){
                    System.out.println("To "+UsefulConstants.TO_MUCH);
                }else if(guess < number){
                    System.out.println("To "+UsefulConstants.TO_LESS);
                }else{
                    System.out.println("To "+UsefulConstants.YES);
                    System.out.println(UsefulConstants.CONGRATULATIONS+", "+(trial+1)+" - tyle prób zajeło Ci odgadnięcie liczby "+this.number);
                    break;
                }
            }catch (Exception e){
                System.out.println("Hmm, '"+scanner.next()+"' to "+UsefulConstants.NOT_A_NUMBER);
            }

            trial++;
        }

    }
}
