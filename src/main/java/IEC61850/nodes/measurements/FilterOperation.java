package IEC61850.nodes.measurements;

import IEC61850.LN;
import IEC61850.ReadComtrade;
import IEC61850.nodes.measurements.filter.Filter;
import IEC61850.nodes.measurements.filter.Fourier;
import IEC61850.objects.measurements.ACD;
import IEC61850.objects.measurements.ACT;
import IEC61850.objects.measurements.WYE;
import IEC61850.objects.measurements.settings.ASG;
import IEC61850.objects.measurements.settings.ING;
import IEC61850.objects.samples.SAV;
import lombok.Data;
import java.io.IOException;

@Data
public class FilterOperation implements LN {

    ReadComtrade readComtrade = new ReadComtrade();

    private WYE A = new WYE();
    private WYE PhV = new WYE();
    private SAV instIa = new SAV(), instIb = new SAV(), instIc = new SAV();
    private SAV instUa = new SAV(), instUb = new SAV(), instUc = new SAV();
    private ASG StrVal = new ASG();
    private ING OpDlTmms = new ING();
    private double counter = 0;
    private ACT Op = new ACT();
    private ACD Str = new ACD();

    private Filter fIa = new Fourier(1), fIb = new Fourier(1), fIc = new Fourier(1);
    private Filter fUa = new Fourier(1), fUb = new Fourier(1), fUc = new Fourier(1);

    @Override
    public void process() throws IOException {

        fIa.process(instIa, A.getPhsA().getCVal());
        fIb.process(instIb, A.getPhsB().getCVal());
        fIc.process(instIc, A.getPhsC().getCVal());

        fUa.process(instUa, PhV.getPhsA().getCVal());
        fUb.process(instUb, PhV.getPhsB().getCVal());
        fUc.process(instUc, PhV.getPhsC().getCVal());

        boolean phsA = A.getPhsA().getCVal().getMag().getF().getValue() > StrVal.getSetVal().getF().getValue();
        boolean phsB = A.getPhsB().getCVal().getMag().getF().getValue() > StrVal.getSetVal().getF().getValue();
        boolean phsC = A.getPhsC().getCVal().getMag().getF().getValue() > StrVal.getSetVal().getF().getValue();
        boolean general = phsA || phsB || phsC;

        Str.getGeneral().setValue(general);
        Str.getPhsA().setValue(phsA);
        Str.getPhsB().setValue(phsB);
        Str.getPhsC().setValue(phsC);
        if (general) {
            counter += 0.25;
        }
        Op.getGeneral().setValue(counter > OpDlTmms.getSetVal());
        Op.getPhsA().setValue(counter > OpDlTmms.getSetVal());
        Op.getPhsB().setValue(counter > OpDlTmms.getSetVal());
        Op.getPhsC().setValue(counter > OpDlTmms.getSetVal());
    }

}