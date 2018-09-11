package davidcui.com.FetchOprsReport;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ReadConfigFile {

	public WorkFlow read(){
		WorkFlow wf = null;
//		XMLDecoder xmlDecoder = null;
		try {
			String path = this.getClass().getResource("").getPath();
			File file = new File(path+"config.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(WorkFlow.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			wf = (WorkFlow)jaxbUnmarshaller.unmarshal(file);
			
			
//			FileInputStream fis = new FileInputStream(path+"config.xml");
//			BufferedInputStream bis = new BufferedInputStream(fis);
//			xmlDecoder = new XMLDecoder(bis);
//			wf = (WorkFlow) xmlDecoder.readObject();
			for(Item item : wf.getItems()){
				System.out.println(item.toString());
			}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			if(xmlDecoder != null){
//				xmlDecoder.close();
//			}
		}
		
		return wf;
		
	}
	
	public static void main(String[] args){
		ReadConfigFile reader = new ReadConfigFile();
//		System.out.println(reader.getClass().getResource("").getPath());
		WorkFlow wf = reader.read();
		System.out.println("Complete");
	}
}
