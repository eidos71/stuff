package test.tilu.stuff;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunTestBeats {
	 private final static Logger logger = LoggerFactory.getLogger(RunTestBeats.class);
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogger() {
		
		logger.warn("********");
		fail("Not yet implemented");
	}

}
