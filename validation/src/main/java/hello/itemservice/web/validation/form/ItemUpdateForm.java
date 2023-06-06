package hello.itemservice.web.validation.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class ItemUpdateForm {
	
	@NotNull// 수정 요구사항 추가
    private Long id;
	
	@NotNull
	private String itemName;
	
	@NotNull
	@Range(min=1000, max=1000000)
	private Integer price;
	
	private Integer quantity;
}
