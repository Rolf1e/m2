package con.rolfie.dealabs.controller;

import con.rolfie.dealabs.config.DealConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {DealConfig.class, TestConfig.class, DealController.class})
public class DealControllerTest {

    @Test
    public void should_fetch_all_ordered() throws Exception {
        final String expected = "{" +
                "\"title\": \"Mémoire RAM G.Skill Trident Z Neo (F4-4000C18D-32GTZN) - 32 Go (2x16 Go), DDR4, 4000 MHz\"," +
                "\"temperature\": 68.0," +
                "\"creator\": \"EssaiDeal\"," +
                "\"date\": \"2021-10-12\"," +
                "\"description\": \"G.Skill Trident Z Neo F4-4000C18D-32GTZN Module de mémoire 32 Go 2 x 16 Go DDR4 4000 MHzTypeDDR4 SDRAMCouleurNoir/Argent/RGBEAN4713294224156Référence du fabrica\"," +
                "\"shopName\": \"Amazon\"," +
                "\"shopLink\": \"https://www.dealabs.com/visit/thread/2220841?API=true&user_id={{user_id}}&device_id=7377839\"," +
                "\"imgUrl\": \"https://static-pepper.dealabs.com/threads/raw/default/2220841_1/re/234x330/qt/60/2220841_1.jpg\"" +
                "}," +
                "{" +
                "\"title\": \"Fallout 4 sur PC (Dématérialisé - Steam)\"," +
                "\"temperature\": 174.0," +
                "\"creator\": \"J.Oldman\"," +
                "\"date\": \"2021-10-12\"," +
                "\"description\": \"Excellent prix pour ce 4ème opus de Fallout qui atteint son meilleur prix jamais vu  :3 &#x2192; Version GOTY à 7.99€ https://www.gamebillet.com/fallout-4-game-\"," +
                "\"shopName\": \"Gamebillet\"," +
                "\"shopLink\": \"https://www.dealabs.com/visit/thread/2220705?API=true&user_id={{user_id}}&device_id=7377839\"," +
                "\"imgUrl\": \"https://static-pepper.dealabs.com/threads/raw/default/2220705_1/re/234x330/qt/60/2220705_1.jpg\"" +
                "}";


        given()

        mvc.perform(MockMvcRequestBuilders.get("/deals/all"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }

}