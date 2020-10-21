package br.com.xyz.zedelivery.model.dto.output;

import br.com.xyz.zedelivery.model.PDV;
import lombok.*;

@AllArgsConstructor
@Getter
public class PdvOutput {

    private final String tradingName;
    private final String ownerName;
    private final String document;
    private final CoverageAreaOutput coverageArea;
    private final PointOutput address;

    public PdvOutput(PDV pdv){
        this.tradingName = pdv.getTradingName();
        this.ownerName = pdv.getOwnerName();
        this.document = pdv.getDocument();
        this.coverageArea = new CoverageAreaOutput(pdv);
        this.address = new PointOutput(pdv);
    }
}
