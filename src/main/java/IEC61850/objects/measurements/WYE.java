package IEC61850.objects.measurements;

import lombok.Data;

@Data
public class WYE {

    private CMV phsA = new CMV();
    private CMV phsB = new CMV();
    private CMV phsC = new CMV();
    private CMV phsAB = new CMV();
    private CMV phsBC = new CMV();
    private CMV phsCA = new CMV();
    private CMV phsNeut = new CMV();

}
