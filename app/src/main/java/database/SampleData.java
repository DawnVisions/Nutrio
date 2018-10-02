package database;

import java.util.ArrayList;
import java.util.List;

import model.Feeding;
import model.Weight;

public class SampleData
{

    public static ArrayList<Weight> sampleWeights;
    public static ArrayList<Feeding> sampleFeedings;

    static
    {
        sampleWeights = new ArrayList<Weight>();
        sampleWeights.add(new Weight(2018, 9, 21, null, 7, 12));
        sampleWeights.add(new Weight(2018, 9, 22, null, 7, 10));
        sampleWeights.add(new Weight(2018, 9, 23, null, 7, 8));
        sampleWeights.add(new Weight(2018, 9, 24, null, 7, 9));
        sampleWeights.add(new Weight(2018, 9, 27, null, 7, 11));
        sampleWeights.add(new Weight(2018, 9, 28, null, 7, 15));
        sampleWeights.add(new Weight(2018, 9, 29, null, 8, 0));

        sampleFeedings = new ArrayList<Feeding>();
        sampleFeedings.add(new Feeding(2018, 9, 27, 1, 30, "am", "Breast", "24:00", "Left"));
        sampleFeedings.add(new Feeding(2018, 9, 27, 3, 45, "am", "Breast", "12:00", "Right"));
        sampleFeedings.add(new Feeding(2018, 9, 27, 6, 30, "am", "Breast", "45:00", "Left"));
        sampleFeedings.add(new Feeding(2018, 9, 27, 9, 10, "am", "Bottle", "18", ""));
        sampleFeedings.add(new Feeding(2018, 9, 27, 12, 50, "pm", "Breast", "15:00", "Left"));
        sampleFeedings.add(new Feeding(2018, 9, 27, 1, 30, "pm", "Breast", "10:00", "Right"));
        sampleFeedings.add(new Feeding(2018, 9, 27, 4, 50, "pm", "Bottle", "24", ""));
        sampleFeedings.add(new Feeding(2018, 9, 27, 7, 30, "pm", "Breast", "10:00", "Right"));
        sampleFeedings.add(new Feeding(2018, 9, 27, 10, 55, "pm", "Breast", "40:00", "Left"));
        sampleFeedings.add(new Feeding(2018, 9, 28, 3, 30, "am", "Bottle", "22", ""));
    }
}
