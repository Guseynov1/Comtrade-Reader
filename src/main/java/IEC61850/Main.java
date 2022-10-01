package IEC61850;

import IEC61850.nodes.gui.NHMI;
import IEC61850.nodes.gui.other.NHMISignal;
import IEC61850.nodes.measurements.FilterOperation;
import IEC61850.nodes.measurements.control.CSWI;
import IEC61850.nodes.measurements.control.XCBR;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Data
public class Main {

    private static String SQL = "INSERT INTO emergency (times, Ia, Ib, Ic, fIa, fIb, fIc, Ua, Ub, Uc) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/comtrade";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final List<String> listQa = new ArrayList<>();
    private static final List<String> listQb = new ArrayList<>();
    private static final List<String> listQc = new ArrayList<>();
    private static final List<String> listIa = new ArrayList<>();
    private static final List<String> listIb = new ArrayList<>();
    private static final List<String> listIc = new ArrayList<>();
    private static final List<String> listUa = new ArrayList<>();
    private static final List<String> listUb = new ArrayList<>();
    private static final List<String> listUc = new ArrayList<>();


    private static Iterator<String> listTiterator;
    private static Iterator<String> listQiteratorA;
    private static Iterator<String> listQiteratorB;
    private static Iterator<String> listQiteratorC;
    private static Iterator<String> listIiteratorA;
    private static Iterator<String> listIiteratorB;
    private static Iterator<String> listIiteratorC;
    private static Iterator<String> listUiteratorA;
    private static Iterator<String> listUiteratorB;
    private static Iterator<String> listUiteratorC;



    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // TODO: Search for the working time of the program

        long programStartTime = System.nanoTime();

        // TODO: Failure Time Search Variables

        double faultStartTime = 0, faultEndTime = 0, faultTime;

        ReadComtrade readComtrade = new ReadComtrade();
        CSWI cswi = new CSWI();
        FilterOperation filterOperation = new FilterOperation();
        NHMI nhmi = new NHMI();
        NHMI nhmi0 = new NHMI();
        XCBR xcbr = new XCBR();


        readComtrade.readComtrade("C:\\Users\\Хамзат\\IdeaProjects\\WCR\\src\\main\\resources\\comtrade\\22\\cfg");

        filterOperation.setInstIa(readComtrade.getSignals().get(0));
        filterOperation.setInstIb(readComtrade.getSignals().get(1));
        filterOperation.setInstIc(readComtrade.getSignals().get(2));

        filterOperation.setInstUa(readComtrade.getSignals().get(12));
        filterOperation.setInstUb(readComtrade.getSignals().get(13));
        filterOperation.setInstUc(readComtrade.getSignals().get(14));

        filterOperation.getStrVal().getSetVal().getF().setValue(100f);
        filterOperation.getOpDlTmms().setSetVal(50);
        filterOperation.setA(filterOperation.getA());

        cswi.setOpOpn1(filterOperation.getOp());

        xcbr.setPos(cswi.getPos());

        nhmi.addSignals(new NHMISignal("IaF", filterOperation.getA().getPhsA().getCVal().getMag().getF()));
        nhmi.addSignals(new NHMISignal("IbF", filterOperation.getA().getPhsB().getCVal().getMag().getF()));
        nhmi.addSignals(new NHMISignal("IcF", filterOperation.getA().getPhsC().getCVal().getMag().getF()));
        nhmi.addSignals(new NHMISignal("Ia", filterOperation.getInstIa().getInstMag().getF()));
        nhmi.addSignals(new NHMISignal("Ib", filterOperation.getInstIb().getInstMag().getF()));
        nhmi.addSignals(new NHMISignal("Ic", filterOperation.getInstIc().getInstMag().getF()));
        nhmi.addSignals(new NHMISignal("Пуск", filterOperation.getStr().getGeneral()));
        nhmi.addSignals(new NHMISignal("Срабатывание", filterOperation.getOp().getGeneral()));
        //                new NHMISignal("StrVal", ptoc.getStrVal().getSetMag().getF()));

        nhmi0.addSignals(new NHMISignal("Ua", readComtrade.getSignals().get(12).getInstMag().getF()));
        nhmi0.addSignals(new NHMISignal("Ub", readComtrade.getSignals().get(13).getInstMag().getF()));
        nhmi0.addSignals(new NHMISignal("Uc", readComtrade.getSignals().get(14).getInstMag().getF()));

        while (readComtrade.getIterator().hasNext()) {
            readComtrade.process();
            filterOperation.process();
            nhmi.process();
            nhmi0.process();
            cswi.process();
            xcbr.process();

            listQa.add(String.valueOf(filterOperation.getA().getPhsA().getCVal().getMag().getF().getValue()));
            listQb.add(String.valueOf(filterOperation.getA().getPhsB().getCVal().getMag().getF().getValue()));
            listQc.add(String.valueOf(filterOperation.getA().getPhsC().getCVal().getMag().getF().getValue()));
            listIa.add(String.valueOf(filterOperation.getInstIa().getInstMag().getF().getValue()));
            listIb.add(String.valueOf(filterOperation.getInstIb().getInstMag().getF().getValue()));
            listIc.add(String.valueOf(filterOperation.getInstIc().getInstMag().getF().getValue()));
            listUa.add(String.valueOf(filterOperation.getInstUa().getInstMag().getF().getValue()));
            listUb.add(String.valueOf(filterOperation.getInstUb().getInstMag().getF().getValue()));
            listUc.add(String.valueOf(filterOperation.getInstUc().getInstMag().getF().getValue()));

        }

        Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            listTiterator = readComtrade.getListT().iterator();
            listQiteratorA = listQa.iterator();
            listQiteratorB = listQb.iterator();
            listQiteratorC = listQc.iterator();
            listIiteratorA = listIa.iterator();
            listIiteratorB = listIb.iterator();
            listIiteratorC = listIc.iterator();
            listUiteratorA = listUa.iterator();
            listUiteratorB = listUb.iterator();
            listUiteratorC = listUc.iterator();

            while (listQiteratorA.hasNext() && listTiterator.hasNext() && listQiteratorB.hasNext() && listQiteratorC.hasNext()
                    && listIiteratorA.hasNext() && listIiteratorB.hasNext() && listIiteratorC.hasNext() && listUiteratorA.hasNext()
                    && listUiteratorB.hasNext() && listUiteratorC.hasNext()) {
                String valuesT = listTiterator.next();
                String valuesQa = listQiteratorA.next();
                String valuesQb = listQiteratorB.next();
                String valuesQc = listQiteratorC.next();
                String valueIa = listIiteratorA.next();
                String valueIb = listIiteratorB.next();
                String valueIc = listIiteratorC.next();
                String valueUa = listUiteratorA.next();
                String valueUb = listUiteratorB.next();
                String valueUc = listUiteratorC.next();

                int times = Integer.parseInt(valuesT);
                double fIa = Double.parseDouble(valuesQa);
                double fIb = Double.parseDouble(valuesQb);
                double fIc = Double.parseDouble(valuesQc);
                double Ia = Double.parseDouble(valueIa);
                double Ib = Double.parseDouble(valueIb);
                double Ic = Double.parseDouble(valueIc);
                double Ua = Double.parseDouble(valueUa);
                double Ub = Double.parseDouble(valueUb);
                double Uc = Double.parseDouble(valueUc);

                preparedStatement.setInt(1, times);
                preparedStatement.setDouble(2, fIa);
                preparedStatement.setDouble(3, fIb);
                preparedStatement.setDouble(4, fIc);
                preparedStatement.setDouble(5, Ia);
                preparedStatement.setDouble(6, Ib);
                preparedStatement.setDouble(7, Ic);
                preparedStatement.setDouble(8, Ua);
                preparedStatement.setDouble(9, Ub);
                preparedStatement.setDouble(10, Uc);


                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();



        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }


        // TODO: Failure time when receiving and sending it to MySQL

        faultTime = (faultEndTime - faultStartTime);
        System.out.printf("Fault time is %.1f milliseconds", ((faultTime) / 1000));
        System.out.println();
//        statement.execute(String.format("insert into valid (faultTime) values(%s);", faultTime));
//        statement.close();

        // TODO: Continuation of the search for the working time of the program

        long endTime = System.nanoTime();
        System.out.printf("Program work time is %f seconds", (double) (endTime - programStartTime) / 1000000000);

    }
//
        public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("C:\\Users\\Хамзат\\IdeaProjects\\WCR\\src\\main\\resources\\database.properties"))){
            properties.load(in);
        }
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, user, password);

    }


//    public void should() throws SQLException {
//        try (Connection connection = getConnection()){
//            assertTrue(connection.isValid(1));
//            assertFalse(connection.isClosed());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public static double getIteratorSize(Iterator iterator) {
//        AtomicInteger count = new AtomicInteger(0);
//        iterator.forEachRemaining(element -> {
//            count.incrementAndGet();
//        });
//        return count.get();
//    }


}

