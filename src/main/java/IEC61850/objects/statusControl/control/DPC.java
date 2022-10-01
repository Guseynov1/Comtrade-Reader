package IEC61850.objects.statusControl.control;

import IEC61850.objects.samples.Attribute;
import lombok.Data;

@Data
public class DPC {
    private Enum<State> stVal = State.ON;
    private Attribute<Boolean> ctlVal = new Attribute<>(true);
    private Attribute<Boolean> stSeld = new Attribute<>(false);
    private Attribute<Boolean> subEna = new Attribute<>(false);

    public enum State{
        INTERMEDIATE, OFF, ON, BAD;
    }

}
