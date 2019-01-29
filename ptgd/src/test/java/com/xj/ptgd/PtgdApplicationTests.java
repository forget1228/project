package com.xj.ptgd;

import com.xj.ptgd.common.util.HttpClientUtil;
import com.xj.ptgd.dao.DealerDataDao;
import com.xj.ptgd.entity.body.Dealer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PtgdApplicationTests {
    @Test
    public void contextLoads() {
        System.out.println(1);
    }
}
