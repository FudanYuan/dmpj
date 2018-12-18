package com.dmpj;

import com.dmpj.utils.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DmpjApplicationTests {

    @Test
    public void contextLoads() {
        String timeSlice = TimeUtil.getTimeSlice(1509514111L, 1531411624L);
        System.out.println(timeSlice);
    }

}

