package davidcui.com.FetchOprsReport;

import junit.framework.TestCase;

public class ReadConfigFileTest extends TestCase {

	public void testRead() {
		ReadConfigFile reader = new ReadConfigFile();
		WorkFlow wf = reader.read();
		assertTrue(wf != null);
	}

}
