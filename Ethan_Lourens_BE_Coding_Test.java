/*Ethan Lourens
BE Coding Test*/

import java.util.*;
import java.util.Scanner;
import java.util.Map.*;
import java.util.stream.*;

public class Ethan_Lourens_BE_Coding_Test {
    public static HashMap<String, Integer> teamValues = new HashMap<String, Integer>();
    public static Map<String, Integer> sortHashMap;
    public static Integer win = 3;
    public static Integer tie = 1;
    public static Integer loss = 0;
    public static Integer count = 0;
    public static String inputLine;

    public static String getTeamName(String team) {
        String out = "";
        for (int i = 0; i < team.length(); i++) {
            if (!Character.isDigit(team.charAt(i))) {
                out = out + team.charAt(i);
            }
        }
        return out.trim();
    }

    public static Integer getVal(String team) {
        Integer num = Integer.parseInt(team.replaceAll("[\\D]", ""));
        return num;
    }

    public static void hashMapScan(String team, Integer teamPoint) {
        team = getTeamName(team);
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
        sortHashMap = teamValues.entrySet().stream()
                .sorted(Collections
                        .reverseOrder(
                                Map.Entry.<String, Integer>comparingByValue())
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static void printOutput() {
        Integer prevVal = -1;
        System.out.printf("\n%s\n", "Output:");
        for (Map.Entry mapVal : sortHashMap.entrySet()) {
            count++;
            String mapkey = (String) mapVal.getKey();
            Integer mapValue = (Integer) mapVal.getValue();
            if (prevVal == mapValue) {
                System.out.printf("%d. %s, %d pts\n", count - 1, mapkey.trim(), mapValue);
                prevVal = mapValue;
            } else {
                System.out.printf("%d. %s, %d pts\n", count, mapkey.trim(), mapValue);
                prevVal = mapValue;
            }
        }
    }

    public void run() {
        Scanner inputScan = new Scanner(System.in);
        System.out.printf("%s\n %s\n %s\n %s\n\n %s\nInput:\n", "Welcome to my Program",
                "Instructions: Please enter valid (formatted) Game Information",
                "Instructions: For example: Lions 3, Snakes 3", "Instructions: Press Enter to move to the next line",
                "Please enter Game Information or enter 'done' when completed");
        while (!(inputLine = inputScan.nextLine()).equals("done")) {
            String[] splitTeam = inputLine.split(",");
            String teamA = splitTeam[0];
            String teamB = splitTeam[1];
            calculateWin(teamA, teamB);
        }
        rearangeHashmap();
        printOutput();
        inputScan.close();
    }

    public static void main(String[] args) {
        Ethan_Lourens_BE_Coding_Test obj = new Ethan_Lourens_BE_Coding_Test();
        obj.run();
    }
}