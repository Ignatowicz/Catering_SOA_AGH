package pl.agh.kis.soa.catering.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Bill implements Serializable {

    Date generatedDate;
    Date orderDate;
    Float price;
    String additionalInformation;
    List<String> orderedPosition;

}
