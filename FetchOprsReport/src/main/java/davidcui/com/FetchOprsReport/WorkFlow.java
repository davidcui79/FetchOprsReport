package davidcui.com.FetchOprsReport;

import java.io.Serializable;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  

@XmlRootElement (name="workflow")
public class WorkFlow {

	private String name;
	private Vector<Item> items;
	
	
	public String getName() {
		return name;
	}
	
	@XmlAttribute(name="name")
	public void setName(String name){
		this.name = name;
	}
	

	public Vector<Item> getItems() {
		return items;
	}
	
	@XmlElement(name = "item")
	public void setItems(Vector<Item> items) {
		this.items = items;
	}	
}

class Item{
	private String name;
	private String url;
	private Vector<Status> status;
	

	public String getName() {
		return name;
	}
	
	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}
	

	public String getUrl() {
		return url;
	}
	
	@XmlAttribute(name = "url")
	public void setUrl(String url) {
		this.url = url;
	}
	

	public Vector<Status> getStatus() {
		return status;
	}
	
	@XmlElement(name = "status")
	public void setStatus(Vector<Status> status) {
		this.status = status;
	}
	
	public boolean validate(){
		if(getName()==null) return false;
		if(getUrl()==null) return false;
		
		//must have at least one status
		if(getStatus()==null) return false;
		if(getStatus().size()==0) return false;
		
		return true;
	}
	
	@Override
	public String toString(){
		String rc = "item ";
		rc += "name=" + getName() + ", ";
		rc += "url=" + getUrl()	+ "\n";
		for(Status status : getStatus()){
			rc+= status.toString();
		}
		
		return rc;
	}
}

enum StatusType{
	VALUE("value"),
	RANGE("range"),
	DEFAULT("default");
	
	private String type;
	StatusType(String type){
		this.type = type;
	}
	
	@Override
	public String toString(){
		return type;
	}
}

class Status{
	private String type;
	private String value;
	private int min;
	private int max;
	private Action action;

	public String getType() {
		return type;
	}
	
	@XmlAttribute(name="type")
	public void setType(String type) {
		this.type = type;
	}
	

	public String getValue() {
		return value;
	}
	
	@XmlAttribute(name="value")
	public void setValue(String value) {
		this.value = value;
	}
	

	public int getMin() {
		return min;
	}
	
	@XmlAttribute(name="min")
	public void setMin(int min) {
		this.min = min;
	}
	

	public int getMax() {
		return max;
	}
	
	@XmlAttribute(name="max")
	public void setMax(int max) {
		this.max = max;
	}
	

	public Action getAction() {
		return action;
	}
	
	@XmlElement(name="action")
	public void setAction(Action action) {
		this.action = action;
	}
	
	public boolean validate(){
		if(getType() == null) return false;
		
		if(getType().equals(StatusType.VALUE.toString())){
			if(getValue()==null) return false;
		}
		
		if(getType().equals(StatusType.RANGE.toString())){
			if(getMin()==0 || getMax()==0) return false;
		}
		
		//must have an action
		if(getAction()==null) return false;
		
		return true;
	}
	
	@Override
	public String toString(){
		String rc = "status ";
		//Attributes
		rc+= "type=" + getType() +", ";
		rc+= "value=" + getValue() +", ";
		rc+= "min=" + getMin() + ", ";
		rc+= "max=" + getMax() + "\n";
		
		//Element, only action here
		Action action = getAction();
		if(action != null) {
		    rc+= getAction().toString() + "\n";
		} else {
			rc+= "action=null\n";
		}
		
		return rc;
	}

}


class Action {
	private String message;
	private String exec;

	public String getMessage() {
		return message;
	}
	
	@XmlAttribute(name="message")
	public void setMessage(String message) {
		this.message = message;
	}
	

	public String getExec() {
		return exec;
	}
	
	@XmlAttribute(name="exec")
	public void setExec(String exec) {
		this.exec = exec;
	}
	
	public boolean validate(){
		if(getMessage() == null) return false;
		
		return true;
	}
	
	@Override
	public String toString(){
		String rc = "action ";
		
		rc+= "message=" + getMessage() + ", ";
		rc+= "exec=" + getExec() + "\n";
		
		return rc;
	}

}