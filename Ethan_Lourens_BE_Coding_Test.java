/*Ethan Lourens*/

import java.util.*;
import java.util.Scanner;

public class Ethan_Lourens_BE_Coding_Test {
    public static HashMap<String, Integer> teamValues = new HashMap<String, Integer>();
    public static TreeMap<String, Integer> sortHashMap = new TreeMap<>(Collections.reverseOrder());
    public static Integer win = 3;
    public static Integer tie = 1;
    public static Integer loss = 0;
    public static Integer count = 0;
    public static String inputLine;

    public static String removeAllDigit(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                result = result + str.charAt(i);
            }
        }

        // Return result
        return result;
    }

    public static Integer getVal(String str) {
        Integer num = Integer.parseInt(str.replaceAll("[\\D]", ""));
        return num;
    }

    public static void hashMapScan(String team, Integer teamPoint) {
        team = removeAllDigit(team);
        if (teamValues.containsKey(team)) {
            teamValues.replace(team, (teamValues.get(team) + teamPoint));
        } else {
            addToHashMap(team, teamPoint);
        }
    }

    public static void addToHashMap(String team, Integer teamPoint) {
        String teamName = team;
        Integer teamPoints = teamPoint;
        teamValues.put(teamName, teamPoints);
    }

    public static void calculateWin(String teamA, String teamB) {
        Integer teamAScore = getVal(teamA);
        Integer teamBScore = getVal(teamB);

        if (teamAScore > teamBScore) {
            teamAScore = win;
            teamBScore = loss;
        } else if (teamAScore < teamBScore) {
            teamAScore = loss;
            teamBScore = win;
        } else if (teamAScore == teamBScore) {
            teamAScore = tie;
            teamBScore = tie;
        }

        hashMapScan(teamA, teamAScore);
        hashMapScan(teamB, teamBScore);
    }

    public static void rearangeHashmap() {
        sortHashMap.putAll(teamValues);
    }

    public static void printOutput() {
        Integer prevVal = -1;
        for (Map.Entry mapElement : sortHashMap.entrySet()) {
            count++;
            String key = (String) mapElement.getKey();
            Integer value = (Integer) mapElement.getValue();
            if (prevVal == value) {
                System.out.println((count - 1) + ". " + key.trim() + ", " + value + " pts");
                prevVal = value;
            } else {
                System.out.println(count + ". " + key.trim() + ", " + value + " pts");
                prevVal = value;
            }
        }
    }

    public void run() {
        Scanner myObj = new Scanner(System.in); // Create a Scanner object
        System.out.println("Please enter Games, enter 'stop' when done");
        while (!(inputLine = myObj.nextLine()).equals("stop")) {
            String[] splitTeam = inputLine.split(",");
            String teamA = splitTeam[0];
            String teamB = splitTeam[1];
            calculateWin(teamA, teamB);
        }
        rearangeHashmap();
        printOutput();
        myObj.close();
    }

    public static void main(String[] args) {
        Ethan_Lourens_BE_Coding_Test obj = new Ethan_Lourens_BE_Coding_Test();
        obj.run();
    }
}