package IEC61850.nodes.measurements.control;

import IEC61850.LN;
import IEC61850.objects.measurements.ACT;
import IEC61850.objects.statusControl.control.DPC;
import IEC61850.objects.statusControl.control.INC;
import lombok.Data;

@Data
public class CSWI implements LN {


    private DPC Pos = new DPC(); // control - общее состояние
    private ACT OpOpn = new ACT();
    private ACT OpOpn1 = new ACT(); // отключить выключатель
    private ACT OpOpn2 = new ACT();
    private ACT OpOpn3 = new ACT();
    private ACT OpOpn4 = new ACT();
    private ACT OpOpn5 = new ACT();

    @Override
    public void process() {

        OpOpn.getGeneral().setValue(OpOpn1.getGeneral().getValue() ||
                OpOpn2.getGeneral().getValue() ||
                OpOpn3.getGeneral().getValue() ||
                OpOpn4.getGeneral().getValue() ||
                OpOpn5.getGeneral().getValue());
        if(OpOpn.getGeneral().getValue() && Pos.getCtlVal().getValue()){
            Pos.setStVal(DPC.State.OFF); // если сработало и выключатель включен, то присваиваем выключить
        }

    }




}
