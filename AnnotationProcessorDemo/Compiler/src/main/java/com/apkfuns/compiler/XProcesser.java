package com.apkfuns.compiler;

import com.apkfuns.annotations.BindClass;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class XProcesser extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(BindClass.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println(" process ##############");
        try {
            helloWorldGenerator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 在桌面上生成 Java 类 (~/Hello/com/apkfuns/annotaion/HelloWorld.java)
     * 需要 rebuild
     * @throws IOException
     */
    private void helloWorldGenerator() throws IOException {
        MethodSpec main = MethodSpec.methodBuilder("main").addModifiers(Modifier.PUBLIC, Modifier.STATIC).returns(void.class).addParameter(String[].class, "args").addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!").build();
        MethodSpec testMethod = MethodSpec.methodBuilder("test")
                .addModifiers(Modifier.PRIVATE)
                .returns(String.class)
                .addJavadoc("java doc")
                .addParameter(int.class, "abc")
                .addComment("拉拉拉拉")
                .addException(IOException.class)
                .addAnnotation(Deprecated.class)
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .addStatement("return null")
                .build();
        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld").addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main).addMethod(testMethod)
                .addField(FieldSpec.builder(Object.class, "mLock", Modifier.PRIVATE).initializer("new Object()").build())
                .build();
        JavaFile javaFile = JavaFile.builder("com.apkfuns.annotation", helloWorld).build();
        javaFile.writeTo(new File(System.getProperty("user.home") + "/Desktop/Hello"));
    }
}
