package IEC61850;

import IEC61850.objects.samples.SAV;
import lombok.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class ReadComtrade implements LN {

    private List<SAV> signals = new ArrayList<>();

    // TODO: Read CFG file
    private List<String> cfgFileLines = new ArrayList<>();

    // TODO: Read DAT file
    private List<String> datFileLines = new ArrayList<>();

    private List<Float> aBuffer = new ArrayList<>();
    private List<Float> bBuffer = new ArrayList<>();
    private List<String> listT = new ArrayList<>();

    private Iterator<String> iterator;
    private int signalNumber;
    public ReadComtrade() {
    }

    // TODO: Upload Comtrade file (.cfg)
    public void readComtrade(String path){
        cfgFileLines = readFile(path + ".cfg");
        datFileLines = readFile(path + ".dat");

        iterator = datFileLines.iterator();

        /* Extracting the number of signals */
        int analogNumber = Integer.parseInt(cfgFileLines.get(1).split(",")[1].replace("A", ""));
        int discreteNumber = Integer.parseInt(cfgFileLines.get(1).split(",")[2].replace("D", ""));
        signalNumber = analogNumber + discreteNumber;

        if(signals.size() < signalNumber) {
            for (int i = 0; i < 100; i++) {
                signals.add(new SAV());
            }
        }
        /* Extraction of scaling signals (coefficients of analog signals) */
        for (int i=2; i<(2+analogNumber); i++){
            String line = cfgFileLines.get(i);
            String[] lSplit = line.split(",");
            aBuffer.add(Float.parseFloat(lSplit[5]));
            bBuffer.add(Float.parseFloat(lSplit[6]));
        }

        System.out.printf("The waveform is loaded, the number of signals: %s, number of samples: %s %n%n", signalNumber, datFileLines.size());

    }

    @Override
    public void process() throws IOException {
        if(iterator.hasNext()){
            String[] split = iterator.next().split(",");
            listT.add(String.valueOf(Integer.parseInt(split[1])));
            for(int s = 0; s < signalNumber; s++){
                float value = Float.parseFloat(split[s+2]);
                if (s < aBuffer.size()) value = value * aBuffer.get(s) + bBuffer.get(s);
                SAV sav = signals.get(s);
                sav.getInstMag().getF().setValue(value * 1000);
            }

        }

    }

    // TODO: Upload file contents
    private static List<String> readFile(String path){
        List<String> fileEntry = new ArrayList<>();

        try {
            File file = new File(path);
            if(!file.exists()) System.err.println(path + " - The file was not found, the path was specified incorrectly");

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while(line!=null){
                fileEntry.add(line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error when entering/outputting data from a file!");
            e.printStackTrace();
        }

        return fileEntry;
    }


}
