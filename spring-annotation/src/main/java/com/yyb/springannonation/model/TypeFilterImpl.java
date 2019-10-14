package com.yyb.springannonation.model;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class TypeFilterImpl implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // MetadataReader:读取到的当前类的信息
        // MetadataReaderFactory 可以获取到其他类的信息
        metadataReader.getAnnotationMetadata();//获取当前类注解信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();//获取当前正在扫描的类的信息
        metadataReader.getResource();//获取当前类的资源信息，比如类的路径

        String className = classMetadata.getClassName();
        //只要类名包含oo，就能加到IOC容器
        if(className.contains("oo")){
            return true;
        }
        return false;
    }
}
