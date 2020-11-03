package br.com.xyz.zedelivery.pdv;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("test")
class PDVRepositoryTest {

    @Autowired
    private PDVRepository pdvRepository;
    private PDV adegaAlemao;

    @Test
    public void should_return_two_pdv_when_we_have_points_equals(){
        Point point = createPoint("POINT (-43.297337 -23.013538)");

        MultiPolygon multipolygon = createMultipolygon("MULTIPOLYGON (((-43.36556 -22.99669, -43.36539 -23.01928, -43.26583 -23.01802, -43.25724 -23.00649, -43.23355 -23.00127, -43.2381 -22.99716, -43.23866 -22.99649, -43.24063 -22.99756, -43.24634 -22.99736, -43.24677 -22.99606, -43.24067 -22.99381, -43.24886 -22.99121, -43.25617 -22.99456, -43.25625 -22.99203, -43.25346 -22.99065, -43.29599 -22.98283, -43.3262 -22.96481, -43.33427 -22.96402, -43.33616 -22.96829, -43.342 -22.98157, -43.34817 -22.97967, -43.35142 -22.98062, -43.3573 -22.98084, -43.36522 -22.98032, -43.36696 -22.98422, -43.36717 -22.98855, -43.36636 -22.99351, -43.36556 -22.99669)))");

        adegaAlemao = pdvRepository.save(new PDVBuilder().withId()
                .withTradingName("adega do alemao")
                .withOwnerName("heitor")
                .withDocument("79.074.184/0001-94")
                .withCoverageArea(multipolygon)
                .withPoint(point).create());

        List<PDV> byAddress = pdvRepository.findByAddress(point);

         assertThat(byAddress.size(),is(1));
    }


    private Point createPoint(String point){
        try {
            return (Point) new WKTReader().read(point);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private MultiPolygon createMultipolygon(String multipolygon){
        try {
            return (MultiPolygon) new WKTReader().read(multipolygon);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
