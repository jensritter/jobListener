package org.jens.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@ContextConfiguration(locations= {"classpath*:org/jens/test/context.xml","classpath:/context-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringRunner {


    @Test
    public void testDI() throws SQLException {
        org.junit.Assume.assumeTrue(true);

    }

}

