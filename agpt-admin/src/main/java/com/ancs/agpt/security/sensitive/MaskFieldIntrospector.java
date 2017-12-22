package com.ancs.agpt.security.sensitive;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class MaskFieldIntrospector extends JacksonAnnotationIntrospector {
	
	
	    
	/* @Override
	  public boolean isAnnotationBundle(Annotation ann) {
	    if (ann.annotationType().equals(MaskField.class)) {
	      return false;
	    } else {
	      return super.isAnnotationBundle(ann);
	    }
	  }*/
	 @Override
	    public Object findSerializer(Annotated annotated) {
	        //  经测试,只对方法有用
	        if(annotated instanceof AnnotatedMethod) {
	        	MaskField maskField = annotated.getAnnotated().getAnnotation(MaskField.class);
	            if(maskField != null) {
	                return new MaskFieldSerializer();
	            }
	        }
	        return super.findSerializer(annotated);
	    }

	 
}
