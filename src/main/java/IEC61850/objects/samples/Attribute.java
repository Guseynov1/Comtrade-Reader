package IEC61850.objects.samples;

import lombok.Data;

@Data
public class Attribute<T> {

    private T value;

    public Attribute(T value) {  this.value = value;  }

}
