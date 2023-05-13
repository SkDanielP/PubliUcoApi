package co.edu.uco.publiuco.api.validator;

import java.util.List;

public interface Validation<T> {
	
	Result execute(T data);
	

}
