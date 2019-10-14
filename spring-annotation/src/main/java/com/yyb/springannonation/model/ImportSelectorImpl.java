package com.yyb.springannonation.model;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ImportSelectorImpl implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.yyb.springannonation.model.Book","com.yyb.springannonation.model.Computer"};
    }
}
