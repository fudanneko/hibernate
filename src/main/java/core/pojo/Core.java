package core.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Core implements Serializable {
	private static final long serialVersionUID = 1457755989409740329L;
	private boolean successful;
	private String message;
	
	
	
	//以下可以用 @Data取代 Lombok實作
//	public Core() {
//	}
//
//	public Core(boolean successful, String message) {
//		this.successful = successful;
//		this.message = message;
//	}
//
//	public boolean isSuccessful() {
//		return successful;
//	}
//
//	public void setSuccessful(boolean successful) {
//		this.successful = successful;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
}
