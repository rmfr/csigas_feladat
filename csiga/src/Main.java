import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import com.sun.org.apache.xpath.internal.functions.FuncTrue;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Snail {
    private String color;
    private int distance;

    public Snail(String color) {
        this.color = color;
        this.distance = 0;
    }

    public void move() {
        this.distance += new Random().nextInt(4);
    }

    public void accelerate() {
        this.distance *= 2;
    }

    public int getDistance() {
        return this.distance;
    }

    public String getColor() {
        return this.color;
    }
}

class Race {
    private ArrayList<Snail> snails;
    private String bet;

    public Race() {
        this.snails = new ArrayList<>();
        this.snails.add(new Snail("piros"));
        this.snails.add(new Snail("zöld"));
        this.snails.add(new Snail("kék"));
    }

    public String validBet(Scanner bIn) {
        boolean valid = false;
        // Snail toBet = snails.stream()
        //           .map(Snail::getColor)
        //         .map(Snail::getDistance);
        List<String> toBet = snails.stream()
                .map(Snail::getColor)
                .collect(Collectors.toList());
        boolean isValid = false;

        do {
            System.out.println("A versenyzők, akikre fogadhat: " + toBet);

            isValid = toBet.contains(bIn.nextLine());


        } while (!isValid);
        return bIn.nextLine();
    }

    public void run(int rounds) {
        Random random = new Random();
        for (int i = 0; i < rounds; i++) {
            for (Snail snail : this.snails) {
                snail.move();
                if (random.nextDouble() < 0.2) {
                    snail.accelerate();
                }
            }
        }
    }

    public String getWinner() {
        Snail winner = this.snails.stream()
                .max(Comparator.comparingInt(Snail::getDistance))
                .orElse(null);
        if (winner != null) return winner.getColor();
        return null;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner bIn = new Scanner(System.in);
        Race race = new Race();
        int rounds = 5;
        race.validBet(bIn);
        race.run(rounds);
        System.out.println("A verseny győztese: " + race.getWinner());
    }
}


