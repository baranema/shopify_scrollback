package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    protected Grid grid;
    protected Sequence[] sequences;
    protected Buffer buffer;

    public void loadLevel() throws FileNotFoundException {
        File file = new File("levels/01.txt");
        Scanner sc = new Scanner(file);
        String[][] initGrid = new String[5][5];
        ArrayList<String[]> initSequences = new ArrayList<String[]>();

        //Set buffer
        buffer = new Buffer(Integer.parseInt(sc.nextLine()));

        //skip an empty line
        sc.nextLine();

        //load the grid into a string[][]
        for (int i = 0; i<5; i++){
            initGrid[i]  = sc.nextLine().split(" ");
        }

        //skip an empty line
        sc.nextLine();


        //load sequences into an arraylist of sequences
        while (sc.hasNextLine()) {
            initSequences.add(sc.nextLine().split(" "));
        }

        //initiate array size of sequences
        sequences = new Sequence[initSequences.size()];

        //initiate array of sequences
        for(int i=0; i<sequences.length; i++){
            sequences[i] = new Sequence(initSequences.get(i));
        }

        //initiate grid
        grid = new Grid(initGrid);

    }
}
