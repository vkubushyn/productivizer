package com.vk.productivizer;

import java.util.Date;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class ProductivityCalculatorValidator extends AbstractValidator {

	public void validate(ValidationContext ctx) {
        //make sure dates are correct
        Date toDate = (Date) ctx.getProperty().getValue();
        Date fromDate = (Date) ctx.getProperties("from")[0].getValue();
        
        if(fromDate.after(toDate)) {
        	this.addInvalidMessage(ctx, "to", "The \"to\" date must be a later date than the \"from\" date!");
        }
	}
	
}