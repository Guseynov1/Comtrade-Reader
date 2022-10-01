package IEC61850.objects.statusControl;

import IEC61850.objects.samples.Attribute;
import lombok.Data;

@Data
public class BCR { // считывание показаний двоичного счетчика

    private Attribute<Integer> actVal = new Attribute<>(0);
    private Attribute<Integer> frVal = new Attribute<>(0);
    private Attribute<Float> pulsQty = new Attribute<>((float) 0.);
    private Attribute<Boolean> frEna = new Attribute<>(false);
    private Attribute<Integer> frPd = new Attribute<>(0);
    private Attribute<Boolean> frRs = new Attribute<>(false);
    private String d;
    private String dU;
    private String cdcNs;
    private String cdcName;
    private String dataNs;


}
