package hello.itemservice.web.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import hello.itemservice.domain.item.Item;

public class ItemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Item.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Item item = (Item) target;
		
		//검증 로직
    	if(!StringUtils.hasText(item.getItemName())) {
    		errors.rejectValue("itemName", "required");
    	}
    	
    	if(item.getPrice() == null || item.getPrice()<1000|| item.getPrice()>1000000) {
    		errors.rejectValue("price", "range", new Object[] {1000, 1000000}, null);
    	}
    	
    	if(item.getQuantity() == null || item.getQuantity() > 10000) {
    		errors.rejectValue("quantity", "max", new Object[] {9999}, null);
    	}
    	
    	//특정 필드가 아닌 복합 룰 검증
    	if(item.getPrice() != null && item.getQuantity() != null ) {
    		int resultPrice = item.getPrice()*item.getQuantity();
    		if(resultPrice <10000) {
    			errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
    		}
    	}
	}

	
}