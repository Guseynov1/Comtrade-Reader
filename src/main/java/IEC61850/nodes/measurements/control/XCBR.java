package IEC61850.nodes.measurements.control;

import IEC61850.LN;
import IEC61850.objects.statusControl.BCR;
import IEC61850.objects.statusControl.control.DPC;
import IEC61850.objects.statusControl.control.SPC;
import IEC61850.objects.statusControl.status.INS;
import IEC61850.objects.statusControl.status.SPS;
import lombok.Data;

@Data
public class XCBR implements LN {

    private DPC Pos = new DPC(); // положение переключателя

    @Override
    public void process() {

        // если значение положения выключателя OFF - выключаем
        if(Pos.getStVal().equals(DPC.State.OFF)) {
            Pos.getCtlVal().setValue(false);
        }

    }


}
