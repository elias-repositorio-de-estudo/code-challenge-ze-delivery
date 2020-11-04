package br.com.xyz.zedelivery.pdv.dto.output;

import br.com.xyz.zedelivery.pdv.PDV;
import lombok.*;

@AllArgsConstructor
@Getter
public class OutputPDV {

    private final String tradingName;
    private final String ownerName;
    private final String document;
    private final OutputCoverageArea coverageArea;
    private final OutputPoint address;

    public OutputPDV(PDV pdv){
        this.tradingName = pdv.getTradingName();
        this.ownerName = pdv.getOwnerName();
        this.document = pdv.getDocument();
        this.coverageArea = new OutputCoverageArea(pdv);
        this.address = new OutputPoint(pdv);
    }
}
